package com.example.netdeal.netdeal;

import com.example.netdeal.netdeal.service.PasswordService;

public class TesteSenhas {

    public static void main(String args[]){
        PasswordService passwordService = new PasswordService();
        String input = "RTabBd1-T%304**";
        passwordService.validaPassword(input);
    }
}
