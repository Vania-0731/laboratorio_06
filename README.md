# Proyecto de CRUD de Cursos

Este es un proyecto de ejemplo que permite gestionar cursos en una aplicación web utilizando Java con Jakarta EE y MySQL. Permite agregar, editar, listar y eliminar cursos de una base de datos.

## Requisitos

- Java 11 o superior
- Maven (para manejar dependencias)
- Base de datos MySQL (o MariaDB)
- Servidor Apache Tomcat (o cualquier servidor compatible con Jakarta EE)

## Instalación

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/usuario/tu-proyecto.git

## Configura la base de datos
Asegúrate de tener MySQL o MariaDB instalado. Luego, crea la base de datos y las tablas necesarias ejecutando los siguientes comandos SQL:
Primero esto:
CREATE TABLE Curso(
chrCurCodigo char(3) NOT NULL,
vchCurNombre varchar(50) NULL,
intCurCreditos int(2) NULL
);

Luego esto:
ALTER TABLE Curso
ADD PRIMARY KEY (chrCurCodigo);
insert Curso values('c01','junior',5);
insert Curso values('c02','senior',5);
insert Curso values('c03','moviles',5);

# Configuración del Proyecto
  Importa el proyecto en IntelliJ IDEA o tu IDE favorito.

## Compila el proyecto usando Maven para resolver las dependencias:
mvn clean install
Despliega el proyecto en un servidor de aplicaciones compatible con Jakarta EE (como Apache Tomcat).

## Uso
Accede a la aplicación a través de un navegador web.

Puedes agregar, editar, listar y eliminar cursos usando las interfaces proporcionadas en la aplicación.

## Notas
Este proyecto está basado en un CRUD simple que interactúa con una base de datos MySQL.

Las operaciones de CRUD están implementadas utilizando servlets y JSP.

