package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("pracownikService")
public class PracownikServiceImpl implements PracownikService {
    @Autowired
    private PracownikDAO pracownikDAO;
    @Override
    public Integer create(Pracownik pracownik) {
        return pracownikDAO.create(pracownik);
    }

    @Override
    public Pracownik select(String id) {
        return pracownikDAO.select(id);
    }

    @Override
    public Integer czyIstniejeParacownik(String login, String haslo) {
        return pracownikDAO.czyIstniejeParacownik(login, haslo);
    }

    @Override
    public Integer czyLoginZajety(String login, String identyfikator) {
        return pracownikDAO.czyLoginZajety(login, identyfikator);
    }

    @Override
    public Pracownik update(Pracownik pracownik) {
        return pracownikDAO.update(pracownik);
    }

    @Override
    public void delete(Pracownik pracownik) {
        pracownikDAO.delete(pracownik);
    }
}
