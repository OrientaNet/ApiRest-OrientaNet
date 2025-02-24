package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findOneByEmail(String email);
    boolean existsByEmail(String email);
}
