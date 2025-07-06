package com.example.parkee.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParkingLotResponse extends BaseResponse {

  private String companyName;

  private String address;

  private int vehicleCapacity;

  private int occupiedSpots;

  private List<VehicleTypeResponse> vehicleTypes;

  private List<PaymentMethodResponse> paymentMethods;
}
