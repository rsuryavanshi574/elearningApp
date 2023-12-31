package com.bank_project.entity;

import com.bank_project.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    @Email
    private String emailId;
    private String password;
    private Date dob;
    private String adharNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<MobileNumber> mobileNumbers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_depositorAccount_relationship",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "depositor_account_id")
    )
    private List<DepositorAccount> depositorAccounts;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<BorrowerAccount> borrowerAccounts;

}
