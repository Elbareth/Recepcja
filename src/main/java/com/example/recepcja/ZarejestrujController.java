package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class ZarejestrujController {
    @Autowired
    private PracownikService pracownikService;
    @GetMapping("/zarejestruj")
    public ModelAndView zarejestruj(ModelMap model){
        return new ModelAndView("zarejestruj", "pracownik",new Pracownik());
    }
    @PostMapping(value = "/save", consumes = "multipart/form-data", produces ={"application/json","application/xml"})
    public ModelAndView save(@ModelAttribute("pracownik")Pracownik pracownik, ModelMap model){
        String login = pracownik.getLogin();
        String haslo = pracownik.getHaslo();
        String identyfikator = pracownik.getIdentyfikator();
        String imie = pracownik.getImie();
        String nazwisko = pracownik.getNazwisko();
        String email =pracownik.getEmail();
        String nrTelefonu = pracownik.getNrTelefonu();
        String podpis = pracownik.getPodpis();
        //Czy juz nie ma takiego uzytkownika?
        if(pracownikService.czyLoginZajety(login,identyfikator) >= 1){
            return new ModelAndView("zarejestruj", "pracownik",new Pracownik());
        }
        //Czy mail jest popraw i czy telefon jest poprawny - to sprawdza formularz html?
        //Czy pola nie są puste
        else if(login == null || haslo == null || imie == null || nazwisko == null || email == null || nrTelefonu == null || identyfikator == null || podpis == null){
            return new ModelAndView("zarejestruj", "pracownik",new Pracownik());
        }
        String shaszowaneHaslo = null;
        //pracownik.setEmail(email);
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(haslo.getBytes());
            BigInteger no = new BigInteger(1,messageDigest);
            shaszowaneHaslo = no.toString();
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        pracownik.setHaslo(shaszowaneHaslo);
        pracownikService.create(pracownik);
        return new ModelAndView("zaloguj", "pracownik",new Pracownik());
    }
}
