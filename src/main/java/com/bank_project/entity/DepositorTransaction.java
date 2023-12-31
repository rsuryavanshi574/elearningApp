package com.bank_project.entity;

import com.bank_project.enums.TransactionType;
import lombok.Data;

import jakarta.persistence.*;
@Entity
@Data
public class DepositorTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DepositorAccount depositorAccount;
}
