package com.example.recepcja;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository("rezerwacjeRepository")
public class RezerwacjeDAOImpl implements RezerwacjeDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public RezerwacjeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Rezerwacje rezerwacje) {
        Session session = null;
        Transaction transaction = null;
        Integer result = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = (Integer) session.save(rezerwacje);
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
    public List<Rezerwacje> selectData(Date data) {
        Session session = null;
        Transaction transaction = null;
        List<Rezerwacje> rezerwacje = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //rezerwacje = session.createQuery("SELECT * FROM recepcja.Rezerwacje WHERE  "+ data +" >= 'Poczatek' AND "+data+" <= 'Koniec';").list();
            Query query = session.createQuery("FROM Rezerwacje WHERE  :data  >= Poczatek AND :data <= Koniec").setParameter("data",data);
            rezerwacje = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return rezerwacje;
    }

    @Override
    public List<Rezerwacje> selectKlient(BigInteger idKlient) {
        Session session = null;
        Transaction transaction = null;
        List<Rezerwacje> rezerwacje = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Rezerwacje WHERE  IDKlient = :idKlient").setParameter("idKlient",idKlient);
            rezerwacje = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return rezerwacje;
    }

    @Override
    public List<Rezerwacje> selectUsluga(String nazwa) {
        Session session = null;
        Transaction transaction = null;
        List<Rezerwacje> rezerwacje = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Rezerwacje WHERE  NazwaUslugi = :nazwa ").setParameter("nazwa",nazwa);
            rezerwacje = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return rezerwacje;
    }

    @Override
    public void update(Rezerwacje rezerwacje) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(rezerwacje);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void delete(Rezerwacje rezerwacje) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(rezerwacje);
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
