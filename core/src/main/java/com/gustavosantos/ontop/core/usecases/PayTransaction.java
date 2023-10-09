package com.gustavosantos.ontop.core.usecases;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.ports.PaymentsGateway;
import com.gustavosantos.ontop.core.ports.TransactionsRepository;

public class PayTransaction {

    private final PaymentsGateway paymentsGateway;

    private final TransactionsRepository transactionsRepository;

    private final BankAccount ontopAccount;

    public PayTransaction(PaymentsGateway paymentsGateway,
                          TransactionsRepository transactionsRepository,
                          BankAccount ontopAccount) {
        this.paymentsGateway = paymentsGateway;
        this.transactionsRepository = transactionsRepository;
        this.ontopAccount = ontopAccount;
    }

    //TODO transactional context
    public void execute(String userId, String transactionId) {
        Transaction transaction = transactionsRepository.findById(userId, transactionId);
        BankAccount destinationAccount = transaction.destinationAccount();
        paymentsGateway.executePayment(ontopAccount, destinationAccount, transaction.netAmount());
        transactionsRepository.updateTransactionStatus(transaction, Transaction.Status.SUCCEEDED);
    }

}
