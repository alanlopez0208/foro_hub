# Foro Alura - API REST

Este proyecto es mi solución al desafío de construir una API REST para un foro, como parte del curso "Practicando Spring Framework: Challenge Foro Hub" de Alura.

## Tecnologías Utilizadas

* **Backend:** Spring Boot (Java)
* **Base de datos:**  [Especifica tu base de datos, ej: MySQL, PostgreSQL, H2]
* **Herramienta de pruebas:** Insomnia
* **Seguridad:** JSON Web Tokens (JWT)

## Funcionalidades

Esta API REST proporciona las siguientes funcionalidades:

* **Listar tópicos:** Permite obtener una lista de todos los tópicos existentes en el foro a través de una petición GET a `/tópicos`.  Retorna un código de estado HTTP 200 (OK) con la lista de tópicos.

* **Crear un tópico:** Permite crear un nuevo tópico en el foro a través de una petición POST a `/tópicos`.  Requiere autenticación JWT.  El cuerpo de la petición debe incluir los datos del tópico (título, mensaje, ID del usuario, nombre del curso, etc.).  Retorna un código de estado HTTP 201 (Created) con los datos del tópico creado.

* **Eliminar un tópico:** Permite eliminar un tópico existente en el foro a través de una petición DELETE a `/tópicos/{id}`.  Requiere autenticación JWT.  Retorna un código de estado HTTP 200 (OK) si el tópico se eliminó correctamente, o un código de estado HTTP 404 (Not Found) si el tópico no existe.

* **Autenticación:**  La API utiliza JWT para autenticar a los usuarios.  Un endpoint de autenticación (ej: `/auth`) permite obtener un token JWT al proporcionar credenciales válidas.

