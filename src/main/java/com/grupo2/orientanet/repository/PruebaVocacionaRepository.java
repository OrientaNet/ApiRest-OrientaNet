package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.PruebaVocacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaVocacionaRepository extends JpaRepository<PruebaVocacional, Long> {
}
