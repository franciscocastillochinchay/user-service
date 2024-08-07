package com.reto.proyecto.proyecto.controller;

import com.reto.proyecto.proyecto.dto.UserRequestDTO;
import com.reto.proyecto.proyecto.dto.UserCreatedResponseDTO;
import com.reto.proyecto.proyecto.exception.EmailAlreadyExistsException;
import com.reto.proyecto.proyecto.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
      UserCreatedResponseDTO userCreatedResponseDTO = userService.registerUser(userRequestDTO);
      return new ResponseEntity<>(userCreatedResponseDTO, HttpStatus.CREATED);


  }

  @GetMapping(value = "/getAllUsers", produces = "application/json")
  public ResponseEntity<?> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }






}
