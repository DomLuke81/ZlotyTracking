<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Banknot</title>
    <%--    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/mapDistance.css"/>"/>
    <script type="module" src="<c:url value="/js/mapShowDistance.js"/>"></script>
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
                        <p hidden class="hiddenPlace">${spot.place.zipCode}, ${spot.place.place}</p>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
        <td>
            <div id="container">
                <div id="map"></div>
                <div id="sidebar">
                    <div id="directions-panel"></div>
                </div>
            </div>
        </td>
    </tr>
</table>
<%@include file="../footer.jspf" %>
<script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&callback=initMap&v=weekly"
        defer
></script>
</body>
</html>
