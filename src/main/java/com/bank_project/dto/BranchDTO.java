package com.bank_project.dto;

import com.bank_project.entity.BorrowerAccount;
import com.bank_project.entity.DepositorAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    private Integer id;

    private String name;
    private String city;
    private String branch_code;

    private List<DepositorAccount> depositorAccounts;

    private List<BorrowerAccount> borrowerAccounts;

}
