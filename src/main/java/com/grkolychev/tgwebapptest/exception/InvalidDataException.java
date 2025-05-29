package com.grkolychev.tgwebapptest.exception;

public class InvalidDataException extends RuntimeException {

  public InvalidDataException(String msg) {
    super(msg);
  }

  public InvalidDataException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
