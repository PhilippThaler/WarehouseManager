package com.philippthaler.app.exceptions;

/**
 * Exception class that gets thrown when a position is already taken
 */
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
