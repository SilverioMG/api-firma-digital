# API FIRMA DIGITAL

## Entorno Desarrollo
- **Sistema Operativo**: Linux Mint 21.3 Cinnamon
- **Java**: Temurin 21
- **IDE**: IntelliJ IDEA

## Endpoints del API
### 1. Generar clave privada/pública de un usuario
- **Método**: `POST`
- **URL**: `http://127.0.0.1:8080/api/generatekeys/{userName}`
- **Descripción**: *Este endpoint genera un par de claves (private/pública) para un usuario.  
   Las claves se almacena dentro de un keyStore (.pks) 
   con el nombre del usuario dentro de la carpeta **'keystoreData'** en la raíz de este proyecto.  
   Cada usuario tiene su propio keyStore.  
   NOTA: Cada vez que se ejecute este endpoint para el mismo usuario, sus claves se volverán a generar 
   y sobreescribirán las ya existentes (las firmas digitales realizadas con las claves antiguas dejarán 
   de ser válidas y sería necesario volver a firmar cada documento de nuevo.* 

### 2. Firmar documento por un usuario
- **Método**: `POST`
- **URL**: `http://127.0.0.1:8080/api/signaturedocument`
- **Body**:
  ```json
  {
    "userName": "string",
    "documentBase64": "string"
  }
- **Descripción**: *Este endpoint permite que un usuario firme un 'documento pdf' codificado en Base64.
  El api utiliza la 'clave privada' del usuario para firmar el documento.
  Se devuelve como respuesta la 'firmaDigital' (codificada en Base64) del documento.
  Si el usuario no tiene todavía su par de claves generado, el api devuelve un error.
  Que un usuario no tenga su par de claves generado significa que no se ha creado su keyStore correspondiente utlizando
  el endpoint 'POST - api/generatekeys/{userName}'.*

### 3. Verificar firma de documento para un usuario
- **Método**: `POST`
- **URL**: `http://127.0.0.1:8080/api/verifySignature`
- **Body**:
  ```json
  {
    "userName": "string",
    "digitalSignatureBase64": "string",
    "documentBase64": "string"
  }
- **Descripción**: *Este endpoint permite verificar si un usuario a firmado digitalmente un documento.  
  Es necesario envíar al api el 'documento pdf' codificado en Base64 previamente firmado y la 'firmaDigital'
  (codificada en Base64) devuelta en el endoint 'POST - api/signaturedocument'.
  El api verifica que el 'documento' ha sido previamente firmado con dicha 'firmaDigital'
  utilizando la 'clave pública' del usuario.*


## Ejecución del servicio api rest
- **Con Java 21**:   
  Se ha copiado la versión más reciente del artefacto .jar de este api rest en la carpeta 
 'JAR' situada en la raíz de este proyecto.  
  Ejecutar el comando: '**java -jar JAR/firmadigital-1.0.0-SNAPSHOT.jar**'  


- **Con Docker (no hace falta tener instalado Java 21)**: 
  Se ha añadido el archivo 'Dockerfile' en la raíz de este proyecto.  
  - Generar imagen: '**docker build -t firmadigital-api .**'  
  - Ejecutar imagen en contenedor: '**docker run --name firmadigital-api -p 8080:8080 firmadigital-api:latest**'  
  - Detener ejecución contenedor: '**docker stop firmadigital-api**'  
  - Eliminar contenedor: '**docker rm firmadigital-api**'  
  - Eliminar imagen: '**docker rmi firmadigital-api:latest**'  
  - Consultar contenedores: '**docker ps -a**'  
  - Consultar imágenes: '**docker images**' 


- **Con DockerCompose (no hace falta tener instalado Java 21)**:  
  - Se ha añadido el archivo 'docker-compose.yml' en la raíz de este proyecto.  
  - Generar imagen y ejecutarla en contenedor: '**docker-compose up**'  
  - Detener ejecución contenedor: '**docker stop firmadigital-api**'  
  - Eliminar contenedor: '**docker rm firmadigital-api**'  
  - Eliminar imagen: '**docker rmi firmadigital-api:latest**'  
  - Consultar contenedores: '**docker ps -a**'  
  - Consultar imágenes: '**docker images**'  


## Pruebas del servicio api rest
- **Postman:** Se ha copiado una **Postman collection** en la carpeta 'postman' en la raíz de este proyecto para poder probar cada endpoint del api rest.  

- **Pdf de prueba:** Se ha copiado en la carpeta 'pdf-prueba' en la raíz de este proyecto 2 archivos .pdf y 2 archivos .txt con el contenido de cada pdf
  codificado en Base64 para utilizar en las pruebas.  
  Es necesario utilizar el formato Base64 para envíar el documento pdf en el body Json de las request que lo necesiten.  
  Las request añadidas en la Postman collection ya tienen en el campo 'documentBase64' del body Json un pdf codificado en Base64 para poder utilizarlo durante las pruebas.
