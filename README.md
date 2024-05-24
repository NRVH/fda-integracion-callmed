# Servicio de integración Callmed

Este proyecto integra servicios de la API de Callmed para el manejo y 
consulta de recetas médicas.

# Configuración de la Aplicación

La aplicación está configurada para correr sobre Quarkus y utiliza varias extensiones y configuraciones para su correcto funcionamiento y para exponer su API. A continuación, se detalla la configuración principal utilizada en el archivo `application.properties`.


```properties
quarkus.http.port=8081

quarkus.swagger-ui.path=/api-integracion-callmed
quarkus.swagger-ui.always-include=true
quarkus.swagger-ui.enable=true
quarkus.swagger-ui.title=api-integracion-callmed
quarkus.swagger-ui.description=API Documentation

quarkus.smallrye-openapi.enable=true

quarkus.rest-client."com.fahorro.integracion.client.CallmedClient".url=https://www.winstondata.com.mx/SUSE2
quarkus.rest-client."com.fahorro.integracion.client.CallmedClient".scope=javax.inject.Singleton

callmed.user=usrFAPrueba
callmed.password=W3bbHnn76
```

## EndPoints

### Consulta de recetas

- **Endpoint:** `GET /callmed/receta`
- **Query Parameter:** `nur` - Número único de receta.
- **Descripción:** Devuelve los datos de la receta basados en el NUR proporcionado.
- **Respuestas:**
    - `200`: Receta encontrada y procesada.
    - `400`: Datos de entrada inválidos.
    - `404`: Datos no encontrados.
    - `500`: Error interno del servidor.
    - `503`: Servicio API Callmed no disponible.
    - `504`: Timeout de comunicación con API Winstondata.

**Ejemplo de uso:**
#### Request

```bash
curl -X GET "http://<host>/callmed/api/v1/receta?nur=<nur>"
```
#### Response

```json
{
  "cliente": null,
  "copago": null,
  "copiaTantos": null,
  "diagnostico": null,
  "elegibilidad": null,
  "especialidad": "MEDICINA GENERAL",
  "familiar": "126",
  "fechaReceta": "12/7/2023 3:30:49 PM",
  "icd": "",
  "imagenParaSurtir": null,
  "ldpos": null,
  "lineItem": [
    {
      "EAN": "300090017125",
      "antibiotico": null,
      "cantidad": "2",
      "categoria": null,
      "claseTerapeutica": null,
      "controlado": null,
      "departamento": null,
      "descuento": null,
      "division": null,
      "duracion": null,
      "estatus": null,
      "frecuencia": null,
      "ieps": null,
      "imagen": null,
      "indicaciones": "test",
      "iva": null,
      "laboratorio": null,
      "medicamento": {
        "Cantidad": "2",
        "EAN": "300090017125",
        "FechaConsulta": "12/7/2023 3:30:49 PM",
        "ICD10": "",
        "Indicaciones": "test",
        "Medicamento": "HALCION 0.250 MG ORAL 30 TAB C2   7125",
        "NUR": "100023463593"
      },
      "numeroAutorizacion": "E231207000002",
      "nur": "100023463593",
      "precio_unitario_bruto": null,
      "precio_unitario_neto": null,
      "razonParaNoSurtir": null,
      "sePuedeSurtir": null,
      "segmento": null,
      "subcategoria": null,
      "sustanciaActiva": null,
      "unidadMe": null
    }
  ],
  "medico": "Medico primer contacto 5",
  "medicoCedula": null,
  "mensajeParaSurtir": null,
  "nomina": null,
  "numeroCliente": null,
  "numeroDiagnostico": null,
  "numeroPaciente": "91101280700",
  "numeroPreAutorizacion": null,
  "numeroProveedorReceta": null,
  "numeroSubCliente": null,
  "paciente": "xxxx",
  "pagoContado": null,
  "proveedorReceta": null,
  "razonNoSurtir": null,
  "recetaGrupo": null,
  "requiereNip": null,
  "sePuedeSurtir": null,
  "subCliente": null,
  "tieneCopago": null,
  "tipoCopago": null,
  "tipoNip": null,
  "tipoPrecios": null,
  "tipoReceta": null,
  "ventaValida": null
}
```

### Consulta de estatus de servicio

**GET** `/callmed/test`

Se agregó un metodo para validar si el servicio está disponible,
para este servicio no es necesario enviar algun dato, contesta solo al consultar el endpoint

#### Reponse

```json
{
  "message": "fda integracion callmed version 1.0.0 is available!",
  "status": 200,
  "success": true
}
```

## Desarrollo

Este proyecto utiliza Jakarta EE y MicroProfile para definir los recursos de la API y manejar la inyección de dependencias respectivamente.

#### Clases Principales:

- **CallmedResources**: Define los endpoints de la API y maneja las solicitudes HTTP.
- **CallmedService**: Contiene la lógica de negocio para procesar las solicitudes, interactuar con el cliente REST, y construir las respuestas.
- **CallmedClient**: Interfaz del cliente REST utilizado para comunicarse con la API externa de Winstondata.




## Levantar aplicacion en modo dev

Puedes ejecutar tu aplicación en modo de desarrollo que permite la codificación en vivo utilizando:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus ahora viene con una UI de Desarrollo, que está disponible solo en modo de desarrollo en http://localhost:8080/q/dev/.

## Empaquetando y ejecutando la aplicación

La aplicación puede ser empaquetada utilizando:

```shell script
./mvnw package
```

Esto produce el archivo`quarkus-run.jar` en el directorio `target/quarkus-app/`.
Ten en cuenta que no es un _über-jar_, ya que las dependencias se copian en el directorio `target/quarkus-app/lib/`.

La aplicación ahora puede ejecutarse utilizando `java -jar target/quarkus-app/quarkus-run.jar`.

Si deseas construir un _über-jar_, ejecuta el siguiente comando:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

La aplicación, empaquetada como un über-jar, ahora puede ejecutarse utilizando `java -jar target/*-runner.jar`.

## Creando un ejecutable nativo

Puedes crear un ejecutable nativo utilizando:

```shell script
./mvnw package -Dnative
```

O, si no tienes GraalVM instalado, puedes construir el ejecutable nativo en un contenedor utilizando:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Luego puedes ejecutar tu ejecutable nativo con: `./target/vita-medica-1.0-SNAPSHOT-runner`

Si deseas aprender más sobre la creación de ejecutables nativos, por favor consulta  https://quarkus.io/guides/maven-tooling.

