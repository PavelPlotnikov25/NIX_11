package com;
import com.command.*;
import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Phone;
import com.repository.PhoneRepository;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Main {


    private static final PhoneService PHONE_SERVICE = PhoneService.getInstance();


    public static void main(String[] args) {

         boolean exit = false;
        final Commands[] values = Commands.values();
        final List<String> names = UserInputUtil.getNamesOfType(values);
        do {
            int commandIndex = UserInputUtil.getUserInput(values.length, names);
            switch (values[commandIndex]) {
                case CREATE -> new Create().execute();
                case PRINT -> new Print().execute();
                case UPDATE -> new Update().execute();
                case DELETE -> new Delete().execute();
                case EXIT -> exit = true;
            }

        } while (!exit);
    }

    }
