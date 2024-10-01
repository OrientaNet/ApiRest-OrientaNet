package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.SesionAsesoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionAsesoriaRepository extends JpaRepository<SesionAsesoria, Long> {
}
