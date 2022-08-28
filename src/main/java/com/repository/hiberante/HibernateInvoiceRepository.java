package com.repository.hiberante;

import com.annotations.Singleton;
import com.config.HibernateSessionFactoryUtil;
import com.model.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Singleton
public class HibernateInvoiceRepository implements com.repository.InvoiceRepository {

    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private static HibernateInvoiceRepository instance;

    public static HibernateInvoiceRepository getInstance() {
        if (instance == null) {
            instance = new HibernateInvoiceRepository();
        }
        return instance;
    }

    @Override
    public Invoice save(Invoice invoice) {
        invoice.getProducts().forEach(product -> product.setInvoice(invoice));
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(invoice);
        session.getTransaction().commit();
        session.close();
        return invoice;
    }

    @Override
    public List<Invoice> findInvoicesWithSumHigher(Double sum) {
        Session session = sessionFactory.openSession();
        List<Invoice> invoices = session.createQuery("select invoice from Invoice invoice where invoice.sum > :sum", Invoice.class).setParameter("sum", sum).getResultList();
        session.close();
        return invoices;
    }

    @Override
    public Long countOfInvoices() {
        Session session = sessionFactory.openSession();
        Long countOfInvoices = (Long) session.createQuery("select count (id) from Invoice").getSingleResult();
        session.close();
        return countOfInvoices;
    }

    @Override
    public void update(Invoice invoice) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(invoice);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<String> groupInvoicesBySum() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Invoice> root = criteriaQuery.from(Invoice.class);
        criteriaQuery.multiselect(criteriaBuilder.count(root.get("id")), root.get("sum"));
        criteriaQuery.groupBy(root.get("sum"));
        TypedQuery<Object[]> query = session.createQuery(criteriaQuery);
        List<Object[]> resultList = query.getResultList();
        List<String> result = new ArrayList<>();
        for (Object[] objects: resultList){
            result.add("count =  " + objects[0] + " sum = " + objects[1]);
        }
        return result;
    }
}

