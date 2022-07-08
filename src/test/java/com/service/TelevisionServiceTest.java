package com.service;


import com.model.television.ManufacturerTelevision;
import com.model.television.Television;
import com.repository.television.TelevisionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.invocation.RealMethod;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class TelevisionServiceTest {
    private TelevisionService target;
    private TelevisionRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TelevisionRepository.class);
        target = new TelevisionService(repository);
    }

    @Test
    void createAndSaveTelevisionsWrongCount() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.createAndSaveTelevisions(-1));
    }
    @Test
    void createAndSaveTelevisionsZeroCount() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> target.createAndSaveTelevisions(0));
    }
    @Test
    void createAndSaveTelevisions() {
        target.createAndSaveTelevisions(3);
        Mockito.verify(repository).saveAll(Mockito.anyList());
    }

    @Test
    void printAll() {
        target.printAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void update() {
        Television television = new Television("Title", 5, 139,"Model 11 PRO", ManufacturerTelevision.SONY, 55);
        target.saveTelevision(television);
        television.setTitle("UPDATED");
        target.update(television);
        target.getAll();
        ArgumentCaptor<Television> argumentCaptor = ArgumentCaptor.forClass(Television.class);
        Mockito.verify(repository).update(argumentCaptor.capture());
        Assertions.assertEquals("UPDATED", argumentCaptor.getValue().getTitle());
    }

    @Test
    void getAll() {
        target.getAll();
        Mockito.verify(repository).getAll();
    }
    @Test
    void saveTelevision() {
        final Television television = new Television("Title", 25, 199, "MODEL FHD", ManufacturerTelevision.LG, 65);
        target.saveTelevision(television);
        ArgumentCaptor<Television> argumentCaptor = ArgumentCaptor.forClass(Television.class);
        Mockito.verify(repository).save(argumentCaptor.capture());
        assertEquals("Title", argumentCaptor.getValue().getTitle());
    }
    @Test
    void saveTelevisionZeroCount() {
        final Television television = new Television("Title", 0, 199, "MODEL FHD", ManufacturerTelevision.SONY, 32);
        target.saveTelevision(television);
        ArgumentCaptor<Television> argumentCaptor = ArgumentCaptor.forClass(Television.class);
        Mockito.verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals("Title", argumentCaptor.getValue().getTitle());
        Assertions.assertEquals(-1, argumentCaptor.getValue().getCount() );
    }

    @Test
    void findById() {
        final Television television = new Television("Title", 1, 199, "MODEL 3310", ManufacturerTelevision.LG, 32);
        Mockito.when(repository.findById(television.getId())).thenReturn(Optional.of(television));
        Assertions.assertEquals(repository.findById(television.getId()), Optional.of(television));
    }
    @Test
    void findById_IncorrectId() {
        Mockito.when(repository.findById(anyString())).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findById(anyString()));
        Mockito.verify(repository).findById(anyString());
    }
    

    @Test
    void delete() {
        Television television1 = new Television("Title", 1, 199, "MODEL 3310", ManufacturerTelevision.SONY, 35);
        Television television2 = new Television("Title", 1, 399, "MODEL 3310", ManufacturerTelevision.XIAOMI,55);
        target.delete(television1.getId());
        Mockito.verify(repository,Mockito.times(1)).delete(television1.getId());
    }

}