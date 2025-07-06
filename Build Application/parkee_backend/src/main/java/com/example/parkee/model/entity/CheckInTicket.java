package com.example.parkee.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "check_in_ticket")
@Data
public class CheckInTicket extends BaseEntity {

  @Column(name = "plate_number")
  private String plateNumber;

  @ManyToOne
  @JoinColumn(name = "parking_spot_id", referencedColumnName = "id")
  private ParkingSpot parkingSpot;


  @Column(name = "is_active")
  private boolean isActive;
}
