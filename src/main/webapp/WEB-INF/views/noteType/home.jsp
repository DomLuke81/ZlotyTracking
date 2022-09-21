<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Emisje banknotów</title>
</head>
<body>
<%@include file="../header.jspf" %>
<div>
    <h2>Emisje banknotów</h2>
    <p><a href="/admin/noteTypes/edit/0">Dodaj emisję banknotów</a></p>
    <table>
        <thead>
        <tr>
            <th>nominał</th>
            <th>emisja</th>
            <th>wzór</th>
            <th>usunięty?</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.content}" var="noteType">
            <tr>
                <td>${noteType.denomination}</td>
                <td>${noteType.edition}</td>
                <td><img src="data:image/jpeg;base64,${noteType.image}" /></td>
                <c:choose>
                    <c:when test="${noteType.active}">
                        <td></td>
                        <td>
                            <a href="/admin/noteTypes/edit/${noteType.id}">Edytuj</a>
                            <a href="/admin/noteTypes/delete/${noteType.id}">Usuń (bezpiecznie)</a>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>Usunięty</td>
                        <td>
                            <a href="/admin/noteTypes/edit/${noteType.id}">Edytuj</a>
                            <a href="/admin/noteTypes/delete/${noteType.id}">Przywróć</a>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    Strona: ${page.number + 1} z ${page.totalPages} Przejdź:
    <c:forEach begin="1" end="${page.totalPages}" var="itemPages">
        <c:choose>
            <c:when test="${itemPages == (page.number + 1)}">${itemPages}</c:when>
            <c:otherwise><a href="/admin/noteTypes/page/${itemPages}">${itemPages}</a></c:otherwise>
        </c:choose>
    </c:forEach>
</div>
<%@include file="../footer.jspf" %>
</body>
</html>
