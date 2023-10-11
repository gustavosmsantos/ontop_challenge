package com.gustavosantos.ontop.core.usecases;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.domain.Wallet;
import com.gustavosantos.ontop.core.exceptions.InsufficientFundsException;
import com.gustavosantos.ontop.core.exceptions.ResourceOwnershipException;
import com.gustavosantos.ontop.core.logic.TransactionsFactory;
import com.gustavosantos.ontop.core.ports.BankAccountRepository;
import com.gustavosantos.ontop.core.ports.TransactionsRepository;
import com.gustavosantos.ontop.core.ports.WalletsGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log
@Component
@RequiredArgsConstructor
public class TransferToBankAccount {

    private final WalletsGateway walletsGateway;

    private final TransactionsRepository transactionsRepository;

    private final BankAccountRepository bankAccountRepository;

    private final TransactionsFactory transactionsFactory;

    //TODO transactional context
    public void execute(Long userId, BigDecimal amount) throws InsufficientFundsException {
        Wallet wallet = walletsGateway.retrieveWallet(userId);
        BankAccount destinationAccount = bankAccountRepository.findByUserId(userId).orElseThrow();
        try {
            Transaction transaction = transactionsFactory.withdrawal(amount, wallet, destinationAccount);
            transactionsRepository.save(transaction);
            walletsGateway.withdraw(wallet, amount);
        } catch (ResourceOwnershipException e) {
            //TODO log error
            throw new RuntimeException(e);
        }

    }

}
