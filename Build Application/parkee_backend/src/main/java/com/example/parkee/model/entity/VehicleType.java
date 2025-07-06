package com.example.parkee.model.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_type")
@Data
public class VehicleType extends BaseEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @Column(name = "name")
  private String name;
}
