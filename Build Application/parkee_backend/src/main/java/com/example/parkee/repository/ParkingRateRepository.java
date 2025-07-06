package com.example.parkee.repository;

import com.example.parkee.model.entity.ParkingRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRateRepository extends JpaRepository<ParkingRate, String> {

    Optional<ParkingRate> findById(String id);

}
