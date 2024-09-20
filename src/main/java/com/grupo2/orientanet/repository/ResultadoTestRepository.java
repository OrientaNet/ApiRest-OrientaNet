package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.PruebaVocacional;
import com.grupo2.orientanet.model.entity.ResultadoTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoTestRepository extends JpaRepository<ResultadoTest, Long> {
}
