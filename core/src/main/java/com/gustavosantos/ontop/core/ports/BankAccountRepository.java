package com.gustavosantos.ontop.core.ports;

import com.gustavosantos.ontop.core.domain.BankAccount;

import java.util.Optional;

public interface BankAccountRepository {

    Optional<BankAccount> findByUserId(Long userId);

}
