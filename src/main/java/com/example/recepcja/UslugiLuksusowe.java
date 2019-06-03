package com.example.recepcja;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class UslugiLuksusowe implements Serializable {
    @Id
    @NotNull
    @ManyToOne(targetEntity = Rezerwacje.class)
    @JoinColumn(name = "Nazwa", nullable = false)
    public String nazwa;
    @NotNull
    @Column(name="cena")
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
        return "UslugiLuksusowe{" +
                "nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                '}';
    }
}
