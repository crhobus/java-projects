package TransportadoraDAO;

import java.util.List;

import DAOFactory.ArqTextoGenerico;
import Modelo.Transportadora;

public class TransportadotaTexto extends ArqTextoGenerico implements TransportadoraDAO {

    @Override
    public boolean arqTransVazio() throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getProxCodTrans() throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Transportadora getTransportadora(String nome) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Transportadora> listTransportadora() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeTransportadora(String nome) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean setTransportadora(Transportadora transportadora) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }
}
