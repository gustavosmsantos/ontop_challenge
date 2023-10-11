package com.gustavosantos.ontop.respositories.transaction;

import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.respositories.bank.account.BankAccountEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "status", nullable = false)
    private Transaction.Status status;

    @Column(name = "type", nullable = false)
    private Transaction.Type type;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private List<TransactionComponentEntity> components;

    @OneToOne
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccountEntity bankAccount;

    @OneToOne
    @JoinColumn(name = "parent_transaction_id", referencedColumnName = "id")
    private TransactionEntity parentTransaction;

    public static TransactionEntity from(Transaction transaction) {
        List<TransactionComponentEntity> components = transaction.components().stream()
                .map(TransactionComponentEntity::from)
                .toList();

        TransactionEntityBuilder builder = new TransactionEntityBuilder()
                .userId(transaction.userId())
                .status(transaction.status())
                .type(transaction.type())
                .bankAccount(BankAccountEntity.from(transaction.destinationAccount()))
                .components(components);

        if (transaction.parentTransaction() != null) {
            builder.parentTransaction(TransactionEntity.from(transaction.parentTransaction()));
        }

        return builder.build();
    }

}
