package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.Experto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertoRepository extends JpaRepository<Experto, Long> {

}
