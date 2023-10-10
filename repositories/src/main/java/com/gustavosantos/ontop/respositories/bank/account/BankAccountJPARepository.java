package com.gustavosantos.ontop.respositories.bank.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountJPARepository extends JpaRepository<BankAccountEntity, Long> {

    BankAccountEntity findByUserId(Long userId);

}
