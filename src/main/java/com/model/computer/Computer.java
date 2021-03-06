package com.model.computer;

import com.model.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Computer extends Product{
    private final String model;
    private final ManufacturerComputer manufacturer;

    public Computer(String title, int count, double price, String model, ManufacturerComputer manufacturer) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    @Override
    public Product copy(){
        return new Computer(
                this.title,
                this.count,
                this.price,
                this.model,
                this.manufacturer);
    }
}
