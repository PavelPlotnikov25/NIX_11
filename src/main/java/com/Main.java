package com;
import com.command.*;
import com.model.Product;
import com.model.ProductType;
import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.repository.PhoneRepository;
import com.service.PhoneService;
import com.service.ProductService;

import java.util.*;


public class Main {

    private static final ProductService<Phone> PHONE_PRODUCT_SERVICE = PhoneService.getInstance();
    private static final PhoneService PHONE_SERVICE = PhoneService.getInstance();


    public static void main(String[] args) {
        Phone phone = new Phone("title for test", 10, 125, "Model for test ", Manufacturer.APPLE);
        PHONE_PRODUCT_SERVICE.createAndSaveProduct(10);
        //Creating a price statistics
        System.out.println(PHONE_PRODUCT_SERVICE.priceStatistics());
       //Checking Predicate
        PHONE_PRODUCT_SERVICE.printProductHigherThatExpectedPrice(10);
        // Sum of products by Reduce
        System.out.println(PHONE_PRODUCT_SERVICE.sumOfProductsByReduce());
        //Product to Map
        System.out.println(PHONE_PRODUCT_SERVICE.sortProductsToMap());
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("productType", phone.getProductType());
        productMap.put("title", phone.getTitle());
        productMap.put("price", phone.getPrice());
        //productMap.put("model", phone.getModel());
        productMap.put("count", phone.getCount());
        productMap.put("manufacturer", phone.getManufacturer());
        System.out.println(PHONE_PRODUCT_SERVICE.createProductFromMap(productMap));

        //Checking Details

        List<String> details = new ArrayList<>();
        details.add("CPU");
        details.add("Display");
        details.add("Stylus");
        Phone testPhone = new Phone("Title", 1, 100, ProductType.PHONE, "MODEL", Manufacturer.APPLE, details);
        PHONE_PRODUCT_SERVICE.save(testPhone);
        System.out.println(PHONE_SERVICE.checkDetails("CPU"));
        System.out.println(PHONE_SERVICE.checkDetails("Battery"));






//        boolean exit = false;
//        final Commands[] values = Commands.values();
//        final List<String> names = UserInputUtil.getNamesOfType(values);
//        do {
//            int commandIndex = UserInputUtil.getUserInput(values.length, names);
//            switch (values[commandIndex]) {
//                case CREATE -> new Create().execute();
//                case PRINT -> new Print().execute();
//                case UPDATE -> new Update().execute();
//                case DELETE -> new Delete().execute();
//                case EXIT -> exit = true;
//            }
//
//        } while (!exit);
//    }

    }
}