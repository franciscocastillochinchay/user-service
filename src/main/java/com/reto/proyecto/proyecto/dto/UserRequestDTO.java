package com.reto.proyecto.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
  @NotBlank(message = "name es obligatorio")
  private String name;
  @NotBlank(message = "email es obligatorio")
  @Email
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email no es válido")
  private String email;
  @NotBlank
  private String password;
  private List<PhoneDTO> phones;
}
