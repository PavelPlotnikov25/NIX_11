package com.example.service;

import com.example.model.NotifiableProduct;
import com.example.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
        private final ProductRepository productRepository = ProductRepository.getInstance();
        private static NotificationService instance;

        public static NotificationService getInstance(){
            if (instance == null){
                return new NotificationService();
            }
            return instance;
        }

    public int filterNotifiableProductsAndSendNotifications() {
        int notifications = 0;
        List<NotifiableProduct> products = productRepository.getAll().stream()
                .filter(NotifiableProduct.class::isInstance)
                .map(NotifiableProduct.class::cast)
                .toList();
        for (NotifiableProduct product : products) {
            //sending some notifications here
            notifications++;
        }
        return notifications;
    }
}
