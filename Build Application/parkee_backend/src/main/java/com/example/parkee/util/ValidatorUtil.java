package com.example.parkee.util;

import com.example.parkee.model.constant.ErrorMessage;
import com.example.parkee.model.entity.*;
import com.example.parkee.model.response.exception.BadRequestException;
import com.example.parkee.model.response.exception.NotFoundException;

import java.util.Objects;

public class ValidatorUtil {

  public static void validatePageableRequest(int pageNumber, int pageSize) {

    if (pageNumber < 0) {
      throw new BadRequestException(ErrorMessage.PAGE_NUMBER_MUST_BE_AT_LEAST_0);
    } else if (pageSize < 1 || pageSize > 100) {
      throw new BadRequestException(ErrorMessage.PAGE_SIZE_MUST_BE_BETWEEN_1_AND_100);
    }
  }

  public static void validateRequest(boolean expression, String errorMessage) {

    if (!expression) {
      throw new BadRequestException(errorMessage);
    }
  }

  public static void validateParkingSpot(ParkingSpot spot) {

    if (Objects.isNull(spot)) {
      throw new NotFoundException(ErrorMessage.PARKING_SPOT_NOT_FOUND);
    }
  }

  public static void validatePaymentMethod(PaymentMethod paymentMethod) {

    if (Objects.isNull(paymentMethod)) {
      throw new NotFoundException(ErrorMessage.PAYMENT_METHOD_NOT_REGISTERED);
    }
  }

  public static CheckInTicket validateCheckInTicket(CheckInTicket ticket) {

    if (Objects.isNull(ticket)) {
      throw new NotFoundException(ErrorMessage.CHECK_IN_TICKET_NOT_FOUND);
    }
    return ticket;
  }

  public static ParkingLot validateParkingLot(ParkingLot parkingLot) {

    if (Objects.isNull(parkingLot)) {
      throw new NotFoundException(ErrorMessage.PARKING_LOT_NOT_FOUND);
    }
    return parkingLot;
  }
}
