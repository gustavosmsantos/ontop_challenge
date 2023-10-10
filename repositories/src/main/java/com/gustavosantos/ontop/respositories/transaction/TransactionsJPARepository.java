package com.gustavosantos.ontop.respositories.transaction;

import com.gustavosantos.ontop.core.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsJPARepository extends JpaRepository<TransactionEntity, Long> {

    @Query("select t from TransactionEntity t where t.id = :id and t.userId = :userId")
    TransactionEntity findByUserAndId(@Param("userId") Long userId,
                                      @Param("id") Long id);

    @Modifying
    @Query("update TransactionEntity t set t.status = :status where t.id = :id")
    void updateTransactionStatus(@Param("id") Long transactionId, @Param("status") Transaction.Status transactionStatus);

}
