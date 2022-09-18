<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
</head>
<body>
  <sec:authorize access="hasAuthority('ADMIN')">
    <p>Admin</p>
  </sec:authorize>
  <sec:authorize access="hasAuthority('USER')">
    User
  </sec:authorize>
  <sec:authorize access="isAuthenticated()">
    <p>Zalogowany jako: <sec:authentication property="principal.username"/></p>
    <p>Posiada role: <sec:authentication property="authorities"/></p>
  </sec:authorize>
</body>
</html>
