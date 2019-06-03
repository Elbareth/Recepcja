package com.example.recepcja;

import java.util.List;

public interface PokojService {
    public abstract Pokoj select(String nazwa);
    public abstract List<Pokoj> selectAll();
    public abstract Pokoj update(Pokoj pokoj);
}
