package com.example.recepcja;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository("klientRepository")
public class KlientDAOImpl implements KlientDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public KlientDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Klient klient) {
        Session session = null;
        Transaction transaction = null;
        Integer result = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = (Integer) session.save(klient);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return result;
    }

    @Override
    public Klient select(BigInteger id) {
        Session session = null;
        Transaction transaction = null;
        List<Klient> klient = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Klient WHERE ID = :id").setParameter("id",id);
            klient = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return klient.get(0);
    }

    @Override
    public List<Klient> selectNazwisko(String nazwisko) {
        Session session = null;
        Transaction transaction = null;
        List<Klient> klient = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Klient WHERE Nazwisko = :nazwisko").setParameter("nazwisko",nazwisko);
            klient = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return klient;
    }

    @Override
    public List<Klient> selectLista() {
        Session session = null;
        Transaction transaction = null;
        List<Klient> klient = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Klient");
            klient = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return klient;
    }

    @Override
    public Klient update(Klient klient) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(klient);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return select(klient.id);
    }

    @Override
    public void delete(Klient klient) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(klient);
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally{
            session.close();
        }
    }
}
