package com.repository;


import com.model.computer.Computer;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Computer save(T product);

    void saveAll(List<T> product);

    boolean update(T product);

    boolean delete(String id);

    List<T> getAll();

    Optional<T> findById(String id);
}
