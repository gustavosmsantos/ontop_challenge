package com.gustavosantos.ontop.core.logic;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.domain.TransactionBuilder;
import com.gustavosantos.ontop.core.domain.Wallet;
import com.gustavosantos.ontop.core.exceptions.InsufficientFundsException;

import java.math.BigDecimal;

import static com.gustavosantos.ontop.core.domain.Transaction.Type.REFUND;

public class Transactions {

    public static Transaction withdrawal(BigDecimal amount, Wallet userWallet, BankAccount destinationAccount)
    throws InsufficientFundsException {

        if (amount.compareTo(userWallet.funds()) > 0) {
            throw new InsufficientFundsException("User %s has no available funds to proceed".formatted(userWallet.owner()));
        }

        return TransactionBuilder.builder()
                .userId(userWallet.owner())
                .type(Transaction.Type.WITHDRAWAL)
                .build();
    }

    public static Transaction refund(Transaction originalTransaction) {
        if (!originalTransaction.canBeRefunded()) {
            throw new IllegalStateException("Transaction of type %s cannot be reverted".formatted(originalTransaction.type()));
        }

        return originalTransaction.with(transactionBuilder -> {
            transactionBuilder.type(REFUND);
            transactionBuilder.value(originalTransaction.value().abs());
            transactionBuilder.parentTransaction(originalTransaction);
        });
    }

}
