package com.gustavosantos.ontop.gateways.payment;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.ports.PaymentsGateway;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log
@Component
public class PaymentsApiGateway implements PaymentsGateway {

    @Override
    public void executePayment(BankAccount optopAccount, BankAccount destinationAccount, BigDecimal amount) {
        log.info("Executing payment");
    }

}
