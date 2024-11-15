package com.grupo2.orientanet.mapper;

import com.grupo2.orientanet.dto.AuthResponseDTO;
import com.grupo2.orientanet.dto.LoginDTO;
import com.grupo2.orientanet.dto.UsuarioRegistrationDTO;
import com.grupo2.orientanet.dto.UsuarioProfileDTO;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private  ModelMapper modelMapper;

    public Usuario toUserEntity (UsuarioRegistrationDTO registrationDTO){
        return modelMapper.map(registrationDTO, Usuario.class);
    }

    public UsuarioProfileDTO toUsuarioProfileDTO(Usuario usuario){

        UsuarioProfileDTO usuarioProfileDTO = modelMapper.map(usuario, UsuarioProfileDTO.class);
        //mapeo manual
        if(usuario.getEstudiante() != null){
            usuarioProfileDTO.setNombre(usuario.getEstudiante().getNombre());
            usuarioProfileDTO.setApellido(usuario.getEstudiante().getApellido());
            usuarioProfileDTO.setInformacionPersonal(usuario.getEstudiante().getInformacionPersonal());
            usuarioProfileDTO.setNivelAcademico(usuario.getEstudiante().getNivelAcademico());
            usuarioProfileDTO.setInstitucion(usuario.getEstudiante().getInstitucion());
            usuarioProfileDTO.setIntereses(usuario.getEstudiante().getIntereses());
            usuarioProfileDTO.setCarreraInteres(usuario.getEstudiante().getCarreraInteres());
            usuarioProfileDTO.setRole(usuario.getRole().getName());
        } if (usuario.getExperto() != null){
            usuarioProfileDTO.setNombre(usuario.getExperto().getNombre());
            usuarioProfileDTO.setApellido(usuario.getExperto().getApellido());
            usuarioProfileDTO.setInformacionPersonal(usuario.getExperto().getInformacionPersonal());
            usuarioProfileDTO.setExperiencia(usuario.getExperto().getExperiencia());
            usuarioProfileDTO.setCertificaciones(usuario.getExperto().getCertificaciones());
            usuarioProfileDTO.setCarrera(usuario.getExperto().getCarrera());
            usuarioProfileDTO.setRole(usuario.getRole().getName());
        }

        return usuarioProfileDTO;
    }



    public AuthResponseDTO toAuthResponseDTO(Usuario usuario, String token){
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        String nombre = (usuario.getEstudiante() != null) ? usuario.getEstudiante().getNombre()
                : (usuario.getExperto() != null) ? usuario.getExperto().getNombre()
                : "Admin";
        String apellido = (usuario.getExperto() != null) ? usuario.getExperto().getApellido()
                : (usuario.getEstudiante() != null) ? usuario.getEstudiante().getApellido()
                : "User";

        authResponseDTO.setNombre(nombre);
        authResponseDTO.setApellido(apellido);
        authResponseDTO.setRole(String.valueOf(usuario.getRole().getName()));

        return authResponseDTO;
    }

}
