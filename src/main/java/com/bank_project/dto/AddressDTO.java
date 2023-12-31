package com.bank_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Integer id;
    private String streetAddress;
    private String city;
    private String state;
    private Integer pincode;


}
