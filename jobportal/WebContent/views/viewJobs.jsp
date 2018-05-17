<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jobs</title>
</head>
<body>
	<div class="container">
		<h1 style="text-align: center;"> All jobs</h1>	
		
		<table class="table table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Message</th>
		      <th scope="col">Job</th>
		      <th scope="col">Poster Name</th>
		      <th scope="col">Phone</th>
		      <th scope="col">Password</th>
		    </tr>
		  </thead>
		  <tbody>
		   <c:forEach items = "${jobs}" var = "job">		 
			 	<tr>
			      <th scope="row"><c:out value = "${job.id}"/></th>
			      <td><c:out value = "${job.messageBody}"/></td>
			      <td><c:out value = "${job.jobName}"/></td>
			      <td><c:out value = "${job.posterName}"/></td>
			      <td><c:out value = "${job.contactPhone}"/></td>
			      <td><c:out value = "${job.jobPostingPassword}"/></td>
			    </tr>   
	      	</c:forEach>	      	
		  </tbody>
		</table>		
	</div>

</body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</html>
