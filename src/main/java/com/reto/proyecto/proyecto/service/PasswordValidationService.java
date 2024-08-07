package com.reto.proyecto.proyecto.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidationService {
  @Value("${password.regex}")
  private  String PASSWORD_REGEX;

  public boolean validatePassword(String password) {
    return password != null && password.matches(PASSWORD_REGEX);
  }
}