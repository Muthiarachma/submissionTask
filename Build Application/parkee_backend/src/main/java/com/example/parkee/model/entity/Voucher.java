package com.example.parkee.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voucher")
@Data
public class Voucher extends BaseEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @Column(name = "discount")
  private double discount;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;
}
