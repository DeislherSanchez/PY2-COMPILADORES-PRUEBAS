@echo off

set "CUP_JAR=C:\javacup\java-cup-11b.jar"
set "CUP_RT_JAR=C:\javacup\java-cup-11b-runtime.jar"
if not exist "%CUP_RT_JAR%" set "CUP_RT_JAR=%CUP_JAR%"

cd parser_flex
call jflex -d ..\generated\lexer Lexer.flex
cd ..

java -jar "%CUP_JAR%" -destdir generated\parser parser_flex\Parser.cup

javac -cp ".;generated\lexer;generated\parser;%CUP_RT_JAR%" clases\*.java generated\lexer\*.java generated\parser\*.java

pause

cls

java -cp ".;clases;generated\lexer;generated\parser;%CUP_RT_JAR%" Main archivos_prueba\prueba3.txt

