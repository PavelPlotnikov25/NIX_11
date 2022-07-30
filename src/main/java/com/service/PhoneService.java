package com.service;

import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.model.television.ManufacturerTelevision;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;

public class PhoneService extends ProductService<Phone> {

    public PhoneService(CrudRepository<Phone> repository){super(repository);}
    private static PhoneService instance;

    private PhoneService(final PhoneRepository repository){
        super(repository);
        this.repository = repository;
    }

    public static PhoneService getInstance(){
        if (instance == null){
            instance = new PhoneService(PhoneRepository.getInstance());
        }
        return instance;
    }


    @Override
    protected Phone createProduct() {
        return new Phone(
                "Title- " + RANDOM.nextInt(111),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000),
                "MODEL - " + RANDOM.nextInt(),
                getRandomManufacturer());
    }
    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
    public boolean checkDetails(String checkedDetail){
        return repository.getAll()
                .stream()
                .flatMap(phone -> phone.getDetails()
                .stream())
                .anyMatch(detail -> detail.equals(checkedDetail));
    }
}
