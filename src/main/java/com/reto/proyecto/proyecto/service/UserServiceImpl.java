package com.reto.proyecto.proyecto.service;

import com.reto.proyecto.proyecto.config.JwtService;
import com.reto.proyecto.proyecto.config.JwtUtil;
import com.reto.proyecto.proyecto.dto.PhoneDTO;
import com.reto.proyecto.proyecto.dto.UserRequestDTO;
import com.reto.proyecto.proyecto.dto.UserCreatedResponseDTO;
import com.reto.proyecto.proyecto.dto.UserResponseDTO;
import com.reto.proyecto.proyecto.entity.Phone;
import com.reto.proyecto.proyecto.entity.Role;
import com.reto.proyecto.proyecto.entity.User;
import com.reto.proyecto.proyecto.exception.EmailAlreadyExistsException;
import com.reto.proyecto.proyecto.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;
  @Autowired
  private PasswordValidationService passwordValidationService;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private JwtUtil jwtUtil;

  private final JwtService jwtService;

  @Override
  public UserCreatedResponseDTO registerUser(UserRequestDTO userRequestDTO) {
    if (!passwordValidationService.validatePassword(userRequestDTO.getPassword())) {
      throw new IllegalArgumentException("password con formato incorrecto: ");
    }
    if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
      throw new EmailAlreadyExistsException("El correo ya registrado");
    }

    User user = new User();
    user.setName(userRequestDTO.getName());
    user.setEmail(userRequestDTO.getEmail());
    user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
    user.setCreated(new Date());
    user.setModified(new Date());
    user.setLastLogin(new Date());
    user.setRole(Role.USER);
    user.setActive(true);
    user.setToken(jwtUtil.generateToken(user.getEmail(), user.isActive()));
    user.setPhones(userRequestDTO.getPhones().stream().map(phoneDTO -> {
      Phone phone = new Phone();
      phone.setNumber(phoneDTO.getNumber());
      phone.setCitycode(phoneDTO.getCitycode());
      phone.setContrycode(phoneDTO.getContrycode());
      return phone;
    }).collect(Collectors.toList()));

    userRepository.save(user);

    return convertToResponseDTO(user);


  }

  @Override
  public List<UserResponseDTO> getAllUsers() {
    return userRepository.findAll().stream().map(this::convertToResponseDTO1).collect(Collectors.toList());
  }


  private UserCreatedResponseDTO convertToResponseDTO(User user) {
    UserCreatedResponseDTO responseDTO = new UserCreatedResponseDTO();
    responseDTO.setId(user.getId());
    responseDTO.setCreated(user.getCreated());
    responseDTO.setModified(user.getModified());
    responseDTO.setLastLogin(user.getLastLogin());
    responseDTO.setToken(user.getToken());
    responseDTO.setIsActive(user.isActive());
    return responseDTO;
  }

  private UserResponseDTO convertToResponseDTO1(User user) {
    UserResponseDTO responseDTO = new UserResponseDTO();
    responseDTO.setId(user.getId());
    responseDTO.setCreated(user.getCreated());
    responseDTO.setModified(user.getModified());
    responseDTO.setLastLogin(user.getLastLogin());
    responseDTO.setToken(user.getToken());
    responseDTO.setIsActive(user.isActive());
    responseDTO.setName(user.getName());
    responseDTO.setEmail(user.getEmail());
    responseDTO.setPhones(user.getPhones().stream().map(phone -> {
      PhoneDTO phoneDTO = new PhoneDTO();
      phoneDTO.setNumber(phone.getNumber());
      phoneDTO.setCitycode(phone.getCitycode());
      phoneDTO.setContrycode(phone.getContrycode());
      return phoneDTO;
    }).collect(Collectors.toList()));
    return responseDTO;
  }
}
