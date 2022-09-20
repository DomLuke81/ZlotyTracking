<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panel administratora</title>
</head>
<body>
<%@include file="../header.jspf" %>
<div>
    <h2>Kody pocztowe</h2>
    <table onclick="location.href='/admin/zipCodes'" title="Kliknij - pokaż wszystkie">
        <thead>
        <th>kod pocztowy</th>
        <th>miejscowość</th>
        <th>powiat</th>
        <th>województwo</th>
        </thead>
        <tbody>
        <c:forEach items="${zipCodes}" var="zipCode">
            <tr>
                <td>${zipCode.zipCode}</td>
                <td>${zipCode.place}</td>
                <td>${zipCode.county}</td>
                <td>${zipCode.voivodeship}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <h2>Emisje banknotów</h2>
    <table onclick="location.href='/admin/noteTypes'" title="Kliknij - pokaż wszystkie">
        <thead>
        <th>nominał</th>
        <th>emisja</th>
        <th>wzór</th>
        </thead>
        <tbody>
        <c:forEach items="${noteTypes}" var="noteType">
            <tr>
                <td>${noteType.value}</td>
                <td>${noteType.edition}</td>
                <td>obraz</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="../footer.jspf" %>
</body>
</html>
