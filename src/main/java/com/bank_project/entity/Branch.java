package com.bank_project.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String city;


    private Integer branch_code;

   /* @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<DepositorAccount> depositorAccounts;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<BorrowerAccount> borrowerAccounts;*/

}
