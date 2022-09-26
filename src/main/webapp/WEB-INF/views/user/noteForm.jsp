<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edycja banknotu</title>
</head>
<body>
<%@include file="../header.jspf" %>
<table>
    <form:form method="post" modelAttribute="noteSpotDto">
        <form:hidden path="id"/>
        <tr>
            <td>Nominał:</td>
            <td>
                <c:forEach items="${noteTypes}" var="noteType">
                    <form:radiobutton id="noteType${noteType.id}" path="noteTypeDto.id" value="${noteType.id}" />
                    <label for="noteType${noteType.id}">
                        <img src="data:image/jpeg;base64,${noteType.image}" alt="${noteType.denomination}" width="125"/>
                    </label>
                </c:forEach>
            </td>
            <td><form:errors path="noteTypeDto" cssClass="error"/></td>
        </tr>
        <%--        <tr>--%>
        <%--            <td>Emisja:</td>--%>
        <%--            <td><form:input path="edition" type="number" min="1994" max="2050"/></td>--%>
        <%--            <td><form:errors path="edition" cssClass="error"/></td>--%>
        <%--        </tr>--%>


        <tr>
            <td colspan="3">
                <p id="message-box"><c:if test="${message != null}"> ${message}</c:if></p>
                <form:button type="submit">Zapisz</form:button>
            </td>
        </tr>
    </form:form>
</table>
<p><a href="/admin/noteTypes">Wróć</a></p>
<%@include file="../footer.jspf" %>
</body>
</html>
