package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.UsuarioProfileDTO;
import com.grupo2.orientanet.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario/profile")
@RequiredArgsConstructor
public class UsuarioProfileController {

    private final UsuarioService usuarioService;

    //actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioProfileDTO> update(@PathVariable Long id, @RequestBody UsuarioProfileDTO usuarioProfileDTO) {
        UsuarioProfileDTO updatedProfile = usuarioService.updateUsuarioProfile(id, usuarioProfileDTO);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    //obtener perfil de usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioProfileDTO> getProfile(@PathVariable Long id) {
        UsuarioProfileDTO usuarioProfile = usuarioService.getUsusarioProfileById(id);
        return new ResponseEntity<>(usuarioProfile, HttpStatus.OK);
    }
}
