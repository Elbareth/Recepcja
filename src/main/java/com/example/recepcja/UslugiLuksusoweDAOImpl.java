package com.example.recepcja;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("uslugiLuksusoweRepository")
public class UslugiLuksusoweDAOImpl implements UslugiLuksusoweDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public UslugiLuksusoweDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(UslugiLuksusowe uslugiLuksusowe) {
        Session session = null;
        Transaction transaction = null;
        Integer result = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = (Integer) session.save(uslugiLuksusowe);
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
    public Integer czyIstniejeUsluga(String nazwa) {
        Session session = null;
        Transaction transaction = null;
        Integer licznik = 0;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(*) FROM UslugiLuksusowe WHERE Nazwa = :nazwa").setParameter("nazwa",nazwa);
            licznik = (int) (long) query.uniqueResult();
            System.out.println("Liczba uslug "+licznik);
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
    public UslugiLuksusowe select(String nazwa) {
        Session session = null;
        Transaction transaction = null;
        List<UslugiLuksusowe> uslugiLuksusowe = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //uslugiLuksusowe = session.createQuery("SELECT * FROM recepcja.UslugiLuksusowe WHERE  Nazwa = '"+ nazwa +"';").list();
            Query query = session.createSQLQuery("SELECT * FROM UslugiLuksusowe WHERE  Nazwa = :nazwa").addScalar("nazwa", StandardBasicTypes.STRING).addScalar("cena",StandardBasicTypes.FLOAT).setString("nazwa",nazwa).setResultTransformer(Transformers.aliasToBean(UslugiLuksusowe.class));
            uslugiLuksusowe = query.list();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return uslugiLuksusowe.get(0);
    }

    @Override
    public List<UslugiLuksusowe> select() {
        Session session = null;
        Transaction transaction = null;
        List<UslugiLuksusowe> uslugiLuksusowe = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM UslugiLuksusowe").addScalar("Nazwa", StandardBasicTypes.STRING).addScalar("cena",StandardBasicTypes.FLOAT).setResultTransformer(Transformers.aliasToBean(UslugiLuksusowe.class));
            uslugiLuksusowe = query.list(); // basen is string not this format!
            /*List<Object []> lista = query.list();
            for(Object [] row : lista){
                UslugiLuksusowe uslugiLuksusowe1 = new UslugiLuksusowe();
                uslugiLuksusowe1.setNazwa(row[0].toString());
                uslugiLuksusowe1.setCena((Float) row[1]);
                uslugiLuksusowe.add(uslugiLuksusowe1);
            }*/
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return uslugiLuksusowe;
    }

    @Override
    public UslugiLuksusowe update(UslugiLuksusowe uslugiLuksusowe) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(uslugiLuksusowe);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        finally {
            session.close();
        }
        return select(uslugiLuksusowe.nazwa);
    }

    @Override
    public void delete(UslugiLuksusowe uslugiLuksusowe) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(uslugiLuksusowe);
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
