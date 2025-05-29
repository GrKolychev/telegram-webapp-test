package com.grkolychev.tgwebapptest.exception.handler;

import com.grkolychev.tgwebapptest.exception.AuthException;
import com.grkolychev.tgwebapptest.exception.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private static final String GENERIC_ERROR_MESSAGE = "Something went wrong. Please contact support team.";
  private static final String AUTH_FAILED_ERROR_MESSAGE = "Authorization failed";
  private static final String BAD_REQUEST_ERROR_MESSAGE = "Bad request";

  @ExceptionHandler(AuthException.class)
  protected ResponseEntity<String> handleAuthException(AuthException ex) {
    log.warn("Authorization failed: {}", ex.getMessage());

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AUTH_FAILED_ERROR_MESSAGE);
  }

  @ExceptionHandler(InvalidDataException.class)
  protected ResponseEntity<String> handleInvalidDataException(InvalidDataException ex) {
    log.warn("Authorization failed: {}", ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST_ERROR_MESSAGE);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<String> handleGeneralException(Exception ex) {
    log.error("Unexpected error: {}, message: {}, cause: {}", ex.getClass().getName(), ex.getMessage(), ex.getCause());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GENERIC_ERROR_MESSAGE);
  }

}
