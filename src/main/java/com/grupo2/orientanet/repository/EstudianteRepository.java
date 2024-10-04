package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.model.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Consulta para contar cuántos estudiantes están interesados en cada carrera
    @Query("SELECT new com.grupo2.orientanet.dto.CarreraDTO(e.carreraInteres.id, e.carreraInteres.nombre, e.carreraInteres.descripcion) " +
            "FROM Estudiante e " +
            "GROUP BY e.carreraInteres.id, e.carreraInteres.nombre, e.carreraInteres.descripcion " +
            "ORDER BY COUNT(e) DESC")
    List<CarreraDTO> findCarreraConMasInteres();
}
