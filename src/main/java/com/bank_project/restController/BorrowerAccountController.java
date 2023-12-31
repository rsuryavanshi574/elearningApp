package com.bank_project.restController;

import com.bank_project.dto.BorrowerAccountDTO;
import com.bank_project.entity.BorrowerAccount;
import com.bank_project.service.BorrowerAccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowerAccountController {

    @Autowired
    BorrowerAccountService borrowerAccountService;

    @Autowired
    HttpSession session;

    @PostMapping("/api/v1/borrowerAccounts/{customerId}")
    public String addBorrowerAccount(@RequestBody BorrowerAccount borrowerAccount, @PathVariable Integer customerId){
        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        System.out.println("customer id = "+customerId);
        String addedBorrowerAccount = borrowerAccountService.addBorrowerAccount(borrowerAccount, customerId);

        return addedBorrowerAccount;
    }

    @GetMapping("/api/v1/borrowerAccounts/{accountNumber}")
    public BorrowerAccountDTO getBorrowerAccount(@PathVariable Long accountNumber){
        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        BorrowerAccountDTO borrowerAccount = borrowerAccountService.getBorrowerAccountByAccountNumber(accountNumber);

        return borrowerAccount;
    }

    @DeleteMapping("/api/v1/borrowerAccounts/{id}")
    public String deleteBorrowerAccount(@PathVariable Integer id){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        String deletedBorrowerAccount = borrowerAccountService.deleteBorrowerAccount(id);

        return deletedBorrowerAccount;
    }

}
