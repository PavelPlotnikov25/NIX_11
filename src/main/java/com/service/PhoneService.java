package com.service;

import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.model.television.ManufacturerTelevision;
import com.repository.CrudRepository;

public class PhoneService extends ProductService<Phone> {
    public PhoneService(CrudRepository<Phone> repository){super(repository);}

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
}
