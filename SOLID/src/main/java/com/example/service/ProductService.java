package com.example.service;

import com.example.model.NotifiableProduct;
import com.example.model.Product;
import com.example.model.ProductBundle;
import com.example.repository.IProductRepository;
import com.example.repository.ProductRepository;

import java.util.List;
import java.util.Random;

public class ProductService {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    private static ProductService instance;

    public static ProductService getInstance(){
        if (instance == null){
            instance = new ProductService();
        }
        return instance;
    }

    public Product generateRandomProduct() {
        Random random = new Random();
        if (random.nextBoolean()) {
            ProductBundle productBundle = new ProductBundle();
            productBundle.setAmount(random.nextInt(15));
            productBundle.setAvailable(random.nextBoolean());
            productBundle.setPrice(random.nextDouble());
            productBundle.setId(random.nextLong());
            productBundle.setTitle(random.nextFloat() + "" + random.nextDouble());
            return productBundle;
        } else {
            NotifiableProduct product = new NotifiableProduct();
            product.setId(random.nextLong());
            product.setTitle(random.nextFloat() + "" + random.nextDouble());
            product.setAvailable(random.nextBoolean());
            product.setChannel(random.nextBoolean() + "" + random.nextDouble());
            product.setPrice(random.nextDouble());
            return product;
        }
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }
}
