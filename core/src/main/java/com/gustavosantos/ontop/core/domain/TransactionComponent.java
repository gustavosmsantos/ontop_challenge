package com.gustavosantos.ontop.core.domain;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigDecimal;

@RecordBuilder
public record TransactionComponent(Type type, BigDecimal value) {

    public enum Type {

        NET_AMOUNT, FEE

    }

}
