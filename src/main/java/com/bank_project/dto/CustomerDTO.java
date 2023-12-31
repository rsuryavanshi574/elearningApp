package com.bank_project.dto;

import com.bank_project.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {


    private Integer id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Date dob;
    private Gender gender;


    private List<AddressDTO> addresses;

    private List<MobileNumberDTO> mobileNumbers;

}
