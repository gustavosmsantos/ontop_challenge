package com.gustavosantos.ontop.gateways.wallet;

import com.gustavosantos.ontop.core.domain.Wallet;
import com.gustavosantos.ontop.core.ports.WalletsGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log
@Component
@RequiredArgsConstructor
public class WalletApiGateway implements WalletsGateway {

    private final WalletsClient client;

    @Override
    public Wallet retrieveWallet(Long userId) {
        return client.retrieveBalance(userId).toDomain();
    }

    @Override
    public void topUp(Wallet wallet, BigDecimal amount) {
        log.info("executing topup operation");
    }

    @Override
    public void withdraw(Wallet wallet, BigDecimal amount) {
        log.info("executing withdraw operation");
    }

}
