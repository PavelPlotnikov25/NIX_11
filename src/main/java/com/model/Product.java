package com.model;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(schema = "hibernate")
public abstract class Product{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Expose
    protected String id;
    @Column
    @Expose
    protected String title;
    @Column
    @Expose
    protected int count;
    @Column
    @Expose
    protected double price;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @Expose
    protected Invoice invoice;
    @Transient
    protected ProductType type;

    public Product(String id) {
        this.id = UUID.randomUUID().toString();
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
