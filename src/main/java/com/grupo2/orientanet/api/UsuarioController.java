package com.grupo2.orientanet.api;

import com.grupo2.orientanet.dto.UsuarioRegistrationDTO;
import com.grupo2.orientanet.dto.UsuarioProfileDTO;
import com.grupo2.orientanet.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")

public class UsuarioController {


    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioProfileDTO>> getAllUsuarios() {
        List<UsuarioProfileDTO> usuarios = usuarioService.getAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UsuarioProfileDTO> getUsuarioById(@PathVariable("id") Long id) {
//        UsuarioProfileDTO usuario = usuarioService.getById(id);
//        return new ResponseEntity<>(usuario, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<UsuarioRegistrationDTO> createUsuario(@Valid @RequestBody UsuarioRegistrationDTO usuarioRegistrationDTO) throws Exception {
//        UsuarioRegistrationDTO createdAutor = usuarioService.create(usuarioRegistrationDTO);
//        return new ResponseEntity<>(createdAutor, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UsuarioRegistrationDTO> updateUsuario(@PathVariable("id") Long id, @Valid
//                                                 @RequestBody UsuarioRegistrationDTO usuarioRegistrationDTO) throws Exception {
//        UsuarioRegistrationDTO updateUsuario = usuarioService.update(id, usuarioRegistrationDTO);
//        return new ResponseEntity<>(updateUsuario, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
//        usuarioService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
