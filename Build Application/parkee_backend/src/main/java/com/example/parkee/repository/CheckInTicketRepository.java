package com.example.parkee.repository;

import com.example.parkee.model.entity.CheckInTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInTicketRepository extends JpaRepository<CheckInTicket, String> {

    boolean existsByPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(String plateNumber, String parkingLotId);

    CheckInTicket findByIdAndPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(String id, String plateNumber, String parkingLotId);

    CheckInTicket findByPlateNumberAndIsActiveTrue (String plateNumber);
}
