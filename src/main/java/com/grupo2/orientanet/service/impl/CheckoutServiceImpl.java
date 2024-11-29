package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.PagoDTO;
import com.grupo2.orientanet.dto.PaymentCaptureResponse;
import com.grupo2.orientanet.dto.PaymentOrderResponse;
import com.grupo2.orientanet.integration.payment.paypal.dto.OrderCaptureResponse;
import com.grupo2.orientanet.integration.payment.paypal.dto.OrderResponse;
import com.grupo2.orientanet.integration.payment.paypal.service.PaypalService;
import com.grupo2.orientanet.model.entity.Pago;
import com.grupo2.orientanet.service.CheckoutService;
import com.grupo2.orientanet.service.PagoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.hibernate.loader.internal.AliasConstantsHelper.get;

@AllArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private final PaypalService paypalService;
    @Autowired
    private final PagoService pagoService;

    @Override
    public PaymentOrderResponse createPayment(Long pagoId, String returnUrl, String CancelUrl) {
        OrderResponse orderResponse = paypalService.createOrder(pagoId, returnUrl, CancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();
        return new PaymentOrderResponse(paypalUrl);
    }

    @Override
    public PaymentCaptureResponse capturePayment(String orderId) {
        OrderCaptureResponse orderCaptureResponse = paypalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();
            PagoDTO pagoDTO = pagoService.confirmarPago(Long.parseLong(purchaseIdStr));
            paypalCaptureResponse.setPagoId(pagoDTO.getId());
        }
        return paypalCaptureResponse;
    }
}
