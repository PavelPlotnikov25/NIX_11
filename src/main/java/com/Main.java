package com;

import com.model.computer.Computer;
import com.model.television.Television;
import com.repository.computer.ComputerRepository;
import com.repository.phone.PhoneRepository;
import com.repository.television.TelevisionRepository;
import com.service.ComputerService;
import com.service.PhoneService;
import com.service.TelevisionService;


public class Main {
    private static final PhoneService PHONE_SERVICE = new PhoneService(new PhoneRepository());
    private static final ComputerService COMPUTER_SERVICE = new ComputerService(new ComputerRepository());
    private static final TelevisionService TELEVISION_SERVICE = new TelevisionService(new TelevisionRepository());

    public static void main(String[] args) {
        System.out.println("~~~~~~~~~PHONES~~~~~~~~~~~~~~");
        PHONE_SERVICE.createAndSavePhones(4);
        PHONE_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~COMPUTERS~~~~~~~~~~~~~~");
        COMPUTER_SERVICE.createAndSaveComputers(3);
        COMPUTER_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~TELESIVIONS~~~~~~~~~~~~~~");
        TELEVISION_SERVICE.createAndSaveTelevisions(3);
        TELEVISION_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~UPDATING COMPUTER'S COUNT (INDEX 2) to 1.~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Computer computer = COMPUTER_SERVICE.getAll().get(2);
        computer.setCount(1);
        COMPUTER_SERVICE.update(computer);
        System.out.println("Computers updated!");
        COMPUTER_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~~~DELETING TELEVISION~~~~~~~~~~~~~~~~~~~~~~~~~");
        Television television = TELEVISION_SERVICE.getAll().get(1);
        TELEVISION_SERVICE.delete(television.getId());
        TELEVISION_SERVICE.printAll();
    }
}
