package com.bank_project.service;

import com.bank_project.dto.DepositorAccountDTO;
import com.bank_project.dto.DepositorTransactionDTO;
import com.bank_project.entity.DepositorAccount;
import com.bank_project.entity.DepositorTransaction;
import org.springframework.stereotype.Service;
import java.util.List;

public interface DepositorAccountService {

    public String addTransactionForDepositorAccount(Long accountNumber, DepositorTransaction transaction);

    public String addDepositorAccount(DepositorAccount depositorAccount, Integer id);

   // public DepositorAccountDTO getDepositorAccountByAccountNumber(Long accountNo);

    public List<DepositorTransactionDTO> getTransactionsByAccountNumber(Long accountNo);

    public DepositorAccountDTO getDepositorAccountWithTransactionsByAccountNumber(Long accountNo);

   // public String deleteTransactionsByAccountNumber(Long accountNo, Integer transactionId);

    public String deleteDepositorAccount(Long accNo);

}
