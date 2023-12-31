package com.bank_project.dto;

import com.bank_project.entity.Branch;
import com.bank_project.entity.Customer;
import com.bank_project.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerAccountDTO {

    private Integer id;
    private Long accountNumber;
    private Double balance;

    private LoanType loanType;


    

}
