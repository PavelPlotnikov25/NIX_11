package com.service;

import com.model.phone.Phone;
import com.model.television.ManufacturerTelevision;
import com.model.television.Television;
import com.repository.television.TelevisionRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelevisionService {
    private static final Random RANDOM = new Random();
    private final TelevisionRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(TelevisionService.class);

    public TelevisionService(TelevisionRepository repository) {
        this.repository = repository;
    }

    public void createAndSaveTelevisions(int count){
        List<Television> televisions = new LinkedList<>();
        if (count < 1 ){
            throw new IllegalArgumentException("Count must be bigger than 0");
        }
        for (int i = 0; i < count; i++){
            televisions.add(new Television(
                    "Title - " + RANDOM.nextInt(500),
                    RANDOM.nextInt(50),
                    RANDOM.nextDouble(500),
                    "Model- " + RANDOM.nextInt(500),
                    getRandomManufacturer(),
                    RANDOM.nextInt(100)
            ));
            logger.info("SAVE " + televisions);
        }
        repository.saveAll(televisions);
    }

    private ManufacturerTelevision getRandomManufacturer() {
        final ManufacturerTelevision[] values = ManufacturerTelevision.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
    public void printAll() {
        for (Television television : repository.getAll()) {
            System.out.println(television);
        }
    }
    public void update(Television television){
        repository.update(television);
    }
    public List<Television> getAll(){
        return repository.getAll();
    }
    public Optional<Television> findById(String id){return repository.findById(id);}
    public void delete(String id) {
            repository.delete(id);
    }
    public void saveTelevision(Television television){
        if (television.getCount() == 0){
            television.setCount(-1);
        }
        repository.save(television);
    }
}
