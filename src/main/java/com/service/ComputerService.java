package com.service;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.model.Product;
import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.repository.CrudRepository;
import com.repository.DB.DBComputerRepository;
import com.repository.hiberante.HibernateComputerRepository;
import com.repository.mongoDB.MongoComputerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ComputerService extends ProductService<Computer>{
    private static ComputerService instance;

    @Autowired
    protected ComputerService(CrudRepository<Computer> repository){
        super(repository);
    }

    public static ComputerService getInstance(){
        if (instance == null){
            instance = new ComputerService(MongoComputerRepository.getInstance());
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
                getRandomManufacturer(),null);
    }
    private ManufacturerComputer getRandomManufacturer() {
        final ManufacturerComputer[] values = ManufacturerComputer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public List<Product> getFreeProduct(int count) {
        final List<Product> result = new ArrayList<>();
        final List<Computer> all = repository.getAll();
        for (Computer computer:all) {
            if (computer.getInvoiceId() == null && result.size() < count){
                result.add(computer);
            }
        }
        return result;
    }

    public List<Product> getAllByInvoiceId(String invoiceID){
        return repository.getAll().stream()
                .filter(television -> television.getInvoiceId().equals(invoiceID))
                .collect(Collectors.toList());
    }
}