package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.UsuarioRequestDTO;
import com.grupo2.orientanet.dto.UsuarioResponseDTO;
import com.grupo2.orientanet.mapper.UsuarioMapper;
import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.repository.UsuarioRepository;
import com.grupo2.orientanet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    @Autowired
    UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioResponseDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioMapper::responseToDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioResponseDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con el "+id+" no encontrado"));
        return usuarioMapper.responseToDTO(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioResponseDTO getByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return  usuarioMapper.responseToDTO(usuario);
    }

    @Transactional
    @Override
    public UsuarioRequestDTO create(UsuarioRequestDTO usuarioRequestDTO) throws Exception {


        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado.");
        }

        Usuario usuario = usuarioMapper.requestToEntity(usuarioRequestDTO);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.requestToDTO(usuario);

    }

    @Transactional
    @Override
    public UsuarioRequestDTO update(Long id, UsuarioRequestDTO updateUsuarioRequestDTO) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("El usuario no existe");
        }

        // Verificar si el correo ya existe en la base de datos y si es de otro usuario
        Usuario existingUsuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("El usuario no existe"));


        // Copiar valores de los campos que se deben actualizar
        existingUsuario.setNombre(updateUsuarioRequestDTO.getNombre());
        existingUsuario.setApellido(updateUsuarioRequestDTO.getApellido());
        existingUsuario.setContrasena(updateUsuarioRequestDTO.getContrasena());
        existingUsuario.setEmail(updateUsuarioRequestDTO.getEmail());
        existingUsuario.setRole(updateUsuarioRequestDTO.getRole());
        existingUsuario.setUpdatedAt(LocalDateTime.now());  // Actualizar solo el campo updated_at

        existingUsuario = usuarioRepository.save(existingUsuario);

        return usuarioMapper.requestToDTO(existingUsuario);
    }





    @Transactional
    @Override
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("el id del autor no fue encontrado"));
        usuarioRepository.delete(usuario);
    }
}

