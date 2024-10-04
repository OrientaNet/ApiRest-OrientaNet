package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    List<Suscripcion> findByEstudianteId(Long estudianteId);
}
