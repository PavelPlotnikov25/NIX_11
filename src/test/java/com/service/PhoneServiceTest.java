package com.service;

import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.repository.phone.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class PhoneServiceTest {

    private PhoneService target;
    private PhoneRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(PhoneRepository.class);
        target = new PhoneService(repository);
    }

    @Test
    void createAndSavePhonesWrongCount() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.createAndSavePhones(-1));
    }
    @Test
    void createAndSavePhonesZeroCount() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.createAndSavePhones(0));
    }

    @Test
    void createAndSavePhones() {
        target.createAndSavePhones(4);
        Mockito.verify(repository).saveAll(Mockito.anyList());
    }


    @Test
    void update() {
        Phone phone = new Phone("Title", 5, 139,"Model 11 PRO", Manufacturer.APPLE);
        target.savePhone(phone);
        phone.setTitle("UPDATED");
        target.update(phone);
        target.getAll();
        ArgumentCaptor<Phone> argumentCaptor = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).update(argumentCaptor.capture());
        Assertions.assertEquals("UPDATED", argumentCaptor.getValue().getTitle());
    }

    @Test
    void getAll() {
        target.getAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void printAll() {
        target.printAll();
        Mockito.verify(repository).getAll();
    }
    @Test
    void savePhone() {
        final Phone phone = new Phone("Title", 25, 199, "MODEL S10", Manufacturer.SAMSUNG);
        target.savePhone(phone);
        ArgumentCaptor<Phone> argumentCaptor = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argumentCaptor.capture());
        assertEquals("Title", argumentCaptor.getValue().getTitle());
    }
    @Test
    void savePhoneZeroCount() {
        final Phone phone = new Phone("Title", 0, 199, "MODEL 3310", Manufacturer.NOKIA);
        target.savePhone(phone);
        ArgumentCaptor<Phone> argumentCaptor = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals("Title", argumentCaptor.getValue().getTitle());
        Assertions.assertEquals(-1, argumentCaptor.getValue().getCount() );
    }

    @Test
    void findById() {
        final Phone phone = new Phone("Title", 1, 199, "MODEL 3310", Manufacturer.NOKIA);
        Mockito.when(repository.findById(phone.getId())).thenReturn(Optional.of(phone));
        Assertions.assertEquals(repository.findById(phone.getId()), Optional.of(phone));
    }
    @Test
    void findById_IncorrectId() {
        Mockito.when(repository.findById(anyString())).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findById(anyString()));
        Mockito.verify(repository).findById(anyString());
    }


    @Test
    void delete() {
        Phone phone1 = new Phone("Title", 1, 199, "MODEL 3310", Manufacturer.NOKIA);
        Phone phone2 = new Phone("Title", 1, 399, "MODEL 3310", Manufacturer.NOKIA);
        target.delete(phone1.getId());
        Mockito.verify(repository,Mockito.times(1)).delete(phone1.getId());
    }
}