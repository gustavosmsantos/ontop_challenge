package com.gustavosantos.ontop.gateways.payment;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.CompanyBankAccount;
import com.gustavosantos.ontop.core.domain.PaymentInfo;
import com.gustavosantos.ontop.core.ports.PaymentsGateway;
import com.gustavosantos.ontop.gateways.payment.dto.NewPaymentRequest;
import com.gustavosantos.ontop.gateways.payment.dto.NewPaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log
@Component
@RequiredArgsConstructor
public class PaymentsApiGateway implements PaymentsGateway {

    private final PaymentsClient paymentsClient;

    @Override
    public PaymentInfo executePayment(CompanyBankAccount ontopAccount, BankAccount destinationAccount, BigDecimal amount) {
        NewPaymentRequest request = NewPaymentRequest.from(ontopAccount, destinationAccount, amount);
        //TODO error handling
        NewPaymentResponse response = paymentsClient.createPayment(request);
        return new PaymentInfo(response.paymentInfo().id(), response.requestInfo().status().name());
    }

}
