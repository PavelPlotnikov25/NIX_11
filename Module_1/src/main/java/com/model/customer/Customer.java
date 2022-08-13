package com.model.customer;

import java.util.UUID;

public class Customer {
    private final String id;
    private final String email;
    private int age;

    public Customer(String email, int age) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
