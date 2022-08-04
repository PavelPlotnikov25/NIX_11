package com.service;


import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PhoneServiceTest {
    private PhoneService target;
    private PhoneRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(PhoneRepository.class);
        target = new PhoneService(repository);
    }

    @Test
    void createAndSaveProductWrongCount() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.createAndSaveProduct(-1));
    }
    @Test
    void createAndSaveProductZeroCount() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.createAndSaveProduct(0));
    }
    @Test
    void createAndSaveProduct() {
        target.createAndSaveProduct(3);
        Mockito.verify(repository).saveAll(Mockito.anyList());
    }

    @Test
    void printAll() {
        target.printAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void update() {
        Phone phone = new Phone("Title", 5, 139,"Model 11 PRO", Manufacturer.APPLE);
        target.save(phone);
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
    void getAll_byCallRealMethod() {
        when(target.getAll()).thenCallRealMethod();
        Assertions.assertThrows(NullPointerException.class, () -> target.getAll());
        Mockito.verify(repository).getAll();
    }
    @Test
    void savePhone() {
        final Phone phone = new Phone("Title", 25, 199, "MODEL FHD", Manufacturer.SONY);
        target.save(phone);
        ArgumentCaptor<Phone> argumentCaptor = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argumentCaptor.capture());
        assertEquals("Title", argumentCaptor.getValue().getTitle());
    }
    @Test
    void savePhoneZeroCount() {
        final Phone phone = new Phone("Title", 0, 199, "MODEL FHD", Manufacturer.NOKIA);
        target.save(phone);
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
    public void findById_byArgumentMatchers() {
        final Phone phone = new Phone("Title", 2, 199, "X560", Manufacturer.APPLE);
        Mockito.when(repository.findById(ArgumentMatchers.argThat(id -> {
            Assertions.assertEquals("2532153", id);
            return true;
        }))).thenReturn(Optional.of(phone));

        Optional<Phone> actualPhone = target.findById("2532153");
        Mockito.verify(repository).findById(anyString());
        Assertions.assertTrue(actualPhone.isPresent());
        Assertions.assertEquals(phone.getId(), actualPhone.get().getId());
    }
    @Test
    void delete() {
        Phone phone1 = new Phone("Title", 1, 199, "MODEL 3310", Manufacturer.SAMSUNG);
        Phone phone2 = new Phone("Title", 1, 399, "MODEL 3310", Manufacturer.XIAOMI);
        target.delete(phone1.getId());
        Mockito.verify(repository,Mockito.times(1)).delete(phone1.getId());
    }

}