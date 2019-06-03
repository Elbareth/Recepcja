<%@ page language="java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Zarejestruj sie</title>
        <link rel="stylesheet" type="text/css" href="/resources/style.css"/>
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>Rejestracja</h1>
        <div class="body">
        <s:form method = "post" action="/save" modelAttribute="pracownik" enctype="multipart/form-data">
            Login: <s:input required = "required" placeholder = "Wprowadz login" title="Tej login jest juz zajety" maxlength="50" path = "Login"/><br/>
            Haslo: <s:password required = "required" placeholder = "Wprowadz poprawne haslo" title ="Musi sie skladac z duzych i malych liter oraz cyfr" minlength = "8" maxlength="50" path = "Haslo" pattern="^[A-Za-z0-9]*$"/><br/>
            Imie: <s:input required = "required" placeholder = "Wprowadz imie" maxlength="50" minlength="3" path = "Imie"/><br/>
            Nazwisko: <s:input required = "required" placeholder = "Wprowadz nazwisko" maxlength="50" minlength = "3" path = "Nazwisko"/><br/>
            Identyfikator: <s:input required = "required" placeholder = "Wprowadz nadany identyfikator" maxlength="50" path = "Identyfikator"/><br/>
            Podpis: <s:input required = "required" placeholder = "Wprowadz poprawny podpis" maxlength="50" path = "Podpis" pattern = "^[A-Z][a-z]*[0-9]+[a-z]*$"/><br/>
            Email: <s:input required = "required" placeholder = "Wprowadz poprawny e-mail" maxlength="50" path = "Email" type = "email"/><br/>
            Numer Telefonu: <s:input required = "required" placeholder = "Wprowadz poprawny numer telefonu" maxlength="50" path = "NrTelefonu" pattern = "^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$"/><br/>
            <input type='submit' value ="Zarejestruj sie"/>
        </s:form>
        </div>
    </body>
</html>