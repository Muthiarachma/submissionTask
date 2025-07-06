package com.example.parkee.model.response.exception;

public class ServiceUnavailableException extends RuntimeException {

  public ServiceUnavailableException(String message) {

    super(message);
  }
}
