package com.bank_project.entity;

import lombok.Data;

@Data
public class LoginRequest {

    String emailId;
    String password;
}