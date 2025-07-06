package com.example.parkee.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "parking_rate")
@Data
public class ParkingRate extends BaseEntity {

  @Column(name = "rate_per_hour")
  private double ratePerHour;
}
