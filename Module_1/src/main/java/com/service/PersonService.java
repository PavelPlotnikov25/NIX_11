package com.service;

import com.model.customer.Customer;

import java.util.Random;

public class PersonService {
    static Random random = new Random();

    public static Customer createNewCustomer(){
        return new Customer(createRandomEmail(10), random.nextInt(12, 65));
    }

    private static String createRandomEmail(int lenght) {
        StringBuilder stringBuilder = new StringBuilder();
        String origin = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890";
        for (int i = 0; i < lenght; i++) {
            int letter = random.nextInt(62);
            stringBuilder.append(origin.charAt(letter));
        }
        stringBuilder.append("@gmail.com");
        return stringBuilder.toString();
    }

}
