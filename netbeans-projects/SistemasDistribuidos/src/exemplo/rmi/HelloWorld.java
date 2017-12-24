/** HelloWorld.java **/
package exemplo.rmi;

import java.rmi.*;

public interface HelloWorld extends Remote {

    public String hello() throws RemoteException;
}
