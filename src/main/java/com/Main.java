package com;
import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Phone;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Main {


    private static final PhoneService PHONE_SERVICE = PhoneService.getInstance();


    public static void main(String[] args) {
        Computer.Builder builder = new Computer.Builder(1);
        builder.setCount(1000);
        builder.setModel("TEST MODEL");
        builder.setTitle("TEST TITLE");
        builder.setManufacturer(ManufacturerComputer.APPLE);
        Computer computer = builder.build();
        System.out.println(computer);



//        PHONE_SERVICE.createProductFromXml("phone.xml");
//        PHONE_SERVICE.createProductFromJson("phone.json");
//        PHONE_SERVICE.printAll();

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