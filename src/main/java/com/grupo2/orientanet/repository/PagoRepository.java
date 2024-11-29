package com.grupo2.orientanet.repository;


import com.grupo2.orientanet.model.entity.Pago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findPagosBySuscripcionId(Long suscripcionId);

}
