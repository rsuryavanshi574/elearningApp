package com.bank_project.entity;

import com.bank_project.enums.LoanType;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class BorrowerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long accountNumber;
    private Double balance;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @ManyToOne
    private Customer customer;

   /* @ManyToOne
    private Branch branch;*/


}
