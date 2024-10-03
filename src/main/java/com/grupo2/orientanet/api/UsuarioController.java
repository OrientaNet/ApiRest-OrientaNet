package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.UsuarioRequestDTO;
import com.grupo2.orientanet.dto.UsuarioResponseDTO;
import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable("id") Long id) {
        UsuarioResponseDTO usuario = usuarioService.getById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioRequestDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        UsuarioRequestDTO createdAutor = usuarioService.create(usuarioRequestDTO);
        return new ResponseEntity<>(createdAutor, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRequestDTO> updateUsuario(@PathVariable("id") Long id,@Valid
                                                 @RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        UsuarioRequestDTO updateUsuario = usuarioService.update(id, usuarioRequestDTO);
        return new ResponseEntity<>(updateUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
