package com.example.parkee.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.parkee.model.response.api.ApiListResponse;
import com.example.parkee.model.response.api.ApiResponse;
import com.example.parkee.model.response.api.ApiSingleResponse;
import com.example.parkee.model.response.wrapper.PageMetaData;

import java.util.List;

public class BaseController {

  protected <T> ApiListResponse toApiListResponse(List<T> content, PageMetaData pageMetaData) {

    return new ApiListResponse(content, pageMetaData);
  }

  protected <T> ApiSingleResponse toApiSingleResponse(T data) {

    return new ApiSingleResponse(data);
  }

  protected ApiResponse toErrorApiResponse(String error) {

    return new ApiResponse(error);
  }

  protected <T> ResponseEntity toSuccessResponseEntity(ApiListResponse listResponse) {

    return new ResponseEntity(listResponse, HttpStatus.OK);
  }

  protected <T> ResponseEntity toSuccessResponseEntity(ApiSingleResponse<T> singleResponse) {

    return new ResponseEntity(singleResponse, HttpStatus.OK);
  }

  protected <T> ResponseEntity toErrorResponseEntity(String error, HttpStatus httpStatus) {

    return new ResponseEntity(new ApiResponse(error), httpStatus);
  }

  protected PageMetaData getPageMetaData(Page page, int pageNumber, int pageSize) {

    return PageMetaData.builder()
        .pageNumber(pageNumber)
        .pageSize(pageSize)
        .totalRecords(page.getTotalElements())
        .build();
  }
}
