package com.gustavosantos.ontop.core.ports;

import com.gustavosantos.ontop.core.domain.Transaction;

public interface TransactionsRepository {

    void save(Transaction transaction);

    Transaction findById(String userId, String id);

    void updateTransactionStatus(Transaction transaction, Transaction.Status newStatus);

}
