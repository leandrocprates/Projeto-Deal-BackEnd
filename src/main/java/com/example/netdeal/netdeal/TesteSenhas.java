package com.example.netdeal.netdeal;

import com.example.netdeal.netdeal.service.PasswordService;

public class TesteSenhas {

    public static void main(String args[]){
        PasswordService passwordService = new PasswordService();
        passwordService.validaPassword("Rabcd");
    }
}
