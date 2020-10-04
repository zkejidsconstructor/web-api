package com.zkejid.constructor.webstaticresources.api.v1;

public class ResourceNotAvailableException extends RuntimeException {

  public ResourceNotAvailableException() {
  }

  public ResourceNotAvailableException(String message) {
    super(message);
  }

  public ResourceNotAvailableException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotAvailableException(Throwable cause) {
    super(cause);
  }

  public ResourceNotAvailableException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
