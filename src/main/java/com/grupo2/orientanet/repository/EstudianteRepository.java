package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}
