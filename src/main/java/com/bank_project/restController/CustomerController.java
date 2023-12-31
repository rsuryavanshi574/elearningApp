package com.bank_project.restController;

import com.bank_project.dto.AddressDTO;
import com.bank_project.dto.CustomerDTO;
import com.bank_project.entity.Address;
import com.bank_project.entity.Customer;
import com.bank_project.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    HttpSession session;

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Integer id){
        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomer(){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("user not authorised");
        }
        List<CustomerDTO> allCustomer = customerService.getAllCustomer();

        return allCustomer;
    }


    @PostMapping
    public String addCustomer(@RequestBody Customer customer){
        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        String addCustomer = customerService.addCustomer(customer);

        return addCustomer;
    }

    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable Integer id, @RequestBody Customer customer){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        String updateCustomer = customerService.updateCustomer(id, customer);

        return updateCustomer;
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Integer id){

        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        String deleteCustomer = customerService.deleteCustomer(id);

        return deleteCustomer;
    }

    @GetMapping("/{customerId}/addresses")
    public List<AddressDTO> getAddresses(@PathVariable Integer customerId){
        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        List<AddressDTO> addressesUsingCustomerId = customerService.getAddressesUsingCustomerId(customerId);
        return addressesUsingCustomerId;
    }

    @PostMapping("/{id}/addresses")
    public String addAddress(@RequestBody Address address, @PathVariable Integer id){
        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        String addressAdded = customerService.addAddress(address, id);
        return addressAdded;
    }

    @DeleteMapping("/{customerId}/addresses/{addressId}")
    public String deleteAddress(@PathVariable Integer customerId, @PathVariable Integer addressId){
        if(session.getAttribute("user")==null){
            throw new RuntimeException("User not authorised");
        }
        return customerService.deleteAddress(customerId, addressId);
    }
}
