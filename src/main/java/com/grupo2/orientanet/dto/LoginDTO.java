package com.grupo2.orientanet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @Email(message = "El correo electronico no es valido")
    @NotBlank(message = "El correo electronicones obligatorio")
    private String email;

    @NotBlank(message = "El password es obligatorio")
    private String contrasena;
}
