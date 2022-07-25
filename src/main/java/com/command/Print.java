package com.command;

import com.model.ProductType;
import com.model.computer.Computer;
import com.model.phone.Phone;
import com.model.television.Television;
import com.service.ComputerService;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.TelevisionService;


public class Print implements Command{

    private static final ProductService<Phone> PHONE_PRODUCT_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TELEVISION_PRODUCT_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Computer> COMPUTER_PRODUCT_SERVICE = ComputerService.getInstance();

    @Override
    public void execute() {
        System.out.println("What do you want to print:");
        final ProductType[] values = ProductType.values();
        int userType = -1;
        do {
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%d) %s%n", i, values[i].name());
            }
            int input = SCANNER.nextInt();
            if (input >= 0 && input < values.length) {
                userType = input;
            }
        } while (userType == -1);

        switch (values[userType]) {
            case PHONE -> PHONE_PRODUCT_SERVICE.printAll();
            case TELEVISION -> TELEVISION_PRODUCT_SERVICE.printAll();
            case COMPUTER -> COMPUTER_PRODUCT_SERVICE.printAll();
            default -> {
                throw new IllegalArgumentException("Unknown ProductType " + values[userType]);
            }
        }
    }
}
