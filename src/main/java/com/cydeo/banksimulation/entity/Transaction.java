package com.cydeo.banksimulation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account receiver;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account sender;

    private BigDecimal amount;
    private String message;
    @Column(columnDefinition = "DATE")
    private Date creationDate;

}
