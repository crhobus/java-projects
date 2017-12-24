cd C:\Users\Caio\Documents\NetBeansProjects\SistemasDistribuidos\build\classes
set path=%path%;"C:\Program Files\Java\jdk1.7.0\bin"
set classpath=.
rmic -keep projeto.rmi.bradesco.ServidorBradesco
rmic -keep projeto.rmi.rmi.ServidorRMI
start rmiregistry
pause