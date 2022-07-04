package com.service;

import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.repository.phone.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PhoneService {
    private static final Random RANDOM = new Random();
    private static final PhoneRepository REPOSITORY = new PhoneRepository();
    private static final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    public void createAndSavePhones(int count) {
        List<Phone> phones = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            phones.add(new Phone(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            ));
            logger.info("SAVE " + phones);
        }

        REPOSITORY.saveAll(phones);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Phone phone : REPOSITORY.getAll()) {
            System.out.println(phone);
        }
    }
    public void update(Phone phone){
        REPOSITORY.update(phone);
    }
    public List<Phone> getAll(){
        return REPOSITORY.getAll();
    }
    public Optional<Phone> findById(String id){
        return REPOSITORY.findById(id);
    }
    public void delete(String id){
        REPOSITORY.delete(id);
    }
}
