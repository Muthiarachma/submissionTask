package com.example.parkee.controller;

import com.example.parkee.model.entity.CheckInTicket;
import com.example.parkee.model.response.CheckInTicketResponse;
import com.example.parkee.repository.CheckInTicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.parkee.model.constant.ApiPath;
import com.example.parkee.model.entity.ParkingLot;
import com.example.parkee.model.response.ParkingLotResponse;
import com.example.parkee.model.response.api.ApiSingleResponse;
import com.example.parkee.service.ParkingService;
import static com.example.parkee.util.ResponseUtil.toParkingLotResponse;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.PARKING, produces = MediaType.APPLICATION_JSON_VALUE)
public class ParkingController extends BaseController {

  @Autowired
  private ParkingService parkingService;

  @Autowired
  private CheckInTicketRepository checkInTicketRepository;

  @GetMapping
  public ResponseEntity<ApiSingleResponse<ParkingLotResponse>> getParkingLotDetail(@RequestParam String id) {

    try {
      ParkingLot parkingLot = parkingService.getParkingLotDetail(id);
      int occupiedSpots = parkingService.countOccupiedSpots(id);
      ParkingLotResponse response = toParkingLotResponse(parkingLot, occupiedSpots);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#ParkingController#getParkingLotDetail ERROR! with id: {} and error: {}", id, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("/plate-number")
  public ResponseEntity<ApiSingleResponse<CheckInTicketResponse>> getPlateNumber(@RequestParam String plateNumber) {

    try {
      CheckInTicket checkInTicket = checkInTicketRepository.findByPlateNumberAndIsActiveTrue(plateNumber);
      return toSuccessResponseEntity(toApiSingleResponse(checkInTicket));
    } catch (RuntimeException e) {
      log.error("#ParkingController#getParkingLotDetail ERROR! with plateNumber: {} and error: {}", plateNumber, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
