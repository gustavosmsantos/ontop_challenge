package com.gustavosantos.ontop.respositories.bank.account;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.BankAccountBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bank_accounts")
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "routing_number", nullable = false)
    private String routingNumber;

    @Column(name = "nin", nullable = false)
    private String nin;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "bank_name")
    private String bankName;

    public static BankAccountEntity from(BankAccount bankAccount) {
        return new BankAccountEntityBuilder()
                .id(bankAccount.id())
                .userId(bankAccount.userId())
                .name(bankAccount.name())
                .surname(bankAccount.surname())
                .routingNumber(bankAccount.routingNumber())
                .nin(bankAccount.nin())
                .accountNumber(bankAccount.accountNumber())
                .bankName(bankAccount.bankName())
                .build();
    }

    public BankAccount toDomain() {
        return BankAccountBuilder.builder()
                .id(this.id)
                .userId(this.userId)
                .name(this.name)
                .surname(this.surname)
                .routingNumber(this.routingNumber)
                .nin(this.nin)
                .accountNumber(this.accountNumber)
                .bankName(this.bankName)
                .build();
    }

}
