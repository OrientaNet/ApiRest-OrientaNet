package com.grupo2.orientanet.dto;

import lombok.Data;

@Data
public class PaymentCaptureResponse {
    private boolean completed;
    private Long pagoId;
}
