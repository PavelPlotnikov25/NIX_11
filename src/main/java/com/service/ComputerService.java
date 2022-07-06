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
    private static final Random RANDOM = new Random();
    private static final ComputerRepository REPOSITORY = new ComputerRepository();
    private static final Logger logger = LoggerFactory.getLogger(ComputerService.class);

    public void createAndSaveComputers(int count){
        List <Computer> computers = new LinkedList<>();
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
        REPOSITORY.saveAll(computers);
    }

    private ManufacturerComputer getRandomManufacturer() {
        final ManufacturerComputer[] values = ManufacturerComputer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
    public void printAll() {
        for (Computer computer : REPOSITORY.getAll()) {
            System.out.println(computer);
        }
    }
    public void update(Computer computer){
        REPOSITORY.update(computer);
    }
    public List<Computer> getAll(){
        return REPOSITORY.getAll();
    }
    public Optional<Computer> findById(String id){
        return REPOSITORY.findById(id);
    }
    public void delete(String id){
        REPOSITORY.delete(id);
    }
}
