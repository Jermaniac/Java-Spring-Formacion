<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-3.5.1.slim.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/popper.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>Error en la aplicacion</h1>
			<p>Fecha: ${fechaHora}</p>
			<p>Mensaje: ${elError.message}</p>
		</div>
	</div>

</body>
</html>