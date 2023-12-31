package com.bank_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileNumberDTO {

    private Integer id;
    private String countryCode;
    private String mobileNumber;


}
