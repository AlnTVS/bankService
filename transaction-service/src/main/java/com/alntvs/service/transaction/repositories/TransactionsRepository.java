package com.alntvs.service.transaction.repositories;

import com.alntvs.service.transaction.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
}
