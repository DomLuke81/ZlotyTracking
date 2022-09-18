<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
    <div>
    <a href="<c:url value="/login"/>">Masz już konto, przejdź do strony logowania</a>
    </div>
    <form:form method="post" modelAttribute="user">
        <div>
            <label id="email">Email:</label>
            <form:input path="email" id="email"/>
            <form:errors path="email"/>
        </div>
        <div>
            <label id="firstName">Imię:</label>
            <form:input path="firstName" id="firstName"/>
            <form:errors path="firstName"/>
        </div>
        <div>
            <label id="password">Hasło:</label>
            <form:password path="password" id="password"/>
            <form:errors path="password"/>
        </div>
        <div>
            <button type="submit">Zarejestruj</button>
        </div>
    </form:form>
    <div>
        <c:if test="${registered == true}">
            <h2>Zarejestrowano użytkownika.</h2>
            Możesz się teraz <a href="<c:url value="/login"/>">zalogować</a>.
        </c:if>
    </div>
</body>
</html>
