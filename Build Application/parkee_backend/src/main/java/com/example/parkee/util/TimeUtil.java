package com.example.parkee.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUtil {

  public static long getParkingDurationInSeconds(LocalDateTime checkInDate, LocalDateTime checkOutDate) {

    return Duration.between(checkInDate, checkOutDate).getSeconds();
  }
}
