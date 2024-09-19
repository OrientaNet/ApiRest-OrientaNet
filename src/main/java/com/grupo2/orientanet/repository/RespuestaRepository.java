package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.Respuesta;
import com.grupo2.orientanet.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}
