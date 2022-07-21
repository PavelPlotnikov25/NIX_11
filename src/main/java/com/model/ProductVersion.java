package com.model;

import java.util.Date;

public class ProductVersion {
    private Product product;
    private int version;
    private Date dateOfAdding;

    public ProductVersion(Product product, int version, Date dateOfAdding) {
        this.product = product;
        this.version = version;
        this.dateOfAdding = dateOfAdding;
    }

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public int getVersion() {return version;}

    public void setVersion(int version) {this.version = version;}

    public Date getDateOfAdding() {return dateOfAdding;}

    public void setDateOfAdding(Date dateOfAdding) {this.dateOfAdding = dateOfAdding;}

    @Override
    public String toString() {
        return "ProductVersion{" +
                "product=" + product +
                ", version=" + version +
                ", dateOfAdding=" + dateOfAdding +
                '}';
    }
}
