# Resumen del proyecto por bloques

## Objetivo del proyecto

Este proyecto implementa un sistema de biblioteca en Java usando una arquitectura de tres capas:

- `Presentation`
- `Business`
- `Data`

Tambien usa:

- `DTO`
- `Mapper`
- `JDBC`
- `MySQL`

La idea principal fue construir un proyecto claro, funcional y facil de explicar en clase.

---

## Por que decidi dividir el proyecto en bloques

Dividir el proyecto en bloques me ayudo a trabajar con orden y evitar avanzar demasiado rapido sobre partes que todavia no estaban listas.

Las razones principales fueron:

- Me permitio construir primero la base y luego el nucleo del sistema.
- Hizo mas facil detectar errores porque cada bloque tenia un objetivo claro.
- Evito mezclar demasiadas cosas al mismo tiempo.
- Me ayudo a seguir una logica real de desarrollo: primero libros, luego miembros y prestamos, luego multas, luego roles y finalmente extras.
- Tambien hizo mas facil medir el progreso del proyecto.

La regla que segui fue esta:

`no avanzar al siguiente bloque si el anterior no estaba funcionando`

---

## Estado actual por bloques

### Bloque 1 - Books

Estado: `Listo`

Incluye:

- catalogo de libros
- busqueda
- ordenacion por nombre, autor e ID
- detalles ampliados del libro

Importancia:

Es la base del sistema porque sin catalogo de libros no se puede prestar ni devolver nada.

---

### Bloque 2 - Members + Loans

Estado: `Listo`

Incluye:

- registro de miembros
- listado de miembros
- prestamos
- devoluciones
- prestamos activos
- prestamos por miembro
- vencidos

Importancia:

Es el nucleo principal del proyecto porque conecta libros con miembros.

---

### Bloque 3 - Fines

Estado: `Listo`

Incluye:

- generacion de multas por retraso
- consulta de multas
- pago de multas

Importancia:

Hace el sistema mas realista porque incorpora reglas de negocio sobre retrasos.

---

### Bloque 4 - Menus por rol

Estado: `Listo en version simple`

Incluye:

- `User`
- `Librarian`
- `Admin`

Decision importante:

Los roles estan simulados en `Presentation`.

No existe login real ni autenticacion persistida.

---

### Bloque 5 - Extras

Estado: `Listo`

Extras implementados:

- perfil de miembro
- actualizar informacion del miembro
- suspender miembro
- extender prestamos
- detalles ampliados del libro
- registro enriquecido de prestamos vencidos
- reviews
- estadisticas de libros mas prestados
- notificaciones

Importancia:

Este bloque hace que el proyecto se vea mas profesional y aprovecha mejor el esquema real de MySQL.

---

### Bloque 6 - Limpieza + presentacion

Estado: `Listo`

Realizado:

- limpieza de configuracion del proyecto
- soporte para variables de entorno
- archivo `.env.example`
- `README.md` con ejecucion y demo sugerida
- resumen actualizado para la presentacion
- `.gitignore` ampliado para artefactos locales y binarios

Pendiente recomendado:

- pruebas manuales finales en MySQL
- tests automaticos si queda tiempo

---

## Codigo importante y por que se usa

### 1. Punto de entrada del programa

Archivo: [Main.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/Main.java)

```java
public class Main {
    public static void main(String[] args) {
        LibraryMenu libraryMenu = new LibraryMenu();
        libraryMenu.showMainMenu();
    }
}
```

Funcion:

Inicia el programa y entrega el control al menu principal.

---

### 2. Conexion a la base de datos

Archivo: [DatabaseConnection.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/data/DatabaseConnection.java)

```java
public class DatabaseConnection {
    private static final String URL = getRequiredEnv("DB_URL");
    private static final String USER = getRequiredEnv("DB_USER");
    private static final String PASSWORD = getRequiredEnv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

Funcion:

Centraliza la conexion JDBC y evita dejar credenciales hardcodeadas en el codigo.

---

### 3. Entrada segura en menus

Archivo: [MenuInput.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/presentation/MenuInput.java)

```java
public static int readPositiveInt(Scanner scanner, String prompt) {
    while (true) {
        int value = readInt(scanner, prompt);

        if (value > 0) {
            return value;
        }

        System.out.println("Please enter a number greater than 0.");
    }
}
```

Funcion:

Evita que la aplicacion se rompa por entradas invalidas del usuario.

---

### 4. Prestamo con consistencia

Archivo: [LoanService.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/business/LoanService.java)

```java
try (Connection conn = DatabaseConnection.getConnection()) {
    conn.setAutoCommit(false);

    boolean loanCreated = loanDAO.createLoan(conn, bookId, memberId, loanDate, dueDate);
    boolean stockUpdated = loanCreated && bookDAO.decreaseAvailableCopies(conn, bookId);

    if (loanCreated && stockUpdated) {
        conn.commit();
        return true;
    }

    conn.rollback();
    return false;
}
```

Funcion:

Garantiza que prestamo y actualizacion de stock ocurran juntos.

---

### 5. Devolucion con multa automatica

Archivo: [LoanService.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/business/LoanService.java)

```java
if (today.isAfter(loan.getDueDate()) && !fineDAO.hasFineForLoan(conn, loan.getId())) {
    long lateDays = ChronoUnit.DAYS.between(loan.getDueDate(), today);
    double fineAmount = lateDays * 2.0;
    boolean fineCreated = fineDAO.createFine(conn, loan.getId(), fineAmount);
```

Funcion:

Genera multas automaticamente si una devolucion ocurre fuera de plazo.

---

### 6. Perfil de miembro

Archivo: [MemberDAO.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/data/MemberDAO.java)

Funcion:

Resume informacion del miembro, prestamos y multas en una sola consulta para construir una vista de perfil.

---

### 7. Detalles ampliados del libro

Archivo: [BookDAO.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/data/BookDAO.java)

Funcion:

Une libros, descripciones, autores y categorias para mostrar una ficha mas rica.

---

### 8. Reviews, estadisticas y notificaciones

Archivos:

- [ReviewService.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/business/ReviewService.java)
- [NotificationService.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/business/NotificationService.java)
- [BookService.java](/Users/ozeca/Downloads/bibliotek-java-structure/src/main/java/se/josecarlos/bibliotek/business/BookService.java)

Funcion:

Amplian el proyecto con funcionalidades de valor real apoyadas en el esquema `bibliotek.sql`.

---

## Flujo general del proyecto

El flujo principal que se puede explicar en la demo es:

`Menu -> Service -> DAO -> Database`

Ejemplo:

1. El usuario elige una opcion en el menu.
2. El menu llama a un `Service`.
3. El `Service` valida reglas del negocio.
4. El `DAO` consulta o actualiza MySQL.
5. El sistema devuelve un resultado claro en consola.

---

## Conclusiones para la demo

Actualmente el proyecto tiene listos:

- Bloque 1
- Bloque 2
- Bloque 3
- Bloque 4 en version simple
- Bloque 5
- Bloque 6

Mensaje importante para explicar:

`El sistema ya es funcional en su core. La parte de roles fue resuelta de forma simple en Presentation para no aumentar complejidad innecesaria en la base de datos. Los extras del Bloque 5 se apoyan en tablas reales del esquema MySQL, por eso el sistema se siente mas completo sin necesidad de implementar login real.`
