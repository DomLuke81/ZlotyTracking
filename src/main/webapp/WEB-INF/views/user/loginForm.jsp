<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Logowanie</title>
</head>
<body>
<div>
    Jeśli nie masz jeszcze konta, przejdź do <a href="<c:url value="/login/register"/>">strony rejestracji</a>
</div>
<form:form method="post" modelAttribute="user">
    <div>
        <label id="email">Email:</label>
        <form:input path="email" id="email"/>
        <form:errors path="email"/>
    </div>
    <div>
        <label id="password">Hasło:</label>
        <form:password path="password" id="password"/>
        <form:errors path="password"/>
    </div>
    <div>
        <button type="submit">Zaloguj</button>
    </div>
</form:form>
<div>
    <c:if test="${param.error == true}">
        <p>Nazwa użytkownika lub hasło są niepoprawne.</p>
    </c:if>
</div>
</body>
</html>
