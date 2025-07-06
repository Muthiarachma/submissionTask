package com.example.parkee.model.response.exception;

public class BadRequestException extends RuntimeException {

  public BadRequestException(String message) {

    super(message);
  }
}
