package com.alntvs.service.transaction.repositories.specification;

import com.alntvs.service.transaction.entities.Transaction;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecifications {
    public static Specification<Transaction> findAllByBankAccountNumber(String bankAccountNumber) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("cardNumTX"), "%" + bankAccountNumber + "%");
    }
//
//    public static Specification<Transaction> surnameLike(String surname) {
//        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), "%" + surname + "%");
//    }
}
