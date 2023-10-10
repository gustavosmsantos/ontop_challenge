package com.gustavosantos.ontop.core.usecases;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.ports.PaymentsGateway;
import com.gustavosantos.ontop.core.ports.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

@RequiredArgsConstructor
public class PayTransaction {

    private final PaymentsGateway paymentsGateway;

    private final TransactionsRepository transactionsRepository;

    @Qualifier(value = "ontopBankAccount")
    private final BankAccount ontopAccount;

    //TODO transactional context
    public void execute(Long userId, Long transactionId) {
        Transaction transaction = transactionsRepository.findById(userId, transactionId);
        BankAccount destinationAccount = transaction.destinationAccount();
        paymentsGateway.executePayment(ontopAccount, destinationAccount, transaction.netAmount());
        transactionsRepository.updateTransactionStatus(transaction, Transaction.Status.SUCCEEDED);
    }

}
