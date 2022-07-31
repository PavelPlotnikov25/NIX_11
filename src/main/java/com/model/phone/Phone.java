package com.model.phone;

import com.model.Product;
import com.model.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Phone extends Product {
    private final String model;
    private final Manufacturer manufacturer;

    private List<String> details = new ArrayList<>();


    public Phone(String title, int count, double price, ProductType productType, String model, Manufacturer manufacturer, List<String> details) {
        super(title, count, price, productType);
        this.model = model;
        this.manufacturer = manufacturer;
        this.details = details;
    }

    public Phone(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price, ProductType.PHONE);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
    @Override
    public String toString() {
        return "Phone{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", model=" + model +
                '}';
    }

    @Override
    public Product copy(){
        return new Phone(
                this.title,
                this.count,
                this.price,
                this.model,
                this.manufacturer);
    }
}
