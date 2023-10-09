package com.gustavosantos.ontop.core.domain;

import java.math.BigDecimal;

public record Wallet(String owner, BigDecimal funds) {
}
