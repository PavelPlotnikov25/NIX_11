package com.model.phone;

import com.google.gson.annotations.Expose;
import com.model.Product;
import com.model.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Phone extends Product {
    @Column
    @Expose
    private String model;
    @Column
    @Expose
    private Manufacturer manufacturer;
    @Transient
    private String invoiceId;
    @Transient
    private List<String> details = new ArrayList<>();
    @Transient
    private Date date;
    @Transient
    private String currency;
    @Transient
    private OperationSystem os;
    @Transient
    private ProductType type;

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
    public Phone(String title, int count, double price,  String model, Manufacturer manufacturer, String invoiceId) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.invoiceId = super.invoiceID;
        this.type = ProductType.PHONE;
    }

    public Phone() {

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
