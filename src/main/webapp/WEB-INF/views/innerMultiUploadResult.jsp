<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	writer : ${writer }<br>
	<c:forEach items="${files }" var="file">
	<p>filename : ${file }<br>
		<img src="${file }"></p>
	</c:forEach>
	

</body>
</html>