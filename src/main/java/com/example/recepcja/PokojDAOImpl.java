package com.example.recepcja;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pokojRepository")
public class PokojDAOImpl implements PokojDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PokojDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pokoj select(String nazwa) {
        Session session = null;
        Transaction transaction = null;
        List<Pokoj> pokoj = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Pokoj WHERE Nazwa = :nazwa").setParameter("nazwa",nazwa);
            pokoj = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return pokoj.get(0);
    }

    @Override
    public List<Pokoj> selectAll() {
        Session session = null;
        Transaction transaction = null;
        List<Pokoj> pokoj = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Pokoj");
            pokoj = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return pokoj;
    }

    @Override
    public Pokoj update(Pokoj pokoj) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(pokoj);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return select(pokoj.nazwa);
    }
}
