package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Transactional
@Service("klientService")
public class KlientServiceImpl implements KlientService {
    @Autowired
    private KlientDAO klientDAO;
    @Override
    public Integer create(Klient klient) {
        return klientDAO.create(klient);
    }

    @Override
    public Klient select(BigInteger id) {
        return klientDAO.select(id);
    }

    @Override
    public List<Klient> selectNazwisko(String nazwisko) {
        return klientDAO.selectNazwisko(nazwisko);
    }

    @Override
    public List<Klient> selectLista() {
        return klientDAO.selectLista();
    }

    @Override
    public Klient update(Klient klient) {
        return  klientDAO.update(klient);
    }

    @Override
    public void delete(Klient klient) {
        klientDAO.delete(klient);
    }
}
