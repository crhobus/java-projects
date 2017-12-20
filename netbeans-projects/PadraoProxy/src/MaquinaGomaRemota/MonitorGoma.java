package MaquinaGomaRemota;

import java.rmi.RemoteException;

public class MonitorGoma {

    private MaquinaGomaRemota maquina;

    public MonitorGoma(MaquinaGomaRemota maquina) {
        this.maquina = maquina;
    }

    public void relatorio() {
        try {
            System.out.println("Maquina de goma: " + maquina.getLolizacao());
            System.out.println("Registro atual: " + maquina.getCont() + " gomas");
            System.out.println("Estado atual: " + maquina.getEstado());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
