package com.model.product;


public enum ProductType {

    TELEPHONE("Telephone"),
    TELEVISION("Television");

    final String value;
    ProductType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
