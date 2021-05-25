@REM Begin all REM lines with '@' in case MAVEN_BATCH_ECHO is 'on'
@echo off
@REM set title of command window
title %0

@REM 当前脚本文件所在的路径
set CurProPath=%~dp0
@REM 设置 java文件目录
set JavaPath=%CurProPath%src\main\java
@REM 设置 java文件测试目录
set JavaTestPath=%CurProPath%src\test\java
@REM 设置资源目录
set JavaResourcePath=%JavaPath%\..\resources
@REM 设置 class 目录
set JavaClassPath=%CurProPath%target\classes
@REM 设置基础包名
set GroupIdPath=\com\example\demo
set GroupId=com.example.demo
@REM 判断 class 目录是否存在，不存在就创建
if not exist %JavaClassPath% (
    md %JavaClassPath%
    echo %JavaClassPath% 目录不存在，已创建该目录！
) else (
    echo %JavaClassPath% 目录已存在，无需创建！
)
@REM 把java目录里面的资源文件 copy 到 class 目录中
if exist %JavaResourcePath% (
    copy %JavaResourcePath%\* %JavaClassPath%
)

@setlocal

set ERROR_CODE=0

@REM To isolate internal variables from possible post scripts, we use another setlocal
@setlocal

@REM ==== START VALIDATION ====
if not "%JAVA_HOME%" == "" goto OkJHome

echo.
echo Error: JAVA_HOME not found in your environment. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

:OkJHome
if exist "%JAVA_HOME%\bin\java.exe" goto init
echo.
echo Error: JAVA_HOME is set to an invalid directory. >&2
echo JAVA_HOME = "%JAVA_HOME%" >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

@REM ==== END VALIDATION ====

:init
set lib=.;target\classes

@REM %JAVA_HOME%\bin\javac -d target\classes -sourcepath src\java -classpath target\classes .\src\main\java\com\mycompany\mypro\App.java
@REM %JAVA_HOME%\bin\javac -d target\classes -sourcepath src\java -classpath target\classes .\src\test\java\com\mycompany\mypro\AppTest.java

@REM %JAVA_HOME%\bin\javac -d target\classes -sourcepath src\java -classpath target\classes %JavaPath%%GroupIdPath%\App.java
@REM %JAVA_HOME%\bin\javac -d target\classes -sourcepath src\java -classpath %lib% %JavaPath%%GroupIdPath%\App.java
"%JAVA_HOME%\bin\javac" -d target\classes -sourcepath src\java -classpath %lib% @sourcefiles 

@REM %JAVA_HOME%\bin\java -classpath target\classes %GroupId%.App
@REM "%JAVA_HOME%\bin\java" -classpath %lib% %GroupId%.Main

@REM 将target目录下的文件打包为jar文件，并运行
"%JAVA_HOME%\bin\jar" cvfm Web.jar MANIFEST.MF -C target\classes .
@REM exit
@pause