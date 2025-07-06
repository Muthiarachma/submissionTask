package com.example.parkee.util;

import org.springframework.beans.BeanUtils;
import com.example.parkee.model.entity.*;
import com.example.parkee.model.response.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil {

    public static ParkingLotResponse toParkingLotResponse(ParkingLot parkingLot, int occupiedSpots) {

        List<VehicleTypeResponse> vehicleTypes = parkingLot.getVehicleTypes()
                .stream()
                .map(ResponseUtil::toVehicleTypeResponse)
                .collect(Collectors.toList());
        List<PaymentMethodResponse> paymentMethods = parkingLot.getPaymentMethods()
                .stream()
                .map(ResponseUtil::toPaymentMethodResponse)
                .collect(Collectors.toList());

        ParkingLotResponse response = ParkingLotResponse.builder()
                .vehicleCapacity(parkingLot.getVehicleCapacity())
                .occupiedSpots(occupiedSpots)
                .vehicleTypes(vehicleTypes)
                .paymentMethods(paymentMethods)
                .build();
        BeanUtils.copyProperties(parkingLot, response);

        return response;
    }

    public static VehicleTypeResponse toVehicleTypeResponse(VehicleType vehicleType) {

        VehicleTypeResponse response = new VehicleTypeResponse();
        BeanUtils.copyProperties(vehicleType, response);

        return response;
    }

    public static PaymentMethodResponse toPaymentMethodResponse(PaymentMethod paymentMethod) {

        PaymentMethodResponse response = new PaymentMethodResponse();
        BeanUtils.copyProperties(paymentMethod, response);

        return response;
    }

    public static CheckInTicketResponse toCheckInTicketResponse(CheckInTicket checkInTicket) {

        CheckInTicketResponse response = CheckInTicketResponse.builder()
                .plateNumber(checkInTicket.getPlateNumber())
                .vehicleType(checkInTicket.getParkingSpot().getVehicleType().getName())
                .id(checkInTicket.getId())
                .build();
        BeanUtils.copyProperties(checkInTicket, response);

        return response;
    }

    public static GetCheckOutTicketDetailResponse toGetCheckOutTicketDetailResponse(CheckInTicket checkInTicket,
                                                                                    ParkingPriceResponse priceResponse, LocalDateTime checkOutDate) {

        return GetCheckOutTicketDetailResponse.builder()
                .checkInDate(checkInTicket.getCreatedDate())
                .durationInSeconds(priceResponse.getDurationInSeconds())
                .price(priceResponse.getPrice())
                .discount(priceResponse.getDiscount())
                .finalPrice(priceResponse.getFinalPrice())
                .createdDate(checkOutDate)
                .build();
    }

    public static CheckOutTicketResponse toCheckOutTicketResponse(CheckOutTicket checkOutTicket) {

        CheckInTicket checkInTicket = checkOutTicket.getCheckInTicket();
        ParkingSpot parkingSpot = checkInTicket.getParkingSpot();
        long duration =
                TimeUtil.getParkingDurationInSeconds(checkInTicket.getCreatedDate(), checkOutTicket.getCreatedDate());

        CheckOutTicketResponse response = CheckOutTicketResponse.builder()
                .checkInTicketId(checkInTicket.getId())
                .plateNumber(checkInTicket.getPlateNumber())
                .parkingType(parkingSpot.getVehicleType().getName())
                .checkInDate(checkInTicket.getCreatedDate())
                .duration(duration)
                .paymentMethodName(checkOutTicket.getPaymentMethod()
                        .getName())
                .build();
        BeanUtils.copyProperties(checkOutTicket, response);

        return response;
    }


}
