cd C:\Users\Caio\Documents\NetBeansProjects\SistemasDistribuidos\src

set path=%path%;"C:\Program Files\Java\jdk1.7.0\bin"

javac projeto\corba\sistemaBancarioCorba\*.java

set classpath=.

javac projeto\corba\sistemaBancarioCorba\*.java

start orbd

start java -classpath C:\Users\Caio\Documents\NetBeansProjects\SistemasDistribuidos\ojdbc14.jar; projeto.corba.sistemaBancarioCorba.SistemaCorba

pause