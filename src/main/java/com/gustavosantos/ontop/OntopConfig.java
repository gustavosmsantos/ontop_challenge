package com.gustavosantos.ontop;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.BankAccountBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class OntopConfig {

    @Bean(name = "ontopBankAccount")
    public BankAccount ontopBankAccount() {
        return BankAccountBuilder.builder().build();
    }

}
