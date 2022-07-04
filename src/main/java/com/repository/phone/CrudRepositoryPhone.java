package com.repository.phone;

import com.model.phone.Phone;

import java.util.List;
import java.util.Optional;

public interface CrudRepositoryPhone {
    void save(Phone phone);

    void saveAll(List<Phone> phones);

    boolean update(Phone phone);

    boolean delete(String id);

    List<Phone> getAll();

    Optional<Phone> findById(String id);
}
