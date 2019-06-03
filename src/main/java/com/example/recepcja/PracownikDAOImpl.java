package com.example.recepcja;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pracownikRepository")
public class PracownikDAOImpl implements PracownikDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PracownikDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Pracownik pracownik) {
        Integer result = null;
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = Integer.parseInt ((String) session.save(pracownik));
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally{
            session.close();
        }
        return result;
    }

    @Override
    public Pracownik select(String id) {
        Session session = null;
        Transaction transaction = null;
        List<Pracownik> pracownik = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Pracownik WHERE Identyfikator = :id").setParameter("id",id);
            pracownik = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return pracownik.get(0);
    }

    @Override
    public Integer czyIstniejeParacownik(String login, String haslo) {
        Session session = null;
        Transaction transaction = null;
        Integer licznik = 0;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(*) FROM Pracownik WHERE Login = :login AND Haslo = :haslo").setParameter("login",login).setParameter("haslo",haslo);
            licznik = (int) (long) query.uniqueResult();
            System.out.println("Liczba pracownikow "+licznik);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return licznik;
    }

    @Override
    public Integer czyLoginZajety(String login, String identyfikator) {
        Session session = null;
        Transaction transaction = null;
        Integer licznik = 0;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(*) FROM Pracownik WHERE Login = :login AND Identyfikator = :identyfikator").setParameter("login",login).setParameter("identyfikator",identyfikator);
            licznik = (int) (long) query.uniqueResult();
            System.out.println("Liczba pracownikow "+licznik);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return licznik;
    }

    @Override
    public Pracownik update(Pracownik pracownik) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(pracownik);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return select(pracownik.identyfikator);
    }

    @Override
    public void delete(Pracownik pracownik) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(pracownik);
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
    }
}
