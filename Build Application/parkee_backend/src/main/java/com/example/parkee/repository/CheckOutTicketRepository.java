package com.example.parkee.repository;

import com.example.parkee.model.entity.CheckOutTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutTicketRepository extends JpaRepository<CheckOutTicket, String> {
}
