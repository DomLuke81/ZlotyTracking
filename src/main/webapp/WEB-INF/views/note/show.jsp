<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Banknot</title>
</head>
<body>
<%@include file="../header.jspf" %>
<table>
    <tr>
        <td>
            <img src="data:image/jpeg;base64,${note.noteType.image}" alt="${note.noteType.denomination}PLN"/>
        </td>
        <td>
            <table>
                <tr>
                    <td>Nominał:</td>
                    <td>${note.noteType.denomination}</td>
                </tr>
                <tr>
                    <td>Emisja:</td>
                    <td>${note.noteType.edition}</td>
                </tr>
                <tr>
                    <td>Numer seryjny:</td>
                    <td>${note.serialNumber}</td>
                </tr>
            </table>
        </td>
    <tr>
        <td>
            <table>
                <thead>
                <tr>
                    <th>data</th>
                    <th>miejsce</th>
                    <th>użytkownik</th>
                    <th>opis</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${note.spots}" var="spot">
                    <tr>
                        <td>${spot.spotTime}</td>
                        <td>${spot.place.place}</td>
                        <td>${spot.userName}</td>
                        <td>${spot.description}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
        <td>
            mapa
        </td>
    </tr>
</table>
<%@include file="../footer.jspf" %>
</body>
</html>
