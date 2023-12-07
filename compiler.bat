@echo off
cd src
javac -d ..\bin myPackage\*.java
cd..
cd bin
jar cfm ..\Gatherer.jar ..\res\MANIFEST.MF myPackage\*.class
cd..
java -jar Gatherer.jar