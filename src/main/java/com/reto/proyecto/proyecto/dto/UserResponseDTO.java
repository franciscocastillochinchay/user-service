package com.reto.proyecto.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
  private Long id;
  private Date created;
  private Date modified;
  private Date lastLogin;
  private String token;
  private Boolean isActive;
  private String name;
  private String email;
  private List<PhoneDTO> phones;

}
