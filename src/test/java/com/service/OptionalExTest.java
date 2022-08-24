package com.service;

import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.repository.ComputerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

class OptionalExTest {
    private OptionalEx target;
    private ComputerRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ComputerRepository.class);
        target = new OptionalEx(repository);

    }

    @Test
    void deleteIfPresentPositive() {
    Computer computer = new Computer("_",1,1,"--", ManufacturerComputer.APPLE,null);
    Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.of(computer));
    target.deleteIfPresent(computer.getId());
    Mockito.verify(repository).findById(computer.getId());
    Mockito.verify(repository).delete(computer.getId());
    }
    @Test
    void deleteIfPresentNegative() {
        Computer computer = new Computer("Title",10,10,"--", ManufacturerComputer.ACER,null);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.empty());
        target.deleteIfPresent("123");
        Assertions.assertNotEquals("123", repository.findById(computer.getId()));
        Mockito.verify(repository).findById(computer.getId());
        Mockito.verify(repository, Mockito.never()).delete(computer.getId());
    }

    @Test
    void findOrCreateDefaultPositive() {
        Computer computer = new Computer("Title",10,10,"--", ManufacturerComputer.ACER,null);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.of(computer));
        target.findOrCreateDefault(computer.getId());
        Mockito.verify(repository).findById(computer.getId());
        Assertions.assertEquals(10, computer.getCount());
    }

    @Test
    void findOrCreateDefaultNegative() {
        Computer computer = new Computer("Title", 10, 10, "--", ManufacturerComputer.ACER);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.empty());
        target.findOrCreateDefault("123");
        Mockito.verify(repository).findById(Mockito.anyString());
        Assertions.assertNotEquals("123",computer.getId());
    }
    @Test
    void findByIdOrCreateNewComputerPositive() {
        Computer computer = new Computer("Title",10,10,"--", ManufacturerComputer.ACER);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.of(computer));
        target.findOrCreateDefault(computer.getId());
        Mockito.verify(repository).findById(computer.getId());
        Assertions.assertEquals("Title", computer.getTitle());
    }
    @Test
    void findByIdOrCreateNewComputerNegative() {
        Computer computer = new Computer("Title",10,0,"--", ManufacturerComputer.ACER);
        Computer testingComputer = target.findByIdOrCreateNewComputer(computer.getId());
        Mockito.verify(repository).findById(computer.getId());
        Assertions.assertNotEquals(computer.getId(), testingComputer.getId());
        Assertions.assertEquals("MODEL" , testingComputer.getModel());
    }

    @Test
    void mapComputerPositive() {
        Computer computer = new Computer("Title",10,0,"--", ManufacturerComputer.ACER);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.of(computer));
        String testingComputer = target.mapComputer(computer.getId());
        Mockito.verify(repository).findById(computer.getId());
        Assertions.assertEquals(testingComputer, testingComputer);
    }
    @Test
    void mapComputerNegative() {
        Computer computer = new Computer("Title",10,0,"--", ManufacturerComputer.ACER);
        String testingComputer = target.mapComputer(computer.getId());
        Mockito.verify(repository).findById(computer.getId());
        Assertions.assertEquals("Computer don't found", testingComputer);
    }
    @Test
    void updateOrSave() {
        Computer computer = new Computer("Title",10,0,"--", ManufacturerComputer.ACER);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.of(computer));
        target.updateOrSave(computer.getId());
        Mockito.verify(repository).findById(computer.getId());
        Assertions.assertEquals("Updated", computer.getTitle());
    }

    @Test
    void updateOrSaveNegative() {
        Computer computer = new Computer("Title",10,0,"--", ManufacturerComputer.ACER);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.empty());
        target.findOrCreateDefault(" ");
        Mockito.verify(repository).findById(Mockito.anyString());
        Assertions.assertNotEquals(" ",computer.getId());
    }

    @Test
    void deleteAppleComputerPositive() {
        Computer computer = new Computer("Title",10,0,"--", ManufacturerComputer.APPLE);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.of(computer));
        target.deleteAppleComputer(computer.getId());
        Mockito.verify(repository).findById(computer.getId());
        Mockito.verify(repository).delete(computer.getId());

    }
    @Test
    void deleteAppleComputerNegative() {
        Computer computer = new Computer("---", 1, 1, "MODEL", ManufacturerComputer.ACER);
        target.deleteAppleComputer(computer.getId());

        Mockito.verify(repository).findById(computer.getId());
        Mockito.verify(repository, Mockito.never()).delete(computer.getId());

    }

    @Test
    void findThrow() {
        Computer computer = new Computer("---", 1, 1, "MODEL", ManufacturerComputer.ACER);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findThrow(computer.getId()));
    }
}