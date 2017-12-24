package projeto.rmi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {

    public boolean saque(long nrConta, String senha, double valor) throws RemoteException;

    public boolean deposito(long nrConta, double valor) throws RemoteException;

    public boolean transferencia(long nrContaOrigem, String senha, double valor, long nrContaDestino) throws RemoteException;
}
