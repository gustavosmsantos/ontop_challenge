package com.gustavosantos.ontop.core.ports;

import com.gustavosantos.ontop.core.domain.BankAccount;

public interface BankAccountRepository {

    BankAccount findByUserId(Long userId);

}
