package com.gustavosantos.ontop.core.domain;

import java.math.BigDecimal;

public record Wallet(Long ownerId, BigDecimal funds) {
}
