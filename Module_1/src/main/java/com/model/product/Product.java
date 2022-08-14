package com.model.product;

import java.util.UUID;

public abstract class Product {
    private final String id;
    private int price;
    private ProductType productType;

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Product(ProductType productType) {
        this.id = UUID.randomUUID().toString();
        this.productType = getProductType();
    }
}
