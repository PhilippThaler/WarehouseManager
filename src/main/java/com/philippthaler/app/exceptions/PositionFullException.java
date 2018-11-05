package com.philippthaler.app.exceptions;

public class PositionFullException extends RuntimeException {

  public PositionFullException() {
    super();
  }

  public PositionFullException(String message) {
    super(message);
  }

  public PositionFullException(String message, Throwable cause) {
    super(message, cause);
  }

  public PositionFullException(Throwable cause) {
    super(cause);
  }
}
