package com.grupo2.orientanet.dto;


import com.grupo2.orientanet.model.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    private Long id;

    @NotBlank(message = "El nombre debe ser obligatorio")
    @Size(max = 50,message = "El nombre debe tener 50 caracteres o menos")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]{2,}$", message = "El nombre debe contener al menos 2 caracteres alfabéticos y puede incluir acentos o ñ.")
    private String nombre;

    @NotBlank(message = "El apellido debe ser obligatorio")
    @Size(max = 100,message = "El apellido debe tener 100 caracteres o menos")
    private String apellido;


    @NotBlank(message = "La contrasena es obligatoria")
    @Size(min = 8,message = "la contrasena debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una minúscula y un número.")
    private String contrasena;

    @Email
    @NotBlank(message = "El email debe ser obligatorio")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

}
