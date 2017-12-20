package MaquinaGomaRemota;

import java.io.Serializable;

public interface Estado extends Serializable {

    public void inserirMoeda();

    public void ejetarMoeda();

    public void acionarAlavanca();

    public void entregar();
}
