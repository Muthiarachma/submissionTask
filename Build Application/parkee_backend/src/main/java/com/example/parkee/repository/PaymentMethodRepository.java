package com.example.parkee.repository;

import com.example.parkee.model.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {

  PaymentMethod findByIdAndMarkForDeleteFalse(String id);
}
