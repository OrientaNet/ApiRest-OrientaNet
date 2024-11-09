package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.AuthResponseDTO;
import com.grupo2.orientanet.dto.LoginDTO;
import com.grupo2.orientanet.dto.UsuarioProfileDTO;
import com.grupo2.orientanet.dto.UsuarioRegistrationDTO;
import com.grupo2.orientanet.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final UsuarioService usuarioService;


    // Registro Estudiantes
    @PostMapping("/register/estudiante")
    public ResponseEntity<UsuarioProfileDTO> registerEstudiante(@Valid @RequestBody UsuarioRegistrationDTO usuarioRegistrationDTO) {
        UsuarioProfileDTO usuarioProfile = usuarioService.registerEstudiante(usuarioRegistrationDTO);
        return new ResponseEntity<>(usuarioProfile, HttpStatus.CREATED);
    }

    // Registro Expertos
    @PostMapping("/register/experto")
    public ResponseEntity<UsuarioProfileDTO> register(@Valid @RequestBody UsuarioRegistrationDTO usuarioRegistrationDTO) {
        UsuarioProfileDTO expertoProfile = usuarioService.registerExperto(usuarioRegistrationDTO);
        return new ResponseEntity<>(expertoProfile, HttpStatus.CREATED);
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@Valid @RequestBody LoginDTO loginDTO){
        AuthResponseDTO authResponse = usuarioService.login(loginDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
