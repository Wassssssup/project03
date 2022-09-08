<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 관리</title>
</head>
<body>
<div id="wrap" align="center" method="post" name="frm">
	<h1>정보 삭제</h1>
		<form action="moviedelete.do">
			<table>
				<tr>
					<td>
						<c:choose>
							<c:when test="${empty movie.poster }">
								<img src="images/noimage.gif">
							</c:when>
							<c:otherwise>
								<img src="images/${movie.poster }" width="200" height="300">
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<table>
							<tr>
								<th style="width:80px">제목</th>
								<td>${movie.title }</td>
							</tr>
							<tr>
								<th>감독</th>
								<td>${movie.director }</td>
							</tr>
							<tr>
								<th>배우</th>
								<td>${movie.actor }</td>
							</tr>
							<tr>
								<th>사진</th>
								<td>${movie.poster }</td>
							</tr>
							<tr>
								<th>시놉시스</th>
								<td><div style="height:220px; width:100%">${movie.synopsis }</div>
							</tr>
						</table>
					</td>
				</tr>
			</table><br>
			<input type="hidden" name="code" value="${movie.code }">
			<input type="submit" value="삭제">
			<input type="button" value="목록" onclick="location.href='movielist.do'">
		</form>
</div>
</body>
</html>