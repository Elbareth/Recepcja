package com.example.recepcja;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface RezerwacjeService {
    public abstract Integer create(Rezerwacje rezerwacje);
    public abstract List<Rezerwacje> selectData(Date data);//musimy sprawdzić jakie pokoje są w danym terminie zarezerwowane
    public abstract List<Rezerwacje> selectKlient(BigInteger idKlient);//sprawdzamy czy taki klient zarezerwował pokój
    public abstract List<Rezerwacje> selectUsluga(String nazwa);
    public abstract void update(Rezerwacje rezerwacje);
    public abstract void delete(Rezerwacje rezerwacje);
}
