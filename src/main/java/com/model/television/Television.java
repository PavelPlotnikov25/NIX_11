package com.model.television;

import com.model.Product;
import com.model.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Television extends Product {
    private String model;
    private ManufacturerTelevision manufacturer;
    private int diagonal;
    private String invoiceId;



    public Television(String title, int count, double price, String model, ManufacturerTelevision manufacturer, int diagonal) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.diagonal = diagonal;
        this.type = ProductType.TELEVISION;
    }
    public Television(String title, int count, double price, String model, ManufacturerTelevision manufacturer, int diagonal, String invoiceId) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.diagonal = diagonal;
        this.invoiceId=invoiceId;
        this.type = ProductType.TELEVISION;
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
