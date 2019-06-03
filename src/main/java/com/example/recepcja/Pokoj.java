package com.example.recepcja;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Pokoj implements Serializable {
    @Id
    @NotNull
    @OneToOne(targetEntity = Rezerwacje.class)
    @JoinColumn(name = "Nazwa", nullable = false)
    public String nazwa;
    @NotNull
    public Float cena;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Pokoj{" +
                "nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                '}';
    }
}
