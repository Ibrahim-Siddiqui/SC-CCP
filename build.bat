@echo off
REM Build script for Hotel Reservation System

echo ==========================================
echo Hotel Reservation System - Build Script
echo ==========================================
echo.

REM Create target directory
if not exist target mkdir target
if not exist target\classes mkdir target\classes
if not exist target\test-classes mkdir target\test-classes

echo [1] Compiling source files...
cd src\main\java
javac -d ..\..\..\target\classes domain\*.java
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)
javac -d ..\..\..\target\classes Main.java
cd ..\..\..\

echo.
echo [2] Compilation successful!
echo.
echo Source files compiled to: target\classes\
echo.
echo To run the application:
echo   java -cp target\classes Main
echo.
echo Note: Unit tests require JUnit libraries.
echo       For tests, please install Maven and run: mvn test
echo.

pause
