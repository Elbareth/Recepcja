<%@ page language="java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Lista Uslug</title>
        <link rel="stylesheet" type="text/css" href="/resources/style.css"/>
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display&display=swap" rel="stylesheet">
    </head>
    <body>
    <h1>Rezerwacja pokoju ${nazwa} na dzien ${data}</h1>
    <div class="menu">
        <a href = "/stronaGlowna"><input type="submit" value="Strona Glowna"/></a>
        <a href = "/listaKlientow"> <input type="submit" value="Obsluga Klienta"/> </a>
        <a href = "/listaUslug"> <input type="submit" value="Uslugi"/> </a>
        <a href = "/wyloguj"> <input type="submit" value="Wyloguj sie"/></a>
    </div>
    <br/>
    <br/>
    <div class="body">
        <s:form method="post" action="/zapiszRezerwacje" modelAttribute="pokoj" enctype="multipart/form-data">
            Imie: <input type="text" name="imie"/><br/>
            Nazwisko: <input type="text" name="nazwisko"/><br/>
            Email: <input type="text" name="email"/><br/>
            NrTelefonu: <input type="text" name="nrTelefonu"/><br/>
            Nazwa pokoju: <input type="text" name="pokoj" value="${nazwa}"/><br/>
            Data Rozpoczecia: <input type="text" name="poczatek" value="${data}"/><br/>
            Data Zakonczenia: <input type="text" name="koniec"/><br/>
            <input type="submit" value="Zatwierdz"/>
        </s:form>
    </div>
    </body>
</html>