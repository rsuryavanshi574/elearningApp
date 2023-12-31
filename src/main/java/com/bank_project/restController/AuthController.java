package com.bank_project.restController;

import com.bank_project.entity.Customer;
import com.bank_project.entity.LoginRequest;
import com.bank_project.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired  //scope = session -> every user session new object
    HttpSession session;


    @PostMapping("/login")  //authenticate user
    public String login(@RequestBody LoginRequest loginRequest){
        //use repository/DOA layer to get the user and verify the password
        Optional<Customer> customerOptional = customerRepository.findByEmailId(loginRequest.getEmailId());
        Customer customer = customerOptional.get();

        if( loginRequest.getEmailId().equals(customer.getEmailId()) && loginRequest.getPassword().equals(customer.getPassword()) ) {
            session.setAttribute("user",loginRequest.getEmailId());
            return "Logged In  \nWelcome "+customer.getFirstName();
        }
        session.invalidate();
        return "Login Failed";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "Logged Out";
    }

}
