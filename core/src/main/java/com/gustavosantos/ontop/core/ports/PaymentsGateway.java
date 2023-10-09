package com.gustavosantos.ontop.core.ports;

import com.gustavosantos.ontop.core.domain.BankAccount;

import java.math.BigDecimal;

public interface PaymentsGateway {

    void executePayment(BankAccount optopAccount, BankAccount destinationAccount, BigDecimal amount);

}
