package com.grupo2.orientanet.dto;

import com.grupo2.orientanet.model.enums.MetodoPago;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PagoDTO {
    private Long id;

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor que cero")
    private Double monto;

    @NotNull(message = "La fecha de pago no puede ser nula")
    private LocalDate fechaPago;

    @NotNull(message = "El método de pago no puede ser nulo")
    private String metodoPago;

    private String estadoPago;
}
