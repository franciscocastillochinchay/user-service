package com.reto.proyecto.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedResponseDTO {
  private Long id;
  private Date created;
  private Date modified;
  private Date lastLogin;
  private String token;
  private Boolean isActive;

}
