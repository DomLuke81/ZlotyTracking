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
            <td>
                    ${noteSpotDto.denominationRadios}
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
            <td id="edition-cell">
                <input type="hidden" id="noteTypeDtoId" value="${noteSpotDto.noteTypeDto.id}"/>
            </td>
        </tr>
        <tr>
            <td>Numer seryjny:</td>
            <td><form:input path="noteSerialNumber" maxlength="9" pattern="[A-z]{2}\d{7}" placeholder="AA0000000"/></td>
            <td><form:errors path="noteSerialNumber" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Notatka:</td>
            <td><form:input path="description"/></td>
            <td><form:errors path="description" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <p id="message-box"><c:if test="${message != null}"> ${message}</c:if></p>
                <form:button type="submit">Zapisz</form:button>
            </td>
        </tr>
    </table>
</form:form>
<p><a href="/admin/noteTypes">Wróć</a></p>
<%@include file="../footer.jspf" %>
<script src="<c:url value="/js/noteSpotForm.js"/>" type="text/javascript"></script>
</body>
</html>
