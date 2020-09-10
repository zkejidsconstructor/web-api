package com.zkejid.constructor.webstaticresources.api.v1;

public class UrlPathAlreadyUsedException extends RuntimeException {

  public UrlPathAlreadyUsedException() {
  }

  public UrlPathAlreadyUsedException(String message) {
    super(message);
  }

  public UrlPathAlreadyUsedException(String message, Throwable cause) {
    super(message, cause);
  }

  public UrlPathAlreadyUsedException(Throwable cause) {
    super(cause);
  }

  public UrlPathAlreadyUsedException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
