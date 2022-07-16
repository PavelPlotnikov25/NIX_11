package com.service;

import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.repository.computer.ComputerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


public class ComputerService {
    static final Random RANDOM = new Random();
    private static final Logger logger = LoggerFactory.getLogger(ComputerService.class);
    private final ComputerRepository repository;

    public ComputerService(ComputerRepository repository) {
        this.repository = repository;
    }


    public void createAndSaveComputers(int count){
        List <Computer> computers = new LinkedList<>();
        if (count < 1){
            throw new IllegalArgumentException("count must be bigger than 0 ");
        }
        for (int i = 0; i < count; i++){
            computers.add(new Computer(
                    "Title- " + RANDOM.nextInt(500),
                    RANDOM.nextInt(20),
                    RANDOM.nextDouble(1000),
                    "Model- " + RANDOM.nextInt(),
                    getRandomManufacturer()
            ));
            logger.info("Save" + computers);
        }
        repository.saveAll(computers);
    }

    private ManufacturerComputer getRandomManufacturer() {
        final ManufacturerComputer[] values = ManufacturerComputer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
    public void printAll() {
        for (Computer computer : repository.getAll()) {
            System.out.println(computer);
        }
    }
    public void saveComputer(Computer computer) {
        if (computer.getCount() == 0) {
            computer.setCount(-1);
        }
        repository.save(computer);
    }
    public void update(Computer computer){
        repository.update(computer);
    }
    public List<Computer> getAll(){
        return repository.getAll();
    }
    public Optional<Computer> findById(String id){
        return repository.findById(id);
    }
    public void delete(String id){repository.delete(id);
    }
}
