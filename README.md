# Proyecto 2 - Análisis Semántico y Generación Código Intermedio

<img width="250" height="44" alt="logo-tec" src="https://github.com/user-attachments/assets/a8a9c8d9-f1a6-481a-b542-577e156254e6" />

**Institución:** Tecnológico  de Costa Rica Centro Académico de Limón  
**Curso:** IC5701 - Compiladores e interpretes  
**Grupo:** 60  
**Profesor:** Allan Rodriguez Davila  
**Semestre:** I Semestre 2026  
## Estudiantes

| Nombre                   | Carnet      |
|--------------------------|-------------|
| Natalia Granados Rosales | 2021144286  |
| Deislher Sanchez Funez   | 2023032794  |

---

## Requisitos

### Instalaciones necesarias

- **JDK 26:**  
  https://www.oracle.com/latam/java/technologies/downloads/#jdk26-windows

- **Java Runtime:**  
  https://www.java.com/es/download/

- **CUP (java-cup-11b):**  
  https://www2.cs.tum.edu/projects/cup/

- **JFlex:**  
  https://jflex.de/download.html

---

## Configuracion de variables de entorno

Luego de extraer el contenido de los archivos ZIP, configurar lo siguiente:

### CLASSPATH

Crear una nueva variable de entorno con los siguientes valores:
Nombre: CLASSPATH
Valor:  C:\javacup\java-cup-11b.jar;C:\javacup\java-cup-11b-runtime.jar;
### PATH

Agregar la siguiente ruta al PATH del sistema:
C:\jflex\bin
---

## Ejecucion Windows

> IMPORTANTE: El comando debe ejecutarse estando ubicado dentro de la carpeta `programa`.

Ejecutar el siguiente comando:

**Opcion rapida (recomendado):** .\build_and_run.bat

**Opcion manual (paso a paso):**

**1. Generar el scanner con JFlex:** jflex -d generated\lexer parser_flex\Lexer.flex

**2. Generar el parser con CUP:** java -jar C:\javacup\java-cup-11b.jar -destdir generated\parser parser_flex\Parser.cup

**3. Compilar todos los archivos Java:** javac -cp ".;generated\lexer;generated\parser;C:\javacup\java-cup-11b-runtime.jar" clases\*.java generated\lexer\*.java generated\parser\*.java

**4. Ejecutar el programa:** java -cp ".;clases;generated\lexer;generated\parser;C:\javacup\java-cup-11b-runtime.jar" Main archivos_prueba\prueba3.txt
