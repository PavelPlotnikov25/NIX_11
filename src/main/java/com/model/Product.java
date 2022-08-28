package com.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class Product{
    protected String id;
    protected String title;
    protected int count;
    protected double price;
    protected ProductType type;

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }



    protected Product(String title, int count, double price) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.count = count;
        this.price = price;
    }
    abstract public Product copy() throws CloneNotSupportedException;

}
