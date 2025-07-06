package com.example.parkee.service;

import com.example.parkee.model.entity.Voucher;
import com.example.parkee.model.response.ParkingPriceResponse;

import java.time.LocalDateTime;

public interface ParkingRateService {

    ParkingPriceResponse calculateParkingPrice(LocalDateTime checkInDate, LocalDateTime checkOutDate, Voucher voucher);
}
