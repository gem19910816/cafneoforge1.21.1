@rem
@rem Ammo Unify - Gradle wrapper launcher (Windows)
@rem
@rem Requires JDK 21. Uses JAVA_HOME when set, otherwise falls back to java on PATH.
@rem Keep this file ASCII-only: cmd.exe reads .bat files using the OEM code page.
@rem
@echo off
setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_HOME=%DIRNAME%

set JAVA_EXE=java.exe
if defined JAVA_HOME set JAVA_EXE=%JAVA_HOME%\bin\java.exe

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

endlocal
