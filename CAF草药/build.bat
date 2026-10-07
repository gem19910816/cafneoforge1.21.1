@echo off
rem Crop Expansion - NeoForge 1.21.1 build launcher (ASCII only, keep CRLF)
setlocal
cd /d "%~dp0"
powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0build.ps1" %*
set RC=%ERRORLEVEL%
echo.
if not "%RC%"=="0" echo BUILD FAILED with exit code %RC%
pause
exit /b %RC%
