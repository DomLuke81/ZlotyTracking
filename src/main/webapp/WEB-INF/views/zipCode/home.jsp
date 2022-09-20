<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kody pocztowe</title>
</head>
<body>
<%@include file="../header.jspf" %>
<div>
    <h2>Kody pocztowe</h2>
    <p><a href="edit/0"></a></p>
    <table>
        <thead>
        <th>kod pocztowy</th>
        <th>miejscowość</th>
        <th>powiat</th>
        <th>województwo</th>
        <th></th>
        </thead>
        <tbody>
        <c:forEach items="${zipCodes}" var="zipCode">
            <tr>
                <td>${zipCode.zipCode}</td>
                <td>${zipCode.place}</td>
                <td>${zipCode.county}</td>
                <td>${zipCode.voivodeship}</td>
                <td>
                    <a href="edit/${zipCode.id}">Edytuj</a>
                    <a href="delete/${zipCode.id}">Usuń z użycia</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="../footer.jspf" %>
</body>
</html>
