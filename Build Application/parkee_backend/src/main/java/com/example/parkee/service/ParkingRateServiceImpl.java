package com.example.parkee.service;

import com.example.parkee.model.entity.ParkingRate;
import com.example.parkee.model.entity.Voucher;
import com.example.parkee.model.response.ParkingPriceResponse;
import com.example.parkee.repository.ParkingRateRepository;
import com.example.parkee.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParkingRateServiceImpl implements ParkingRateService {

  private final ParkingRateRepository parkingRateRepository;

  @Autowired
  public ParkingRateServiceImpl(ParkingRateRepository parkingRateRepository) {
    this.parkingRateRepository = parkingRateRepository;
  }

  @Override
  public ParkingPriceResponse calculateParkingPrice(LocalDateTime checkInDate, LocalDateTime checkOutDate, Voucher voucher) {
    validateCheckInOutDate(checkInDate, checkOutDate);

    long durationInSeconds = TimeUtil.getParkingDurationInSeconds(checkInDate, checkOutDate);
    int durationInHours = (int) Math.ceil(durationInSeconds / 3600.0);

    ParkingRate parkingRate = parkingRateRepository.findById("BASIC")
            .orElseThrow(() -> new IllegalStateException("Parking rate 'BASIC' is not available."));

    double ratePerHour = parkingRate.getRatePerHour();
    double price = durationInHours * ratePerHour;
    double discount = getVoucherDiscount(voucher);
    double finalPrice = calculateFinalPrice(price, discount);

    return ParkingPriceResponse.builder()
            .durationInSeconds(durationInSeconds)
            .price(price)
            .discount(discount)
            .finalPrice(finalPrice)
            .build();
  }

  private void validateCheckInOutDate(LocalDateTime checkIn, LocalDateTime checkOut) {
    if (checkOut.isBefore(checkIn)) {
      throw new IllegalArgumentException("Check-out date cannot be before check-in date.");
    }
  }

  private double getVoucherDiscount(Voucher voucher) {
    return Optional.ofNullable(voucher)
            .map(Voucher::getDiscount)
            .orElse(0.0);
  }

  private double calculateFinalPrice(double price, double discount) {
    return Math.max(price - discount, 0.0);
  }
}

