@REM Begin all REM lines with '@' in case MAVEN_BATCH_ECHO is 'on'
@echo off
@REM set title of command window
title %0

@REM ��ǰ�ű��ļ����ڵ�·��
set CurProPath=%~dp0
@REM ���� java�ļ�Ŀ¼
set JavaPath=%CurProPath%src\main\java
@REM ���� java�ļ�����Ŀ¼
set JavaTestPath=%CurProPath%src\test\java
@REM ������ԴĿ¼
set JavaResourcePath=%JavaPath%\..\resources
@REM ���� class Ŀ¼
set JavaClassPath=%CurProPath%target\classes
@REM ���û�������
set GroupIdPath=\com\example\demo
set GroupId=com.example.demo
@REM �ж� class Ŀ¼�Ƿ���ڣ������ھʹ���
if not exist %JavaClassPath% (
    md %JavaClassPath%
    echo %JavaClassPath% Ŀ¼�����ڣ��Ѵ�����Ŀ¼��
) else (
    echo %JavaClassPath% Ŀ¼�Ѵ��ڣ����贴����
)
@REM ��javaĿ¼�������Դ�ļ� copy �� class Ŀ¼��
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

@REM ��targetĿ¼�µ��ļ����Ϊjar�ļ���������
"%JAVA_HOME%\bin\jar" cvfm Web.jar MANIFEST.MF -C target\classes .
@REM exit
@pause