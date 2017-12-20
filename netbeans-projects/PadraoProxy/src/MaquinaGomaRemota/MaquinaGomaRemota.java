package MaquinaGomaRemota;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MaquinaGomaRemota extends Remote {

    public int getCont() throws RemoteException;

    public String getLolizacao() throws RemoteException;

    public Estado getEstado() throws RemoteException;
}
