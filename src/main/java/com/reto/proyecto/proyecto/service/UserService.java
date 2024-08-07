package com.reto.proyecto.proyecto.service;

import com.reto.proyecto.proyecto.dto.UserRequestDTO;
import com.reto.proyecto.proyecto.dto.UserCreatedResponseDTO;
import com.reto.proyecto.proyecto.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
  public UserCreatedResponseDTO registerUser(UserRequestDTO userRequestDTO);

  public List<UserResponseDTO> getAllUsers();
}
