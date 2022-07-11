package com.service;

import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.repository.computer.ComputerRepository;
import com.repository.phone.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ComputerServiceTest {
    private ComputerService target;
    private ComputerRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ComputerRepository.class);
        target = new ComputerService(repository);
    }

    @Test
    void createAndSaveComputersWrongCount() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.createAndSaveComputers(-1));
    }
    @Test
    void createAndSaveComputersZeroCount() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.createAndSaveComputers(0));
    }
    @Test
    void createAndSaveComputers() {
        target.createAndSaveComputers(2);
        Mockito.verify(repository).saveAll(Mockito.anyList());
    }


    @Test
    void printAll() {
        target.printAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void update() {
        Computer computer  = new Computer("Title", 5, 139,"Model X", ManufacturerComputer.ASUS);
        target.saveComputer(computer);
        computer.setTitle("UPDATED");
        target.update(computer);
        target.getAll();
        ArgumentCaptor<Computer> argumentCaptor = ArgumentCaptor.forClass(Computer.class);
        Mockito.verify(repository).update(argumentCaptor.capture());
        Assertions.assertEquals("UPDATED", argumentCaptor.getValue().getTitle());

    }

    @Test
    void getAll() {
        target.getAll();
        Mockito.verify(repository).getAll();
    }
    @Test
    void getAll_byCallRealMethod() {
        when(target.getAll()).thenCallRealMethod();
        Assertions.assertThrows(NullPointerException.class, () -> target.getAll());
        Mockito.verify(repository).getAll();
    }

    @Test
    void saveComputer() {
        final Computer computer = new Computer("Title", 25, 199, "MODEL X", ManufacturerComputer.ASUS);
        target.saveComputer(computer);
        ArgumentCaptor<Computer> argumentCaptor = ArgumentCaptor.forClass(Computer.class);
        Mockito.verify(repository).save(argumentCaptor.capture());
        assertEquals("Title", argumentCaptor.getValue().getTitle());
    }
    @Test
    void saveComputerZeroCount() {
        final Computer computer = new Computer("Title", 0, 199, "MODEL X", ManufacturerComputer.ASUS);
        target.saveComputer(computer);
        ArgumentCaptor<Computer> argumentCaptor = ArgumentCaptor.forClass(Computer.class);
        Mockito.verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals("Title", argumentCaptor.getValue().getTitle());
        Assertions.assertEquals(-1, argumentCaptor.getValue().getCount() );
    }


    @Test
    void findById() {
        final Computer computer = new Computer("Title", 1, 199, "MODEL 3310", ManufacturerComputer.ASUS);
        Mockito.when(repository.findById(computer.getId())).thenReturn(Optional.of(computer));
        Assertions.assertEquals(repository.findById(computer.getId()), Optional.of(computer));
    }
    @Test
    void findById_IncorrectId() {
        Mockito.when(repository.findById(anyString())).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findById(anyString()));
        Mockito.verify(repository).findById(anyString());
    }

    @Test
    public void findById_byArgumentMatchers() {
        final Computer computer = new Computer("Title", 2, 199, "X560", ManufacturerComputer.APPLE);
        when(repository.findById(ArgumentMatchers.argThat(id -> {
            assertEquals("2532153", id);
            return true;
        }))).thenReturn(Optional.of(computer));

        Optional<Computer> actualComputer = target.findById("2532153");
        Mockito.verify(repository).findById(anyString());
        Assertions.assertTrue(actualComputer.isPresent());
        Assertions.assertEquals(computer.getId(), actualComputer.get().getId());
    }
    @Test
    void delete() {
        Computer computer1 = new Computer("Title", 1, 199, "MODEL 3310", ManufacturerComputer.ASUS);
        Computer computer2 = new Computer("Title", 1, 99, "MODEL 3310", ManufacturerComputer.ACER);
        target.delete(computer2.getId());
        Mockito.verify(repository,Mockito.times(1)).delete(computer2.getId());
    }
}