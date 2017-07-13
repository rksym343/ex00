<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="resources/dist/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	.row{
		margin-bottom: 4px;
		margin-top: 4px;
		
	}
	#row1 div{
		border : 1px solid black;
		padding : 10px;
	}
	#row2 div{
		border : 1px solid red;
		padding : 10px;
	}
	#row3 div{
		border : 1px solid blue;
		padding : 10px;
	}
	#row4 div{
		border : 1px solid green;
		padding : 10px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row" id="row1">
			<div class="col-md-1">01.col-md-1</div>
			<div class="col-md-1">02.col-md-1</div>
			<div class="col-md-1">03.col-md-1</div>
			<div class="col-md-1">04.col-md-1</div>
			<div class="col-md-1">05.col-md-1</div>
			<div class="col-md-1">06.col-md-1</div>
			<div class="col-md-1">07.col-md-1</div>
			<div class="col-md-1">08.col-md-1</div>
			<div class="col-md-1">09.col-md-1</div>
			<div class="col-md-1">10.col-md-1</div>
			<div class="col-md-1">11.col-md-1</div>
			<div class="col-md-1">12.col-md-1</div>
		</div>
		<div class="row" id="row2">
			<div class="col-md-8">.col-md-8</div>
			<div class="col-md-4">.col-md-4</div>
		</div>
		<div class="row" id="row3">
			<div class="col-md-4">.col-md-4</div>
			<div class="col-md-4">.col-md-4</div>
			<div class="col-md-4">.col-md-4</div>
		</div>
		<div class="row" id="row4">
			<div class="col-md-6">.col-md-6</div>
			<div class="col-md-6">.col-md-6</div>
		</div>
	</div>

</body>
</html>