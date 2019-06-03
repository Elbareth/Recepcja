package com.example.recepcja;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
public class Rezerwacje implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public BigInteger id;
    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    public Date poczatek;
    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    public Date koniec;
    @NotNull
    @OneToOne(mappedBy = "id",cascade={CascadeType.ALL})
    //@JoinColumn(name ="id")
    public Klient idKlient; // Can not set java.math.BigInteger field com.example.recepcja.Klient.id to com.example.recepcja.Rezerwacje
    @NotNull
    @OneToOne(mappedBy = "nazwa", cascade={CascadeType.ALL})
    public Pokoj nazwaPokoju;
    @OneToMany(mappedBy = "nazwa", cascade={CascadeType.ALL})
    public List<UslugiLuksusowe> nazwaUslugi;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getPoczatek() {
        return poczatek;
    }

    public void setPoczatek(Date poczatek) {
        this.poczatek = poczatek;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public Date getKoniec() {
        return koniec;
    }

    public void setKoniec(Date koniec) {
        this.koniec = koniec;
    }

    public Klient getIdKlient() {
        return idKlient;
    }

    public void setIdKlient(Klient idKlient) {
        this.idKlient = idKlient;
    }

    public Pokoj getNazwaPokoju() {
        return nazwaPokoju;
    }

    public void setNazwaPokoju(Pokoj nazwaPokoju) {
        this.nazwaPokoju = nazwaPokoju;
    }

    public List<UslugiLuksusowe> getNazwaUslugi() {
        return nazwaUslugi;
    }

    public void setNazwaUslugi(List<UslugiLuksusowe> nazwaUslugi) {
        this.nazwaUslugi = nazwaUslugi;
    }

    @Override
    public String toString() {
        return "Rezerwacje{" +
                "id=" + id +
                ", poczatek=" + poczatek +
                ", koniec=" + koniec +
                ", idKlient=" + idKlient +
                ", nazwaPokoju=" + nazwaPokoju +
                ", nazwaUslugi=" + nazwaUslugi +
                '}';
    }
}
