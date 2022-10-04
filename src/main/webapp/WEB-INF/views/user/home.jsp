<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Strona użytkownika</title>
</head>
<body>
<%@include file="../header.jspf" %>
<table>
    <tr>
        <td>
            <p><a href="/user/notes/edit/0">Dodaj nowy banknot...</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <h2><br/>Moje ostatnio wprowadzone banknoty</h2>
            <table onclick="location.href='/user/notes/spots'" title="Kliknij - pokaż wszystkie">
                <thead>
                <tr>
                    <th>nominał</th>
                    <th>emisja</th>
                    <th>numer seryjny</th>
                    <th>lokalizacja</th>
                    <th>data</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${noteSpots}" var="noteSpot">
                        <tr>
                            <td><img src="data:image/jpeg;base64,${noteSpot.noteTypeDto.image}"
                                     alt="${noteSpot.noteTypeDto.denomination}" width="62"/></td>
                            <td>${noteSpot.noteTypeDto.edition}</td>
                            <td>${noteSpot.noteSerialNumber}</td>
                            <td>${noteSpot.place.place}</td>
                            <td>${noteSpot.spotTime}</td>
                        </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <h2><br/>Moje ostatnie trafienia</h2>
            <table onclick="location.href='/user/notes/hits'" title="Kliknij - pokaż wszystkie">
                <thead>
                <tr>
                    <th>nominał</th>
                    <th>emisja</th>
                    <th>numer seryjny</th>
                    <th>liczba trafień</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${noteHits.content}" var="noteHit">
                        <tr>
                            <td><img src="data:image/jpeg;base64,${noteHit.noteType.image}"
                                     alt="${noteHit.noteType.denomination}" width="62"/></td>
                            <td>${noteHit.noteType.edition}</td>
                            <td>${noteHit.serialNumber}</td>
                            <td>${noteHit.spots.size()}</td>
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
