package VeiculosDAO;

import java.sql.Connection;
import java.util.List;

import Modelo.Veiculo;

public interface VeiculosDAO {

    public boolean setVeiculo(Veiculo veiculo, Connection con) throws Exception;

    public Veiculo getVeiculo(String placa, Connection con) throws Exception;

    public boolean removeVeiculo(String placa, Connection con) throws Exception;

    public List<Veiculo> listVeiculos(Connection con) throws Exception;

    public boolean isVeiVazio(Connection con) throws Exception;

    public int getProxCodVei(Connection con) throws Exception;
}
