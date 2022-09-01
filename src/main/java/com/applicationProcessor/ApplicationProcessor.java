package com.applicationProcessor;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.repository.ComputerRepository;
import com.repository.PhoneRepository;
import com.repository.TelevisionRepository;
import com.repository.hiberante.HibernateComputerRepository;
import com.repository.hiberante.HibernatePhoneRepository;
import com.repository.hiberante.HibernateTelevisionRepository;
import com.repository.mongoDB.MongoComputerRepository;
import com.repository.mongoDB.MongoPhoneRepository;
import com.repository.mongoDB.MongoTelevisionRepository;
import com.service.ComputerService;
import com.service.PhoneService;
import com.service.TelevisionService;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationProcessor {
    private Map<Class<?>,Object> cache;
    private Reflections reflections;

    public ApplicationProcessor(){
        cache = new HashMap<>();
        reflections = new Reflections("com");
    }

    public void createSingleton(){
        Set<Class<?>> singletonClasses = reflections.getTypesAnnotatedWith(Singleton.class);
        cacheRepository(singletonClasses);
        cacheService(singletonClasses);
    }

    private void cacheService(Set<Class<?>> singletonClasses) {
        singletonClasses.forEach(aClass -> {
            Arrays.stream(aClass.getDeclaredConstructors())
                    .forEach(constructor -> {
                        if (constructor.isAnnotationPresent(Autowired.class) && constructor.getParameterCount() == 1) {
                       Object object = null;
                       if (aClass.getSimpleName().equals(MongoPhoneRepository.class.getSimpleName())){
                           object = cache.get(MongoPhoneRepository.class);
                       }else if (aClass.getSimpleName().equals(MongoComputerRepository.class.getSimpleName())){
                           object = cache.get(MongoComputerRepository.class);
                            }else if (aClass.getSimpleName().equals(MongoTelevisionRepository.class.getSimpleName())){
                                object = cache.get(MongoTelevisionRepository.class);
                            }
                            try {
                                constructor.setAccessible(true);
                                object = constructor.newInstance(object);
                                Field field = aClass.getDeclaredField("instance");
                                field.setAccessible(true);
                                field.set(null, object);
                                cache.put(aClass, object);
                                }catch (NoSuchFieldException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                                }
                        }
                    });
        });
    }

    private void cacheRepository(Set<Class<?>> singletonClasses) {
        singletonClasses.forEach(aClass -> {
            Arrays.stream(aClass.getDeclaredConstructors())
                    .forEach(constructor -> {
                        if (constructor.isAnnotationPresent(Autowired.class) && constructor.getParameterCount()==0){
                            try {
                                constructor.setAccessible(true);
                                Object object = constructor.newInstance();
                                Field field = aClass.getDeclaredField("instance");
                                field.setAccessible(true);
                                field.set(null, object);
                                cache.put(aClass, object);
                            }catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e){
                                e.printStackTrace();
                        }
                    }});
        });
    }
}
