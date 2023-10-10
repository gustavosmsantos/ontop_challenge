package com.gustavosantos.ontop.core.domain;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record BankAccount(String userId,
                          String name,
                          String surname,
                          String routingNumber,
                          String nin,
                          String accountNumber,
                          String bankName) {
}
