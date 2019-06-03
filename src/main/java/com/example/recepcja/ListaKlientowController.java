package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import org.threeten.bp.temporal.ChronoUnit;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//dodawanie klientow w rejestracji
@Controller
public class ListaKlientowController {
    @Autowired
    private KlientService klientService;
    @Autowired
    private RezerwacjeService rezerwacjeService;
    @Autowired
    private  PokojService pokojService;
    @Autowired
    private UslugiLuksusoweService uslugiLuksusoweService;
    private List<Klient> lista = new ArrayList<>();
    private List<Float> kwota = new ArrayList<>();
    @GetMapping("/listaKlientow")
    public ModelAndView listaKlientow (ModelMap model, HttpSession sesja){
        //lista = new ArrayList<Klient>();
        //kwota = new ArrayList<Float>();
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("zaloguj","pracownik",new Pracownik());
        }
        lista = klientService.selectLista();//null pointer exception Unknown column 'klient0_.id_id' in 'field list'
        for(int i = 0;i<lista.size();i++){
            System.out.println(lista.get(i));
            /*BigInteger id =lista.get(i).getId();
            //System.out.println(id);
            Rezerwacje rezerwacje = rezerwacjeService.selectKlient(id).get(0);
            System.out.println(rezerwacje);
            Integer days = (int)  (rezerwacje.getPoczatek().getTime() - rezerwacje.getKoniec().getTime());
            Pokoj pokoj = rezerwacje.getNazwaPokoju();
            System.out.println(pokoj);
            Float tmp = pokoj.getCena() * days;
            System.out.println(tmp);
            List<UslugiLuksusowe> uslugiLuksusowe = rezerwacje.getNazwaUslugi();
            for(int k=0;k<uslugiLuksusowe.size();k++){
                tmp = tmp + uslugiLuksusowe.get(i).getCena();
                System.out.println(tmp);
            }*/
            //kwota.add(i,tmp);
            kwota.add(i, (float) ((Math.random()*1000)+1));
        }
        model.put("kwota",kwota);
        //model.put("nazwisko","");
        return new ModelAndView("/listaKlientow","klient",lista);
    }
    @GetMapping(value = "/usun/{nazwa}")
    public ModelAndView usun( @PathVariable("nazwa") String nazwa, ModelMap model, HttpSession sesja){
        List<Klient> klient = klientService.selectNazwisko(nazwa);
        for(int i=0;i<klient.size();i++){
            klientService.delete(klient.get(i));
        }
        lista = klientService.selectLista();
        return new ModelAndView("/listaKlientow","klient", lista);
    }
    @GetMapping(value = "/zaplacone/{nazwisko}")
    public ModelAndView zaplacone( @PathVariable("nazwisko") String nazwisko, ModelMap model, HttpSession sesja){
        List<Klient> klient1 = klientService.selectNazwisko(nazwisko);
        for(int i=0;i<klient1.size();i++){
            klient1.get(0).setCzyZaplacil(true);
            klientService.update(klient1.get(0));
        }
        lista = klientService.selectLista();
        return new ModelAndView("/listaKlentow","klient",lista);
    }
    @PostMapping(value = "/szukaj", consumes = "multipart/form-data", produces = { "application/json", "application/xml" })
    public ModelAndView szukaj(@RequestParam("nazwisko")String nazwisko, ModelMap model, HttpSession sesja){
        lista = klientService.selectNazwisko(nazwisko);
        return new ModelAndView("/listaKlientow","klient",lista);
    }
    @PostMapping(value = "/uaktualnij/{nazwisko}", consumes = "multipart/form-data", produces = { "application/json", "application/xml" })
    public ModelAndView uaktualnij(@ModelAttribute("klient") Klient klient, @PathVariable("nazwisko") String nazwisko, ModelMap model, HttpSession sesja){
        /*for(int i=0;i<klient.size();i++){
            klientService.update(klient.get(0));
        }*/
        klientService.update(klientService.selectNazwisko(nazwisko).get(0));
        return new ModelAndView("/listaKlientow","klient",lista);
        //return new ModelAndView("/edycjaKlienta","idKlient",id);
    }
}
