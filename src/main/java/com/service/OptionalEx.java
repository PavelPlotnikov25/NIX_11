package com.service;


import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.repository.ComputerRepository;
import java.util.Optional;
import java.util.Random;

public class OptionalEx {

    Random random = new Random();
    private final ComputerRepository repository;

    public OptionalEx(ComputerRepository repository){this.repository = repository;}

    public void deleteIfPresent(String id ){
        final Optional<Computer> computerOptional = repository.findById(id);
        computerOptional.ifPresent(computer -> repository.delete(computer.getId()));
    }

    public void findOrCreateDefault(String id){
        Computer computer;
        repository.findById(id).
      orElse(repository.save
              (new Computer(" ", 0, 0, "Default Model", ManufacturerComputer.APPLE)));
    }
    public Computer findByIdOrCreateNewComputer(String id){
        return repository.findById(id).orElseGet(this::createAndSaveNewComputer);
    }
    private Computer createAndSaveNewComputer(){
        Computer computer = new Computer("Title",
                random.nextInt(500),
                random.nextDouble(500),
                "MODEL",
                 ManufacturerComputer.APPLE);
        repository.save(computer);
        return computer;
    }
    public String mapComputer(String id){
        return repository.findById(id)
                .map(Computer::toString)
                .orElse("Computer don't found");
    }
    public void updateOrSave(String id){
        repository.findById(id)
                .ifPresentOrElse(computer -> computer.setTitle("Updated"),
                () -> {
                    Computer computer = createAndSaveNewComputer();
                });
    }
    public void deleteAppleComputer(String id){
       repository.findById(id)
               .filter(computer1 -> computer1.getManufacturer().equals(ManufacturerComputer.APPLE))
               .ifPresent(
                       computer -> repository.delete(computer.getId()));
    }
    public void findThrow(String id){
        repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Computer not found"));
    }

}
