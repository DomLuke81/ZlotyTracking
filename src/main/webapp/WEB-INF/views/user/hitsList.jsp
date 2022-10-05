<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Trafienia banknotów</title>
</head>
<body>
<%@include file="../header.jspf" %>
<div>
    <h2>Moje trafienia banknotów</h2>
    <p><a href="/user/notes/edit/0">Dodaj nowy banknot...</a></p>
    <table>
        <thead>
        <tr>
            <th>nominał</th>
            <th>emisja</th>
            <th>numer seryjny</th>
            <th>liczba trafień</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.content}" var="noteHit">
            <tr>
                <td><img src="data:image/jpeg;base64,${noteHit.noteType.image}"
                         alt="${noteHit.noteType.denomination}" width="62"/></td>
                <td>${noteHit.noteType.edition}</td>
                <td>${noteHit.serialNumber}</td>
                <td>${noteHit.spots.size()}</td>
                <td>
                    <a href="/user/notes/show/${noteHit.spots[0].id}">Pokaż</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    Strona: ${page.number + 1} z ${page.totalPages} Przejdź:
    <c:forEach begin="1" end="${page.totalPages}" var="itemPages">
        <c:choose>
            <c:when test="${itemPages == (page.number + 1)}">${itemPages}</c:when>
            <c:otherwise><a href="/user/notes/hits/${itemPages}">${itemPages}</a></c:otherwise>
        </c:choose>
    </c:forEach>
</div>
<%@include file="../footer.jspf" %>
</body>
</html>
