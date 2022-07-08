package com.repository.phone;

import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.repository.computer.ComputerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PhoneRepositoryTest {

    private PhoneRepository target;
    private Phone phone;

    @BeforeEach
    void setUp() {
        final Random random = new Random();
        target = new PhoneRepository();
        phone = new Phone(
                "Title- " + random.nextInt(500),
                random.nextInt(20),
                random.nextDouble(1000),
                "Model- " + random.nextInt(),
                Manufacturer.NOKIA);
    }

    @Test
    void save() {
        target.save(phone);
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(1,phones.size());
        Assertions.assertEquals(phones.get(0).getId(), phone.getId());
    }
    @Test
    void saveNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
    }

    @Test
    void saveAll() {
        target.saveAll(Collections.singletonList(phone));
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(1, phones.size());
        Assertions.assertEquals(phones.get(0).getId(), phone.getId());
    }

    @Test
    void saveAllNullPhones() {
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        phones.add(null);
        phones.add(phone);
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.saveAll(phones));
    }

    @Test
    void saveAllNoPhones() {
        target.saveAll(Collections.emptyList());
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(0, phones.size());
    }

    @Test
    void saveAllSomePhones() {
        target.saveAll(List.of(phone,phone,phone));
        List<Phone> phones = target.getAll();
        Assertions.assertEquals(3, phones.size());
        Assertions.assertEquals(phones.get(0).getId(),phone.getId());
        Assertions.assertEquals(phones.get(1).getId(),phone.getId());
        Assertions.assertEquals(phones.get(2).getId(),phone.getId());
    }

    @Test
    void update() {
        final String newTitle = "UPDATED";
        target.save(phone);
        phone.setTitle(newTitle);
        final boolean result = target.update(phone);
        Assertions.assertTrue(result);
        final List<Phone> actualPhones = target.getAll();
        Assertions.assertEquals(1, actualPhones.size());
        Assertions.assertEquals(newTitle, actualPhones.get(0).getTitle());
        Assertions.assertEquals(phone.getId(), actualPhones.get(0).getId());
    }
    @Test
    void updateNoPhone() {
        target.save(phone);
        final Phone noPhone = new Phone("Title- ", 25, 199, "MODEL X", Manufacturer.SAMSUNG);
        final boolean result = target.update(noPhone);
        Assertions.assertFalse(result);
        final List<Phone> actualPhones = target.getAll();
        Assertions.assertEquals(1, actualPhones.size());
        Assertions.assertEquals(phone.getId(), actualPhones.get(0).getId());
    }

    @Test
    void delete() {
        target.save(phone);
        final boolean result = target.delete(phone.getId());
        Assertions.assertTrue(result);
        final List<Phone> actualPhones = target.getAll();
        Assertions.assertEquals(0, actualPhones.size());
    }
    @Test
    void deleteNoPhones() {
        target.save(phone);
        final Phone noPhone = new Phone("Title- ", 25, 199, "MODEL X", Manufacturer.SAMSUNG);
        final boolean result = target.delete(noPhone.getId());
        Assertions.assertFalse(result);
        final List<Phone> actualPhones = target.getAll();
        Assertions.assertEquals(1, actualPhones.size());
    }

    @Test
    void getAll() {
        target.save(phone);
        final List<Phone> phones = target.getAll();
        Assertions.assertEquals(1, phones.size());
    }
    @Test
    void getAllNoPhones() {
        final List<Phone> computers = target.getAll();
        Assertions.assertEquals(0, computers.size());
    }

    @Test
    void findById() {
        target.save(phone);
        Optional<Phone> optionalPhone = target.findById(phone.getId());
        Assertions.assertTrue(optionalPhone.isPresent());
        final Phone actualPhone = optionalPhone.get();
        Assertions.assertEquals(phone.getId(), actualPhone.getId());
    }
}