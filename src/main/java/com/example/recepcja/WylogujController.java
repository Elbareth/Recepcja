package com.example.recepcja;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class WylogujController {
    @GetMapping("/wyloguj")
    public ModelAndView stronaGlowna (ModelMap model, HttpSession sesja){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("zaloguj","pracownik",new Pracownik());
        }
        sesja.invalidate();
        return new ModelAndView("zaloguj","pracownik",new Pracownik());
    }
}
