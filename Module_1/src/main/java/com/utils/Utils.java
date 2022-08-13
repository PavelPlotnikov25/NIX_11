package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {
//    public static String getProductsFromFile(String fileName) throws IOException {
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        InputStream inputStream = loader.getResourceAsStream(fileName);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder stringFromFile = new StringBuilder();
//        String string = null;
//        while (true) {
//            string = reader.readLine();
//            if (string == null) {
//                break;
//            } else {
//                stringFromFile.append(string);
//                stringFromFile.append(System.lineSeparator());
//            }
//        }
//        return stringFromFile.toString();
//    }

    public static List<String> getListString(String fileName) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> listFromFile = new ArrayList();
        String string = null;
        while (true) {
            string = reader.readLine();
            if (string == null) {
                break;
            } else {
                listFromFile.add(string);
            }
        }
        return listFromFile;
    }

}
