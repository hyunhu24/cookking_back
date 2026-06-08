package com.springboot.exception;

public class EmailVerificationException extends BusinessLogicException {
  public EmailVerificationException() {
    super(ExceptionCode.EMAIL_CODE_MISMATCH);
  }
}