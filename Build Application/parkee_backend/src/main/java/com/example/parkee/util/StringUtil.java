package com.example.parkee.util;

import com.example.parkee.model.constant.ErrorMessage;
import com.example.parkee.model.response.exception.BadRequestException;
import com.example.parkee.model.response.exception.ServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class StringUtil {

  public static void trimStrings(Object object) {

    Class<?> clazz = object.getClass();
    Field[] fields = clazz.getDeclaredFields();

    Arrays.stream(fields)
        .filter(field -> field.getType() == String.class)
        .forEach(field -> {
          try {
            field.setAccessible(true);
            String fieldValue = (String) field.get(object);

            if (Objects.nonNull(fieldValue)) {
              String trimmedFieldValue = fieldValue.trim();
              field.set(object, trimmedFieldValue);
            }
          } catch (IllegalAccessException e) {
            log.error("#trimStrings ERROR! with object: {}, and error: {}", object, e.getMessage(), e);
            throw new ServiceUnavailableException(ErrorMessage.SERVICE_TEMPORARILY_UNAVAILABLE);
          }
        });
  }

  public static void overMaxLength(String str, int max, String errorMessage) {

    if (str.length() > max) {
      throw new BadRequestException(errorMessage);
    }
  }
}
