<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edycja emisji banknotów</title>
</head>
<body>
<%@include file="../header.jspf" %>
<table>
    <form:form method="post" modelAttribute="noteTypeDto" enctype="multipart/form-data">
        <form:hidden path="id"/>
        <form:hidden path="active"/>
        <tr>
            <td>Nominał:</td>
            <td><form:input path="denomination" type="number" min="10" max="2000000000" autofocus="true" /></td>
            <td><form:errors path="denomination" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Emisja:</td>
            <td><form:input path="edition" type="number" min="1994" max="2050"/></td>
            <td><form:errors path="edition" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Wzór:</td>
            <td><img src="data:image/jpeg;base64,${noteTypeDto.image}" /></td>
            <td><input id="file-input" type="file" name="file" accept="image/jpeg"/><br/>Max. rozmiar pliku to 1 MB.</td>
        </tr>
        <tr>
            <td colspan="3">
                <p id="file-message"><c:if test="${message != null}"> ${message}</c:if></p>
                <form:button type="submit">Zapisz</form:button>
            </td>
        </tr>
    </form:form>
</table>
<p><a href="/admin/noteTypes">Wróć</a></p>
<%@include file="../footer.jspf" %>
<script src="<c:url value="/js/noteTypeFormImage.js"/>" type="text/javascript"></script>
</body>
</html>
