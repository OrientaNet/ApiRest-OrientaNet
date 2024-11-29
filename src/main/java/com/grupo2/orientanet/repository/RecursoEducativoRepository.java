package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.RecursoEducativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecursoEducativoRepository extends JpaRepository<RecursoEducativo, Long> {


    List<RecursoEducativo> findByCarreraId(Long carreraId);

    List<RecursoEducativo> findByExpertoId(Long expertoId);
}
