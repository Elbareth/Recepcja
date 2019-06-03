<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Zaloguj sie</title>
        <link rel="stylesheet" type="text/css" href="/resources/style.css"/>
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>Witaj w wirtualnej recepcji hotelu! By kontynuowac zaloguj sie: </h1> <br/>
        <div class="body">
        <s:form action="/login" method="post" modelAttribute="pracownik" enctype="multipart/form-data">
            Login: <s:input required = "required" placeholder = "Wprowadz login" title="Podany przez Ciebie login lub haslo sa nie poprawne" path="login"/><br/>
            Haslo: <s:password required = "required" placeholder = "Wprowadz haslo" path = "haslo"/><br/>
            <input type = "submit" value="Zaloguj sie"/><br/>
        </s:form>
         <h3>Nie masz utworzonego u nas jeszcze konta? <a href="/zarejestruj">Zarejestruj sie</a></h3>
         </div>
    </body>
</html>