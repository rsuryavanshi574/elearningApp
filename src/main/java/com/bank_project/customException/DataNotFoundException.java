package com.bank_project.customException;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String msg){
        super(msg);
    }
}
