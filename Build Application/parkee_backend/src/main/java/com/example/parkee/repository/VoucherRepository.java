package com.example.parkee.repository;

import com.example.parkee.model.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {

  @Query("SELECT v FROM Voucher v WHERE v.id = :id AND v.quantity > 0 AND v.expirationDate > CURRENT_TIMESTAMP AND v.markForDelete = false")
  Voucher findValidVoucherByIdAndMarkForDeleteFalse(String id);
}
