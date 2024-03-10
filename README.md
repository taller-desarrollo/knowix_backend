# Knowix - Backend

<div align="center">
  <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/postgresql_logo_icon_170836.png" alt="Knowix" width="250"/>
  <img src="https://bgasparotto.com/wp-content/uploads/2017/12/spring-logo.png" alt="Knowix" width="250"/>
</div>

## Descripción

Este proyecto corresponde al backend de la aplicación Knowix, el cual se encarga de la conexión con la base de datos y la lógica de negocio. Este proyecto se encuentra desarrollado en Java 17 y Spring Boot 3.2.3. Para la base de datos se utiliza PostgreSQL 13.4. Y Maven 3.6.3 para la gestión de dependencias.

## Requerimientos

Para hacer correr este proyecto se necesita tener instalado:

- Maven 3.6.3
- Java 17
- Docker 20.10.8
- PostgreSQL 13.4

## Crear y ejecutar contenedor de Docker con PostgreSQL

1. Descargar la imagen de PostgreSQL:

    ```bash
    docker pull postgres
    ```

2. Luego se debe crear el contenedor:

    ```bash
    docker run --name knowix -e POSTGRES_PASSWORD=abc123 -d -p 5434:5432 postgres
    ```

3. Para verificar que el contenedor se encuentra corriendo se debe ejecutar el siguiente comando:

    ```bash
    docker ps
    ```

4. Para conectarse a la base de datos se debe ejecutar el siguiente comando:

    ```bash
    docker exec -it knowix psql -U postgres
    ```

5. Para crear la base de datos se debe ejecutar el siguiente comando:

    ```bash
    CREATE DATABASE bddknowix;
    ```

6. Para conectarse a la base de datos creada se debe ejecutar el siguiente comando:

    ```bash
    \c bddknowix
    ```

## Ejecución

Para compilar el proyecto se debe correr el siguiente comando:

```bash
mvn clean package
```

Para ejecutar el proyecto se debe correr el siguiente comando:

```bash
mvn spring-boot:run
```
