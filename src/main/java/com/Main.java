package com;

import com.model.computer.Computer;
import com.model.television.Television;
import com.repository.computer.ComputerRepository;
import com.repository.phone.PhoneRepository;
import com.repository.television.TelevisionRepository;
import com.service.ComputerService;
import com.service.OptionalEx;
import com.service.PhoneService;
import com.service.TelevisionService;


public class Main {
    private static final PhoneRepository anotherRepository = new PhoneRepository();
    private static final ComputerRepository repository = new ComputerRepository();
    private static final ComputerService COMPUTER_SERVICE = new ComputerService(repository);
    private static final OptionalEx OPTIONAL_EX = new OptionalEx(repository);

    public static void main(String[] args) {
        COMPUTER_SERVICE.createAndSaveComputers(4);
        optionalEx();
        COMPUTER_SERVICE.printAll();


    }
    public static void optionalEx(){
        final String id = COMPUTER_SERVICE.getAll().get(0).getId();
        COMPUTER_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        OPTIONAL_EX.deleteIfPresent(id);
        COMPUTER_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        OPTIONAL_EX.findOrCreateDefault(id);
        COMPUTER_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        OPTIONAL_EX.findByIdOrCreateNewComputer(id);
        COMPUTER_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        OPTIONAL_EX.mapComputer("231141");
        COMPUTER_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        final String id2 = COMPUTER_SERVICE.getAll().get(3).getId();
        OPTIONAL_EX.updateOrSave(id2);
        COMPUTER_SERVICE.printAll();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        OPTIONAL_EX.deleteAppleComputer(id);
        COMPUTER_SERVICE.printAll();
        OPTIONAL_EX.findThrow(id2);



    }


}
