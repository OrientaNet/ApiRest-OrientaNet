package com.grupo2.orientanet.api;

import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.service.UsuarioService;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {

        Usuario compareUser = usuarioService.getByEmail(usuario.getEmail());

        // Si no se encuentra el usuario
        if (compareUser == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        // Verificar la contraseña
        if (!compareUser.getContrasena().equals(usuario.getContrasena())) {
            return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario usuario) throws Exception {
        Usuario newUsuario = usuarioService.create(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
