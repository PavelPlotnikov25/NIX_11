package com.repository.television;

import com.model.television.ManufacturerTelevision;
import com.model.television.Television;
import com.repository.TelevisionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class TelevisionRepositoryTest {
    private TelevisionRepository target;
    private Television television;

    @BeforeEach
    void setUp() {
        final Random random = new Random();
        target = new TelevisionRepository();
        television = new Television("Title - " + random.nextInt(500),
                random.nextInt(50),
                random.nextDouble(500),
                "Model- " + random.nextInt(500),
                ManufacturerTelevision.LG,
                random.nextInt(100));
    }

    @Test
    void save() {
        target.save(television);
        List<Television> televisions = target.getAll();
        Assertions.assertEquals(1, televisions.size());
        Assertions.assertEquals(televisions.get(0).getId(),television.getId());
    }
    @Test
    void saveNull() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.save(null));
        target.save(television);
    }

    @Test
    void saveAll() {
        target.saveAll(Collections.singletonList(television));
        List<Television> televisions = target.getAll();
        Assertions.assertEquals(1, televisions.size());
        Assertions.assertEquals(televisions.get(0).getId(), television.getId());
    }

    @Test
    void saveAll_singleTelevision() {
        target.saveAll(Collections.singletonList(television));
        List<Television> televisions = target.getAll();
        Assertions.assertEquals(1, televisions.size());
        Assertions.assertEquals(televisions.get(0).getId(),television.getId());
    }
    @Test
    void saveAll_noTelevision() {
        target.saveAll(Collections.emptyList());
        List<Television> televisions = target.getAll();
        Assertions.assertEquals(0, televisions.size());
    }
    @Test
    void saveAll_3Televisions() {
        target.saveAll(List.of(television,television,television));
        List<Television> televisions = target.getAll();
        Assertions.assertEquals(3, televisions.size());
        Assertions.assertEquals(televisions.get(0).getId(),television.getId());
        Assertions.assertEquals(televisions.get(1).getId(),television.getId());
        Assertions.assertEquals(televisions.get(2).getId(),television.getId());
    }
    @Test
    void saveAll_NullTelevisions() {
        List <Television> televisions = new ArrayList<>();
        televisions.add(television);
        televisions.add(television);
        televisions.add(null);
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.saveAll(televisions));

    }
    @Test
    void update() {
        final String newTitle = "UPDATED";
        target.save(television);
        television.setTitle(newTitle);
        final boolean result = target.update(television);
        Assertions.assertTrue(result);
        final List <Television> actualTelevisions = target.getAll();
        Assertions.assertEquals(1, actualTelevisions.size());
        Assertions.assertEquals(newTitle,actualTelevisions.get(0).getTitle());
        Assertions.assertEquals(television.getId(), actualTelevisions.get(0).getId());
    }

    @Test
    void updateNoTelevision() {
        target.save(television);
        final Television noTelevision = new Television("Title- ", 25, 199, "MODEL X", ManufacturerTelevision.XIAOMI, 50);
        final boolean result = target.update(noTelevision);
        Assertions.assertFalse(result);
        final List<Television> actualTelevisions = target.getAll();
        Assertions.assertEquals(1,actualTelevisions.size());
        Assertions.assertEquals(television.getId(), actualTelevisions.get(0).getId());

    }

    @Test
    void delete() {
        target.save(television);
        final boolean result = target.delete(television.getId());
        Assertions.assertTrue(result);
        final List<Television> actualTelevisions = target.getAll();
        Assertions.assertEquals(0,actualTelevisions.size());
    }

    @Test
    void deleteNoTelevisions() {
        target.save(television);
        final Television noTelevision = new Television("Title- ", 25, 199, "MODEL", ManufacturerTelevision.SAMSUNG, 55);
        final boolean result = target.delete(noTelevision.getId());
        Assertions.assertFalse(result);
        final List<Television> actualTelevisions = target.getAll();
        Assertions.assertEquals(1,actualTelevisions.size());
    }

    @Test
    void getAll() {
        target.save(television);
        List<Television> televisions = target.getAll();
        Assertions.assertEquals(1,televisions.size());
    }
    @Test
    void getAll_noTelevisions() {
        List<Television> televisions = target.getAll();
        Assertions.assertEquals(0,televisions.size());
    }

    @Test
    void findById() {
        target.save(television);
        Optional<Television> optionalTelevision = target.findById(television.getId());
        Assertions.assertTrue(optionalTelevision.isPresent());
        final Television actualTelevision = optionalTelevision.get();
        Assertions.assertEquals(television.getId(), actualTelevision.getId());
    }
}