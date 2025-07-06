package com.example.parkee.service;

import com.example.parkee.model.entity.ParkingSpot;
import com.example.parkee.model.request.GetParkingSpotsRequest;
import com.example.parkee.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.parkee.model.constant.ErrorMessage;
import com.example.parkee.model.entity.ParkingLot;
import com.example.parkee.repository.ParkingLotRepository;
import com.example.parkee.repository.ParkingSpotRepository;

import java.util.Objects;

import static com.example.parkee.util.ValidatorUtil.validateParkingLot;
import static com.example.parkee.util.ValidatorUtil.validateRequest;

@Service
public class ParkingServiceImpl implements ParkingService {

  @Autowired
  private ParkingLotRepository parkingLotRepository;

  @Autowired
  private ParkingSpotRepository parkingSpotRepository;

  @Override
  public ParkingLot getParkingLotDetail(String id) {

    validateRequest(StringUtils.isNotBlank(id), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);
    return validateParkingLot(parkingLotRepository.findByIdAndMarkForDeleteFalse(id));
  }

  @Override
  public int countOccupiedSpots(String id) {

    validateRequest(StringUtils.isNotBlank(id), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);
    return parkingSpotRepository.countByParkingLotId(id);
  }

  @Override
  public Page<ParkingSpot> getParkingSpots(int pageNumber, int pageSize, GetParkingSpotsRequest request) {

    validateRequest(Objects.nonNull(request), ErrorMessage.REQUEST_MUST_NOT_BE_NULL);
    StringUtil.trimStrings(request);
    validateRequest(StringUtils.isNotBlank(request.getParkingLotId()), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);

    validateParkingLot(parkingLotRepository.findByIdAndMarkForDeleteFalse(request.getParkingLotId()));

    return parkingSpotRepository.findAllByParkingLotIdAndMarkForDeleteFalse(request.getParkingLotId(), PageRequest.of(pageNumber, pageSize));
  }
}
