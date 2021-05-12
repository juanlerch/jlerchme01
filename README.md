DESAFIOS:
Nivel 1: Programa (en cualquier lenguaje de programación) que cumpla con el método ...

- Se utiliza el lenguaje JAVA. 
- La clase Mutante.java implementa el método publico boolean isMutant(String[] dna).


Nivel 2: Crear una API REST…..
- La api rest se encuentra Implementada en Google App Engine, y desplegada en el siguientes  urls
  https://jlerchme02.appspot.com/mutant
- Se implemento mediante  WebServlet en las clases XmenApi.java

Nivel 3: Anexar una base de datos, la cual guarde los ADNs verificados con la API….
- Se utilizó Google Cloud  Datastore para persistir los ADNs.
- Como se estima que puede haber millones de peticiones, se utilizó una técnica de dividir en distintas Entidades lo que son verificaciones de humanos y de mutantes.
- Para evitar duplicados (solo 1 registro por ADN.) , se utilizó una representación alfanumérica del ADN como Key primaria.
- Para contar las filas, se utilizan las estadísticas de cantidad de filas por Entidad provistas por Datastore, habitualmente tienen una actualización de 24/48hs. 
  En caso de no existir estadísticas, se cuentan las filas por cursor.

NOTA: en caso de necesitar estadísticas más recientes, lo que se suele hacer (entre otras soluciones) es usar acumuladores que se ejecutan en background cada X tiempo (por ejemplo 15 Minutos), cuya función es acumular en otra Entidad la cantidad de registros anexados en el último periodo.

- El servicio /stats se encuentra publicado en 
https://jlerchme02.appspot.com/stat

- Para soportar fluctuaciones agresivas de tráfico.. se propone desplegar el aplicativo con Docker en  Google Kubernetes Engine. 

- Los comandos de GCloud para crear el cluster se encuentra en el archivo “kuberentes”. 

NOTA: Por requerimiento de Google Cloud, primero se debe desplegar el proyecto en Google App Engine Flexible para tener acceso desde Kubernetes al servicio de Datastore (ver instrucciones)

 
- Para los Test-Automáticos se utilizó JUNIT , y para “Code coverage” se utilizó Jacoco.exec, alcanzando una cobertura del 81%.
  El test informe del test de  cobertura  se encuentra publicado en https://jlerchme02.appspot.com/my-reports/index.html

- El código fuente se encuentra publico en 
https://github.com/juanlerch/jlerchme01.git

INSTRUCCIONES  ejecutar el programa o la API. (Para Nivel 2 y 3: En README de github).

- URL de la API (Nivel 2 y 3).
https://jlerchme02.appspot.com/mutant
https://jlerchme02.appspot.com/stat

- Para probar / ejecutar la apl se suele utilizar el software POSTMAN, que permite hacer solicitud GET/POST . y se puede compartir las Colecciones 
adjunto link de las solicitudes compartidas https://www.getpostman.com/collections/6b7a078d326c2b07f5d4

- Para ejecutar el programa desde Eclipse:
  Bajar el repositorio GIT a la PC
  Instalar eclipse (https://www.eclipse.org/downloads/) , y gcloud (https://cloud.google.com/sdk/docs/install)
  agregar el pluggin de Google Cloud para Ecplise https://cloud.google.com/eclipse/docs/quickstart
  Importar el proyecto a eclipse (como existing maven project)
  Desde Eclipse
    run as mavel install
    run as mavel test
    deploy to app engine

