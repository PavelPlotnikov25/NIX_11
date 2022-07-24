package com.model.phone;

import com.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phone extends Product {
    private final String model;
    private final Manufacturer manufacturer;

    public Phone(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
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
