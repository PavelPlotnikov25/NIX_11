package com;
import com.model.phone.Phone;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Main {


    private static final PhoneService PHONE_SERVICE = PhoneService.getInstance();


    public static void main(String[] args) throws IOException {
        Utils.getStringFromFile("phone.xml");
        PHONE_SERVICE.createProductFromXml("phone.xml");
        Utils.getStringFromFile("phone.json");
        PHONE_SERVICE.createProductFromJson("phone.json");
        PHONE_SERVICE.printAll();

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