<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja lokalizacji</title>
</head>
<body>
<%@include file="../header.jspf" %>
<c:if test="${message != null}">${message}</c:if>
<table>
    <form:form method="post" modelAttribute="locationZipCode">
        <form:hidden path="id"/>
        <form:hidden path="active"/>
        <tr>
            <td>Kod pocztowy:</td>
            <td><form:input path="zipCode"/></td>
            <td><form:errors path="zipCode" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Miejscowość:</td>
            <td><form:input path="place"/></td>
            <td><form:errors path="place" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Powiat</td>
            <td><form:input path="county"/></td>
            <td><form:errors path="county" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Województwo</td>
            <td><form:input path="voivodeship"/></td>
            <td><form:errors path="voivodeship" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <form:button type="submit">Zapisz</form:button>
            </td>
        </tr>
    </form:form>
</table>
<p><a href="/admin/zipCodes">Wróć</a></p>
<%@include file="../footer.jspf" %>
</body>
</html>
