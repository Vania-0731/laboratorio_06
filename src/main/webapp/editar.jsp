<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 23/04/2025
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tecsup.laboratorio_06.model.Curso" %>
<%
    Curso curso = (Curso) request.getAttribute("curso");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Curso</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4 text-primary">
        <i class="bi bi-pencil-fill me-2"></i>Editar Curso
    </h2>
    <form action="CursoServlet" method="post" class="mt-3">
        <input type="hidden" name="accion" value="actualizar">
        <div class="mb-3">
            <label class="form-label">Código:</label>
            <input type="text" name="codigo" value="<%= curso.getCodigo() %>" readonly class="form-control" placeholder="Código del curso">
        </div>
        <div class="mb-3">
            <label class="form-label">Nombre:</label>
            <input type="text" name="nombre" value="<%= curso.getNombre() %>" class="form-control" required placeholder="Nombre del curso">
        </div>
        <div class="mb-3">
            <label class="form-label">Créditos:</label>
            <input type="number" name="creditos" value="<%= curso.getCreditos() %>" class="form-control" required placeholder="Cantidad de créditos">
        </div>
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-warning">
                <i class="bi bi-save"></i> Actualizar
            </button>
            <a href="CursoServlet?accion=listar" class="btn btn-secondary">
                <i class="bi bi-arrow-left-circle"></i> Volver
            </a>
        </div>
    </form>
</div>
</body>
</html>
