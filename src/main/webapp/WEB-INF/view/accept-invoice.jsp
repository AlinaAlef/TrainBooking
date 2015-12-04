<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.text_ua" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Нове замовлення | Віртуальна Залізнична Каса</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div style="text-align: right">
    <a class="link-cancel" href="/login">Вийти</a>
</div>
<div id="trains-result">
        <h1 class="title-text-shadow">
            Ваш квиток:
        </h1>
        <h3>Дата ${departureDate}</h3>
        <%--<form action="/create-invoice" method="post">--%>
            <table style="margin: auto">
                <tr class="odd">
                    <th>№ поїзда</th>
                    <th>Звідки</th>
                    <th>Куди</th>
                    <th>Відправлення</th>
                    <th>Прибуття</th>
                    <th>Тип вагону </th>
                    <th>Ціна</th>
                </tr>
                <%--<c:forEach var="route" varStatus="loopStatus" items="${routes}">--%>
                    <tr class="even">
                        <td>${train.id}</td>
                        <td>${route.departureStation}</td>
                        <td>${route.destinationStation}</td>
                        <td>${route.departureTime}</td>
                        <td>${route.destinationTime}</td>
                        <c:choose>
                            <c:when test="${wagonType eq 'suite'}">
                                <td><c:out value="Люкс"/></td>
                            </c:when>
                            <c:when test="${wagonType eq 'coupe'}">
                                <td><c:out value="Купе"/></td>
                            </c:when>
                            <c:when test="${wagonType eq 'berth'}">
                                <td><c:out value="Плацкарт"/></td>
                            </c:when>
                        </c:choose>
                        <%--<td>${price.suitePrice}</td>--%>
                        <c:choose>
                        <c:when test="${wagonType eq 'suite'}">
                            <td><c:out value="${price.suitePrice}"/></td>
                        </c:when>
                        <c:when test="${wagonType eq 'coupe'}">
                            <td><c:out value="${price.coupePrice}"/></td>
                        </c:when>
                        <c:when test="${wagonType eq 'berth'}">
                            <td><c:out value="${price.berthPrice}"/></td>
                        </c:when>
                        </c:choose>
                    </tr>
            </table>
</div>
</body>
</html>