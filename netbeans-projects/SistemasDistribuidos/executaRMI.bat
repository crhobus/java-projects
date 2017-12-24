cd C:\Users\Caio\Documents\NetBeansProjects\SistemasDistribuidos\build\classes
set path=%path%;"C:\Program Files\Java\jdk1.7.0_02\bin"
set classpath=.
rmic -keep exemplo.rmi.HelloServer
start rmiregistry
dir RMIHello
pause