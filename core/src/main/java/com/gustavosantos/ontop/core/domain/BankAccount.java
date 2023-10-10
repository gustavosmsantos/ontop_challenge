package com.gustavosantos.ontop.core.domain;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record BankAccount(Long userId,
                          String name,
                          String surname,
                          String routingNumber,
                          String nin,
                          String accountNumber,
                          String bankName) {
}
