package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.AuthResponseDTO;
import com.grupo2.orientanet.dto.LoginDTO;
import com.grupo2.orientanet.dto.UsuarioRegistrationDTO;
import com.grupo2.orientanet.dto.UsuarioProfileDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.UsuarioMapper;
import com.grupo2.orientanet.model.entity.Estudiante;
import com.grupo2.orientanet.model.entity.Experto;
import com.grupo2.orientanet.model.entity.Role;
import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.model.enums.ERole;
import com.grupo2.orientanet.repository.EstudianteRepository;
import com.grupo2.orientanet.repository.ExpertoRepository;
import com.grupo2.orientanet.repository.RoleRepository;
import com.grupo2.orientanet.repository.UsuarioRepository;
import com.grupo2.orientanet.security.TokenProvider;
import com.grupo2.orientanet.security.UserPrincipal;
import com.grupo2.orientanet.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EstudianteRepository estudianteRepository;
    private final ExpertoRepository expertoRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;


    @Override
    public List<UsuarioProfileDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioProfileDTO> usuariosProfile = usuarios.stream().map(usuario -> usuarioMapper.toUsuarioProfileDTO(usuario)).collect(Collectors.toList());
        return usuariosProfile;
    }

    @Override
    public UsuarioProfileDTO registerEstudiante(UsuarioRegistrationDTO usuarioRegistrationDTO) {
        return registerUserWithRole(usuarioRegistrationDTO, ERole.ESTUDIANTE);
    }

    @Override
    public UsuarioProfileDTO registerExperto(UsuarioRegistrationDTO usuarioRegistrationDTO) {
        return registerUserWithRole(usuarioRegistrationDTO, ERole.EXPERTO);
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getContrasena())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Usuario usuario = userPrincipal.getUsuario();

        String token = tokenProvider.createAccessToken(authentication);

        AuthResponseDTO responseDTO = usuarioMapper.toAuthResponseDTO(usuario,token);
        return responseDTO;
    }

    @Transactional
    @Override
    public UsuarioProfileDTO updateUsuarioProfile(Long id, UsuarioProfileDTO usuarioProfileDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        //Verificar si ya existe un cliente o autor con el mismo nombre y apellido (excepto el usuario actual)
        boolean existsAsCustomer = estudianteRepository.existsByNombreAndApellidoAndIdNot(
                usuarioProfileDTO.getNombre(), usuarioProfileDTO.getApellido(), id);
        boolean existsAsExperto = expertoRepository.existsByNombreAndApellidoAndIdNot(
                usuarioProfileDTO.getNombre(), usuarioProfileDTO.getApellido(), id);
        System.out.println("Author exists: " + existsAsExperto);

        if (existsAsCustomer || existsAsExperto) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido");
        }

        if(usuario.getEstudiante()!=null){

            usuario.getEstudiante().setNombre(usuarioProfileDTO.getNombre());
            usuario.getEstudiante().setApellido(usuarioProfileDTO.getApellido());
            usuario.getEstudiante().setInformacionPersonal(usuarioProfileDTO.getInformacionPersonal());
            usuario.getEstudiante().setIntereses(usuarioProfileDTO.getIntereses());
            usuario.getEstudiante().setNivelAcademico(usuarioProfileDTO.getNivelAcademico());
            usuario.getEstudiante().setCarreraInteres(usuarioProfileDTO.getCarreraInteres());
        }

        if(usuario.getExperto()!=null){
            usuario.getExperto().setNombre(usuarioProfileDTO.getNombre());
            usuario.getExperto().setApellido(usuarioProfileDTO.getApellido());
            usuario.getExperto().setInformacionPersonal(usuarioProfileDTO.getInformacionPersonal());
            usuario.getExperto().setExperiencia(usuarioProfileDTO.getIntereses());
            usuario.getExperto().setCarrera(usuarioProfileDTO.getCarrera());
            usuario.getExperto().setCertificaciones(usuarioProfileDTO.getCertificaciones());
        }

        Usuario updatedUser = usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioProfileDTO(updatedUser);

    }

    @Override
    public UsuarioProfileDTO getUsusarioProfileById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return usuarioMapper.toUsuarioProfileDTO(usuario);
    }

    private UsuarioProfileDTO registerUserWithRole (UsuarioRegistrationDTO registrationDTO, ERole roleEnum) {

        //verificar si el email esta registrado o existe usuario con mismo fullname.
        boolean existsByEmail = usuarioRepository.existsByEmail(registrationDTO.getEmail());
        boolean existsAsEstudiante = estudianteRepository.existsByNombreAndApellido(registrationDTO.getNombre(), registrationDTO.getApellido());
        boolean existsAsExperto = expertoRepository.existsByNombreAndApellido(registrationDTO.getNombre(), registrationDTO.getApellido());

        if ( existsByEmail ) {
            throw new IllegalArgumentException("El email ya existe");
        } else if ( existsAsEstudiante || existsAsExperto ) {
            throw new IllegalArgumentException("El usuario ya existe con el mismo nombre y apellido");
        }

        Role role = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new ResourceNotFoundException("El rol no existe"));

        registrationDTO.setContrasena(passwordEncoder.encode(registrationDTO.getContrasena()));
        Usuario usuario = usuarioMapper.toUserEntity(registrationDTO);
        usuario.setRole(role);

        if (roleEnum == ERole.ESTUDIANTE) {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(registrationDTO.getNombre());
            estudiante.setApellido(registrationDTO.getApellido());
            estudiante.setInformacionPersonal(registrationDTO.getInformacionPersonal());
            estudiante.setIntereses(registrationDTO.getIntereses());
            estudiante.setNivelAcademico(registrationDTO.getNivelAcademico());
            estudiante.setCreatedAt(LocalDateTime.now());
            estudiante.setUsuario(usuario);
            usuario.setEstudiante(estudiante);
        } else if (roleEnum == ERole.EXPERTO) {
            Experto experto = new Experto();
            experto.setNombre(registrationDTO.getNombre());
            experto.setApellido(registrationDTO.getApellido());
            experto.setInformacionPersonal(registrationDTO.getInformacionPersonal());
            experto.setCertificaciones(registrationDTO.getCertificaciones());
            experto.setCarrera(registrationDTO.getCarrera());
            experto.setExperiencia(registrationDTO.getExperiencia());
            experto.setUsuario(usuario);
            usuario.setExperto(experto);
        }

        Usuario savedUser = usuarioRepository.save(usuario);
        return usuarioMapper.toUsuarioProfileDTO(savedUser);
    }

}

