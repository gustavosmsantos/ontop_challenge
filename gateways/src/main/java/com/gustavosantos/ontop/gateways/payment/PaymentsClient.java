package com.gustavosantos.ontop.gateways.payment;

import com.gustavosantos.ontop.gateways.payment.dto.NewPaymentRequest;
import com.gustavosantos.ontop.gateways.payment.dto.NewPaymentResponse;
import feign.Headers;
import feign.RequestLine;

public interface PaymentsClient {

    @RequestLine("POST /payments")
    @Headers("Content-Type: application/json")
    NewPaymentResponse createPayment(NewPaymentRequest request);

}
