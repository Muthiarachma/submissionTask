package com.example.parkee.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "parking_lot")
@Data
public class ParkingLot extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "vehicle_capacity")
  private int vehicleCapacity;

  @ManyToMany
  @JoinTable(
      name = "parking_lot_vehicle_type",
      joinColumns = @JoinColumn(name = "parking_lot_id"),
      inverseJoinColumns = @JoinColumn(name = "vehicle_type_id"))
  private Set<VehicleType> vehicleTypes;

  @ManyToMany
  @JoinTable(
      name = "parking_lot_payment_method",
      joinColumns = @JoinColumn(name = "parking_lot_id"),
      inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
  private Set<PaymentMethod> paymentMethods;
}
