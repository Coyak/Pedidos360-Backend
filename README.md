# Microservicio de Catálogo - Pedidos360

Microservicio desarrollado en **Java 17 / 21** con **Spring Boot** para la gestión del catálogo de productos del sistema Pedidos360, integrado con **OAuth 2.0 / JWT** a través de **Microsoft Entra ID (Azure AD)**.

---

## 🚀 Requisitos Previos

* **Java**: JDK 17 o superior
* **Maven**: Incluido mediante Maven Wrapper (`./mvnw` / `mvnw.cmd`) o instalación local (`mvn`)
* **Cuenta / Inquilino de Azure AD**: Para autenticación mediante tokens JWT.

---

## ⚙️ Configuración del Entorno

Asegúrate de tener configurado tu `Tenant ID` de Microsoft Entra ID en `src/main/resources/application.properties`:

```properties
spring.application.name=catalogo

# Configuración de OAuth2 / Resource Server con Microsoft Entra ID (Azure)
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://login.microsoftonline.com/e5372bf0-c5e3-4286-887c-79069f209c1f/v2.0
```

---

## 🛠️ Cómo Ejecutar el Proyecto

Para iniciar el servidor en entorno local de desarrollo, ejecuta en la raíz del proyecto:

```bash
mvn spring-boot:run
```

El microservicio estará disponible por defecto en `http://localhost:8080`.

---

## 📌 Endpoints Disponibles

### 1. Estado del Servicio (Público)
* **Método:** `GET`
* **Ruta:** `/api/status`
* **Acceso:** Libre (sin autenticación)
* **Descripción:** Comprueba la disponibilidad del servicio y la versión activa.
* **Respuesta de Ejemplo (`200 OK`):**

```json
{
  "status": "OK",
  "message": "Microservicio de Catálogo en ejecución",
  "version": "1.3.2"
}
```

---

### 2. Creación de Producto (Protegido)
* **Método:** `POST`
* **Ruta:** `/api/productos`
* **Acceso:** Protegido (requiere cabecera `Authorization: Bearer <JWT_TOKEN>`)
* **Cuerpo de la Petición (`text/plain` o `application/json`):** Nombre del producto.
* **Respuesta Exitosa (`201 CREATED`):**

```json
{
  "status": "CREATED",
  "producto": "LAPTOP GAMER"
}
```

* **Respuesta Error de Validación (`400 BAD REQUEST`):**

```json
{
  "status": "ERROR",
  "message": "El nombre del producto es obligatorio y no puede estar vacío"
}
```

* **Respuesta Sin Autenticación (`401 UNAUTHORIZED`):** Si no se provee un token JWT válido de Azure AD.

---

## 🔒 Configuración de Seguridad y CORS

* **CORS Permitidos:** Patrón global `*` (con soporte para localhost:5173, localhost:4200 y AWS API Gateway).
* **Métodos HTTP:** `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`.
* **Filtro JWT:** Validador flexible de firma criptográfica mediante JWK Set URI (`https://login.microsoftonline.com/e5372bf0-c5e3-4286-887c-79069f209c1f/discovery/v2.0/keys`) para validar audiencia (`aud`) y emisor (`iss`) de Entra ID v1.0/v2.0.

---

## 🏷️ Historial de Versionamiento Semántico (SemVer)

| Versión | Tag | Descripción de Cambios |
| :--- | :--- | :--- |
| **`1.0.0`** | `v1.0.0` | Estructura base del microservicio y endpoint inicial `GET /api/status`. |
| **`1.1.0`** | `v1.1.0` | Incorporación del nuevo endpoint `POST /api/productos`. |
| **`1.1.1`** | `v1.1.1` | Fix de bug en `POST /api/productos` mediante validación contra nulos/vacíos. |
| **`1.2.0`** | `v1.2.0` | Incorporación de dependencias `spring-boot-starter-security` y `oauth2-resource-server` con configuración de Azure AD en `application.properties`. |
| **`1.3.0`** | `v1.3.0` | Implementación de `SecurityConfig.java` con filtro JWT de Azure AD, rutas protegidas/públicas y políticas CORS. |
| **`1.3.1`** | `v1.3.1` | Actualización de documentación en `README.md` y sincronización de versión devuelta por el endpoint de status. |
| **`1.3.2`** | `v1.3.2` | Fix de bug en `SecurityConfig`: validador flexible de token JWT Entra ID (`aud`/`iss`) evitando HTTP 401 en `/api/status` y soporte preflight `OPTIONS`. |