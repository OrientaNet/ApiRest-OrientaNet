package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.UsuarioRequestDTO;
import com.grupo2.orientanet.dto.UsuarioResponseDTO;
import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

 /*   @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioResponseDTO usuarioResponseDTO, UsuarioRequestDTO usuarioRequestDTO) {

        UsuarioResponseDTO compareUser = usuarioService.getByEmail(usuarioResponseDTO.getEmail());

        UsuarioRequestDTO compareUserContrasena = usuarioService.getByEmail(usuarioRequestDTO.getContrasena());

        // Si no se encuentra el usuario
        if (compareUser == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        // Verificar la contraseña
        if (!compareUserContrasena.getContrasena().equals(usuario.getContrasena())) {
            return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
    }*/

    @PostMapping("/register")
    public ResponseEntity<UsuarioRequestDTO> register(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        UsuarioRequestDTO newUsuario = usuarioService.create(usuarioRequestDTO);
        return new ResponseEntity<>(newUsuario, HttpStatus.OK);
    }
}
