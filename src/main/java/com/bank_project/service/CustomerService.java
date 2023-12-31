package com.bank_project.service;

import com.bank_project.dto.AddressDTO;
import com.bank_project.dto.CustomerDTO;
import com.bank_project.entity.Address;
import com.bank_project.entity.Customer;

import java.util.List;

public interface CustomerService {

    public CustomerDTO getCustomerById(Integer id);

    public List<CustomerDTO> getAllCustomer();

    public String addCustomer(Customer customer);

    public String updateCustomer(Integer id, Customer customer);

    public String deleteCustomer(Integer customerId);

    public List<AddressDTO> getAddressesUsingCustomerId(Integer id);

    public String addAddress(Address address, Integer customerId);

    public String updateAddress(Address address, Integer id);

    public String deleteAddress(Integer customerId, Integer addressId);
}
