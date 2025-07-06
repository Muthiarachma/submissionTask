package com.example.parkee.model.response.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiSingleResponse<T> extends ApiResponse {

  private T data;
}
