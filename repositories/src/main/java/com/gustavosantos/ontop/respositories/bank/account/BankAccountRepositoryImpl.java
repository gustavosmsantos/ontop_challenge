package com.gustavosantos.ontop.respositories.bank.account;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.ports.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final BankAccountJPARepository repository;

    @Override
    public Optional<BankAccount> findByUserId(Long userId) {
        return repository.findByUserId(userId).map(BankAccountEntity::toDomain);
    }
}
