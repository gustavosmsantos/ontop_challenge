package com.gustavosantos.ontop.gateways.payment.dto;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.CompanyBankAccount;

import java.math.BigDecimal;

public record NewPaymentRequest(Source source, Destination destination, BigDecimal amount) {

    public record Source(SourceType type, SourceInformation sourceInformation, Account account) {
    }

    public enum SourceType {
        COMPANY
    }

    public record SourceInformation(String name) {
    }

    public record Account(String accountNumber, Currency currency, String routingNumber) {
    }

    public enum Currency {
        USD
    }

    public record Destination(String name, Account account) {
    }

    public static NewPaymentRequest from(CompanyBankAccount companyBankAccount,
                                         BankAccount destinationBankAccount,
                                         BigDecimal amount) {
        return new NewPaymentRequest(
                new Source(SourceType.COMPANY,
                        new SourceInformation(companyBankAccount.companyName()),
                        new Account(companyBankAccount.accountNumber(),
                                Currency.USD,
                                companyBankAccount.routingNumber())),
                new Destination(destinationBankAccount.fullName(),
                        new Account(destinationBankAccount.accountNumber(),
                                Currency.USD,
                                destinationBankAccount.routingNumber())),
                amount);
    }

}
