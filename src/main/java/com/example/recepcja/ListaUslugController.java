package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ListaUslugController {
    @Autowired
    private UslugiLuksusoweService uslugiLuksusoweService;
    @Autowired
    private RezerwacjeService rezerwacjeService;
    private List<UslugiLuksusowe> lista;
    private List<Rezerwacje> listaRezerwacji;
    private List<Klient> listaKlient = new ArrayList<>();
    @GetMapping("/listaUslug")
    public ModelAndView listaUslug (ModelMap model, HttpSession sesja){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("/zaloguj","pracownik",new Pracownik());
        }
        lista = uslugiLuksusoweService.select(); // Unknown column 'uslugiluks0_.nazwa_id' in 'field list'
        for(int i=0;i<lista.size();i++){
            System.out.println(lista.get(i));
        }
        return new ModelAndView("/listaUslug","uslugiLuksusowe",lista);
    }
    @GetMapping(value = "/listaKlientowUslug/{nazwa}")
    public ModelAndView listaKlientowUslug(@PathVariable("nazwa") String nazwa, ModelMap model, HttpSession sesja ){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("/zaloguj","pracownik",new Pracownik());
        }
        listaRezerwacji = rezerwacjeService.selectUsluga(nazwa);
        System.out.println(rezerwacjeService.selectUsluga(nazwa));
        for(int i=0;i<listaRezerwacji.size();i++){
            listaKlient.add(listaRezerwacji.get(i).getIdKlient());
        }
        model.put("usluga",nazwa);
        return new ModelAndView("/uslugiKlientow", "klient",listaKlient);
    }
    @PostMapping(value = "/edytujUslugi", consumes = "multipart/form-data", produces = { "application/json", "application/xml" })
    public ModelAndView edytujUslugi(@ModelAttribute("uslugieLuksusowe") UslugiLuksusowe uslugiLuksusowes, ModelMap model, HttpSession sesja){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("/zaloguj","pracownik",new Pracownik());
        }
        //nowy formularz z zapoamietanymi danymi
        return new ModelAndView("/edycjaUslug","uslugiLuksusowe",lista);
    }
    @GetMapping(value = "/usunUsluge/{nazwa}")
    public ModelAndView usunUsluge(@PathVariable("nazwa") String nazwa, ModelMap model, HttpSession sesja){
        UslugiLuksusowe uslugiLuksusowe1 = uslugiLuksusoweService.select(nazwa);
        uslugiLuksusoweService.delete(uslugiLuksusowe1);
        lista = uslugiLuksusoweService.select();
        return new ModelAndView("/listaUslug","uslugiLuksusowe",lista);
    }
    @GetMapping(value = "/dodajUslugi")
    public ModelAndView dodajUslugi(ModelMap model, HttpSession sesja){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("/zaloguj","pracownik",new Pracownik());
        }
        //nowy formularz
        return new ModelAndView("/dodawanieUslugi","uslugieLuksusowe", new UslugiLuksusowe());
    }
    @PostMapping(value="/checkDodawanieUslugi", consumes = "multipart/form-data", produces = { "application/json", "application/xml" })
    public ModelAndView checkDodawanieUslugi(@ModelAttribute("uslugieLuksusowe") UslugiLuksusowe uslugiLuksusowes, ModelMap model, HttpSession sesja){
        if(uslugiLuksusoweService.czyIstniejeUsluga(uslugiLuksusowes.getNazwa()) >=1){
            model.put("error","Podana nazwa jest juz zajeta");
            return new ModelAndView("/dodajUslugi","uslugieLuksusowe", new UslugiLuksusowe());
        }
        uslugiLuksusoweService.create(uslugiLuksusowes);
        lista = uslugiLuksusoweService.select();
        return new ModelAndView("/listaUslug","uslugiLuksusowe",lista);
    }
    @PostMapping(value = "/edycja", consumes = "multipart/form-data", produces = { "application/json", "application/xml" })
    public ModelAndView edycja(@ModelAttribute("uslugieLuksusowe") List<UslugiLuksusowe> uslugiLuksusowes, ModelMap model, HttpSession sesja){
        for(int i=0;i<uslugiLuksusowes.size();i++){
            uslugiLuksusoweService.update(uslugiLuksusowes.get(i));
        }
        lista = uslugiLuksusoweService.select();
        return new ModelAndView("/listaUslug","uslugiLuksusowe",lista);
    }
}
