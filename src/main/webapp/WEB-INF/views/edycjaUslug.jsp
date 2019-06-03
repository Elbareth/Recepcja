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
        <h1>Edycja uslug</h1>
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
        <s:form action="/edycja" method="post" modelAttribute="uslugiLuksusowe" enctype="multipart/form-data">
        <tr>
            <td>Nazwa: <input type="text" value="${element.nazwa}"/></td>
            <td>Cena: <input type="text" value="${element.cena}"/></td>
            <td><input type="submit" value="Zatwierdz"/> <br/></td>
        </tr>
        </s:form>
        </c:forEach>
        </div>
    </body>
</html>