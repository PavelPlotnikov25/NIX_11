package com.repository.computer;

import com.model.computer.Computer;

import java.util.List;
import java.util.Optional;

public interface CrudRepositoryComputer {
    Computer save(Computer computer);

    void saveAll(List<Computer> computers);

    boolean update(Computer computer);

    boolean delete(String id);

    List<Computer> getAll();

    Optional<Computer> findById(String id);
}
