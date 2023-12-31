package com.bank_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositorAccountDTO {


    private Integer id;
    private Long accountNumber;
    private Double balance;


    //private Branch branch;

    //private List<CustomerDTO> customers;

    private List<DepositorTransactionDTO> transactions;
}
