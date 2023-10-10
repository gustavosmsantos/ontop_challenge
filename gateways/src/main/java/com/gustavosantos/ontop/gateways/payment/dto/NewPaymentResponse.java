package com.gustavosantos.ontop.gateways.payment.dto;

import java.math.BigDecimal;

public record NewPaymentResponse(RequestInfo requestInfo, PaymentInfo paymentInfo) {

    public record RequestInfo(Status status) {}

    public enum Status {
        PROCESSING
    }

    public record PaymentInfo(BigDecimal amount, String id) {}

}
