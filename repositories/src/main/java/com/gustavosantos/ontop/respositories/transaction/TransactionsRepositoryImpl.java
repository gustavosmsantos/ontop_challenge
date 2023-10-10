package com.gustavosantos.ontop.respositories.transaction;

import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.ports.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionsRepositoryImpl implements TransactionsRepository {

    private final TransactionsJPARepository repository;

    @Override
    public void save(Transaction transaction) {
        repository.save(TransactionEntity.from(transaction));
    }

    @Override
    public Transaction findById(Long userId, Long id) {
        repository.findByUserAndId(userId, id);
        return null;
    }

    @Override
    public void updateTransactionStatus(Transaction transaction, Transaction.Status newStatus) {
        repository.updateTransactionStatus(transaction.id(), newStatus);
    }
}
