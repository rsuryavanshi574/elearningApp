package com.bank_project.repository;

import com.bank_project.entity.BorrowerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowerAccountRepository extends JpaRepository<BorrowerAccount, Integer> {

    public Optional<BorrowerAccount> findByAccountNumber(Long accNo);

    public Optional<BorrowerAccount> findByCustomerId(Integer customerId);

}
