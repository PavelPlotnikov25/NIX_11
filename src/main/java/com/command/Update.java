package com.command;


import com.model.Product;
import com.model.ProductType;
import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.model.television.ManufacturerTelevision;
import com.model.television.Television;
import com.service.ComputerService;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.TelevisionService;
import org.apache.commons.lang3.EnumUtils;

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
            case PHONE -> updatePhone(PHONE_PRODUCT_SERVICE);
            case COMPUTER -> updateComputer(COMPUTER_PRODUCT_SERVICE);
            case TELEVISION -> updateTelevision(TELEVISION_PRODUCT_SERVICE);

        }
    }

    private void updateTelevision(ProductService<Television> televisionProductService) {
        System.out.println("Please, enter ID, which television you want to update");
        final String userInputID = SCANNER.next();
        final Optional<Television> foundedTelevision = televisionProductService.findById(userInputID);
        if (foundedTelevision.isEmpty()) {
            System.out.println("There are no products with id = " + userInputID);
        } else if (userInputID.equals(foundedTelevision.get().getId())) {
            System.out.println("enter a new title ");
            final String newTitle = SCANNER.next();
            System.out.println("enter a new price ");
            final double newPrice = SCANNER.nextDouble();
            System.out.println("enter a new count ");
            final int newCount = SCANNER.nextInt();
            System.out.println("enter a new Manufacturer");
            final String newManufacturer = SCANNER.next();
            System.out.println("enter a new Model");
            final String newModel = SCANNER.next();
            System.out.println("enter a new diagonal");
            final int newDiagonal = SCANNER.nextInt();
            System.out.println("PRODUCT HAS BEEN UPDATED !");
            foundedTelevision.get().setCount(newCount);
            foundedTelevision.get().setPrice(newPrice);
            foundedTelevision.get().setTitle(newTitle);
            foundedTelevision.get().setModel(newModel);
            foundedTelevision.get().setManufacturer(EnumUtils.getEnumIgnoreCase(ManufacturerTelevision.class, newManufacturer));
            foundedTelevision.get().setDiagonal(newDiagonal);
            televisionProductService.update(foundedTelevision.get());
        }
    }

    private void updateComputer(ProductService<Computer> computerProductService) {
        System.out.println("Please, enter ID, which computer you want to update");
        final String userInputID = SCANNER.next();
        final Optional<Computer> foundedComputer = computerProductService.findById(userInputID);
        if (foundedComputer.isEmpty()) {
            System.out.println("There are no products with id = " + userInputID);
        } else if (userInputID.equals(foundedComputer.get().getId())) {
            System.out.println("enter a new title ");
            final String newTitle = SCANNER.next();
            System.out.println("enter a new price ");
            final double newPrice = SCANNER.nextDouble();
            System.out.println("enter a new count ");
            final int newCount = SCANNER.nextInt();
            System.out.println("enter a new Manufacturer");
            String newManufacturer = SCANNER.next();
            System.out.println("enter a new Model");
            String newModel = SCANNER.next();
            System.out.println("PRODUCT HAS BEEN UPDATED !");
            foundedComputer.get().setCount(newCount);
            foundedComputer.get().setPrice(newPrice);
            foundedComputer.get().setTitle(newTitle);
            foundedComputer.get().setModel(newModel);
            foundedComputer.get().setManufacturer(EnumUtils.getEnumIgnoreCase(ManufacturerComputer.class, newManufacturer));
            computerProductService.update(foundedComputer.get());
        }
    }

    private void updatePhone(ProductService<Phone> phoneProductService) {
        System.out.println("Please, enter ID, which phone you want to update");
        final String userInputID = SCANNER.next();
        final Optional<Phone> foundedPhone = phoneProductService.findById(userInputID);
        if (foundedPhone.isEmpty()) {
            System.out.println("There are no products with id = " + userInputID);
        } else if (userInputID.equals(foundedPhone.get().getId())) {
            System.out.println("enter a new title ");
            final String newTitle = SCANNER.next();
            System.out.println("enter a new price ");
            final double newPrice = SCANNER.nextDouble();
            System.out.println("enter a new count ");
            final int newCount = SCANNER.nextInt();
            System.out.println("enter a new Manufacturer");
            String newManufacturer = SCANNER.next();
            System.out.println("enter a new Model");
            String newModel = SCANNER.next();
            System.out.println("PRODUCT HAS BEEN UPDATED !");
            foundedPhone.get().setCount(newCount);
            foundedPhone.get().setPrice(newPrice);
            foundedPhone.get().setTitle(newTitle);
            foundedPhone.get().setModel(newModel);
            foundedPhone.get().setManufacturer(EnumUtils.getEnumIgnoreCase(Manufacturer.class, newManufacturer));
            phoneProductService.update(foundedPhone.get());
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

