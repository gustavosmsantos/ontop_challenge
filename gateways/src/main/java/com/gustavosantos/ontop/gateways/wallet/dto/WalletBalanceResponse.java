package com.gustavosantos.ontop.gateways.wallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustavosantos.ontop.core.domain.Wallet;

import java.math.BigDecimal;

public record WalletBalanceResponse(BigDecimal balance, @JsonProperty("user_id") Long userId) {

    public Wallet toDomain() {
        return new Wallet(userId, balance);
    }

}
