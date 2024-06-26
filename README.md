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

7. Posteriormente se debe copiar todo el contenido del archivo .../bdd/bdd.sql y pegarlo en la terminal.

8. De esta forma se creará la base de datos con sus respectivas tablas y datos.

## Autenticación con Keycloak

El proyecto utiliza Keycloak como servidor de autenticación. Para instalar la imagen Docker, ejecuta el siguiente comando:

```bash
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.1 start-dev
```

Después de ejecutar este comando, accede a Keycloak ingresando a localhost:8080 utilizando las credenciales de administrador (usuario: admin, contraseña: admin). Luego, en el menú de la izquierda, crea un nuevo realm. Para hacerlo, sigue estos pasos:

1. Haz clic en el dropdown "Keycloak" en el menú de la izquierda.
2. Haz clic en "Create realm".
3. Importa el archivo 'realm-export.json' que se encuentra en la carpeta 'keycloak' de este repositorio.
4. Haz clic en "Create".

Con estos pasos, se configurará Keycloak con las configuraciones necesarias para la autenticación del proyecto.

Para importar automaticamente el archivo de configuración al crear el contenedor usar el siguiente comando en el directorio de knowix_backend

```bash
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOACK_ADMIN_PASSWORD=admin \
        -v /Keycloak/realm-export.json:/opt/keycloak/data/import \
        quay.io/keycloak/keycloak:24.0.1 \
        start-dev --import-realm
```
## Configuracion de Minio

Para configurar Minio se debe ejecutar el siguiente comando:

para windows:
```bash
docker run \
   -p 9000:9000 \
   -p 9001:9001 \
   --name minio1 \
   -v D:\minio\data:/data \
   -e "MINIO_ROOT_USER=ROOTUSER" \
   -e "MINIO_ROOT_PASSWORD=CHANGEME123" \
   quay.io/minio/minio server /data --console-address ":9001"
```

para linux/MacOS:
```bash
mkdir -p ~/minio/data

docker run \
   -p 9000:9000 \
   -p 9001:9001 \
   --name minio \
   -v ~/minio/data:/data \
   -e "MINIO_ROOT_USER=adminuser" \
   -e "MINIO_ROOT_PASSWORD=adminpassword" \
   quay.io/minio/minio server /data --console-address ":9001"
```

Luego se debe acceder a la interfaz de Minio en la dirección localhost:9000 y configurar el bucket con el nombre 'verification-request-attachment'.

Crea un access key y secret key en la interfaz de Minio y configura las variables de entorno en el archivo .env.

Se debe crear un archivo .env en la raíz del proyecto con las siguientes variables de entorno:

```bash
MINIO_SERVER_URL=http://localhost:9000
MINIO_ACCESS_KEY=access_KEY
MINIO_SECRET_KEY=secret_key
```

Cambia 'access_KEY' y 'secret_key' por las credenciales que creaste en la interfaz de Minio.

## Ejecución

Para compilar el proyecto se debe correr el siguiente comando:

```bash
mvn clean package
```

Para ejecutar el proyecto se debe correr el siguiente comando:

```bash
mvn spring-boot:run
```
