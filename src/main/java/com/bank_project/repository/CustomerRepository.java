package com.bank_project.repository;

import com.bank_project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Optional<Customer> findByEmailId(String emailId);


}
