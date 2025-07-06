package com.example.parkee.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckoutTicketDetailRequest {

  private String parkingLotId;

  private String parkingSlipId;

  private String plateNumber;

  private String voucherCode;
}
