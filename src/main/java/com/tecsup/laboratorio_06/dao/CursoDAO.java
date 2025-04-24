package com.tecsup.laboratorio_06.dao;

import com.tecsup.laboratorio_06.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private final String url = "jdbc:mysql://localhost:3306/escuela";
    private final String user = "root";
    private final String pass = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso();
                c.setCodigo(rs.getString("chrCurCodigo"));
                c.setNombre(rs.getString("vchCurNombre"));
                c.setCreditos(rs.getInt("intCurCreditos"));
                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error al listar los cursos.");
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertar(Curso curso) {
        String sql = "INSERT INTO curso (chrCurCodigo, vchCurNombre, intCurCreditos) VALUES (?,?,?)";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getCodigo());
            ps.setString(2, curso.getNombre());
            ps.setInt(3, curso.getCreditos());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar el curso.");
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Curso curso) {
        String sql = "UPDATE curso SET vchCurNombre = ?, intCurCreditos = ? WHERE chrCurCodigo = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getCreditos());
            ps.setString(3, curso.getCodigo());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el curso.");
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM curso WHERE chrCurCodigo = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el curso.");
            e.printStackTrace();
        }

        return false;
    }

    public Curso buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM curso WHERE chrCurCodigo = ?";
        Curso curso = null;

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                curso = new Curso();
                curso.setCodigo(rs.getString("chrCurCodigo"));
                curso.setNombre(rs.getString("vchCurNombre"));
                curso.setCreditos(rs.getInt("intCurCreditos"));
            }

        } catch (Exception e) {
            System.out.println("Error al buscar el curso.");
            e.printStackTrace();
        }

        return curso;
    }

    public static void main(String[] args) {
        CursoDAO dao = new CursoDAO();

        try (Connection conn = dao.conectar()) {
            if (conn != null) {
                System.out.println("Conexión a la base de datos exitosa.");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo conectar con la base de datos.");
            return;
        }

        // Insertar curso
        Curso nuevoCurso = new Curso();
        nuevoCurso.setCodigo("c05");
        nuevoCurso.setNombre("Base de datos");
        nuevoCurso.setCreditos(4);

        if (dao.insertar(nuevoCurso)) {
            System.out.println("Curso insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el curso.");
        }

        // Listar cursos
        List<Curso> cursos = dao.listar();
        System.out.println("Lista de cursos:");
        for (Curso c : cursos) {
            System.out.println("Código: " + c.getCodigo() +
                    " | Nombre: " + c.getNombre() +
                    " | Créditos: " + c.getCreditos());
        }

        // Actualizar curso
        Curso cursoActualizar = dao.buscarPorCodigo("c05");
        if (cursoActualizar != null) {
            cursoActualizar.setNombre("Bases de datos Avanzadas");
            cursoActualizar.setCreditos(5);

            boolean actualizacionExitosa = dao.actualizar(cursoActualizar);
            if (actualizacionExitosa) {
                System.out.println("Curso actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el curso.");
            }
        } else {
            System.out.println("Curso no encontrado para actualizar.");
        }

        // Eliminar curso
        boolean eliminacionExitosa = dao.eliminar("c05");
        if (eliminacionExitosa) {
            System.out.println("Curso eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el curso.");
        }

        // Buscar curso por código
        Curso cursoBuscado = dao.buscarPorCodigo("c04");
        if (cursoBuscado != null) {
            System.out.println("Curso encontrado:");
            System.out.println("Código: " + cursoBuscado.getCodigo() +
                    ", Nombre: " + cursoBuscado.getNombre() +
                    ", Créditos: " + cursoBuscado.getCreditos());
        } else {
            System.out.println("No se encontró el curso con código c04.");
        }
    }
}