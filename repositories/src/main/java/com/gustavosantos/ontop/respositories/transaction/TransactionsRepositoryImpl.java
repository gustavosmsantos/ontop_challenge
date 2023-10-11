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
        TransactionEntity entity = TransactionEntity.from(transaction);
        repository.save(entity);
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
