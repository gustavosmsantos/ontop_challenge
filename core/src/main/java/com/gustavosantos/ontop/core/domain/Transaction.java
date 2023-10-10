package com.gustavosantos.ontop.core.domain;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigDecimal;

@RecordBuilder
public record Transaction(Long id,
                          String userId,
                          Type type,
                          BigDecimal value,
                          BankAccount destinationAccount,
                          Transaction parentTransaction) implements TransactionBuilder.With {

    public boolean canBeRefunded() {
       return type != Type.REFUND;
    }

    public BigDecimal netAmount() {
        //TODO 
        return value;
    }

    public enum Status {

        PROCESSING, SUCCEEDED, FAILED

    }

    public enum Type {

        TOPUP, WITHDRAWAL, REFUND

    }

}

