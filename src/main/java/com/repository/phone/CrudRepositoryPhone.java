package com.repository.phone;

import com.model.computer.Computer;
import com.model.phone.Phone;
import com.model.television.Television;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface CrudRepositoryPhone {
    void save(Phone phone);

    void saveAll(List<Phone> phones);

    boolean update(Phone phone);

    boolean delete(String id);

    List<Phone> getAll();

    Optional<Phone> findById(String id);
}
