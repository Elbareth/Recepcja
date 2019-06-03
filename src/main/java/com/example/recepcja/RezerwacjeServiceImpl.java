package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Transactional
@Service("rezerwacjeService")
public class RezerwacjeServiceImpl implements RezerwacjeService {
    @Autowired
    private RezerwacjeDAO rezerwacjeDAO;
    @Override
    public Integer create(Rezerwacje rezerwacje) {
        return rezerwacjeDAO.create(rezerwacje);
    }

    @Override
    public List<Rezerwacje> selectData(Date data) {
        return rezerwacjeDAO.selectData(data);
    }

    @Override
    public List<Rezerwacje> selectKlient(BigInteger idKlient) {
        return rezerwacjeDAO.selectKlient(idKlient);
    }

    @Override
    public List<Rezerwacje> selectUsluga(String nazwa) {
        return rezerwacjeDAO.selectUsluga(nazwa);
    }

    @Override
    public void update(Rezerwacje rezerwacje) {
        rezerwacjeDAO.update(rezerwacje);
    }

    @Override
    public void delete(Rezerwacje rezerwacje) {
        rezerwacjeDAO.delete(rezerwacje);
    }
}
