<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<c:set var="language"
	   value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	   scope="session"/>
<fmt:setLocale value="${language}" />
<%--path resources.i18n--%>
<fmt:setBundle basename="i18n.text" />

<html name="language" value="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><fmt:message key= "entry" /></title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body >
	<div id="wrapper">
		<h1 class="title-text-shadow">
			<fmt:message key= "welcomeTo" /><br /><fmt:message key= "virtualRailroadTicket" />
		</h1>
		<h3>
		   <c:if test="${not empty notActivated and notActivated eq 'true'}">
			   <fmt:message key= "yourAccount" />
		   </c:if>
		   <c:if test="${not empty notExists and notExists eq 'true'}">
			   <fmt:message key= "Accountnotfound" />
		   </c:if>
		</h3>
		<form action="/check-login" method="post">
			<table style="margin: auto">
				<tr>
					<td style="text-align: left">E-mail:</td>
					<td><input name="email" type="email" size="35" required /></td>
				</tr>
				<tr>
					<td style="text-align: left"><fmt:message key= "password" />:</td>
					<td><input name="password" type="password" size="35"
						maxlength="35" required /></td>
				</tr>
			</table>
			<table style="margin: auto">
				<tr>
					<td><input type="submit" class="button-accept"
					    name="userLogin"
						value=<fmt:message key= "user" /> /></td>
					<td><input type="submit" class="button-accept"
						name="adminLogin"
						value=<fmt:message key= "Administrator" /> /></td>
				</tr>
			</table>
		</form>
		<form action="/registration" method="get">
			<input type="submit" class="button-register"
			name="register" value=<fmt:message key= "Register" /> />
		</form>

		<li>
			<form>
				<label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-1">
					<input type="radio" id="option-1" class="mdl-radio__button" onchange="submit()"
						   name="language" value="en" ${language == 'en' ? 'checked' : ''}>
					<span class="mdl-radio__label">EN</span>
				</label>
				<label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-2">
					<input type="radio" id="option-2" class="mdl-radio__button" onchange="submit()"
						   name="language" value="ua" ${language == 'ua' ? 'checked' : ''}>
					<span class="mdl-radio__label">UA</span>
				</label>
			</form>
		</li>
	</div>
</body>

</html>