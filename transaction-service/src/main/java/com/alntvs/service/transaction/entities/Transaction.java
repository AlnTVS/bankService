package com.alntvs.service.transaction.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_num_tx")
    private String cardNumTX;

    @Column(name = "card_num_rx")
    private String cardNumRX;

    @Column(name = "sum_of_transaction")
    private Integer sumOfTransaction;

    @Column(name = "date_of_transaction")
    private Timestamp dateOfTransaction;

    public Transaction(String cardNumTX, String cardNumRX, Integer sumOfTransaction, Timestamp dateOfTransaction) {
        this.cardNumTX = cardNumTX;
        this.cardNumRX = cardNumRX;
        this.sumOfTransaction = sumOfTransaction;
        this.dateOfTransaction = dateOfTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cardNumTX='" + cardNumTX + '\'' +
                ", cardNumRX='" + cardNumRX + '\'' +
                ", sumOfTransaction=" + sumOfTransaction +
                ", dateOfTransaction=" + dateOfTransaction +
                '}';
    }
}
