package com.example.parkee.controller;

import com.example.parkee.model.constant.ApiPath;
import com.example.parkee.model.entity.VehicleType;
import com.example.parkee.model.response.VehicleTypeResponse;
import com.example.parkee.model.response.api.ApiSingleResponse;
import com.example.parkee.repository.VehicleTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.VEHICLE, produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleController extends BaseController {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    @GetMapping("/list")
    public ResponseEntity<ApiSingleResponse<VehicleTypeResponse>> getCheckOutTicketDetail() {

        try {
            List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
            return toSuccessResponseEntity(toApiSingleResponse(vehicleTypes));
        } catch (RuntimeException e) {
            log.error("#PaymentController#getList ERROR! error: {}", e.getMessage(),
                    e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
