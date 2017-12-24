package projeto.rmi.bradesco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SistemaBancarioBradesco extends Remote {

    public boolean deposito(long nrConta, double valor) throws RemoteException;
}
