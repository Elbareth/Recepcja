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
        <h1>Lista Uslug</h1>
        <div class="menu">
        <a href = "/stronaGlowna"><input type="submit" value="Strona Glowna"/></a>
        <a href = "/listaKlientow"> <input type="submit" value="Obsluga Klienta"/> </a>
        <a href = "/listaUslug"> <input type="submit" value="Uslugi"/> </a>
        <a href = "/wyloguj"> <input type="submit" value="Wyloguj sie"/></a>
        </div>
        <br/>
        <br/>
        <div class="body">
        <c:forEach var="element" items="${uslugiLuksusowe}">
            <a href="/listaKlientowUslug/${element.nazwa}"><input type="submit" value=${element.nazwa} /></a>
            <a href="/usunUsluge/${element.nazwa}"><img src="/resources/delete1.png"/></a> <br/>
        </c:forEach>
        <br/>
        <s:form method="post" action = "/edytujUslugi" modelAttribute="uslugiLuksusowe" enctype="multipart/form-data"> <input type="submit" value="Edytuj uslugi"/></s:form>
        <br/>
        <a href="/dodajUslugi"><input type="submit" value="Dodaj usluge"/></a>
        </div>
    </body>
</html>