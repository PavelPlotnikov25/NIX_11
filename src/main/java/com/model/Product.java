package com.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(schema = "hibernate")
public abstract class Product{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    protected String id;
    @Column
    protected String title;
    @Column
    protected int count;
    @Column
    protected double price;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    protected Invoice invoice;
    @Transient
    protected ProductType type;

    public Product() {

    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    protected Product(String title, int count, double price) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.count = count;
        this.price = price;
    }
    abstract public Product copy() throws CloneNotSupportedException;

}
