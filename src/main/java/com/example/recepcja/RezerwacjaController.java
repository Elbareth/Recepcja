package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class RezerwacjaController {
    @Autowired
    private RezerwacjeService rezerwacjeService;
    @Autowired
    private PokojService pokojService;
    @Autowired
    private KlientService klientService;
    @Autowired
    UslugiLuksusoweService uslugiLuksusoweService;
    private List<Pokoj> pokoj = new ArrayList<>();
    @GetMapping("/dzien/{id}/{miesiac}")
    public ModelAndView rezerwacja(@PathVariable("id")Integer dzien, @PathVariable("miesiac")String miesiac, ModelMap model, HttpSession sesja){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("zaloguj","pracownik",new Pracownik());
        }
        Calendar calendar = Calendar.getInstance();
        Integer rok = calendar.get(Calendar.YEAR);
        Date data = new Date();
        data.setYear(rok);
        if(miesiac == "styczen") data.setMonth(0);
        if(miesiac == "luty") data.setMonth(1);
        if(miesiac == "marzec") data.setMonth(2);
        if(miesiac == "kwiecien") data.setMonth(3);
        if(miesiac == "maj") data.setMonth(4);
        if(miesiac == "czerwiec") data.setMonth(5);
        if(miesiac == "lipiec") data.setMonth(6);
        if(miesiac == "sierpien") data.setMonth(7);
        if(miesiac == "wrzesien") data.setMonth(8);
        if(miesiac == "pazdziernik") data.setMonth(9);
        if(miesiac == "listopad") data.setMonth(10);
        if(miesiac == "grudzien") data.setMonth(12);
        data.setHours(24*dzien);
        List<Rezerwacje> rezerwacja =  rezerwacjeService.selectData(data);
        for(int i=0;i<rezerwacja.size();i++){
            pokoj.add(rezerwacja.get(i).getNazwaPokoju());
        }
        List<Pokoj> pokoje = pokojService.selectAll();
        model.put("zajete",pokoj);
        model.put("data",data);
        return new ModelAndView("/pokoje","pokoje",pokoje);
    }
    @GetMapping("/zarezerwuj/{nazwa}/{data}")
    public ModelAndView zarezerwuj(@PathVariable("nazwa")String nazwa, @PathVariable("data")Date data, ModelMap modelMap, HttpSession sesja){
        modelMap.put("nazwa",nazwa);
        modelMap.put("data",data);
        return new ModelAndView("/edytorRezerwacji","pokoj", new Pokoj());
    }
    @PostMapping(value = "/zapiszRezerwacje", consumes = "multipart/form-data", produces = { "application/json", "application/xml" })
    public ModelAndView zapiszRezerwacje(@RequestParam("imie") String imie, @RequestParam("nazwisko") String nazwisko, @RequestParam("email") String email,
                                         @RequestParam("nrTelefonu")String nrTelefonu, @RequestParam("pokoj") String nazwa, @RequestParam("poczatek") Date poczatek,
                                         @RequestParam("koniec") Date koniec, ModelMap model, HttpSession sesja){
        Klient klient = new Klient();
        klient.setCzyZaplacil(false);
        klient.setEmail(email);
        klient.setImie(imie);
        Integer tmp = (Integer) klient.getId().intValue() + (Integer) new BigInteger(new String("1")).intValue();
        klient.setId(new BigInteger(tmp.toString()));
        klient.setNazwisko(nazwisko);
        klient.setNrTelefonu(nrTelefonu);
        klientService.create(klient);
        Rezerwacje rezerwacje = new Rezerwacje();
        Integer tmp2 = (Integer) rezerwacje.getId().intValue() + (Integer) new BigInteger(new String("1")).intValue();
        rezerwacje.setId(new BigInteger(tmp2.toString()));
        rezerwacje.setIdKlient(klient);
        rezerwacje.setPoczatek(poczatek);
        rezerwacje.setKoniec(koniec);
        rezerwacje.setNazwaUslugi(uslugiLuksusoweService.select());
        rezerwacje.setNazwaPokoju(pokojService.select(nazwa));
        return new ModelAndView("/stronaGlowna","rezerwacje",new Rezerwacje());

    }
}
