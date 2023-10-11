package com.gustavosantos.ontop.core.domain;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RecordBuilder
public record Transaction(Long id,
                          Long userId,
                          Type type,
                          Status status,
                          List<TransactionComponent> components,
                          BankAccount destinationAccount,
                          Transaction parentTransaction) implements TransactionBuilder.With {

    public boolean canBeRefunded() {
       return type != Type.REFUND && status == Status.SUCCEEDED;
    }

    public BigDecimal grossAmount() {
        return this.components.stream().map(TransactionComponent::value).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal netAmount() {
        Optional<TransactionComponent> netAmount = this.components.stream().filter(c -> TransactionComponent.Type.NET_AMOUNT.equals(c.type())).findFirst();
        return netAmount.orElseThrow().value();
    }

    public enum Status {

        PENDING, PROCESSING, SUCCEEDED, FAILED

    }

    public enum Type {

        WITHDRAWAL, REFUND

    }

}

