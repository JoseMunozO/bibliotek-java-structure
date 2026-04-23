# Bibliotekssystem

Proyecto de biblioteca en Java con:

- arquitectura de tres capas
- JDBC
- DTOs y mappers
- menús por consola
- MySQL

## Estructura

```text
Presentation -> Business -> Data -> Database
               DTO + Mapper
```

## Requisitos

- Java 25
- MySQL con el esquema de [bibliotek.sql](/Users/ozeca/Downloads/bibliotek-java-structure/bibliotek.sql)
- Variables de entorno configuradas

## Variables de entorno

Usa como referencia [.env.example](/Users/ozeca/Downloads/bibliotek-java-structure/.env.example):

```env
DB_URL=jdbc:mysql://localhost:3306/bibliotek
DB_USER=root
DB_PASSWORD=your_password_here
```

## Ejecutar

Compilar:

```bash
javac $(find src/main/java -name '*.java')
```

Ejecutar:

```bash
java -cp src/main/java se.josecarlos.bibliotek.Main
```

## Funcionalidad actual

- catálogo de libros
- búsqueda y ordenación de libros
- detalles ampliados de libros
- registro y gestión de miembros
- préstamos y devoluciones
- multas
- menús por rol simulados
- perfil de miembro
- extensión de préstamos
- registro de vencidos
- reseñas
- estadísticas de libros más prestados
- notificaciones

## Nota sobre roles

Los roles `User`, `Librarian` y `Admin` están simulados en la capa `Presentation`.

No existe login real ni autenticación persistida en base de datos.

## Demo sugerida

1. Mostrar libros disponibles
2. Ordenar libros por nombre, autor e ID
3. Registrar miembro
4. Ver perfil de miembro
5. Prestar libro
6. Devolver libro
7. Ver multas
8. Extender préstamo
9. Ver registro de préstamos vencidos
10. Crear una reseña
11. Ver estadísticas
12. Enviar una notificación
