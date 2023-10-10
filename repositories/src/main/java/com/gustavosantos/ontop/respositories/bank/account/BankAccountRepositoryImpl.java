package com.gustavosantos.ontop.respositories.bank.account;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.ports.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final BankAccountJPARepository repository;

    @Override
    public BankAccount findByUserId(Long userId) {
        repository.findByUserId(userId);
        return null;
    }
}
