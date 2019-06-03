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
        <h1>Rezerwacja pokoju na dzien ${data}</h1>
        <div class="menu">
        <a href = "/stronaGlowna"><input type="submit" value="Strona Glowna"/></a>
        <a href = "/listaKlientow"> <input type="submit" value="Obsluga Klienta"/> </a>
        <a href = "/listaUslug"> <input type="submit" value="Uslugi"/> </a>
        <a href = "/wyloguj"> <input type="submit" value="Wyloguj sie"/></a>
        </div>
        <br/>
        <br/>
        <div class="body">
        <td>
        <c:forEach var="element" items="${pokoje}">
            <tr><a href="/zarezerwuj/${element.nazwa}/${data}"><input type="submit" value=${element.nazwa} /></a></td>
        </c:forEach>
        </td>
        <td>
        <c:forEach var="element" items="${zajete}">
            <tr> ${element.nazwa} </tr>
        </c:forEach>
        </td>
        </div>
    </body>
</html>