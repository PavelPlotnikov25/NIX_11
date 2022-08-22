package com.service;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.model.phone.Manufacturer;
import com.model.television.ManufacturerTelevision;
import com.model.television.Television;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;
import com.repository.TelevisionRepository;

@Singleton
public class TelevisionService extends ProductService<Television> {

    @Autowired
    public TelevisionService(CrudRepository <Television> repository){super(repository);}

    private static TelevisionService instance;


    public static TelevisionService getInstance(){
        if (instance == null){
            instance = new TelevisionService(TelevisionRepository.getInstance());
        }
        return instance;
    }

    @Override
    protected Television createProduct() {
        return new Television(
                "Title- " + RANDOM.nextInt(111),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000),
                "MODEL - " + RANDOM.nextInt(),
                getRandomManufacturer(),
                RANDOM.nextInt(10)
        );
    }
    private ManufacturerTelevision getRandomManufacturer() {
        final ManufacturerTelevision[] values = ManufacturerTelevision.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}
