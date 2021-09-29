package com.alntvs.service.web.repositories;

import com.alntvs.service.web.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountsRepository extends JpaRepository<BankAccount, Long>, JpaSpecificationExecutor<BankAccount> {
    BankAccount findOneByNumber(String number);
}
