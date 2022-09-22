<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja emisji banknotów</title>
</head>
<body>
<%@include file="../header.jspf" %>
<c:if test="${message != null}">${message}</c:if>
<table>
    <form:form method="post" modelAttribute="noteTypeDto" enctype="multipart/form-data">
        <form:hidden path="id"/>
        <form:hidden path="active"/>
        <tr>
            <td>Nominał:</td>
            <td><form:input path="denomination" type="number" min="10" max="2000000000"/></td>
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
            <td><input type="file" name="file"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <form:button type="submit">Zapisz</form:button>
            </td>
        </tr>
    </form:form>
</table>
<p><a href="/admin/noteTypes">Wróć</a></p>
<%@include file="../footer.jspf" %>
</body>
</html>
