package com.repository.hiberante;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.config.HibernateSessionFactoryUtil;
import com.model.Product;
import com.model.phone.Phone;
import com.model.television.Television;
import com.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@Singleton
public class HibernateTelevisionRepository implements CrudRepository<Television> {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private static HibernateTelevisionRepository instance;

    public HibernateTelevisionRepository() {
    }

    public static HibernateTelevisionRepository getInstance(){
        if (instance == null){
            instance = new HibernateTelevisionRepository();
        }
        return instance;
    }


    @Override
    public Product save(Television television) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(television);
        session.getTransaction().commit();
        session.close();
        return television;
    }

    @Override
    public void saveAll(List<Television> products) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Television television:products) {
            session.save(television);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean update(Television product) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.merge(product);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = sessionFactory.openSession();
        session.remove(findById(id));
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Television> getAll() {
        Session session = sessionFactory.openSession();
        List<Television> televisions = session.createQuery("SELECT Television from Television Television", Television.class).getResultList();
        session.close();
        return televisions;
    }

    @Override
    public Optional<Television> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Television> televisions = Optional.ofNullable(session.find(Television.class, id));
        session.close();
        return televisions;
    }
}

