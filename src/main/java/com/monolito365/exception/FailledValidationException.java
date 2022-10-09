package com.monolito365.exception;

public class FailledValidationException extends Exception {
  private static final long serialVersionUID = -4003221714287222703L;

  public FailledValidationException() {
    super();
  }

  public FailledValidationException(String message) {
    super(message);
  }

  public FailledValidationException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
