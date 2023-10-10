package com.gustavosantos.ontop.respositories.transaction;

import com.gustavosantos.ontop.core.domain.Transaction;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column
    private Transaction.Status status;

    public static TransactionEntity from(Transaction transaction) {
        //TODO
        return null;
    }

}
