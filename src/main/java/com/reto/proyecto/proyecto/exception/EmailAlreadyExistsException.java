package com.reto.proyecto.proyecto.exception;

public class EmailAlreadyExistsException extends  RuntimeException {
  public EmailAlreadyExistsException(String message) {
    super(message);
  }

}
