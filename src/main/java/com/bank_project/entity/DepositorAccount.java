package com.bank_project.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class DepositorAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long accountNumber;
    private Double balance;


    @ManyToMany(mappedBy = "depositorAccounts", cascade = CascadeType.PERSIST)
    private List<Customer> customers;

    @OneToMany(mappedBy = "depositorAccount", cascade = CascadeType.ALL)
    private List<DepositorTransaction> transactions;

    /* @ManyToOne
    private Branch branch;


     "branch" : [
        {
            "name" : "Narhe",
            "city" : "Narhe",
            "branch_code" : 555
        }
            ],*/


}
