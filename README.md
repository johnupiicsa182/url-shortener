# url-shortener
 Acortador de url
El servicio tiene como fin poder recibir una url y poder generar un alias en base a ciertas reglas de negocio, al igual de poder consultar algun alias previamente generado y retornar la url que le corresponderia.

La aplicacion usa las liberias de sql lite para poder guardar y consultar los datos que se lleguen hacer durante la implementacion del servicio.
Este archivo se genera en la Ruta:C:\sqlite\data.db por si se desea consultar el archivo.

EL servicio se genero dividiendo los archivos en capas para tener un mayor control sobre el codigo y sea mucho mas facil poder leer el proyecto.

El servicio usa Basic Auth para poder hacer cualquier peticion.
User=nearsoft
Password=JavaSchool
----Ejemplo peticion Post----
Peticion:
  http://localhost:8080/urlShortener/createAlias
  {
      "url":"www.google.maps.com"
  }
Respuesta:
 {
     "url": "www.google.maps.com",
     "respuesta": "AbaBB"
 }
 
 ----Ejemplo peticion Get----
 Peticion:
  http://localhost:8080/urlShortener/getAlias/AbaBB
 Respuesta:
  www.google.maps.com
