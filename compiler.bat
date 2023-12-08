@echo off
cls
cd src
javac -d ..\bin -cp ..\lib\jsoup-1.17.1.jar myPackage\*.java

cd..
cd bin
jar cfm ..\Gatherer.jar ..\res\MANIFEST.MF myPackage\*.class
cd..
java -jar Gatherer.jar