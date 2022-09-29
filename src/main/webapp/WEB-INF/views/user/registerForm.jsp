<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
    <div>
    Masz już konto? Przejdź do <a href="<c:url value="/login"/>">strony logowania</a>
    </div>
    <form:form method="post" modelAttribute="user">
        <div>
            <label id="name">Nazwa:</label>
            <form:input path="name" id="name"/>
            <form:errors path="name"/>
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
