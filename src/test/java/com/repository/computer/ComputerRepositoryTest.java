package com.repository.computer;

import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.repository.ComputerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ComputerRepositoryTest {
    private ComputerRepository target;
    private Computer computer;

    @BeforeEach
    void setUp() {
        final Random random = new Random();
        target = new ComputerRepository();
        computer = new Computer(
                "Title- " + random.nextInt(500),
                random.nextInt(20),
                random.nextDouble(1000),
                "Model- " + random.nextInt(),
                ManufacturerComputer.ASUS
        );
    }

    @Test
    void save() {
        target.save(computer);
        List<Computer> computers = target.getAll();
        Assertions.assertEquals(1, computers.size());
        Assertions.assertEquals(computers.get(0).getId(),computer.getId());
    }
    @Test
    void saveNull() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.save(null));
        target.save(computer);
    }

    @Test
    void saveAll() {
        target.saveAll(Collections.singletonList(computer));
        List<Computer> computers = target.getAll();
        Assertions.assertEquals(1, computers.size());
        Assertions.assertEquals(computers.get(0).getId(),computer.getId());
    }
    @Test
    void saveAll_singleComputer() {
        target.saveAll(Collections.singletonList(computer));
        List<Computer> computers = target.getAll();
        Assertions.assertEquals(1, computers.size());
        Assertions.assertEquals(computers.get(0).getId(),computer.getId());
    }

    @Test
    void saveAll_noComputer() {
        target.saveAll(Collections.emptyList());
        List<Computer> computers = target.getAll();
        Assertions.assertEquals(0, computers.size());
    }
    @Test
    void saveAll_3Computers() {
        target.saveAll(List.of(computer,computer,computer));
        List<Computer> computers = target.getAll();
        Assertions.assertEquals(3, computers.size());
        Assertions.assertEquals(computers.get(0).getId(),computer.getId());
        Assertions.assertEquals(computers.get(1).getId(),computer.getId());
        Assertions.assertEquals(computers.get(2).getId(),computer.getId());
    }
    @Test
    void saveAll_NullComputers() {
        List <Computer> computers = new ArrayList<>();
        computers.add(computer);
        computers.add(computer);
        computers.add(null);
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.saveAll(computers));

    }

    @Test
    void update() {
        final double newPrice = 99999999;
        target.save(computer);
        computer.setPrice(newPrice);
        final boolean result = target.update(computer);
        Assertions.assertTrue(result);
        final List <Computer> actualComputers = target.getAll();
        Assertions.assertEquals(1, actualComputers.size());
        Assertions.assertEquals(newPrice,actualComputers.get(0).getPrice());
        Assertions.assertEquals(computer.getId(), actualComputers.get(0).getId());
    }
    @Test
    void updateNoComputer() {
        target.save(computer);
        final Computer noComputer = new Computer("Title- ", 25, 199, "MODEL X", ManufacturerComputer.ASUS);
        final boolean result = target.update(noComputer);
        Assertions.assertFalse(result);
        final List<Computer> actualComputers = target.getAll();
        Assertions.assertEquals(1,actualComputers.size());
        Assertions.assertEquals(computer.getId(), actualComputers.get(0).getId());

    }

    @Test
    void delete() {
        target.save(computer);
        final boolean result = target.delete(computer.getId());
        Assertions.assertTrue(result);
        final List<Computer> actualComputers = target.getAll();
        Assertions.assertEquals(0,actualComputers.size());
    }

    @Test
    void deleteNoComputers() {
        target.save(computer);
        final Computer noComputer = new Computer("Title- ", 25, 199, "MODEL X", ManufacturerComputer.ASUS);
        final boolean result = target.delete(noComputer.getId());
        Assertions.assertFalse(result);
        final List<Computer> actualComputers = target.getAll();
        Assertions.assertEquals(1,actualComputers.size());
    }


    @Test
    void getAll() {
      target.save(computer);
        List<Computer> computers = target.getAll();
        Assertions.assertEquals(1,computers.size());
    }
    @Test
    void getAll_noComps() {
        List<Computer> computers = target.getAll();
        Assertions.assertEquals(0,computers.size());
    }


    @Test
    void findById() {
        target.save(computer);
        Optional<Computer> optionalComputer = target.findById(computer.getId());
        Assertions.assertTrue(optionalComputer.isPresent());
       final Computer actualComputer = optionalComputer.get();
       Assertions.assertEquals(computer.getId(), actualComputer.getId());
    }

}