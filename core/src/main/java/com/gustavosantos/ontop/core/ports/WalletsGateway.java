package com.gustavosantos.ontop.core.ports;

import com.gustavosantos.ontop.core.domain.Wallet;

import java.math.BigDecimal;

public interface WalletsGateway {

    Wallet retrieveWallet(String userId);

    void topUp(Wallet wallet, BigDecimal amount);

    void withdraw(Wallet wallet, BigDecimal amount);

}
