<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Strona użytkownika</title>
</head>
<body>
<%@include file="../header.jspf" %>

<h2>Moje ostatnio wprowadzone banknoty</h2>
<p><a href="/user/notes/edit/0">Dodaj nowy banknot...</a></p>


<%@include file="../footer.jspf" %>
</body>
</html>
