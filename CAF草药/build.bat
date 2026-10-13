@echo off
rem CAF_Herbs - NeoForge 1.21.1 build entry (ASCII only, keep CRLF + GBK-compatible)
rem Runs the project-local Gradle wrapper only. No absolute paths, no copying
rem outside the project (spec 4.2 / 4.3 / 5.2). Requires JAVA_HOME = a JDK 21.
setlocal
cd /d "%~dp0"
if "%~1"=="" (
  call gradlew.bat build
) else (
  call gradlew.bat %*
)
set RC=%ERRORLEVEL%
echo.
if not "%RC%"=="0" echo BUILD FAILED with exit code %RC%
pause
exit /b %RC%
