package com.gustavosantos.ontop.core.usecases;

import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.domain.Wallet;
import com.gustavosantos.ontop.core.logic.Transactions;
import com.gustavosantos.ontop.core.ports.TransactionsRepository;
import com.gustavosantos.ontop.core.ports.WalletsGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefundTransaction {

    private final WalletsGateway walletsGateway;

    private final TransactionsRepository transactionsRepository;

    //TODO transactional context
    public void execute(String userId, String transactionId) {
        Wallet wallet = walletsGateway.retrieveWallet(userId);
        Transaction transaction = transactionsRepository.findById(userId, transactionId);
        Transaction refundTransaction = Transactions.refund(transaction);
        transactionsRepository.save(refundTransaction);
        walletsGateway.topUp(wallet, refundTransaction.value());
    }

}
