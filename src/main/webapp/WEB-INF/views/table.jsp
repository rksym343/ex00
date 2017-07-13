<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style type="text/css">
	</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">WebSiteName</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="#">Home</a></li>
	      <li><a href="#">Page 1</a></li>
	      <li><a href="#">Page 2</a></li>
	    </ul>
	    <form class="navbar-form navbar-left">
	      <div class="input-group">
	        <input type="text" class="form-control" placeholder="Search">
	        <div class="input-group-btn">
	          <button class="btn btn-default" type="submit">
	            <i class="glyphicon glyphicon-search"></i>
	          </button>
	        </div>
	      </div>
	    </form>
	  </div>
	</nav>


	<div>
	
	<div class="col-md-2">
		<div class="list-group">
		  <a href="#" class="list-group-item active">First item</a>
		  <a href="#" class="list-group-item">Second item</a>
		  <a href="#" class="list-group-item">Third item</a>
		  <a href="#" class="list-group-item">Second item</a>
		  <a href="#" class="list-group-item">Third item</a>
		  <a href="#" class="list-group-item">Second item</a>
		  <a href="#" class="list-group-item">Third item</a>
		  <a href="#" class="list-group-item">Second item</a>
		  <a href="#" class="list-group-item">Third item</a>
		  <a href="#" class="list-group-item">Second item</a>
		  <a href="#" class="list-group-item">Third item</a>
		  <a href="#" class="list-group-item">Second item</a>
		  <a href="#" class="list-group-item">Third item</a>
		  <a href="#" class="list-group-item">Second item</a>
		  <a href="#" class="list-group-item">Third item</a>
		  
		</div>
	</div>
	
	<div class="col-md-10">
		<button type="button" class="btn btn-primary  btn-block" data-toggle="modal" data-target="#myModal">Primary</button>
		<button type="button" class="btn btn-default  btn-block">Default</button>
		
		 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>Some text in the modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
		
		<table class="table table-striped">
			<thead>
			<tr>
				<th>#</th>
				<th>Header</th>
				<th>Header</th>
				<th>Header</th>
				<th>Header</th>
			</tr>
			</thead>
			<tr>
				<td>1000</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
			</tr>
			<tr>
				<td>1000</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
			</tr>
			<tr>
				<td>1000</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
			</tr>
			<tr>
				<td>1000</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
			</tr>
			<tr>
				<td>1000</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
				<td>Header</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>