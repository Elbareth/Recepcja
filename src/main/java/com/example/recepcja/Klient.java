package com.example.recepcja;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "Klient")
public class Klient implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    //@ManyToOne(targetEntity = Rezerwacje.class)
    //@ManyToOne
    @OneToOne(targetEntity = Rezerwacje.class)
    @JoinColumn(name="id", nullable=false) // null gdzies w tym miejscu
    public BigInteger id;
    @NotNull
    @Min(3)
    public String imie;
    @NotNull
    //@Min(3)
    public String nazwisko;
    @NotNull
    @Email
    public String email;
    @NotNull
    @Pattern(regexp = "^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$")
    public String nrTelefonu;
    @NotNull
    public boolean czyZaplacil;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "idKlient")
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public boolean isCzyZaplacil() {
        return czyZaplacil;
    }

    public void setCzyZaplacil(boolean czyZaplacil) {
        this.czyZaplacil = czyZaplacil;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", nrTelefonu='" + nrTelefonu + '\'' +
                ", czyZaplacil=" + czyZaplacil +
                '}';
    }
}
