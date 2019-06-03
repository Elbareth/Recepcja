package com.example.recepcja;

import java.util.List;

public interface UslugiLuksusoweService {
    public abstract Integer create(UslugiLuksusowe uslugiLuksusowe);
    public abstract Integer czyIstniejeUsluga(String nazwa);
    public abstract UslugiLuksusowe select(String nazwa);
    public abstract List<UslugiLuksusowe> select();
    public abstract UslugiLuksusowe update(UslugiLuksusowe uslugiLuksusowe);
    public abstract void delete(UslugiLuksusowe uslugiLuksusowe);
}
