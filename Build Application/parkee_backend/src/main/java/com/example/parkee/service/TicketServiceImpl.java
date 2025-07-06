package com.example.parkee.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parkee.model.constant.ErrorMessage;
import com.example.parkee.model.entity.*;
import com.example.parkee.model.request.CreateCheckInTicketRequest;
import com.example.parkee.model.request.CreateCheckOutTicketRequest;
import com.example.parkee.model.request.GetCheckoutTicketDetailRequest;
import com.example.parkee.model.response.exception.BadRequestException;
import com.example.parkee.model.response.exception.ConflictException;
import com.example.parkee.repository.*;
import com.example.parkee.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

import static com.example.parkee.util.ValidatorUtil.*;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private CheckInTicketRepository checkInTicketRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private CheckOutTicketRepository checkOutTicketRepository;

    @Autowired
    private VoucherRepository voucherRepository;


    @Override
    public CheckInTicket createCheckInTicket(CreateCheckInTicketRequest request) {

        validateRequest(Objects.nonNull(request), ErrorMessage.REQUEST_MUST_NOT_BE_NULL);

        StringUtil.trimStrings(request);

        validateRequest(StringUtils.isNotBlank(request.getPlateNumber()), ErrorMessage.PLATE_NUMBER_MUST_NOT_BE_BLANK);
        validateRequest(StringUtils.isNotBlank(request.getVehicleTypeId()), ErrorMessage.VEHICLE_TYPE_ID_MUST_NOT_BE_BLANK);
        validateRequest(StringUtils.isNotBlank(request.getParkingLotId()), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);

        boolean isOccupiedInParkingLot =
                checkInTicketRepository.existsByPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(
                        request.getPlateNumber(), request.getParkingLotId());
        if (isOccupiedInParkingLot) {
            throw new ConflictException(ErrorMessage.PLATE_NUMBER_IS_OCCUPIED_IN_PARKING_LOT);
        }

        LocalDateTime now = LocalDateTime.now();
        ParkingLot parkingLot = parkingLotRepository.findByIdAndMarkForDeleteFalse(request.getParkingLotId());
        VehicleType vehicleType = vehicleTypeRepository.getReferenceById(request.getVehicleTypeId());

        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setVehicleType(vehicleType);
        parkingSpot.setParkingLot(parkingLot);
        parkingSpot.setCreatedDate(now);
        parkingSpot.setCreatedBy("ADMIN");
        parkingSpot.setUpdatedDate(now);
        parkingSpot.setUpdatedBy("ADMIN");
        parkingSpot.setParking(true);
        parkingSpotRepository.save(parkingSpot);

        CheckInTicket ticket = new CheckInTicket();
        ticket.setPlateNumber(request.getPlateNumber());
        ticket.setParkingSpot(parkingSpot);
        ticket.setActive(true);
        ticket.setCreatedDate(now);
        ticket.setCreatedBy("ADMIN");
        ticket.setUpdatedDate(now);
        ticket.setUpdatedBy("ADMIN");

        return checkInTicketRepository.save(ticket);
    }

    @Override
    public CheckInTicket getCheckInTicketDetail(GetCheckoutTicketDetailRequest request) {

        CheckInTicket checkInTicket =
                checkInTicketRepository.findByIdAndPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(
                        request.getParkingSlipId(), request.getPlateNumber(), request.getParkingLotId());
        return validateCheckInTicket(checkInTicket);
    }

    @Override
    public CheckOutTicket createCheckOutTicket(CreateCheckOutTicketRequest request) {

        validateRequest(Objects.nonNull(request), ErrorMessage.REQUEST_MUST_NOT_BE_NULL);

        StringUtil.trimStrings(request);

        validateRequest(StringUtils.isNotBlank(request.getParkingSlipId()), ErrorMessage.PARKING_SLIP_ID_MUST_BE_NOT_BLANK);
        validateRequest(StringUtils.isNotBlank(request.getParkingLotId()), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);
        validateRequest(StringUtils.isNotBlank(request.getPlateNumber()), ErrorMessage.PLATE_NUMBER_MUST_NOT_BE_BLANK);
        validateRequest(StringUtils.isNotBlank(request.getPaymentMethodId()),
                ErrorMessage.PAYMENT_METHOD_ID_MUST_NOT_BE_BLANK);
        validateRequest(Objects.nonNull(request.getCheckOutDate()), ErrorMessage.CHECK_OUT_DATE_MUST_NOT_BE_BLANK);

        LocalDateTime checkOutDate;
        try {
            checkOutDate = LocalDateTime.parse(request.getCheckOutDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new BadRequestException(ErrorMessage.CHECK_OUT_TICKET_DATE_FORMAT_FALSE);
        }

        CheckInTicket checkInTicket =
                checkInTicketRepository.findByIdAndPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(
                        request.getParkingSlipId(), request.getPlateNumber(), request.getParkingLotId());
        validateCheckInTicket(checkInTicket);

        PaymentMethod paymentMethod = paymentMethodRepository.findByIdAndMarkForDeleteFalse(request.getPaymentMethodId());
        validatePaymentMethod(paymentMethod);

        CheckOutTicket ticket = new CheckOutTicket();
        ticket.setCheckInTicket(checkInTicket);
        ticket.setPaymentMethod(paymentMethod);

        Voucher voucher = voucherRepository.findValidVoucherByIdAndMarkForDeleteFalse(request.getVoucherCode());
        if (Objects.nonNull(voucher)) {
            voucher.setQuantity(voucher.getQuantity() - 1);
            voucherRepository.save(voucher);
            ticket.setVoucher(voucher);
        }

        ticket.setCreatedDate(checkOutDate);
        ticket.setCreatedBy("ADMIN");
        ticket.setUpdatedDate(checkOutDate);
        ticket.setUpdatedBy("ADMIN");

        ticket.setDiscount(request.getDiscount());
        ticket.setPrice(request.getPrice());
        ticket.setFinalPrice(request.getFinalPrice());

        checkInTicket.setActive(false);
        checkInTicketRepository.save(checkInTicket);

        ParkingSpot parkingSpot = parkingSpotRepository.getReferenceById(checkInTicket.getParkingSpot().getId());
        parkingSpot.setParking(false);
        parkingSpotRepository.save(parkingSpot);

        return checkOutTicketRepository.save(ticket);
    }
}
