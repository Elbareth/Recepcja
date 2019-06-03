package com.example.recepcja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class LogowanieController {
    @Autowired
    private PracownikService pracownikService;

    @GetMapping(value = "/zaloguj")
    public ModelAndView stronaPowitalna(ModelMap model){
        return new ModelAndView("/zaloguj","pracownik",new Pracownik());
    }
    @PostMapping(value = "/login", consumes = "multipart/form-data", produces = { "application/json", "application/xml" })
    public /*ModelAndView*/ String login(@ModelAttribute("pracownik") Pracownik pracownik, HttpSession sesja, ModelMap model){
        String shaszowaneHaslo = null;
        String haslo = pracownik.getHaslo();
        String login = pracownik.getLogin();
        try{ // haslo hszujemy by bylo bezpieczne. W bazie sa juz shaszowane hasla
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(haslo.getBytes());
            BigInteger no = new BigInteger(1,messageDigest);
            shaszowaneHaslo = no.toString();
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        if(pracownikService.czyIstniejeParacownik(login,shaszowaneHaslo) == 1){ // sprawdzamy czy istnieje pracownik o podanym loginie i hasle
            sesja.setAttribute("login",login);
            //return new ModelAndView("stronaGlowna");
            return "/stronaGlowna";
        }
        else{
            model.put("error","Podany login lub haslo sa nie poprawne");
            //return new ModelAndView("zaloguj");
            model.put("pracownik",new Pracownik());
            return "/zaloguj";
        }
    }
}
