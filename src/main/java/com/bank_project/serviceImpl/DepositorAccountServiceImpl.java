package com.bank_project.serviceImpl;

import com.bank_project.customException.DataNotFoundException;
import com.bank_project.dto.DepositorAccountDTO;
import com.bank_project.dto.DepositorTransactionDTO;
import com.bank_project.entity.Customer;
import com.bank_project.entity.DepositorAccount;
import com.bank_project.entity.DepositorTransaction;
import com.bank_project.repository.DepositorAccountRepository;
import com.bank_project.service.DepositorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositorAccountServiceImpl implements DepositorAccountService {
    
    @Autowired
    DepositorAccountRepository depositorAccountRepository;

    @Override
    public String addTransactionForDepositorAccount(Long accountNumber, DepositorTransaction transaction) {
        Optional<DepositorAccount> depositorAccountOptional = depositorAccountRepository.findByAccountNumber(accountNumber);

        if(depositorAccountOptional.isPresent()){
            DepositorAccount depositorAccount = depositorAccountOptional.get();
            List<DepositorTransaction> transactions = depositorAccount.getTransactions();
            transactions.add(transaction);

            for(DepositorTransaction t : transactions){
                t.setDepositorAccount(depositorAccount);
            }
            depositorAccount.setTransactions(transactions);

            depositorAccountRepository.save(depositorAccount);

            return "Transaction saved successfully for account Number "+accountNumber;
        }else
            return "Error while adding transaction";


    }

    @Override
    public String addDepositorAccount(DepositorAccount depositorAccount, Integer id) {
        Optional<DepositorAccount> accountOptional = depositorAccountRepository.findById(id);

        if(accountOptional.isPresent()){
            DepositorAccount depositorAccount1 = accountOptional.get();
            List<Customer> customers = depositorAccount1.getCustomers();

            customers.addAll(depositorAccount.getCustomers());
            /*for (int i = 0; i < depositorAccount.getCustomers().size(); i++) {
                customers.add(depositorAccount.getCustomers().get(i));
            }*/

            for (Customer c : customers){
               // c.setDepositorAccounts(depositorAccount1.getId());
            }

            depositorAccountRepository.save(depositorAccount);
        }

        return depositorAccount.getAccountNumber()+" account added successfully...";
    }

   /* @Transactional
    @Override
    public String addDepositorAccount(DepositorAccount depositorAccount) {
        repository.save(depositorAccount);
        depositorAccount.getCustomers().forEach(customer -> customer.setDepositorAccounts());
        return "Depositor account added successfully....";
    }*/


    @Override
    public DepositorAccountDTO getDepositorAccountWithTransactionsByAccountNumber(Long accountNo) {
        Optional<DepositorAccount> depositorAccountOptional = depositorAccountRepository.findByAccountNumber(accountNo);

        if(depositorAccountOptional.isPresent()){
            DepositorAccount depositorAccount = depositorAccountOptional.get();

            List<DepositorTransaction> transactions = depositorAccount.getTransactions();

            List<DepositorTransactionDTO> depositorTransactionDTOS = convertTransactionToTransactionDTO(transactions);

            return new DepositorAccountDTO(depositorAccount.getId(), depositorAccount.getAccountNumber(), depositorAccount.getBalance(), depositorTransactionDTOS);
        }else
            throw new DataNotFoundException(accountNo+" Account not available...");
    }

    @Override
    public List<DepositorTransactionDTO> getTransactionsByAccountNumber(Long accountNo) {
        Optional<DepositorAccount> depositorAccountOptional = depositorAccountRepository.findByAccountNumber(accountNo);

        if(depositorAccountOptional.isPresent()){
            DepositorAccount depositorAccount = depositorAccountOptional.get();

            List<DepositorTransaction> transactions = depositorAccount.getTransactions();
            List<DepositorTransactionDTO> depositorTransactionList = transactions.stream()
                    .map(t -> new DepositorTransactionDTO(t.getId(), t.getAmount(), t.getType()))
                    .collect(Collectors.toList());

            return depositorTransactionList;
        }else
            throw new DataNotFoundException("Transaction not found for this account");
    }

    public List<DepositorTransactionDTO> convertTransactionToTransactionDTO(List<DepositorTransaction> transactions){
        List<DepositorTransactionDTO> depositorTransactionList = transactions.stream()
                .map(t -> new DepositorTransactionDTO(t.getId(), t.getAmount(), t.getType()))
                .collect(Collectors.toList());

        return depositorTransactionList;
    }

    @Transactional
    @Override
    public String deleteDepositorAccount(Long accNo) {
        Optional<DepositorAccount> depositorAccountOptional = depositorAccountRepository.findByAccountNumber(accNo);
        if(depositorAccountOptional.isPresent()){
            DepositorAccount depositorAccount = depositorAccountOptional.get();

            depositorAccountRepository.deleteById(depositorAccount.getId());
        }
        return accNo+" account is deleted....";
    }

    /*@Override
    public DepositorAccountDTO getDepositorAccountByAccountNumber(Long accountNo) {
        Optional<DepositorAccount> depositorAccountOptional = repository.findByAccountNumber(accountNo);

        DepositorAccount depositorAccount = null;

        if(depositorAccountOptional.isPresent()){
            depositorAccount = depositorAccountOptional.get();
        }

        return new DepositorAccountDTO(depositorAccount.getId(), depositorAccount.getAccountNumber(), depositorAccount.getBalance());
    }*/



   /* @Override
    public String deleteTransactionsByAccountNumber(Long accountNo, Integer transactionId) {
        Optional<DepositorAccount> depositorAccountOptional = repository.findByAccountNumber(accountNo);
        if(depositorAccountOptional.isPresent()){
            DepositorAccount depositorAccount = depositorAccountOptional.get();

            repository.deleteById(depositorAccount.getId());
        }
        return "Transaction deleted successfully...";
    }*/
}
