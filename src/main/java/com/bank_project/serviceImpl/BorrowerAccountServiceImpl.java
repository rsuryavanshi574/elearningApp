package com.bank_project.serviceImpl;

import com.bank_project.dto.BorrowerAccountDTO;
import com.bank_project.entity.BorrowerAccount;
import com.bank_project.entity.Customer;
import com.bank_project.repository.BorrowerAccountRepository;
import com.bank_project.service.BorrowerAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowerAccountServiceImpl implements BorrowerAccountService {

    @Autowired
    BorrowerAccountRepository borrowerAccountRepository;



    @Transactional
    @Override
    public String addBorrowerAccount(BorrowerAccount borrowerAccount, Integer customerId) {
        //Optional<BorrowerAccount> accountOptional = repository.findById(customerId);
        Optional<BorrowerAccount> accountOptional = borrowerAccountRepository.findByCustomerId(customerId);

        System.out.println(customerId+", "+accountOptional);

        BorrowerAccount borrowerAccount1 = accountOptional.get();
        System.out.println(borrowerAccount1);

            Customer customer = borrowerAccount1.getCustomer();

            List<BorrowerAccount> borrowerAccounts = customer.getBorrowerAccounts();

            borrowerAccount.setCustomer(customer);

            customer.setBorrowerAccounts(borrowerAccounts);

            borrowerAccountRepository.save(borrowerAccount1);

            return borrowerAccount.getAccountNumber()+" borrower account added successfully";

        //return "Error while adding borrower account... Please try again";
    }

    @Override
    public String deleteBorrowerAccount(Integer accId) {
        borrowerAccountRepository.deleteById(accId);
        return "Account deleted successfully";
    }

    @Override
    public BorrowerAccountDTO getBorrowerAccountByAccountNumber(Long accountNumber) {
        Optional<BorrowerAccount> accountOptional = borrowerAccountRepository.findByAccountNumber(accountNumber);

        BorrowerAccount borrowerAccount = null;
        if(accountOptional.isPresent()){
            borrowerAccount = accountOptional.get();
        }

        return new BorrowerAccountDTO(borrowerAccount.getId(), borrowerAccount.getAccountNumber(), borrowerAccount.getBalance(), borrowerAccount.getLoanType());
    }

}
