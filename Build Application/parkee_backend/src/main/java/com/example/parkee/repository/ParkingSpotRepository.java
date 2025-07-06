package com.example.parkee.repository;

import com.example.parkee.model.entity.ParkingSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {

  Page<ParkingSpot> findAllByParkingLotIdAndMarkForDeleteFalse(
      String parkingLotId, Pageable pageable);

  int countByParkingLotId(String parkingLotId);
}
