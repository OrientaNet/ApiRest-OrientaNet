package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.PaymentCaptureResponse;
import com.grupo2.orientanet.dto.PaymentOrderResponse;
import com.grupo2.orientanet.integration.payment.paypal.dto.PaymentSource;

public interface CheckoutService {

    PaymentOrderResponse createPayment(Long pagoId, String returnUrl, String CancelUrl );

    PaymentCaptureResponse capturePayment(String orderId);

}
