package com.service;

import com.model.Product;
import com.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class ProductService<T extends Product> {
    protected static final Random RANDOM = new Random();
    protected CrudRepository<T> repository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    protected ProductService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public void createAndSaveProduct(int count){
        if (count < 1 ){
            throw new IllegalArgumentException("Count must be bigger than 0");
        }
        List<T> products = new LinkedList<>();
        for (int i = 0; i < count; i++){
            final T phone = createProduct();
            products.add(phone);
            logger.info("SAVE " + phone);
        }
        repository.saveAll(products);
    }
    protected abstract  T createProduct();

    public void printAll() {
        for (T product : repository.getAll()) {
            System.out.println(product);
        }
    }
    public void update(T product){repository.update(product);}
    public List<T> getAll(){return repository.getAll();}
    public Optional<T> findById(String id){return repository.findById(id);}
    public void delete(String id) {
        repository.delete(id);
    }
    public void save(T product){
        if (product.getCount() == 0){
            product.setCount(-1);
        }
        repository.save(product);
    }
}
