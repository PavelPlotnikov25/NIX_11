package com.command;

import lombok.Getter;

@Getter
public enum Commands {
    CREATE("Create product ", new Create()),
    PRINT("Print products ", new Print()),
    UPDATE("Update products", new Update()),
    DELETE("Delete product", new Delete()),
    EXIT("Exit the application", null);


    private final String name;
    private final Command command;

    Commands(String name, Command command) {
        this.name = name;
        this.command = command;
    }
}
