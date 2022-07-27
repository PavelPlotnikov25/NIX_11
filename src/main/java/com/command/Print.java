package com.command;

import com.model.ProductType;
import com.model.computer.Computer;
import com.model.phone.Phone;
import com.model.television.Television;
import com.service.ComputerService;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.TelevisionService;
import jdk.jshell.execution.Util;

import javax.swing.text.Utilities;
import java.util.ArrayList;
import java.util.List;


public class Print implements Command {

    private static final ProductService<Phone> PHONE_PRODUCT_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TELEVISION_PRODUCT_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Computer> COMPUTER_PRODUCT_SERVICE = ComputerService.getInstance();

    @Override
    public void execute() {
        System.out.println("What do you want to print:");
        final ProductType[] values = ProductType.values();
        final List<String> names = getNamesOfType(values);
        final int userInput = UserInputUtil.getUserInput(values.length, names);
        switch (values[userInput]) {
            case PHONE -> PHONE_PRODUCT_SERVICE.printAll();
            case COMPUTER -> COMPUTER_PRODUCT_SERVICE.printAll();
            case TELEVISION -> TELEVISION_PRODUCT_SERVICE.printAll();
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
