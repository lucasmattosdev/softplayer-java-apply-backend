@echo off
REM Script de inicialização do servidor

SET MAVEN=mvn clean install
SET SERVIDOR=..\wildfly\bin\standalone.bat -b 0.0.0.0 --debug 8787 -Djboss.socket.binding.port-offset=17564

if [%1]==[] (
	echo ***** Iniciando o Servidor *****
	call %MAVENWS% && %MAVEN% && %SERVIDOR%
) else (
	if [%1]==[mvn] (
		echo ***** Iniciando Maven *****
		call %MAVEN%
	) else if [%1]==[server] (
		echo ***** Iniciando Servidor *****
		call %SERVIDOR%
	) else if [%1]==[help] (
		echo Comando: start.bat [server/mvn/liquibase]
	)
)