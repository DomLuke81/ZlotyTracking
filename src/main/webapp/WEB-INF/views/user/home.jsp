<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
</head>
<body>
<%@include file="../header.jspf" %>
Strona użytkownika:
<p>Id: ${user.id}</p>
<p>Pass: ${user.password}</p>
<p>accountNonExpired: ${user.firstName}</p>
<p>accountNonLocked: ${user.email}</p>
<p>credentialsNonExpired: ${user.active}</p>
<p>authorities: <c:forEach items="${user.roles}" var="item">${item.role}</c:forEach></p>
</body>
</html>
