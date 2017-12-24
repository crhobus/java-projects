cd C:\Users\Caio\Documents\NetBeansProjects\SistemasDistribuidos\src\exemplo\corba
set path=%path%;"C:\Program Files\Java\jdk1.7.0\bin"
idlj -fall boasVindas.idl
javac boasVindas\*.java
set classpath=.
javac *.java
start orbd
start java boasVindas.servidor
java cliente -ORBInitialHost localhost
pause