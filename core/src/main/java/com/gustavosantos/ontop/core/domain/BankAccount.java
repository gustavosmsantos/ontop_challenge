package com.gustavosantos.ontop.core.domain;

public record BankAccount(String userId,
                          String name,
                          String surname,
                          String routingNumber,
                          String nin,
                          String accountNumber,
                          String bankName) {
}
