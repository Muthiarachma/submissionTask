package com.example.parkee.controller;


import com.example.parkee.model.constant.ApiPath;
import com.example.parkee.model.entity.PaymentMethod;
import com.example.parkee.model.response.PaymentMethodResponse;
import com.example.parkee.model.response.api.ApiSingleResponse;
import com.example.parkee.repository.PaymentMethodRepository;
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
@RequestMapping(value = ApiPath.PAYMENT, produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController extends BaseController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    @GetMapping("/list")
    public ResponseEntity<ApiSingleResponse<PaymentMethodResponse>> getCheckOutTicketDetail() {

        try {
            List<PaymentMethod> paymentMethod = paymentMethodRepository.findAll();
            return toSuccessResponseEntity(toApiSingleResponse(paymentMethod));
        } catch (RuntimeException e) {
            log.error("#PaymentController#getList ERROR! error: {}", e.getMessage(),
                    e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
