<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
    <head>
        <title>Strona Glowna</title>
    </head>
    <body>
        <a href = "/stronaGlowna.html">Strona Glowna</a> |
        <a href = "/listaKlientow.html"> Obsluga Klienta </a> |
        <a href = "/listaUslug.html"> Uslugi </a> |
        <a href = "/wyloguj"> Wyloguj sie</a>
        <br/>
        <tiles:insertAttribute name="content"></tiles:insertAttribute>
    </body>
</html>