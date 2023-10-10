package com.gustavosantos.ontop;

import com.gustavosantos.ontop.core.domain.CompanyBankAccount;
import com.gustavosantos.ontop.core.domain.CompanyBankAccountBuilder;
import com.gustavosantos.ontop.core.logic.TransactionsFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class OntopConfig {

    @Bean
    public CompanyBankAccount ontopBankAccount(@Value("ontop.account.company_name") String companyName,
                                               @Value("ontop.account.number") String accountNumber,
                                               @Value("ontop.account.routing_number") String routingNumber) {
        return CompanyBankAccountBuilder.builder()
                .companyName(companyName)
                .accountNumber(accountNumber)
                .routingNumber(routingNumber)
                .build();
    }

    @Bean
    public TransactionsFactory transactionsFactory(@Value("ontop.transactions.fee") BigDecimal transactionsFee) {
        return new TransactionsFactory(transactionsFee);
    }

}
