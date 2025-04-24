package com.tecsup.laboratorio_06.controller;

import com.tecsup.laboratorio_06.dao.CursoDAO;
import com.tecsup.laboratorio_06.model.Curso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/CursoServlet")
public class CursoServlet extends HttpServlet {
    private CursoDAO dao = new CursoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                List<Curso> cursos = dao.listar();
                if (cursos != null && !cursos.isEmpty()) {
                    request.setAttribute("lista", cursos);
                    request.getRequestDispatcher("listar.jsp").forward(request, response);
                } else {
                    request.setAttribute("mensaje", "No se encontraron cursos.");
                    request.getRequestDispatcher("listar.jsp").forward(request, response);
                }
                break;

            case "agregar":
                request.getRequestDispatcher("agregar.jsp").forward(request, response);
                break;

            case "editar":
                String codigoEditar = request.getParameter("codigo");
                if (codigoEditar != null && !codigoEditar.isEmpty()) {
                    Curso cursoEditar = dao.buscarPorCodigo(codigoEditar);
                    request.setAttribute("curso", cursoEditar);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "No se encontró el código del curso.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                break;

            case "eliminar":
                String codigoEliminar = request.getParameter("codigo");
                if (dao.eliminar(codigoEliminar)) {
                    request.setAttribute("mensaje", "Curso eliminado correctamente.");
                } else {
                    request.setAttribute("mensaje", "No se pudo eliminar el curso.");
                }
                response.sendRedirect("CursoServlet?accion=listar");
                break;

            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            try {
                Curso curso = new Curso();
                curso.setCodigo(request.getParameter("codigo"));
                curso.setNombre(request.getParameter("nombre"));
                curso.setCreditos(Integer.parseInt(request.getParameter("creditos")));

                dao.insertar(curso);
                response.sendRedirect("CursoServlet?accion=listar");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Los créditos deben ser un número.");
                request.getRequestDispatcher("agregar.jsp").forward(request, response);
            }
        } else if ("actualizar".equals(accion)) {
            try {
                Curso curso = new Curso();
                curso.setCodigo(request.getParameter("codigo"));
                curso.setNombre(request.getParameter("nombre"));
                curso.setCreditos(Integer.parseInt(request.getParameter("creditos")));

                dao.actualizar(curso);
                response.sendRedirect("CursoServlet?accion=listar");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Los créditos deben ser un número.");
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
        }
    }
}
