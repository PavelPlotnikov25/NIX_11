package com.service;

import com.model.Product;
import com.model.ProductType;
import com.model.computer.Computer;
import com.model.phone.Phone;
import com.model.television.Television;

import java.util.Random;

public class ProductFactory {
    private static final Random RANDOM = new Random();

    private static final ProductService<Phone> PHONE_PRODUCT_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TELEVISION_PRODUCT_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Computer> COMPUTER_PRODUCT_SERVICE = ComputerService.getInstance();

    private ProductFactory() {
    }

    public static void createAndSave(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_PRODUCT_SERVICE.createAndSaveProduct(1);
            case TELEVISION -> TELEVISION_PRODUCT_SERVICE.createAndSaveProduct(1);
            case COMPUTER -> COMPUTER_PRODUCT_SERVICE.createAndSaveProduct(1);
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }

}
