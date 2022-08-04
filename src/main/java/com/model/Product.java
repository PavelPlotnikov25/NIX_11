package com.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class Product{
    protected final String id;
    protected String title;
    protected int count;
    protected double price;

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    protected ProductType type;

    protected Product(String title, int count, double price) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.count = count;
        this.price = price;
    }
    abstract public Product copy() throws CloneNotSupportedException;

}
