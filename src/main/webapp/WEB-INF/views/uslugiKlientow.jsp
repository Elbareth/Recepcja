<%@ page language="java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Lista Uslug</title>
        <link rel="stylesheet" type="text/css" href="/resources/style.css"/>
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>Lista klientow ${usluga} </h1>
        <div class="menu">
        <a href = "/stronaGlowna"><input type="submit" value="Strona Glowna"/></a>
        <a href = "/listaKlientow"> <input type="submit" value="Obsluga Klienta"/> </a>
        <a href = "/listaUslug"> <input type="submit" value="Uslugi"/> </a>
        <a href = "/wyloguj"> <input type="submit" value="Wyloguj sie"/></a>
        </div>
        <br/>
        <div class="body">
        <c:forEach var="element" items="${klient}">
        <td>
            <tr>${element.imie}</tr>
            <tr>${element.nazwisko}</tr>
            <tr>${element.czyZaplacil}</tr>
            <tr>${element.email}</tr>
            <tr>${element.nrTelefonu}</tr>
        </td>
        </c:forEach>
        </div>
    </body>
</html>