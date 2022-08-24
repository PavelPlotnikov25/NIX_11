package com.service;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.model.Product;
import com.model.computer.Computer;
import com.model.phone.Manufacturer;
import com.model.television.ManufacturerTelevision;
import com.model.television.Television;
import com.repository.CrudRepository;
import com.repository.DB.DBComputerRepository;
import com.repository.DB.DBTelevisionRepository;
import com.repository.PhoneRepository;
import com.repository.TelevisionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class TelevisionService extends ProductService<Television> {

    @Autowired
    public TelevisionService(CrudRepository <Television> repository){super(repository);}

    private static TelevisionService instance;


    public static TelevisionService getInstance(){
        if (instance == null){
            instance = new TelevisionService(DBTelevisionRepository.getInstance());
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

    public List<Product> getFreeProduct(int count) {
        final List<Product> result = new ArrayList<>();
        final List<Television> all = repository.getAll();
        for (Television television:all) {
            if (television.getInvoiceId() == null && result.size() < count){
                result.add(television);
            }
        }
        return result;
    }

    public List<Product> getAllByInvoiceId(String invoiceID){
        return repository.getAll().stream()
                .filter(computer -> computer.getInvoiceId().equals(invoiceID))
                .collect(Collectors.toList());
    }
}
