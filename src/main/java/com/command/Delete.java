package com.command;

import com.model.Product;
import com.model.ProductType;
import com.model.computer.Computer;
import com.model.phone.Phone;
import com.model.television.Television;
import com.service.ComputerService;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.TelevisionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Delete implements Command {

    private static final ProductService<Phone> PHONE_PRODUCT_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TELEVISION_PRODUCT_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Computer> COMPUTER_PRODUCT_SERVICE = ComputerService.getInstance();


    @Override
    public void execute() {
        System.out.println("What product do you want to delete?");
        final ProductType[] values = ProductType.values();
        final List<String> names = getNamesOfType(values);
        final int userInput = UserInputUtil.getUserInput(values.length, names);
        switch (values[userInput]) {
            case PHONE -> deleteProduct(PHONE_PRODUCT_SERVICE);
            case COMPUTER -> deleteProduct(COMPUTER_PRODUCT_SERVICE);
            case TELEVISION -> deleteProduct(TELEVISION_PRODUCT_SERVICE);

        }
    }

    private void deleteProduct(ProductService<? extends Product> productService) {
        boolean flag = false;
        while (!flag) {
            if (productService.getAll().isEmpty()) {
                System.out.println("There are no products ");
            } else {
                System.out.println("Please, enter product ID you want to delete");
                final String userInputID = SCANNER.next();
                final Optional<? extends Product> foundedProduct = productService.findById(userInputID);
                if (foundedProduct.isPresent() && userInputID.equals(foundedProduct.get().getId())) {
                    productService.delete(foundedProduct.get().getId());
                    System.out.println("PRODUCT HAS BEEN DELETED");
                    flag = true;
                } else {
                    System.out.println("There are no products with id = " + String.valueOf(userInputID));
                }
            }
        }
    }

        private List<String> getNamesOfType (final ProductType[] values){
            final List<String> names = new ArrayList<>(values.length);
            for (ProductType type : values) {
                names.add(type.name());
            }
            return names;
        }
}
