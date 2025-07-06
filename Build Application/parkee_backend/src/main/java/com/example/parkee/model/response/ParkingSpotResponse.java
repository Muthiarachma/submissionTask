package com.example.parkee.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingSpotResponse {

  private String id;

  private String spot;

  private boolean isOccupied;
}
