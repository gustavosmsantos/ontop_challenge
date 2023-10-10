package com.gustavosantos.ontop.core.domain;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record CompanyBankAccount(String companyName,
                                 String routingNumber,
                                 String accountNumber) {
}
