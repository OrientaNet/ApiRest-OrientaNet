package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Personalizar findById si es necesario
    @Override
    Optional<Estudiante> findById(Long id);

    boolean existsByNombreAndApellido(String nombre, String apellido);

    boolean existsByNombreAndApellidoAndIdNot(String nombre, String apellido, long estudianteId);
}
