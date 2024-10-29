package com.grupo2.orientanet.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String nombre;
    private String apellido;
    private String role;
}
