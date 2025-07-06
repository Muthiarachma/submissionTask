package com.example.parkee.model.request;

import lombok.Data;

@Data
public class CreateCheckInTicketRequest {

  private String plateNumber;

  private String vehicleTypeId;

  private String parkingLotId;
}
