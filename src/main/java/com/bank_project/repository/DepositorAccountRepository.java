package com.bank_project.repository;

import com.bank_project.entity.DepositorAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositorAccountRepository extends JpaRepository<DepositorAccount, Integer> {

    public Optional<DepositorAccount> findByAccountNumber(Long accNo);

}
