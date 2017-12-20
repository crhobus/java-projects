package MaquinaGomaRemota;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MaquinaGoma extends UnicastRemoteObject implements MaquinaGomaRemota {

    private Estado estadoVazio, estadoSemMoeda, estadoTemMoeda, estadoVendido, estado = estadoVazio;
    private String localizacao;
    private int numeroGomas;

    public MaquinaGoma(String localizacao, int numeroGomas) throws RemoteException {
        estadoVazio = new EstadoVazio(this);
        estadoSemMoeda = new EstadoSemMoeda(this);
        estadoTemMoeda = new EstadoTemMoeda(this);
        estadoVendido = new EstadoVendido(this);
        this.numeroGomas = numeroGomas;
        if (numeroGomas > 0) {
            estado = estadoSemMoeda;
        }
        this.localizacao = localizacao;
    }

    public void inserirMoeda() {
        estado.inserirMoeda();
    }

    public void ejetarMoeda() {
        estado.ejetarMoeda();
    }

    public void acionarAlavanca() {
        estado.acionarAlavanca();
        estado.entregar();
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void lancamentoBala() {
        System.out.println("Fornecendo uma goma ao cliente");
        if (numeroGomas != 0) {
            numeroGomas--;
        }
    }

    public Estado getEstadoSemMoeda() {
        return estadoSemMoeda;
    }

    public Estado getEstadoTemMoeda() {
        return estadoTemMoeda;
    }

    public Estado getEstadoVazio() {
        return estadoVazio;
    }

    public Estado getEstadoVendido() {
        return estadoVendido;
    }

    @Override
    public String toString() {
        return "Maquina de goma";
    }

    public int getCont() throws RemoteException {
        return numeroGomas;
    }

    public String getLolizacao() throws RemoteException {
        return localizacao;
    }

    public Estado getEstado() throws RemoteException {
        return estado;
    }
}
