<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Strona główna</title>
</head>
<body>
<%@include file="header.jspf"%>
Strona główna
<table>
    <tr>
        <td>
            <h2><br/>Ostatnio wprowadzone banknoty</h2>
            <table>
                <thead>
                <tr>
                    <th>nominał</th>
                    <th>emisja</th>
                    <th>numer seryjny</th>
                    <th>lokalizacja</th>
                    <th>data</th>
                    <th>użytkownik</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${noteSpots.content}" var="noteSpot">
                    <tr>
                        <td><img src="data:image/jpeg;base64,${noteSpot.noteTypeDto.image}"
                                 alt="${noteSpot.noteTypeDto.denomination}" width="62"/></td>
                        <td>${noteSpot.noteTypeDto.edition}</td>
                        <td>${noteSpot.noteSerialNumber}</td>
                        <td>${noteSpot.place.place}</td>
                        <td>${noteSpot.spotTime}</td>
                        <td>${noteSpot.userName}</td>
                        <td><a href="/notes/${noteSpot.id}">Pokaż</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <h2><br/>Ostatnie trafienia</h2>
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
                <c:forEach items="${noteHits.content}" var="noteHit">
                    <tr>
                        <td><img src="data:image/jpeg;base64,${noteHit.noteType.image}"
                                 alt="${noteHit.noteType.denomination}" width="62"/></td>
                        <td>${noteHit.noteType.edition}</td>
                        <td>${noteHit.serialNumber}</td>
                        <td>${noteHit.spots.size()}</td>
                        <td><a href="/notes/${noteHit.spots[0].id}">Pokaż</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
</table>

<%@include file="footer.jspf" %>
</body>
</html>
