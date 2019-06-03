package com.example.recepcja;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class StronGlownaController {
    @GetMapping("/stronaGlowna")
    public ModelAndView stronaGlowna (ModelMap model, HttpSession sesja){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("zaloguj","pracownik",new Pracownik());
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<table>");
        List<String> informacje = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Integer dzis = calendar.get(Calendar.DAY_OF_MONTH); // piata informacja
        Integer jakiDzienTygodnia = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        System.out.println(jakiDzienTygodnia);
        Integer miesiacLiczba = calendar.get(Calendar.MONTH);
        String miesiac = null;//pierwsza informacja
        Integer liczbaDni = null; // czwarta informacja
        Integer liczbaWeekendow = calendar.get(Calendar.WEEK_OF_MONTH);//druga informacja
        if(miesiacLiczba == 0){
            miesiac = "styczen";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 1){
            miesiac = "luty";
            liczbaDni = 28;
        }
        if(miesiacLiczba == 2){
            miesiac = "marzec";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 3){
            miesiac = "kwiecien";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 4){
            miesiac = "maj";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 5){
            miesiac = "czerwiec";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 6){
            miesiac = "lipiec";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 7){
            miesiac = "sierpien";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 8){
            miesiac = "wrzesnien";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 9){
            miesiac = "pazdziernik";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 10){
            miesiac = "listopad";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 11){
            miesiac = "grudzien";
            liczbaDni = 31;
        }
        String pierwszyDzienTygodnia = null; // trzecia informacja
        Integer pierwszyDzienTygodniaLiczba = Math.abs((dzis)%jakiDzienTygodnia);
        System.out.println(pierwszyDzienTygodniaLiczba);
        if(pierwszyDzienTygodniaLiczba == 0) pierwszyDzienTygodniaLiczba = 7;
        if(pierwszyDzienTygodniaLiczba == 1) pierwszyDzienTygodnia = "niedziela";
        if(pierwszyDzienTygodniaLiczba == 2) pierwszyDzienTygodnia = "poniedzialek";
        if(pierwszyDzienTygodniaLiczba == 3) pierwszyDzienTygodnia = "wtorek";
        if(pierwszyDzienTygodniaLiczba == 4) pierwszyDzienTygodnia = "sroda";
        if(pierwszyDzienTygodniaLiczba == 5) pierwszyDzienTygodnia = "czwartek";
        if(pierwszyDzienTygodniaLiczba == 6) pierwszyDzienTygodnia = "piatek";
        if(pierwszyDzienTygodniaLiczba == 7) pierwszyDzienTygodnia = "sobota";
        System.out.println(pierwszyDzienTygodnia);
        stringBuffer.append("<tr>");
        pierwszyDzienTygodniaLiczba = pierwszyDzienTygodniaLiczba - 1;
        if(pierwszyDzienTygodniaLiczba == 0) pierwszyDzienTygodniaLiczba = 7;
        for(int i=0;i<pierwszyDzienTygodniaLiczba;i++){
            stringBuffer.append("<td></td>");
        }
        stringBuffer.append("<td><a href=\"/dzien/1/"+miesiac+"\" ><input type =\"submit\" value=\""+pierwszyDzienTygodnia+"\n 1 \"/></a></td>"); // pierwszy dzien w miesiacu
        for(int i=1;i<liczbaDni;i++){ // liczymy od pierwszego bo juz mamy pierwszy dzien
            int tmp = Math.abs(((i+1)%7)); // cos tu nie ak
            if(tmp == 0) tmp = 7;
            if(tmp == 7) pierwszyDzienTygodnia = "niedziela";
            if(tmp == 1) pierwszyDzienTygodnia = "poniedzialek";
            if(tmp == 2) pierwszyDzienTygodnia = "wtorek";
            if(tmp == 3) pierwszyDzienTygodnia = "sroda";
            if(tmp == 4) pierwszyDzienTygodnia = "czwartek";
            if(tmp == 5) pierwszyDzienTygodnia = "piatek";
            if(tmp == 6) pierwszyDzienTygodnia = "sobota";
            int k = i+1;
            stringBuffer.append("<td><a href=\"/dzien/"+k+"/"+miesiac+"\"> <input type =\"submit\" value=\""+pierwszyDzienTygodnia+"\n" + k +"\"/></a></td>");
            if((i+1)%7==0) stringBuffer.append("</tr> <br/>");
        }
        stringBuffer.append("</table>");
        System.out.println(stringBuffer);
        model.put("miesiac",miesiac);
        return new ModelAndView("/stronaGlowna","kalendarz",stringBuffer);
    }
    @GetMapping("/tyl")// przesun do tyl miesiac
    public ModelAndView tyl(ModelMap model, HttpSession sesja){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("zaloguj","pracownik",new Pracownik());
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<table>");
        List<String> informacje = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        Integer dzis = calendar.get(Calendar.DAY_OF_MONTH); // piata informacja
        Integer jakiDzienTygodnia = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        System.out.println(jakiDzienTygodnia);
        Integer miesiacLiczba = calendar.get(Calendar.MONTH);
        String miesiac = null;//pierwsza informacja
        Integer liczbaDni = null; // czwarta informacja
        Integer liczbaWeekendow = calendar.get(Calendar.WEEK_OF_MONTH);//druga informacja
        if(miesiacLiczba == 0){
            miesiac = "styczen";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 1){
            miesiac = "luty";
            liczbaDni = 28;
        }
        if(miesiacLiczba == 2){
            miesiac = "marzec";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 3){
            miesiac = "kwiecien";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 4){
            miesiac = "maj";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 5){
            miesiac = "czerwiec";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 6){
            miesiac = "lipiec";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 7){
            miesiac = "sierpien";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 8){
            miesiac = "wrzesnien";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 9){
            miesiac = "pazdziernik";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 10){
            miesiac = "listopad";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 11){
            miesiac = "grudzien";
            liczbaDni = 31;
        }
        String pierwszyDzienTygodnia = null; // trzecia informacja
        Integer pierwszyDzienTygodniaLiczba = Math.abs((dzis)%jakiDzienTygodnia);
        System.out.println(pierwszyDzienTygodniaLiczba);
        if(pierwszyDzienTygodniaLiczba == 0) pierwszyDzienTygodniaLiczba = 7;
        if(pierwszyDzienTygodniaLiczba == 1) pierwszyDzienTygodnia = "niedziela";
        if(pierwszyDzienTygodniaLiczba == 2) pierwszyDzienTygodnia = "poniedzialek";
        if(pierwszyDzienTygodniaLiczba == 3) pierwszyDzienTygodnia = "wtorek";
        if(pierwszyDzienTygodniaLiczba == 4) pierwszyDzienTygodnia = "sroda";
        if(pierwszyDzienTygodniaLiczba == 5) pierwszyDzienTygodnia = "czwartek";
        if(pierwszyDzienTygodniaLiczba == 6) pierwszyDzienTygodnia = "piatek";
        if(pierwszyDzienTygodniaLiczba == 7) pierwszyDzienTygodnia = "sobota";
        System.out.println(pierwszyDzienTygodnia);
        stringBuffer.append("<tr>");
        pierwszyDzienTygodniaLiczba = pierwszyDzienTygodniaLiczba - 1;
        if(pierwszyDzienTygodniaLiczba == 0) pierwszyDzienTygodniaLiczba = 7;
        for(int i=0;i<pierwszyDzienTygodniaLiczba;i++){
            stringBuffer.append("<td></td>");
        }
        stringBuffer.append("<td><a href=\"/dzien/1/"+miesiac+"\" ><input type =\"submit\" value=\""+pierwszyDzienTygodnia+"\n 1 \"/></a></td>"); // pierwszy dzien w miesiacu
        for(int i=1;i<liczbaDni;i++){ // liczymy od pierwszego bo juz mamy pierwszy dzien
            int tmp = Math.abs(((i+1)%7));
            System.out.println(jakiDzienTygodnia);
            System.out.println(tmp);
            if(tmp ==0) tmp = 7;
            if(tmp == 7) pierwszyDzienTygodnia = "niedziela";
            if(tmp == 1) pierwszyDzienTygodnia = "poniedzialek";
            if(tmp == 2) pierwszyDzienTygodnia = "wtorek";
            if(tmp == 3) pierwszyDzienTygodnia = "sroda";
            if(tmp == 4) pierwszyDzienTygodnia = "czwartek";
            if(tmp == 5) pierwszyDzienTygodnia = "piatek";
            if(tmp == 6) pierwszyDzienTygodnia = "sobota";
            int k = i+1;
            stringBuffer.append("<td><a href=\"/dzien/"+k+"/"+miesiac+"\"> <input type =\"submit\" value=\""+pierwszyDzienTygodnia+"\n" + k +"\"/></a></td>");
            if((i+1)%7==0) stringBuffer.append("</tr> <br/>");
        }
        stringBuffer.append("</table>");
        System.out.println(stringBuffer);
        model.put("miesiac",miesiac);
        return new ModelAndView("/stronaGlowna","kalendarz",stringBuffer);
    }
    @GetMapping("/przod")
    public ModelAndView przod(ModelMap model, HttpSession sesja){
        if(sesja.getAttribute("login")==null){
            return new ModelAndView("zaloguj","pracownik",new Pracownik());
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<table>");
        List<String> informacje = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,1);
        Integer dzis = calendar.get(Calendar.DAY_OF_MONTH); // piata informacja
        Integer jakiDzienTygodnia = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        System.out.println(jakiDzienTygodnia);
        Integer miesiacLiczba = calendar.get(Calendar.MONTH);
        String miesiac = null;//pierwsza informacja
        Integer liczbaDni = null; // czwarta informacja
        Integer liczbaWeekendow = calendar.get(Calendar.WEEK_OF_MONTH);//druga informacja
        if(miesiacLiczba == 0){
            miesiac = "styczen";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 1){
            miesiac = "luty";
            liczbaDni = 28;
        }
        if(miesiacLiczba == 2){
            miesiac = "marzec";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 3){
            miesiac = "kwiecien";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 4){
            miesiac = "maj";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 5){
            miesiac = "czerwiec";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 6){
            miesiac = "lipiec";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 7){
            miesiac = "sierpien";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 8){
            miesiac = "wrzesnien";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 9){
            miesiac = "pazdziernik";
            liczbaDni = 31;
        }
        if(miesiacLiczba == 10){
            miesiac = "listopad";
            liczbaDni = 30;
        }
        if(miesiacLiczba == 11){
            miesiac = "grudzien";
            liczbaDni = 31;
        }
        String pierwszyDzienTygodnia = null; // trzecia informacja
        Integer pierwszyDzienTygodniaLiczba = Math.abs((dzis)%jakiDzienTygodnia);
        System.out.println(pierwszyDzienTygodniaLiczba);
        if(pierwszyDzienTygodniaLiczba == 0) pierwszyDzienTygodniaLiczba = 7;
        if(pierwszyDzienTygodniaLiczba == 1) pierwszyDzienTygodnia = "niedziela";
        if(pierwszyDzienTygodniaLiczba == 2) pierwszyDzienTygodnia = "poniedzialek";
        if(pierwszyDzienTygodniaLiczba == 3) pierwszyDzienTygodnia = "wtorek";
        if(pierwszyDzienTygodniaLiczba == 4) pierwszyDzienTygodnia = "sroda";
        if(pierwszyDzienTygodniaLiczba == 5) pierwszyDzienTygodnia = "czwartek";
        if(pierwszyDzienTygodniaLiczba == 6) pierwszyDzienTygodnia = "piatek";
        if(pierwszyDzienTygodniaLiczba == 7) pierwszyDzienTygodnia = "sobota";
        System.out.println(pierwszyDzienTygodnia);
        stringBuffer.append("<tr>");
        pierwszyDzienTygodniaLiczba = pierwszyDzienTygodniaLiczba - 1;
        if(pierwszyDzienTygodniaLiczba == 0) pierwszyDzienTygodniaLiczba = 7;
        for(int i=0;i<pierwszyDzienTygodniaLiczba;i++){
            stringBuffer.append("<td></td>");
        }
        stringBuffer.append("<td><a href=\"/dzien/1/"+miesiac+"\" ><input type =\"submit\" value=\""+pierwszyDzienTygodnia+"\n 1 \"/></a></td>"); // pierwszy dzien w miesiacu
        for(int i=1;i<liczbaDni;i++){ // liczymy od pierwszego bo juz mamy pierwszy dzien
            int tmp = Math.abs(((i+1)%7));
            if(tmp==0) tmp = 7;
            if(tmp == 7) pierwszyDzienTygodnia = "niedziela";
            if(tmp == 1) pierwszyDzienTygodnia = "poniedzialek";
            if(tmp == 2) pierwszyDzienTygodnia = "wtorek";
            if(tmp == 3) pierwszyDzienTygodnia = "sroda";
            if(tmp == 4) pierwszyDzienTygodnia = "czwartek";
            if(tmp == 5) pierwszyDzienTygodnia = "piatek";
            if(tmp == 6) pierwszyDzienTygodnia = "sobota";
            int k = i+1;
            stringBuffer.append("<td><a href=\"/dzien/"+k+"/"+miesiac+"\"> <input type =\"submit\" value=\""+pierwszyDzienTygodnia+"\n" + k +"\"/></a></td>");
            if((i+1)%7==0) stringBuffer.append("</tr> <br/>");
        }
        stringBuffer.append("</table>");
        System.out.println(stringBuffer);
        model.put("miesiac",miesiac);
        return new ModelAndView("/stronaGlowna","kalendarz",stringBuffer);
    }
}
