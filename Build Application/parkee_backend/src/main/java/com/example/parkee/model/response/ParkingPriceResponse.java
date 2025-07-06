package com.example.parkee.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingPriceResponse {

  private long durationInSeconds;

  private double price;

  private double discount;

  private double finalPrice;
}
