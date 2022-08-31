package com.repository.hiberante;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.config.HibernateSessionFactoryUtil;
import com.model.Product;
import com.model.computer.Computer;
import com.model.phone.Phone;
import com.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@Singleton
public class HibernatePhoneRepository implements CrudRepository<Phone> {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private static HibernatePhoneRepository instance;

    public HibernatePhoneRepository() {
    }

    public static HibernatePhoneRepository getInstance(){
        if (instance == null){
            instance = new HibernatePhoneRepository();
        }
        return instance;
    }


    @Override
    public Product save(Phone phone) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(phone);
        session.getTransaction().commit();
        session.close();
        return phone;
    }

    @Override
    public void saveAll(List<Phone> products) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Phone phone:products) {
            session.save(phone);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean update(Phone product) {
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
    public List<Phone> getAll() {
        Session session = sessionFactory.openSession();
        List<Phone> phones = session.createQuery("SELECT Phone from Phone Phone", Phone.class).getResultList();
        session.close();
        return phones;
    }

    @Override
    public Optional<Phone> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Phone> phone = Optional.ofNullable(session.find(Phone.class, id));
        session.close();
        return phone;
    }
}
