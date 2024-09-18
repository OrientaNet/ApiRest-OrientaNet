package com.grupo2.orientanet.service.impl;

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

    private static final String REGEX_NOMBRE = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]{2,}$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";


    @Autowired
    UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario getById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado TONTO"));
    }

    @Transactional
    @Override
    public Usuario create(Usuario nuevoUsuario) throws Exception {
        validarUsuario(nuevoUsuario);

        // Verificar si el correo ya existe en la base de datos
        if (usuarioRepository.existsByEmail(nuevoUsuario.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado.");
        }

        return usuarioRepository.save(nuevoUsuario);
    }

    @Transactional
    @Override
    public Usuario update(Long id, Usuario updateUsuario) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("El usuario no existe");
        }

        // Verificar si el correo ya existe en la base de datos y si es de otro usuario
        Usuario existingUsuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("El usuario no existe"));
        validarUsuario(updateUsuario);

        // Copiar valores de los campos que se deben actualizar
        existingUsuario.setNombre(updateUsuario.getNombre());
        existingUsuario.setApellido(updateUsuario.getApellido());
        existingUsuario.setContrasena(updateUsuario.getContrasena());
        existingUsuario.setEmail(updateUsuario.getEmail());
        existingUsuario.setRole(updateUsuario.getRole());
        existingUsuario.setUpdatedAt(LocalDateTime.now());  // Actualizar solo el campo updated_at

        return usuarioRepository.save(existingUsuario);
    }

    private void validarUsuario(Usuario usuario) throws Exception {
        // Validar nombre
        if (!Pattern.matches(REGEX_NOMBRE, usuario.getNombre())) {
            throw new Exception("Nombre inválido. Debe contener al menos 2 caracteres y solo letras.");
        }
        // Validar apellido
        if (!Pattern.matches(REGEX_NOMBRE, usuario.getApellido())) {
            throw new Exception("Apellido inválido. Debe contener al menos 2 caracteres y solo letras.");
        }
        // Validar email
        if (!Pattern.matches(REGEX_EMAIL, usuario.getEmail())) {
            throw new Exception("Correo electrónico inválido.");
        }
        // Validar contraseña
        if (!Pattern.matches(REGEX_PASSWORD, usuario.getContrasena())) {
            throw new Exception("Contraseña inválida. Debe tener al menos 8 caracteres, una letra mayúscula, una minúscula y un número.");
        }
    }


    @Transactional
    @Override
    public void delete(Long id) {
        Usuario usuario = getById(id);
        usuarioRepository.delete(usuario);
    }
}

