package com.model;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Expose
    private String id;
    @Column
    @Expose
    private LocalDateTime time;
    @Column
    @Expose
    private double sum;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Expose
    private List<Product> products;


    public Invoice(String id, double sum, List<Product> products, LocalDateTime time) {
        this.id = id;
        this.sum = sum;
        this.products = products;
        this.time = time;
    }
    public Invoice(String id, double sum, LocalDateTime time) {
        this.id = id;
        this.sum = sum;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", sum=" + sum +
                ", products=" + products +
                ", time=" + time +
                '}';
    }
}
