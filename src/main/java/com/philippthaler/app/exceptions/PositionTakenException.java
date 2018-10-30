package com.philippthaler.app.exceptions;

public class PositionTakenException extends RuntimeException {

  public PositionTakenException() {
    super();
  }

  public PositionTakenException(String message) {
    super(message);
  }

  public PositionTakenException(String message, Throwable cause) {
    super(message, cause);
  }

  public PositionTakenException(Throwable cause) {
    super(cause);
  }
}
