package com.model.phone;

import com.model.Product;
import com.model.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Phone extends Product {
    private final String model;
    private final Manufacturer manufacturer;
    private List<String> details = new ArrayList<>();
    private Date date;
    private String currency;
    private OperationSystem os;

    public Phone(String title, int count, double price, String model, Manufacturer manufacturer, Date date, String currency, OperationSystem os) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.date = date;
        this.currency = currency;
        this.os = os;
    }

    public Phone(String title, int count, double price, String model, Manufacturer manufacturer, List<String> details) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.details = details;
    }

    public Phone(String title, int count, double price,  String model, Manufacturer manufacturer) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public List<String> getDetails() {
        return details;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public OperationSystem getOs() {
        return os;
    }

    public void setOs(OperationSystem os) {
        this.os = os;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                ", date=" + date +
                ", currency='" + currency + '\'' +
                ", os=" + os +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Phone{" +
//                "manufacturer=" + manufacturer +
//                ", id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", count=" + count +
//                ", price=" + price +
//                ", model=" + model +
//                '}';
//    }

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
