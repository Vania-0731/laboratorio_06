<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 23/04/2025
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<div class="alert alert-danger" role="alert">
    <h4 class="alert-heading">¡Error!</h4>
    <p>No se pudieron cargar los cursos desde la base de datos.</p>
    <p>Por favor, intente nuevamente más tarde o contacte con el administrador.</p>
</div>
<a href="CursoServlet?accion=listar" class="btn btn-primary">Volver a intentar</a>
</body>
</html>
