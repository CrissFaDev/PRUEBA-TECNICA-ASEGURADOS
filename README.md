# Prueba Técnica - Java 17, Maven, Spring Boot, REST API

## Base de Datos
Se creó una base de datos con las siguientes tablas:

Tabla "ASEGURADOS"
tipo_identificacion: Tipo de identificación (1-CC, 2-CE)
nro_identificacion: Número de Identificación
apellidos: Apellidos del asegurado
nombres: Nombres del asegurado
sexo: Sexo (1-Masculino, 2-Femenino)
fecha_nacimiento: Fecha de nacimiento del asegurado

| tipo_identificacion | nro_identificacion | apellidos | nombres | sexo | fecha_nacimiento |
|---------------------|--------------------|-----------|---------|------|------------------|
| 1                   | 79000001           | APELLIDO1 | NOMBRE1 | 1    | 10-ene-45        |
| 1                   | 79000002           | APELLIDO2 | NOMBRE2 | 1    | 10-ene-50        |
| 1                   | 79000003           | APELLIDO3 | NOMBRE3 | 1    | 10-ene-55        |
| 2                   | 51000001           | APELLIDO4 | NOMBRE4 | 2    | 10-ene-60        |
| 2                   | 51000002           | APELLIDO5 | NOMBRE5 | 2    | 10-ene-65        |
| 2                   | 51000003           | APELLIDO6 | NOMBRE6 | 2    | 10-ene-70        |


## Tabla "AMPAROS"
codigo: Código del amparo
nombre: Nombre del amparo

| codigo | nombre            |
|--------|-------------------|
| 1      | Muerte accidental |
| 2      | Desmembración     |
| 3      | Auxilio funerario |
| 4      | Renta vitalicia   |

## Tabla "PRIMAS"
codigo: Código del amparo
edad_minima: Edad mínima para aplicar prima
edad_maxima: Edad máxima para aplicar prima
valor_prima: Valor de la prima

| codigo | edad_minima | edad_maxima | valor_prima |
|--------|-------------|-------------|-------------|
| 1      | 18          | 45          | 0.02304     |
| 1      | 46          | 75          | 0.02012     |
| 2      | 18          | 50          | 0.1809      |
| 2      | 51          | 70          | 0.16043     |
| 3      | 18          | 60          | 0.14123     |
| 3      | 61          | 70          | 0.1545      |
| 4      | 18          | 50          | 0.12123     |
| 4      | 51          | 70          | 0.1345      |

### Endpoint de Liquidación
Se desarrolló un endpoint que recibe un JSON con los datos de un asegurado y devuelve otro JSON con la liquidación según las reglas de negocio especificadas.

Reglas de Negocio
Valor póliza = valor asegurado * % prima
El valor asegurado debe ser mayor a cero
Todos los datos de entrada son obligatorios
Si algún dato falta, se genera un error 500
Si no le aplica ningún amparo, se retorna el JSON sin amparos
Solo se consideran los amparos solicitados si la edad del asegurado está en el rango de fechas
Si le aplica más de un amparo, el valor de la liquidación es la suma de todos los amparos aplicables según la edad
Se incluyen en la respuesta todos los amparos que correspondan según la edad

## JSON ENTRADA
[  {    "tipo_identificacion": <tipo_identificacion>, 
"nro_identificacion": <nro_identificacion>,
"valor_asegurado": <valor_entero> 
}]

## JSON SALIDA 
[  {    "tipo_identificacion": <tipo_identificacion>,    "nro_identificacion": <nro_identificacion>,    "valor_asegurado": <valor_entero>,    "liquidacion": [      {        "codigo_amparo": <codigo_amparo>,        "nombre_amparo": <nombre_amparo>,        "valor_prima": <valor_prima>      }    ],
    "valor_total": <valor_total>
  }
]

## Entregables
Script de la base de datos
Código Java
Swagger

