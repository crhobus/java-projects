package TransportadoraDAO;

import java.util.List;

import Modelo.Transportadora;

public interface TransportadoraDAO {

    public boolean setTransportadora(Transportadora transportadora) throws Exception;

    public Transportadora getTransportadora(String nome) throws Exception;

    public boolean removeTransportadora(String nome) throws Exception;

    public List<Transportadora> listTransportadora() throws Exception;

    public boolean arqTransVazio() throws Exception;

    public int getProxCodTrans() throws Exception;
}
