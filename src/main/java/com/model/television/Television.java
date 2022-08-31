package com.model.television;

import com.model.Product;
import com.model.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
public class Television extends Product {
    @Column
    private String model;
    @Column
    private ManufacturerTelevision manufacturer;
    @Column
    private int diagonal;
    @Transient
    private String invoiceID;



    public Television(String title, int count, double price, String model, ManufacturerTelevision manufacturer, int diagonal) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.diagonal = diagonal;
    }
    public Television(String title, int count, double price, String model, ManufacturerTelevision manufacturer, int diagonal, String invoiceID) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.diagonal = diagonal;
        this.invoiceID = invoiceID;
        this.type = ProductType.TELEVISION;
    }

    public Television() {

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
