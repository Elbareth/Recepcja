package com.example.recepcja;

import java.math.BigInteger;
import java.util.List;

public interface KlientDAO {
    public abstract Integer create(Klient klient);
    public abstract Klient select(BigInteger id);
    public abstract List<Klient> selectNazwisko(String nazwisko);
    public abstract List<Klient> selectLista();
    public abstract Klient update(Klient klient);
    public abstract void delete(Klient klient);
}
