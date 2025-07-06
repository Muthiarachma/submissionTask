package com.example.parkee.repository;

import com.example.parkee.model.entity.ParkingLot;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {

  @EntityGraph(attributePaths = "vehicleTypes")
  ParkingLot findByIdAndMarkForDeleteFalse(String id);
}
