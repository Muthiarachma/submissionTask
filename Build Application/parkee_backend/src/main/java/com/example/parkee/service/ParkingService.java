package com.example.parkee.service;

import com.example.parkee.model.request.GetParkingSpotsRequest;
import org.springframework.data.domain.Page;
import com.example.parkee.model.entity.ParkingLot;
import com.example.parkee.model.entity.ParkingSpot;

public interface ParkingService {

  ParkingLot getParkingLotDetail(String id);

  int countOccupiedSpots(String id);

  Page<ParkingSpot> getParkingSpots(int pageNumber, int pageSize, GetParkingSpotsRequest request);
}
