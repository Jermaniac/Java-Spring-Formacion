<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscar Clientes</title>
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-3.5.1.slim.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/popper.min.js"></script>
</head>
<body>

	<div class="container">
		<form:form method="POST" modelAttribute="datos" action="buscar">
			<div class="form-group">
				<label for="nif">NIF</label>
				<form:input path="nif" type="text" id="nif" cssClass="form-control" />
				<form:errors path="nif" />
			</div>

			<div class="form-group">
				<label for="buscarTodos">Buscar Todos</label>
				<form:checkbox path="buscarTodos" id="buscarTodos"
					cssClass="form-control" />
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-primary">Buscar</button>
			</div>
		</form:form>
		<c:if test="${hayError != null}">
			<div class="alert alert-danger">Error: ${errorAlta}</div>
		</c:if>
		<c:if test="${hayError == null}">
			<div class="alert alert-success">Exito buscando</div>
			<div>
				<table class="table table-dark">
					<thead>
						<tr>
							<th>Nif</th>
							<th>Nombre</th>
							<th>FechaAlta</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${losClientes}" var="cte">
							<tr>
								<td>${cte.nif}</td>
								<td>${cte.nombre}${cte.apellidos}</td>
								<td>${cte.fechaAlta}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>


	</div>

</body>
</html>