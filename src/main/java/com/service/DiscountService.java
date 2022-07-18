package com.service;

import com.model.Product;

import java.util.Random;

public class DiscountService <T extends Product> {
    public T getProduct() {return product;}

    public void setProduct(T product) {this.product = product;}

    private T product;
    private final Random random = new Random();

    public void getDiscount(T product){
        this.product = product;
        int minDiscount = 10;
        int maxDiscount =30;
        int discount = random.nextInt(minDiscount,maxDiscount);
        double priceWithoutDiscount = product.getPrice();
        double priceWithDiscount = priceWithoutDiscount - ((priceWithoutDiscount * discount)/100);
        product.setPrice(priceWithDiscount);
    }
    public <X extends Number> void increaseCountOfProduct (X count){
        int currentCount = product.getCount();
        product.setCount(currentCount + count.intValue());
    }
}
