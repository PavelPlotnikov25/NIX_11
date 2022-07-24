package com.service;

import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Manufacturer;
import com.repository.ComputerRepository;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


public class ComputerService extends ProductService<Computer>{
    private final ComputerRepository repository;
    private static ComputerService instance;

    protected ComputerService(final ComputerRepository repository){
        super(repository);
        this.repository = repository;
    }

    public static ComputerService getInstance(){
        if (instance == null){
            instance = new ComputerService(ComputerRepository.getInstance());
        }
        return instance;
    }
    @Override
    protected Computer createProduct() {
        return new Computer(
                "Title- " + RANDOM.nextInt(111),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000),
                "MODEL - " + RANDOM.nextInt(),
                getRandomManufacturer());
    }
    private ManufacturerComputer getRandomManufacturer() {
        final ManufacturerComputer[] values = ManufacturerComputer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}