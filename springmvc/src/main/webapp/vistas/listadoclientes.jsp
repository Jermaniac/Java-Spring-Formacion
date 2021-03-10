<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscar Clientes</title>
<link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-3.5.1.slim.min.js"></script>
<script type="text/javascript" src="../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js/popper.min.js"></script>
</head>
<body>

	<div class="container">
		<div>
			<table class="table table-dark">
				<thead>
					<tr>
						<th>Nif</th>
						<th>Nombre</th>
						<th>FechaAlta</th>
						<th>Provincia</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${datos}" var="cte">
						<tr>
							<td>${cte.nif}</td>
							<td>${cte.nombre} ${cte.apellidos}</td>
							<td>${cte.fechaAlta}</td>
							<td>${cte.provincia} </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


	</div>

</body>
</html>