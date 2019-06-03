package com.example.recepcja;

public interface PracownikDAO {
    public abstract Integer create(Pracownik pracownik);
    public abstract Pracownik select(String id);
    public abstract Integer czyIstniejeParacownik(String login, String haslo);
    public abstract Integer czyLoginZajety(String login, String identyfikator);
    public abstract Pracownik update(Pracownik pracownik);
    public abstract void delete(Pracownik pracownik);
}
