package com;

import com.model.computer.Computer;
import com.model.television.Television;
import com.repository.ComputerRepository;
import com.repository.TelevisionRepository;
import com.service.*;

import java.util.Random;


public class Main {
    private static final ComputerRepository repository = new ComputerRepository();
    private static final TelevisionRepository repository1 = new TelevisionRepository();
    private static final ProductService<Television> TELEVISION_SERVICE = new TelevisionService(repository1);
    private static final ProductService<Computer> COMPUTER_SERVICE = new ComputerService(repository);
    private static final Random random = new Random();
    private static final DiscountService<Computer> DISCOUNT_SERVICE = new DiscountService<>();
    private static final DiscountService<Television> DISCOUNT_SERVICE1 = new DiscountService<>();


    public static void main(String[] args) {
       TELEVISION_SERVICE.createAndSaveProduct(1);
       TELEVISION_SERVICE.printAll();
       DISCOUNT_SERVICE1.getDiscount(repository1.getAll().get(0));
       TELEVISION_SERVICE.printAll();
       DISCOUNT_SERVICE1.increaseCountOfProduct(10);
       TELEVISION_SERVICE.printAll();

    }
}