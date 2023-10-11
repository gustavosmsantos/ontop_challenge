package com.gustavosantos.ontop.respositories.bank.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountJPARepository extends JpaRepository<BankAccountEntity, Long> {

    Optional<BankAccountEntity> findByUserId(Long userId);

}
