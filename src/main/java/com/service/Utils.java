package com.service;

import java.io.*;


public class Utils {
    public static String getStringFromFile(String fileName) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringFromFile = new StringBuilder();
        String string = null;
        while (true) {
            string = bufferedReader.readLine();
            if (string == null) {
                break;
            } else {
                stringFromFile.append(string);
                stringFromFile.append(System.lineSeparator());
            }
        }
        return stringFromFile.toString();
    }
}
