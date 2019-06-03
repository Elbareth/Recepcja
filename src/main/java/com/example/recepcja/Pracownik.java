package com.example.recepcja;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
public class Pracownik implements Serializable {
    @Id
    @NotNull
    public String identyfikator;
    @NotNull
    public String imie;
    @NotNull
    public String nazwisko;
    @NotNull
    public String login;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    public String haslo;
    @NotNull
    @Email
    public String email;
    @NotNull
    @Pattern(regexp = "^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$")
    public String nrTelefonu;
    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]*[0-9]+[a-z]*$")
    public String podpis;

    public String getIdentyfikator() {
        return identyfikator;
    }

    public void setIdentyfikator(String identyfikator) {
        this.identyfikator = identyfikator;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public String getPodpis() {
        return podpis;
    }

    public void setPodpis(String podpis) {
        this.podpis = podpis;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "identyfikator='" + identyfikator + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", email='" + email + '\'' +
                ", nrTelefonu='" + nrTelefonu + '\'' +
                ", podpis='" + podpis + '\'' +
                '}';
    }
}
