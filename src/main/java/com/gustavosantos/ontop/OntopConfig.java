package com.gustavosantos.ontop;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.BankAccountBuilder;
import com.gustavosantos.ontop.core.logic.TransactionsFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Configuration
public class OntopConfig {

    @Bean(name = "ontopBankAccount")
    public BankAccount ontopBankAccount() {
        return BankAccountBuilder.builder().build();
    }

    @Bean
    public TransactionsFactory transactionsFactory(@Value("ontop.transactions.fee") BigDecimal transactionsFee) {
        return new TransactionsFactory(transactionsFee);
    }

}
