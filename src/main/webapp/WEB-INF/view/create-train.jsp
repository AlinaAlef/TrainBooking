<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Рух поїздів | Віртуальна Залізнична Каса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div id="wrapper">
		<h1 class="title-text-shadow">Внесення до розкладу нового поїзда</h1>
		<h3>
			<%--<c:if test="${not empty passwordNotMatch and passwordNotMatch eq 'true'}">--%>
		      <%--Введені паролі не співпадають--%>
		    <%--</c:if>--%>
		    <%--<c:if test="${not empty userNotCreated and userNotCreated eq 'true'}">--%>
		        <%--з такими даними вже зареєстрований--%>
		    <%--</c:if>--%>
		</h3>
		<form action="/check-train" method="post">
			<table style="margin: auto; text-align: left">
				<tr>
					<td>Number:</td>
					<td><input name="id" value="${param.id}"
							   type="int" size="2" maxlength="3"
							   required /></td>
				</tr>
				<tr>
					<td>Date:</td>
					<td><input name="date" value="${param.date}"
					type="date" size="35" maxlength="35" required /></td>
				</tr>
				<tr>
					<td>Date:</td>
					<td><input name="date" value="${param.date}"
							   type="date" size="35" maxlength="35" required /></td>
				</tr>
				<tr>
					<td>Suite:</td>
					<td><input name="suiteReserved" value="${param.suiteReserved}"
							   type="int" size="2" maxlength="3"
							   required /></td>
				</tr>
				<tr>
					<td>Couple:</td>
					<td><input name="coupleReserved" value="${param.coupleReserved}"
							   type="int" size="2" maxlength="3"
							   required /></td>
				</tr>
				<tr>
					<td>Berth:</td>
					<td><input name="berthReserved" value="${param.berthReserved}"
					type="int" size="2" maxlength="3"
					required /></td>
				</tr>
			</table>
			<table style="margin: auto">
				<tr>
					<td><input type="submit" class="button-accept" name="create"
						value="Підтвердити" /></td>
					<td><a class="link-cancel" href="/login">
					Відмінити</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>