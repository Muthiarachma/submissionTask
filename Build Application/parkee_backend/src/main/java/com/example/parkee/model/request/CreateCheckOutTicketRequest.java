package com.example.parkee.model.request;

import lombok.Data;

@Data
public class CreateCheckOutTicketRequest {

  private String parkingSlipId;

  private String parkingLotId;

  private String plateNumber;

  private String paymentMethodId;

  private String voucherCode;

  private long durationInSeconds;

  private double price;

  private double discount;

  private double finalPrice;

  private String checkOutDate;
}
