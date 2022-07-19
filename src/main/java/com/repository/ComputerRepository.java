package com.repository;

import com.model.computer.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ComputerRepository implements CrudRepository<Computer> {
    private final List<Computer> computers;
    private final Logger logger = LoggerFactory.getLogger(ComputerRepository.class);

    public ComputerRepository() {
        computers = new LinkedList<>();
    }

    @Override
    public Computer save(Computer computer) {
        if (computer == null) {
            throw new IllegalArgumentException("Cannot save a null computer");
        } else {
            computers.add(computer);
        }
        return computer;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Computer> iterator = computers.iterator();
        while (iterator.hasNext()) {
            final Computer computer = iterator.next();
            if (computer.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveAll(List<Computer> computers) {
        for (Computer computer : computers) {
            save(computer);
        }
    }

    @Override
    public boolean update(Computer computer) {
        final Optional<Computer> result = findById(computer.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Computer originPhone = result.get();
        ComputerCopy.copy(computer, originPhone);
        return true;
    }

    @Override
    public List<Computer> getAll() {
        if (computers.isEmpty()) {
            return Collections.emptyList();
        }
        return computers;
    }

    @Override
    public Optional<Computer> findById(String id) {
        Computer result = null;
        for (Computer computer : computers) {
            if (computer.getId().equals(id)) {
                result = computer;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class ComputerCopy {
        private static void copy(final Computer from, final Computer to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
