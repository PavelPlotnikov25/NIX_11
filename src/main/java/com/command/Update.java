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

public class Update implements Command {

    private static final ProductService<Phone> PHONE_PRODUCT_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TELEVISION_PRODUCT_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Computer> COMPUTER_PRODUCT_SERVICE = ComputerService.getInstance();

    @Override
    public void execute() {
        System.out.println("What product do you want to update: ");
        final ProductType[] values = ProductType.values();
        final List<String> names = getNamesOfType(values);
        final int userInput = UserInputUtil.getUserInput(values.length, names);
        switch (values[userInput]) {
            case PHONE -> findProductToUpdate(PHONE_PRODUCT_SERVICE);
            case COMPUTER -> findProductToUpdate(COMPUTER_PRODUCT_SERVICE);
            case TELEVISION -> findProductToUpdate(TELEVISION_PRODUCT_SERVICE);

        }
    }

    private void findProductToUpdate(ProductService<? extends Product> productService){
        boolean flag = false;
        while (!flag) {
            if (productService.getAll().isEmpty()) {
                System.out.println("There are no products ");
            } else {
                System.out.println("Please, enter ID, which product you want to update");
                final String userInputID = SCANNER.next();
                final Optional<? extends Product> foundedProduct = productService.findById(userInputID);
                if (foundedProduct.isEmpty()) {
                    System.out.println("There are no products with id = " + String.valueOf(userInputID));
                } else if (userInputID.equals(foundedProduct.get().getId())) {
                    System.out.println("enter a new title ");
                    final String newTitle = SCANNER.next();
                    System.out.println("enter a new price ");
                    final double newPrice = SCANNER.nextDouble();
                    System.out.println("enter a new price ");
                    final int newCount = SCANNER.nextInt();
                    System.out.println("PRODUCT HAS BEEN UPDATED !");
                    foundedProduct.get().setCount(newCount);
                    foundedProduct.get().setPrice(newPrice);
                    foundedProduct.get().setTitle(newTitle);
                    productService.update(foundedProduct.get());
                    System.out.println(foundedProduct);
                    flag = true;
                }
            }
        }
    }

    private List<String> getNamesOfType(final ProductType[] values) {
        final List<String> names = new ArrayList<>(values.length);
        for (ProductType type : values) {
            names.add(type.name());
        }
        return names;
    }
}

