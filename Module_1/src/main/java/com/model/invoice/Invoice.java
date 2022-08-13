package com.model.invoice;

import com.model.customer.Customer;
import com.model.product.Product;

import java.util.Date;
import java.util.List;

public class Invoice {
    private List<Product> products;
    private Customer customer;
    private Date creatingDate;
    private InvoiceType invoiceType;
    private long totalInvoiceSum;

    public Invoice(List<Product> products, Customer customer, Date creatingDate, InvoiceType invoiceType) {
        this.products = products;
        this.customer = customer;
        this.creatingDate = creatingDate;
        this.invoiceType = invoiceType;
    }

    public Invoice() {
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public long getTotalInvoiceSum() {
        return totalInvoiceSum;
    }

    public void setTotalInvoiceSum(long totalInvoiceSum) {
        this.totalInvoiceSum = totalInvoiceSum;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "products=" + products +
                ", customer=" + customer +
                ", creatingDate=" + creatingDate +
                ", invioceType=" + invoiceType +
                '}' + System.lineSeparator();
    }
}
