package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("uslugiLuksusoweService")
public class UslugiLuksusoweServiceImpl implements UslugiLuksusoweService {
    @Autowired
    private UslugiLuksusoweDAO uslugiLuksusoweDAO;
    @Override
    public Integer create(UslugiLuksusowe uslugiLuksusowe) {
        return uslugiLuksusoweDAO.create(uslugiLuksusowe);
    }

    @Override
    public Integer czyIstniejeUsluga(String nazwa) {
        return uslugiLuksusoweDAO.czyIstniejeUsluga(nazwa);
    }

    @Override
    public UslugiLuksusowe select(String nazwa) {
        return uslugiLuksusoweDAO.select(nazwa);
    }

    @Override
    public List<UslugiLuksusowe> select() {
        return uslugiLuksusoweDAO.select();
    }

    @Override
    public UslugiLuksusowe update(UslugiLuksusowe uslugiLuksusowe) {
        return uslugiLuksusoweDAO.update(uslugiLuksusowe);
    }

    @Override
    public void delete(UslugiLuksusowe uslugiLuksusowe) {
        uslugiLuksusoweDAO.delete(uslugiLuksusowe);
    }
}
