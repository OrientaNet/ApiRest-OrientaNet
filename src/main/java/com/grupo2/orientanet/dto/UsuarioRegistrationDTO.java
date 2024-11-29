package com.grupo2.orientanet.dto;


import com.grupo2.orientanet.model.entity.Carrera;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistrationDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String Apellido;

    @Email(message = "El correo electronico no es valido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe ser de al menos 8")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una minúscula y un número.")
    private String contrasena;

    private String informacionPersonal;
    //estudiante
    private String nivelAcademico;
    private String institucion;
    private String intereses;
    private Carrera carreraInteres;

    //experto
    private String experiencia;
    private String certificaciones;
    private Carrera carrera;

}
