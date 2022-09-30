<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Wprowadzone banknoty</title>
</head>
<body>
<%@include file="../header.jspf" %>
<div>
    <h2>Moje wprowadzone banknoty</h2>
    <p><a href="/user/notes/edit/0">Dodaj nowy banknot...</a></p>
    <table>
        <thead>
        <tr>
            <th>nominał</th>
            <th>emisja</th>
            <th>numer seryjny</th>
            <th>lokalizacja</th>
            <th>data</th>
            <th>Notatka:</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.content}" var="noteSpot">
            <tr>
                <td><img src="data:image/jpeg;base64,${noteSpot.noteTypeDto.image}"
                         alt="${noteSpot.noteTypeDto.denomination}" width="62"/></td>
                <td>${noteSpot.noteTypeDto.edition}</td>
                <td>${noteSpot.noteSerialNumber}</td>
                <td>${noteSpot.place.place}</td>
                <td>${noteSpot.spotTime}</td>
                <td>${noteSpot.description}</td>
                <td>
                    <a href="/user/notes/show/${noteSpot.id}">Pokaż</a>
                    <a href="/user/notes/edit/${noteSpot.id}">Edytuj</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    Strona: ${page.number + 1} z ${page.totalPages} Przejdź:
    <c:forEach begin="1" end="${page.totalPages}" var="itemPages">
        <c:choose>
            <c:when test="${itemPages == (page.number + 1)}">${itemPages}</c:when>
            <c:otherwise><a href="/user/notes/spots/${itemPages}">${itemPages}</a></c:otherwise>
        </c:choose>
    </c:forEach>
</div>
<%@include file="../footer.jspf" %>
</body>
</html>
