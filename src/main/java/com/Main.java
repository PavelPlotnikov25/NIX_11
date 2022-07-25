package com;

import com.command.Command;
import com.command.Commands;
import com.model.computer.Computer;
import com.model.television.Television;
import com.repository.ComputerRepository;
import com.repository.TelevisionRepository;
import com.service.*;

import java.util.Random;

import static com.command.Command.SCANNER;


public class Main {

    public static void main(String[] args) {
        final Commands[] values = Commands.values();
        boolean exit;

        do {
            exit = userAction(values);
        } while (!exit);
    }
    private static boolean userAction(final Commands[] values) {
        int userCommand = -1;
        do {
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%d) %s%n", i, values[i].getName());
            }
            int input = SCANNER.nextInt();
            if (input >= 0 && input < values.length) {
                userCommand = input;
            }
        } while (userCommand == -1);
        final Command command = values[userCommand].getCommand();
        if (command == null) {
            return true;
        } else {
            command.execute();
            return false;
        }
    }
}