package com.bank_project.service;

import com.bank_project.dto.BorrowerAccountDTO;
import com.bank_project.entity.BorrowerAccount;
import org.springframework.stereotype.Service;

public interface BorrowerAccountService {

    public String addBorrowerAccount(BorrowerAccount borrowerAccount, Integer customerId);

    public String deleteBorrowerAccount(Integer accountId);

    public BorrowerAccountDTO getBorrowerAccountByAccountNumber(Long accountNumber);


}
