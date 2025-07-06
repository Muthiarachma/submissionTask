package com.example.parkee.model.constant;

public interface ErrorMessage {

  String PAGE_NUMBER_MUST_BE_AT_LEAST_0 = "page number must be at least 0";

  String PAGE_SIZE_MUST_BE_BETWEEN_1_AND_100 = "page size must be between 1 and 100";

  String SERVICE_TEMPORARILY_UNAVAILABLE = "service temporarily unavailable";

  String REQUEST_MUST_NOT_BE_NULL = "request must not be null";

  // MEMBER
  String PLATE_NUMBER_MUST_NOT_BE_BLANK = "plate number must not be blank";

  String MEMBER_NOT_FOUND = "member not found";

  // OFFICER
  String OFFICER_NOT_FOUND = "officer not found";

  // PARKING LOT
  String PARKING_LOT_ID_MUST_BE_NOT_BLANK = "parking lot id must not be blank";

  String PARKING_LOT_NOT_FOUND = "parking lot not found";

  // CHECK IN TICKET

  String OFFICER_ID_MUST_NOT_BE_BLANK = "officer id must not be blank";

  String VEHICLE_TYPE_ID_MUST_NOT_BE_BLANK = "vehicle type id must not be blank";

  String VEHICLE_TYPE_NOT_REGISTERED = "vehicle type not registered";

  String PARKING_SPOT_NOT_FOUND = "parking spot not found";

  String PLATE_NUMBER_IS_OCCUPIED_IN_PARKING_LOT = "plate number is occupied in parking lot";

  // CHECK OUT TICKET
  String CHECK_IN_TICKET_NOT_FOUND = "check in ticket not found";

  String PAYMENT_METHOD_ID_MUST_NOT_BE_BLANK = "payment method id must not be blank";

  String PAYMENT_METHOD_NOT_REGISTERED = "payment method not registered";

  String CHECK_OUT_TICKET_DATE_FORMAT_FALSE = "check out ticket date format false";

  String PARKING_SLIP_ID_MUST_BE_NOT_BLANK = "parking slip id must not be blank";

  String CHECK_OUT_DATE_MUST_NOT_BE_BLANK = "check out date must not be blank";

  // VOUCHER
  String VOUCHER_ID_MUST_NOT_BE_BLANK = "voucher id must not be blank";
}
