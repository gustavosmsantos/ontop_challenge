package com.gustavosantos.ontop.core.logic;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.domain.TransactionBuilder;
import com.gustavosantos.ontop.core.domain.TransactionComponent;
import com.gustavosantos.ontop.core.domain.TransactionComponentBuilder;
import com.gustavosantos.ontop.core.domain.Wallet;
import com.gustavosantos.ontop.core.exceptions.InsufficientFundsException;
import com.gustavosantos.ontop.core.exceptions.ResourceOwnershipException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.gustavosantos.ontop.core.domain.Transaction.Type.REFUND;
import static com.gustavosantos.ontop.core.domain.TransactionComponent.Type.FEE;
import static com.gustavosantos.ontop.core.domain.TransactionComponent.Type.NET_AMOUNT;

@RequiredArgsConstructor
public class TransactionsFactory {

    private final BigDecimal transactionFeePercentage;

    private List<TransactionComponent> getTransactionComponents(BigDecimal value) {
        BigDecimal fee = transactionFeePercentage.multiply(value);
        return Arrays.asList(
            new TransactionComponent(NET_AMOUNT, value.subtract(fee)),
            new TransactionComponent(FEE, fee)
        );
    }

    public Transaction withdrawal(BigDecimal amount, Wallet userWallet, BankAccount destinationAccount)
            throws InsufficientFundsException, ResourceOwnershipException {

        if (amount.compareTo(userWallet.funds()) > 0) {
            throw new InsufficientFundsException("User %s has no available funds to proceed".formatted(userWallet.ownerId()));
        }

        if (!Objects.equals(userWallet.ownerId(), destinationAccount.userId())) {
            throw new ResourceOwnershipException("User %s not authorized to perform a transfer to account %s".formatted(userWallet.ownerId(),
                    destinationAccount.userId()));
        }

        return TransactionBuilder.builder()
                .userId(userWallet.ownerId())
                .type(Transaction.Type.WITHDRAWAL)
                .components(this.getTransactionComponents(amount))
                .destinationAccount(destinationAccount)
                .build();
    }

    public Transaction refund(Transaction originalTransaction) {
        if (!originalTransaction.canBeRefunded()) {
            throw new IllegalStateException("Transaction of type %s cannot be reverted".formatted(originalTransaction.type()));
        }

        return originalTransaction.with(transactionBuilder -> {
            transactionBuilder.type(REFUND);
            transactionBuilder.components(originalTransaction.components().stream()
                    .map(c -> TransactionComponentBuilder.from(c).withValue(c.value().abs())).collect(Collectors.toList()));
            transactionBuilder.parentTransaction(originalTransaction);
        });
    }

}
