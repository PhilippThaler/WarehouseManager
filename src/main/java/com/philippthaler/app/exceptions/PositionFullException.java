package com.philippthaler.app.exceptions;

/**
 * Exception class that gets thrown when a position is full.
 */
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
