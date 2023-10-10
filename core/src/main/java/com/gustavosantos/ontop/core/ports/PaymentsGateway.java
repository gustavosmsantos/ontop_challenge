package com.gustavosantos.ontop.core.ports;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.CompanyBankAccount;
import com.gustavosantos.ontop.core.domain.PaymentInfo;

import java.math.BigDecimal;

public interface PaymentsGateway {

    PaymentInfo executePayment(CompanyBankAccount optopAccount, BankAccount destinationAccount, BigDecimal amount);

}
