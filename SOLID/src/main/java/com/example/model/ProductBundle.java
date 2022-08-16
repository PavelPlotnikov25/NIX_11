package com.example.model;

import lombok.Setter;

@Setter
public class ProductBundle extends Product {
    protected int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductBundle{" +
                "id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
