<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 23/04/2025
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.tecsup.laboratorio_06.model.Curso" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Lista de Cursos</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2 class="text-center mb-4 text-primary">
    <i class="bi bi-journal-bookmark-fill me-2"></i>Lista de Cursos
  </h2>
  <div class="d-flex justify-content-between mb-3">
    <a href="CursoServlet?accion=agregar" class="btn btn-primary">
      <i class="bi bi-plus-circle"></i> Agregar Nuevo Curso
    </a>
  </div>
  <table class="table table-bordered table-hover table-striped">
    <thead class="table-dark">
    <tr>
      <th>Código</th>
      <th>Nombre</th>
      <th>Créditos</th>
      <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Curso> lista = (List<Curso>) request.getAttribute("lista");
      if (lista != null && !lista.isEmpty()) {
        for (Curso c : lista) {
    %>
    <tr>
      <td><%= c.getCodigo() %></td>
      <td><%= c.getNombre() %></td>
      <td><%= c.getCreditos() %></td>
      <td>
        <a href="CursoServlet?accion=editar&codigo=<%= c.getCodigo() %>" class="btn btn-warning btn-sm">
          <i class="bi bi-pencil-fill"></i> Editar
        </a>
        <a href="CursoServlet?accion=eliminar&codigo=<%= c.getCodigo() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro?')">
          <i class="bi bi-trash"></i> Eliminar
        </a>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="4" class="text-center">No hay cursos disponibles.</td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
</body>
</html>
