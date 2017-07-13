<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="innerUpload" method="post" enctype="multipart/form-data">
		<input type="text" name="writer" placeholder="작성자">
		<input type="file" name="upFile" value="파일 선택">
		<input type="submit" value="제출">
	</form>

</body>
</html>