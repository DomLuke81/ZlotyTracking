<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edycja banknotu</title>
</head>
<body>
<%@include file="../header.jspf" %>
<form:form method="post" modelAttribute="noteSpotDto">
    <table>
        <form:hidden path="id"/>
        <tr>
            <td>Nominał:</td>
            <td colspan="4">
                <c:forEach items="${noteTypes}" var="noteType">
                    <input type="radio" id="noteTypeDenomination${noteType.id}" name="denominationRadios"
                           class="denominationRadios" value="${noteType.denomination}"
                           <c:if test="${noteSpotDto.denominationRadios == noteType.denomination}">checked</c:if>
                     required/>
                    <label for="noteTypeDenomination${noteType.id}">
                        <img src="data:image/jpeg;base64,${noteType.image}" alt="${noteType.denomination}" width="125"/>
                    </label>
                </c:forEach>
            </td>
            <td rowspan="2"><form:errors path="noteTypeDto" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Emisja:</td>
            <td id="edition-cell" colspan="4">
                <input type="hidden" id="noteTypeDtoId" value="${noteSpotDto.noteTypeDto.id}"/>
            </td>
        </tr>
        <tr>
            <td>Numer seryjny:</td>
            <td colspan="4"><form:input path="noteSerialNumber" maxlength="9" pattern="[A-z]{2}\d{7}" placeholder="AA0000000"
                            required="required"/></td>
            <td><form:errors path="noteSerialNumber" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Lokalizacja:<br/><small>(Gdzie dostałeś banknot?)</small></td>
            <td><s>kod pocztowy:</s></td>
            <td>
                <label for="voivodeships">województwo:</label><br/>
                <select name="voivodeships" id="voivodeships">
                    <option value="">--wybierz województwo--</option>
                    <c:forEach items="${voivodeships}" var="v">
                        <option value="${v}" ${v == noteSpotDto.place.voivodeship ? "selected" : ""}>${v}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <label for="counties">powiat:</label><br/>
                <select name="counties" id="counties">
                    <c:if test="${counties != null}">
                        <option value="">--wybierz powiat--</option>
                        <c:forEach items="${counties}" var="c">
                            <option value="${c}" ${c == noteSpotDto.place.county ? "selected" : ""}>${c}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
            <td>
                <label for="place">miejscowość:</label><br/>
                <select name="place" id="place" required>
                    <c:if test="${places != null}">
                        <option value="">--wybierz miejscowość--</option>
                        <c:forEach items="${places}" var="p">
                            <option value="${p.id}" ${p.place == noteSpotDto.place.place ? "selected" : ""}>
                                    ${p.place}
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
            <td><form:errors path="place" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Czas:</td>
            <td colspan="4"><form:input type="date" path="spotTime" required="required"/></td>
            <td><form:errors path="spotTime" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Notatka:</td>
            <td colspan="4"><form:input path="description" size="100" maxlength="255"/></td>
            <td><form:errors path="description" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <p id="message-box"><c:if test="${message != null}"> ${message}</c:if></p>
                <form:button type="submit">Zapisz</form:button>
            </td>
        </tr>
    </table>
</form:form>
<p><a href="/user/notes/spots">Wróć</a></p>
<%@include file="../footer.jspf" %>
<script src="<c:url value="/js/noteSpotForm.js"/>" type="text/javascript"></script>
</body>
</html>
