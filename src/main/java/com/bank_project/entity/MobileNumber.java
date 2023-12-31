package com.bank_project.entity;

import lombok.Data;

import jakarta.persistence.*;
@Entity
@Data
public class MobileNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String countryCode;
    private String mobileNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
