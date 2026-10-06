@echo off
setlocal
rem Thin Air (1.21.1 NeoForge) build script
set "JAVA_HOME=E:\Minecraft\jave\jdk-21_windows-x64_bin\jdk-21.0.3"
set "GRADLE_USER_HOME=E:\Minecraft\开发者制作\mod制作\通用\gradle-home"
"E:\Minecraft\开发者制作\mod制作\通用\gradle-8.8\bin\gradle.bat" %*
endlocal
