package DAOFactory;

import AnimalDAO.AnimalDAO;
import AnimalDAO.BinarioAnimal;
import ClienteDAO.BinarioCliente;
import ClienteDAO.ClienteDAO;
import ConsultaDAO.BinarioConsulta;
import ConsultaDAO.ConsultaDAO;
import EspecieDAO.BinarioEspecie;
import EspecieDAO.EspecieDAO;
import ExameDAO.BinarioExame;
import ExameDAO.ExameDAO;
import TratamentoDAO.BinarioTratamento;
import TratamentoDAO.TratamentoDAO;
import VeterinarioDAO.BinarioVeterinario;
import VeterinarioDAO.VeterinarioDAO;

public class BinarioFactory extends DAOFactory {

    @Override
    public AnimalDAO createAnimalDAO() {
        return new BinarioAnimal();
    }

    @Override
    public ClienteDAO createClienteDAO() {
        return new BinarioCliente();
    }

    @Override
    public ConsultaDAO createConsultaDAO() {
        return new BinarioConsulta();
    }

    @Override
    public EspecieDAO createEspecieDAO() {
        return new BinarioEspecie();
    }

    @Override
    public ExameDAO createExameDAO() {
        return new BinarioExame();
    }

    @Override
    public TratamentoDAO createTratamentoDAO() {
        return new BinarioTratamento();
    }

    @Override
    public VeterinarioDAO createVeterinarioDAO() {
        return new BinarioVeterinario();
    }
}
