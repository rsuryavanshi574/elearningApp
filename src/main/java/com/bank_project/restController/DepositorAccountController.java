package com.bank_project.restController;

import com.bank_project.dto.DepositorAccountDTO;
import com.bank_project.dto.DepositorTransactionDTO;
import com.bank_project.entity.DepositorAccount;
import com.bank_project.entity.DepositorTransaction;
import com.bank_project.service.DepositorAccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositorAccountController {

    @Autowired
    DepositorAccountService depositorAccountService;

    @Autowired
    HttpSession session;

    @PostMapping("/api/v1/depositorAccounts/{accountNumber}")
    public String addTransactionsForDepositorAccount(@PathVariable Long accountNumber, @RequestBody DepositorTransaction transaction){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        String transactionSaved = depositorAccountService.addTransactionForDepositorAccount(accountNumber, transaction);
        return transactionSaved;
    }

    @PostMapping("/api/v1/depositorAccounts/{id}")
    public String addDepositorAccount(@RequestBody DepositorAccount depositorAccount, @PathVariable Integer id){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        String accountAdded = depositorAccountService.addDepositorAccount(depositorAccount, id);

        return accountAdded;
    }

    @GetMapping("/api/v1/depositorAccounts/{accountNumber}")
    public DepositorAccountDTO getDepositorAccountWithTransactionsByAccountNumber(@PathVariable Long accountNumber){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        return depositorAccountService.getDepositorAccountWithTransactionsByAccountNumber(accountNumber);
    }

    @GetMapping("/api/v1/depositorAccounts/transactions/{accountNumber}")
    public List<DepositorTransactionDTO> getTransactionsByAccountNumber(@PathVariable Long accountNumber){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }

        return depositorAccountService.getTransactionsByAccountNumber(accountNumber);

    }

    @DeleteMapping("/api/v1/depositorAccounts/{accountNumber}")
    public String deleteDepositorAccount(@PathVariable Long accountNumber){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        String deletedDepositorAccount = depositorAccountService.deleteDepositorAccount(accountNumber);

        return deletedDepositorAccount;
    }

}
