package com.model.computer;

import com.model.Product;
import com.model.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Objects;


@Getter
@Setter
@Entity
public class Computer extends Product{
    @Column
    private String model;
    @Column
    private ManufacturerComputer manufacturer;
    @Transient
    private String invoiceId;



    public Computer(String title, int count, double price, String model, ManufacturerComputer manufacturer, String invoiceId) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.invoiceId = invoiceId;
        this.type = ProductType.COMPUTER;
    }

    public Computer(String title, int count, double price, String model, ManufacturerComputer manufacturer) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = ProductType.COMPUTER;
    }

    public Computer() {
        super();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return model.equals(computer.model) && manufacturer == computer.manufacturer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, manufacturer);
    }


    @Override
    public Product copy(){
        return new Computer(
                this.title,
                this.count,
                this.price,
                this.model,
                this.manufacturer,
                this.invoiceId);
    }

    public static class Builder{
        private Computer buildedComputer;

        public Builder(double price) {
            
            if (price < 0) {
                System.out.println("Price cannot be lower than 0");
            } else {
                buildedComputer = new Computer("Empty", 0,price, "empty model", null, null);
            }
        }

        public Builder setTitle(String title){
            buildedComputer.setTitle(title);
            return this;
        }

        public Builder setCount(int count){
            if (count < 0){
                System.out.println("Count cannot be lower than 0");
            }
            buildedComputer.setCount(count);
            return this;
        }

        public Builder setModel(String model){
            buildedComputer.setModel(model);
            return this;
        }

        public Builder setManufacturer(ManufacturerComputer manufacturer){
            buildedComputer.setManufacturer(ManufacturerComputer.valueOf(String.valueOf(manufacturer)));
            return this;
        }

        public Computer build(){
            return buildedComputer;
        }
    }
}
