package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.model.entity.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    List<Suscripcion> findByEstudianteId(Long estudianteId);

    @Query("SELECT p FROM Pago p WHERE p.suscripcion.id = :suscripcionId")
    List<Pago> findPagosBySuscripcionId(@Param("suscripcionId") Long suscripcionId);


}
