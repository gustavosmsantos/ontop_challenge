package com.gustavosantos.ontop.gateways.wallet;

import com.gustavosantos.ontop.core.domain.Wallet;
import com.gustavosantos.ontop.core.ports.WalletsGateway;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log
@Component
public class WalletApiGateway implements WalletsGateway {

    @Override
    public Wallet retrieveWallet(String userId) {
        log.info("retrieving wallet");
        return null;
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
