package com.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Invoice {
    private String id;
    private double sum;
    private List<Product> products;
    private LocalDateTime time;

    public Invoice(String id, double sum, List<Product> products, LocalDateTime time) {
        this.id = id;
        this.sum = sum;
        this.products = products;
        this.time = time;
    }
    public Invoice(String id, double sum, LocalDateTime time) {
        this.id = id;
        this.sum = sum;
        this.time = time;
    }

    public Invoice() {
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", sum=" + sum +
                ", products=" + products +
                ", time=" + time +
                '}';
    }
}
