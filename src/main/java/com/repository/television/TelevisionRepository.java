package com.repository.television;

import com.model.phone.Phone;
import com.model.television.Television;
import com.repository.phone.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TelevisionRepository implements CrudRepositoryTelevision {
    private final List<Television> televisions;
    private final Logger logger = LoggerFactory.getLogger(TelevisionRepository.class);

    public TelevisionRepository() {televisions = new LinkedList<>();}

    @Override
    public void save(Television television) {televisions.add(television);}

    @Override
    public void saveAll(List<Television> televisions) {
        for (Television television: televisions) {
            save(television);
        }

    }

    @Override
    public boolean update(Television television) {
        final Optional<Television> result = findById(television.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Television originTelevision = result.get();
        TelevisionRepository.TelevisionCopy.copy(television, originTelevision);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Television> iterator = televisions.iterator();
        while (iterator.hasNext()) {
            final Television television = iterator.next();
            if (television.getId().equals(id)) {
                iterator.remove();
                logger.info("DELETE " + television);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Television> getAll() {
        if (televisions.isEmpty()) {
            return Collections.emptyList();
        }
        return televisions;
    }

    @Override
    public Optional<Television> findById(String id) {

        Television result = null;
        for (Television television : televisions) {
            if (television.getId().equals(id)) {
                result = television;
            }
        }
        return Optional.ofNullable(result);
    }
    private static class TelevisionCopy {
        private static void copy(final Television from, final Television to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
