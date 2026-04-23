# Plan de trabajo — Bibliotekssystem (Java + SQL + trelagersarkitektur)

## Contexto del proyecto

Este proyecto está pensado para un nivel **YH**, con enfoque en:

- Java orientado a objetos
- trelagersarkitektur
- JDBC
- DTOs y mappers
- streams y lambda
- una estructura fácil de entender y explicar en clase

Situación actual:
- primera vez usando Java
- trabajando solo
- dedicación alta: **5–8 horas al día (incluyendo fines de semana)**

---

# 🎯 Estrategia general

Con tu ritmo actual NO estás en modo “llegar justo”.

👉 Estás en modo:
**hacer un proyecto sólido + bien presentado + buena nota**

---

# ⏱️ Tiempo disponible

- ~3.5 semanas hasta entrega
- ~120–170 horas reales de trabajo

👉 Con esto puedes hacer:
- core completo
- extras
- limpieza y presentación tranquila

---

# 🧱 Arquitectura del proyecto

```text
Presentation → Service → DAO → Database
                ↓
              DTO + Mapper
```

Capas:
- Presentation → menús
- Business → lógica
- Data → JDBC
- DTO → datos para mostrar
- Mapper → conversión

---

# 🚨 Regla más importante

👉 NO avances si lo anterior no funciona

Ejemplo:
- no hacer loans sin members
- no hacer fines sin loans

---

# 🧠 Estrategia de desarrollo

Cada feature debe seguir:

1. DAO
2. Service
3. DTO + Mapper
4. Menu
5. Test

---

# 📆 PLAN POR BLOQUES (ACTUALIZADO)

## 🔵 Bloque 1 (3–4 días)
### BOOKS (base del sistema)

- BookDAO completo
- BookService
- BookDTO
- BookMapper
- BookMenu

Usar:
- streams
- filtros
- mapping a DTO

✅ Resultado:
Catálogo completo y limpio

---

## 🟢 Bloque 2 (4–5 días)
### MEMBERS + LOANS (núcleo del proyecto)

- MemberDAO + Service
- LoanDAO + Service

Implementar:
- borrowBook()
- returnBook()

Reglas:
- validar stock
- validar miembro
- actualizar copias

✅ Resultado:
Sistema funcional real

---

## 🟡 Bloque 3 (3–4 días)
### FINES

- FineDAO
- FineService

Implementar:
- generar multa por retraso
- ver multas
- pagar multa

✅ Resultado:
Sistema más realista

---

## 🟣 Bloque 4 (2–3 días)
### MENÚS POR ROL

Menu principal:
1. User
2. Librarian
3. Admin

Implementar:
- LibraryMenu
- BookMenu
- LoanMenu

No complicarse:
solo funcional

---

## 🟠 Bloque 5 (3–5 días)
### EXTRAS (solo si todo funciona)

Implementado:
- reviews
- notificaciones
- estadisticas de libros mas prestados
- perfil de miembro
- actualizacion de miembro
- suspension de miembros
- extension de prestamos
- detalles ampliados de libros
- registro enriquecido de prestamos vencidos

✅ Resultado:
Sistema mas completo y mas profesional, apoyado en el esquema real de MySQL

---

## 🔴 Bloque final (2–3 días)
### LIMPIEZA + PRESENTACIÓN

Implementado:
- limpieza de configuracion y artefactos locales
- soporte para variables de entorno
- documentacion de ejecucion
- resumen para demo
- README con demo sugerida

Pendiente recomendado:
- probar todo manualmente contra MySQL
- si hay tiempo, agregar tests automaticos

✅ Resultado:
Proyecto preparado para entrega, demo y portfolio

---

# 🎓 Qué debes poder explicar

- qué hace cada capa
- qué es un DTO
- flujo:
  Menu → Service → DAO → DB
- borrowBook
- returnBook

---

# 🧪 Cómo trabajar cada día

## Sesión ideal

1. Calentamiento (30–60 min)
2. Desarrollo (3–5h)
3. Testing (1–2h)
4. Limpieza (30–60 min)

---

# ⚠️ Errores a evitar

- no probar código
- hacer todo a la vez
- sobrecomplicar
- cambiar estructura constantemente

---

# 🏁 MVP mínimo

Debes tener sí o sí:

- ver libros
- registrar miembro
- prestar libro
- devolver libro

---

# 🚀 Objetivo final

Proyecto que sea:

- claro
- funcional
- estructurado
- explicable

---

# 💬 Conclusión

Con tu ritmo:

✔ llegas seguro  
✔ puedes hacer extras  
✔ puedes destacar  

Pero siempre:

👉 prioriza claridad sobre complejidad  
👉 construye por fases  
👉 prueba TODO  
