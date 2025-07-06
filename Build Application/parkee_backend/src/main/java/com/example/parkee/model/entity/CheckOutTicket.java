package com.example.parkee.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "check_out_ticket")
@Data
public class CheckOutTicket extends BaseEntity {

  @Column(name = "price")
  private double price;

  @Column(name = "discount")
  private double discount;

  @Column(name = "final_price")
  private double finalPrice;

  @OneToOne
  @JoinColumn(name = "check_in_ticket_id", referencedColumnName = "id")
  private CheckInTicket checkInTicket;

  @ManyToOne
  @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
  private PaymentMethod paymentMethod;

  @ManyToOne
  @JoinColumn(name = "voucher_id", referencedColumnName = "id")
  private Voucher voucher;
}
