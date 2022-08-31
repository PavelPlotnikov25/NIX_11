package com.repository.hiberante;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.config.HibernateSessionFactoryUtil;
import com.model.Product;
import com.model.computer.Computer;
import com.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@Singleton
public class HibernateComputerRepository implements CrudRepository<Computer> {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private static HibernateComputerRepository instance;

    public HibernateComputerRepository() {
    }

    public static HibernateComputerRepository getInstance(){
        if (instance == null){
            instance = new HibernateComputerRepository();
        }
        return instance;
    }


    @Override
    public Product save(Computer computer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(computer);
        session.getTransaction().commit();
        session.close();
        return computer;
    }

    @Override
    public void saveAll(List<Computer> products) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Computer computer:products) {
            session.save(computer);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean update(Computer product) {
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
    public List<Computer> getAll() {
        Session session = sessionFactory.openSession();
        List<Computer> computers = session.createQuery("SELECT Computer from Computer Computer", Computer.class).getResultList();
        session.close();
        return computers;
    }

    @Override
    public Optional<Computer> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Computer> computer = Optional.ofNullable(session.find(Computer.class, id));
        session.close();
        return computer;
    }
}
