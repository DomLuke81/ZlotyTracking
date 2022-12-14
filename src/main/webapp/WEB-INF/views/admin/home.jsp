<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Panel administratora</title>
</head>
<body>
<%@include file="../header.jspf" %>
<table>
    <tr>
        <td>
            <h2>Ostatnie kody pocztowe</h2>
            <table onclick="location.href='/admin/zipCodes'" title="Kliknij - pokaż wszystkie">
                <thead>
                <tr>
                    <th>kod pocztowy</th>
                    <th>miejscowość</th>
                    <th>powiat</th>
                    <th>województwo</th>
                </tr>
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
        </td>
        <td rowspan="3">
            <h2>Ostatnie emisje banknotów</h2>
            <table onclick="location.href='/admin/noteTypes'" title="Kliknij - pokaż wszystkie">
                <thead>
                <tr>
                    <th>nominał</th>
                    <th>emisja</th>
                    <th>wzór</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${noteTypes}" var="noteType">
                    <tr>
                        <td>${noteType.denomination}</td>
                        <td>${noteType.edition}</td>
                        <td><img src="data:image/jpeg;base64,${noteType.image}" width="250" height="125"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
</table>
<%@include file="../footer.jspf" %>
</body>
</html>
