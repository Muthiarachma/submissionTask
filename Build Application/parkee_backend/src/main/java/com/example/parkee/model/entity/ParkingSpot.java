package com.example.parkee.model.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "parking_spot")
@Data
public class ParkingSpot extends BaseEntity {

  @Column(name = "parking")
  private boolean parking;

  @ManyToOne
  @JoinColumn(name = "vehicle_type_id", referencedColumnName = "id")
  private VehicleType vehicleType;

  @ManyToOne
  @JoinColumn(name = "parking_lot_id", referencedColumnName = "id")
  private ParkingLot parkingLot;
}
