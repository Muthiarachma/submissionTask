package com.example.parkee.controller;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.parkee.model.constant.ApiPath;
import com.example.parkee.model.entity.CheckInTicket;
import com.example.parkee.model.entity.CheckOutTicket;
import com.example.parkee.model.entity.Voucher;
import com.example.parkee.model.request.CreateCheckInTicketRequest;
import com.example.parkee.model.request.CreateCheckOutTicketRequest;
import com.example.parkee.model.request.GetCheckoutTicketDetailRequest;
import com.example.parkee.model.response.CheckInTicketResponse;
import com.example.parkee.model.response.CheckOutTicketResponse;
import com.example.parkee.model.response.GetCheckOutTicketDetailResponse;
import com.example.parkee.model.response.ParkingPriceResponse;
import com.example.parkee.model.response.api.ApiSingleResponse;
import com.example.parkee.service.ParkingRateService;
import com.example.parkee.service.TicketService;
import com.example.parkee.service.VoucherService;
import com.example.parkee.util.ResponseUtil;

import java.time.LocalDateTime;

import static com.example.parkee.util.ResponseUtil.toCheckInTicketResponse;
import static com.example.parkee.util.ResponseUtil.toCheckOutTicketResponse;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.TICKET, produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController extends BaseController {

  @Autowired
  private TicketService ticketService;

  @Autowired
  private ParkingRateService parkingRateService;

  @Autowired
  private VoucherService voucherService;

  @PostMapping("/check-in")
  public ResponseEntity<ApiSingleResponse<CheckInTicketResponse>> createCheckInTicket(
      @RequestBody CreateCheckInTicketRequest request) {

    try {
      CheckInTicket ticket = ticketService.createCheckInTicket(request);
      CheckInTicketResponse response = toCheckInTicketResponse(ticket);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#TicketController#createCheckInTicket ERROR! with request: {} and error: {}", request, e.getMessage(),
          e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("/check-out")
  public ResponseEntity<ApiSingleResponse<GetCheckOutTicketDetailResponse>> getCheckOutTicketDetail(
      @RequestParam String parkingLotId,
      @RequestParam String parkingSlipId,
      @RequestParam String plateNumber,
      @RequestParam String voucherCode) {

    try {
      LocalDateTime checkOutDate = LocalDateTime.now();
      GetCheckoutTicketDetailRequest request = GetCheckoutTicketDetailRequest.builder()
          .parkingLotId(parkingLotId)
          .parkingSlipId(parkingSlipId)
          .plateNumber(plateNumber)
          .voucherCode(voucherCode)
          .build();

      CheckInTicket checkInTicket = ticketService.getCheckInTicketDetail(request);
      Voucher voucher = null;
      if (StringUtils.isNotBlank(voucherCode)) {
        voucher = voucherService.findValidVoucherById(voucherCode);
      }

      ParkingPriceResponse priceResponse =
          parkingRateService.calculateParkingPrice(checkInTicket.getCreatedDate(), checkOutDate, voucher);
      System.out.println("=========================================");
      System.out.println(priceResponse.toString());
      GetCheckOutTicketDetailResponse response =
          ResponseUtil.toGetCheckOutTicketDetailResponse(checkInTicket, priceResponse, checkOutDate);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error(
          "#TicketController#getCheckOutTicketDetail ERROR! with parkingLotId: {}, parkingSlipId: {}, plateNumber: {}, voucherCode: {} and error: {}",
          parkingLotId, parkingSlipId, plateNumber, voucherCode, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PostMapping("/check-out")
  public ResponseEntity<ApiSingleResponse<CheckOutTicketResponse>> createCheckOutTicket(
      @RequestBody CreateCheckOutTicketRequest request) {

    try {
      CheckOutTicket ticket = ticketService.createCheckOutTicket(request);
      CheckOutTicketResponse response = toCheckOutTicketResponse(ticket);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#TicketController#createCheckOutTicket ERROR! with request: {} and error: {}", request, e.getMessage(),
          e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
