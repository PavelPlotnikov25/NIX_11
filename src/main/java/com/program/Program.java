package com.program;

import com.command.*;

import java.util.List;

public class Program {

    public void run(){

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