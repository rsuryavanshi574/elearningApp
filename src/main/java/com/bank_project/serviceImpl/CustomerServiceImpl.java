package com.bank_project.serviceImpl;

import com.bank_project.customException.DataNotFoundException;
import com.bank_project.dto.AddressDTO;
import com.bank_project.dto.CustomerDTO;
import com.bank_project.dto.MobileNumberDTO;
import com.bank_project.entity.Address;
import com.bank_project.entity.Customer;
import com.bank_project.entity.MobileNumber;
import com.bank_project.repository.CustomerRepository;
import com.bank_project.service.CustomerService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper;

    /* @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Integer id){
        return service.getCustomerById(id);
    }*/
    @Override
    public CustomerDTO getCustomerById(Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(customerOptional.isPresent()){
            Customer c = customerOptional.get();

            List<Address> addresses = c.getAddresses();


            List<MobileNumber> mobileNumbers = c.getMobileNumbers();

            List<AddressDTO> addressDTO = convertAddressIntoAddressDTO(addresses);
            List<MobileNumberDTO> mobileNumberDTO = convertMobileNumberIntoMobileNumberDTO(mobileNumbers);

            return new CustomerDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getEmailId(), c.getDob(), c.getGender(), addressDTO, mobileNumberDTO);
        }else
            throw new DataNotFoundException("Customer not found.. Please add customer");
        }

    @Override
    public List<CustomerDTO> getAllCustomer() {

        List<Customer> customers = customerRepository.findAll();
        List<Address> addresses = null;
        List<MobileNumber> mobileNumbers = null;
        for (Customer c : customers){
            addresses = c.getAddresses();

            mobileNumbers = c.getMobileNumbers();
        }

        List<AddressDTO> addressDTO = convertAddressIntoAddressDTO(addresses);

        List<MobileNumberDTO> mobileNumberDTO = convertMobileNumberIntoMobileNumberDTO(mobileNumbers);

        /*List<CustomerDTO> customerDTOList = customers.stream().map(customer -> new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmailId(), customer.getDob(), customer.getGender(), addressDTO, mobileNumberDTO))
                .collect(Collectors.toList());*/

        //CustomerDTO customerDTO = this.modelMapper.map(customers, CustomerDTO.class); for single obj

        List<CustomerDTO> customerDTOList = customers.stream()
                    .map(customer -> this.modelMapper.map(customer, CustomerDTO.class))
                    .collect(Collectors.toList());

        return customerDTOList;
    }

    @Transactional
    @Override
    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        customer.getAddresses().forEach(address -> address.setCustomer(customer));
        customer.getMobileNumbers().forEach(mobileNumber -> mobileNumber.setCustomer(customer));
        customer.getBorrowerAccounts().forEach(borrowerAccount -> borrowerAccount.setCustomer(customer));

        return "customer details inserted successfully...";
    }

    @Override
    public String updateCustomer(Integer id, Customer customer) {

        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(customerOptional.isPresent()) {

            Customer customer1 = customerOptional.get();

            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            customer1.setEmailId(customer.getEmailId());
            customer1.setPassword(customer.getPassword());
            customer1.setDob(customer.getDob());
            customer1.setAdharNumber(customer.getAdharNumber());
            customer1.setGender(customer.getGender());
            customer1.setAddresses(customer.getAddresses());
            customer1.setMobileNumbers(customer.getMobileNumbers());

            customerRepository.save(customer1);
            return "Customer details updated successfully...";
        }
        return "Customer not found for "+id+" Id. please enter valid Id";
    }

    @Transactional
    @Override
    public String deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
        return "customer details deleted....";
    }

    @Override
    public List<AddressDTO> getAddressesUsingCustomerId(Integer customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            List<Address> addresses = customerOptional.get().getAddresses();
            List<AddressDTO> addressDTOS = convertAddressIntoAddressDTO(addresses);

            return addressDTOS;
        }else
         throw new DataNotFoundException("Address not found for "+customerId+" customer id");
    }

    @Override
    public String addAddress(Address address, Integer customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            List<Address> addresses = customer.getAddresses();
            addresses.add(address);
            address.setCustomer(customer);
            customerRepository.save(customer);
            return "Address added successfully of customer id "+customerId;
        }

        return "Error while adding address. Please try again...";
    }

    @Override
    public String updateAddress(Address address, Integer id) {
        return null;
    }

    @Override
    public String deleteAddress(Integer customerId, Integer addressId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            List<Address> addresses = customer.getAddresses();

            for (Address a : addresses){
                if(a.getId() == addressId){
                    addresses.remove(addresses.lastIndexOf(addresses));
                }
            }

            return "address deleted successfully for customer id "+customerId;
        }else
            return "error while deleting address of customer "+customerId;
    }

    public  List<AddressDTO> convertAddressIntoAddressDTO(List<Address> addresses){

        List<AddressDTO> addressDTO = addresses.stream()
                .map(a -> new AddressDTO(a.getId(), a.getStreetAddress(), a.getCity(), a.getState(), a.getPincode()))
                .collect(Collectors.toList());
        return addressDTO;
    }

    public  List<MobileNumberDTO> convertMobileNumberIntoMobileNumberDTO(List<MobileNumber> mobileNumbers){

        List<MobileNumberDTO> mobileNumberDTO = mobileNumbers.stream()
                .map(mn -> new MobileNumberDTO(mn.getId(), mn.getCountryCode(), mn.getMobileNumber()))
                .collect(Collectors.toList());

        return mobileNumberDTO;
    }

}
