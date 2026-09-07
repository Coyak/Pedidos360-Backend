# Microservicio de Catálogo - Pedidos360

Microservicio desarrollado en Java con **Spring Boot** para la gestión del catálogo de productos en el sistema Pedidos360.

---

## 🚀 Requisitos Previos

* **Java**: JDK 17 o superior
* **Maven**: Incluido a través de Maven Wrapper (`./mvnw` / `mvnw.cmd`)

---

## 🛠️ Cómo Ejecutar el Proyecto

Para iniciar el servidor en entorno de desarrollo, ejecuta el siguiente comando en la raíz del proyecto:

```bash
./mvnw spring-boot:run
```

> **En Windows (PowerShell / CMD):**
> ```cmd
> .\mvnw.cmd spring-boot:run
> ```

El microservicio se iniciará por defecto en `http://localhost:8080`.

---

## 📌 Endpoints Disponibles

### 1. Estado del Servicio
* **Método:** `GET`
* **Ruta:** `/api/status`
* **Descripción:** Comprueba el estado de disponibilidad del microservicio.
* **Respuesta de Ejemplo (`200 OK`):**

```json
{
  "status": "OK",
  "message": "Microservicio de Catálogo en ejecución",
  "version": "1.0.0"
}
```