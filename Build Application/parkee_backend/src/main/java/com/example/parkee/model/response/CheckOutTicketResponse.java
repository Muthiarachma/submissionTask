package com.example.parkee.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutTicketResponse extends BaseResponse {

  private String checkInTicketId;

  private String plateNumber;

  private String parkingType;

  private String parkingSpot;

  private String parkingLotAddress;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime checkInDate;

  private long duration;

  private String paymentMethodName;

  private double price;

  private double discount;

  private double finalPrice;
}
