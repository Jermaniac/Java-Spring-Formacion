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
		<form:form method="POST" modelAttribute="cliente" action="alta">
			<div class="form-group">
				<label for="nif">NIF</label>
				<form:input path="nif" type="text" id="nif" cssClass="form-control" />
				<form:errors path="nif" />
				<span class="nif.errors"></span>
			</div>
			<div class="form-group">
				<label for="nombre">Nombre</label>
				<form:input path="nombre" type="text" id="nombre"
					cssClass="form-control" />
				<form:errors path="nombre" />
			</div>
			<div class="form-group">
				<label for="apellidos">Apellidos</label>
				<form:input path="apellidos" type="text" id="apellidos"
					cssClass="form-control" />
				<form:errors path="apellidos" />
			</div>
			<div class="form-group">
				<label for="provincia">Provincia</label>
				<form:select path="provincia" id="provincia"
					items="${lasProvincias}" cssClass="form-control"></form:select>
				<form:errors path="provincia"></form:errors>

			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Enviar</button>
			</div>
		</form:form>
		<c:if test="${errorAlta != null}">
			<div class="alert alert-danger">Error: ${errorAlta}</div>
		</c:if>
		<c:if test="${altaOK != null}">
			<div class="alert alert-success">Exito: ${altaOK}</div>
		</c:if>

	</div>

</body>
</html>