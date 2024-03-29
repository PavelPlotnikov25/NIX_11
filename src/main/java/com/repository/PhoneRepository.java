package com.repository;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.model.computer.Computer;
import com.model.phone.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
@Singleton
public class PhoneRepository implements CrudRepository<Phone> {
    private final List<Phone> phones;
    private final Logger logger = LoggerFactory.getLogger(PhoneRepository.class);

    private static PhoneRepository instance;


    public PhoneRepository() {
        phones = new LinkedList<>();
    }

    public static CrudRepository<Phone> getInstance() {
        if (instance == null){
            instance = new PhoneRepository();
        }
        return instance;
    }

    @Override
    public Computer save(Phone phone) {
        if (phone == null){
            throw new IllegalArgumentException();
        }else {
        phones.add(phone);
        }
        return null;
    }

    @Override
    public void saveAll(List<Phone> phones) {
        for (Phone phone : phones) {
            save(phone);
        }
    }

    @Override
    public boolean update(Phone phone) {
        final Optional<Phone> result = findById(phone.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Phone originPhone = result.get();
        PhoneCopy.copy(phone, originPhone);
        return true;
    }

    @Override
    public boolean delete(String id) {
        return phones.removeIf(phone -> phone.getId().equals(id));
    }

    @Override
    public List<Phone> getAll() {
        if (phones.isEmpty()) {
            return Collections.emptyList();
        }
        return phones;
    }

    @Override
    public Optional<Phone> findById(String id) {
        Phone result = null;
        for (Phone phone : phones) {
            if (phone.getId().equals(id)) {
                result = phone;
            }
        }
        return Optional.ofNullable(result);
    }


    private static class PhoneCopy {
        private static void copy(final Phone from, final Phone to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
