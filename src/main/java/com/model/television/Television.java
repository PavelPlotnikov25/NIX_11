package com.model.television;

import com.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Television extends Product {
    private final String model;
    private final ManufacturerTelevision manufacturer;
    private final int diagonal;



    public Television(String title, int count, double price, String model, ManufacturerTelevision manufacturer, int diagonal) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return "Television{" +
                "manufacturer=" + manufacturer +
                ", diagonal " + diagonal + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    @Override
    public Product copy(){
        return new Television(
                this.title,
                this.count,
                this.price,
                this.model,
                this.manufacturer,
                this.diagonal);
    }

}
