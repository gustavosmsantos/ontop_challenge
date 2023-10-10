package com.gustavosantos.ontop.core.usecases;

import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.domain.Wallet;
import com.gustavosantos.ontop.core.logic.TransactionsFactory;
import com.gustavosantos.ontop.core.ports.TransactionsRepository;
import com.gustavosantos.ontop.core.ports.WalletsGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefundTransaction {

    private final WalletsGateway walletsGateway;

    private final TransactionsRepository transactionsRepository;

    private final TransactionsFactory transactionsFactory;

    //TODO transactional context
    public void execute(Long userId, Long transactionId) {
        Wallet wallet = walletsGateway.retrieveWallet(userId);
        Transaction transaction = transactionsRepository.findById(userId, transactionId);
        Transaction refundTransaction = transactionsFactory.refund(transaction);
        transactionsRepository.save(refundTransaction);
        walletsGateway.topUp(wallet, refundTransaction.grossAmount());
    }

}
