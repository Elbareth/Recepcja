<%@ page language="java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Lista Klientow</title>
        <link rel="stylesheet" type="text/css" href="/resources/style.css"/>
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1> Lista Gosci </h1>
        <div class="menu">
        <a href = "/stronaGlowna"><input type="submit" value="Strona Glowna"/></a>
        <a href = "/listaKlientow"> <input type="submit" value="Obsluga Klienta"/> </a>
        <a href = "/listaUslug"> <input type="submit" value="Uslugi"/> </a>
        <a href = "/wyloguj"> <input type="submit" value="Wyloguj sie"/></a>
        </div>
        <br/>
        <br/>
        <div class="body">
        <s:form method = "post" action = "/szukaj" modelAttribute="klient" enctype="multipart/form-data">
            <input type ="text" name="nazwisko"/>
            <input type="submit" value="Wyszukaj"/>
        </s:form>
        <br/>
        <br/>
        <table>
            <c:forEach var="element" items="${klient}" varStatus="status">
                <s:form method="post" action="/uaktualnij/${element.nazwisko}" modelAttribute="klient" enctype="multipart/form-data">
                <tr>
                    <td>  <input type="text" value="${element.imie}"/> </td>
                    <td>  <input type ="text" value="${element.nazwisko}"/></td>
                    <td>  <input type ="text" value="${element.email}" />  </td>
                    <td>  <input type ="text" value="${element.nrTelefonu}" /></td>
                    <td>  <input type ="text" value="${element.czyZaplacil}"/> </input></td>
                    <td> <a href="/zaplacone/${element.nazwisko}">${kwota[status.index]} zl </a> </td>
                    <td> <a href="/usun/${element.nazwisko}"><img src="/resources/delete1.png"/></a> </td>
                    <td> <input type="submit" value="Zapisz zmiany"/> </td>
                </tr>
                </s:form>
            </c:forEach>
        </table>
        <br/>
        </div>
    </body>
</html>