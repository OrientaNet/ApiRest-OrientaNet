package com.grupo2.orientanet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.AnyKeyJavaClass;

@Data
@AllArgsConstructor
public class PaymentOrderResponse {
    private String paypalUrl;
}
