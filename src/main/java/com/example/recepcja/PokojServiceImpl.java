package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("pokojService")
public class PokojServiceImpl implements PokojService {
    @Autowired
    private PokojDAO pokojDAO;
    @Override
    public Pokoj select(String nazwa) {
        return pokojDAO.select(nazwa);
    }

    @Override
    public List<Pokoj> selectAll() {
        return pokojDAO.selectAll();
    }

    @Override
    public Pokoj update(Pokoj pokoj) {
        return pokojDAO.update(pokoj);
    }
}
