package com.repository.television;

import com.model.television.Television;

import java.util.List;
import java.util.Optional;

public interface CrudRepositoryTelevision {
    void save(Television television);

    void saveAll(List<Television> televisions);

    boolean update(Television television);

    boolean delete(String id);

    List<Television> getAll();

    Optional<Television> findById(String id);
}
