package DAOFactory;

import TratamentoDAO.TextoTratamento;
import TratamentoDAO.TratamentoDAO;
import VeterinarioDAO.TextoVeterinario;
import VeterinarioDAO.VeterinarioDAO;
import AnimalDAO.AnimalDAO;
import AnimalDAO.TextoAnimal;
import ClienteDAO.ClienteDAO;
import ClienteDAO.TextoCliente;
import ConsultaDAO.ConsultaDAO;
import ConsultaDAO.TextoConsulta;
import EspecieDAO.EspecieDAO;
import EspecieDAO.TextoEspecie;
import ExameDAO.ExameDAO;
import ExameDAO.TextoExame;

public class TextoFactory extends DAOFactory {

    @Override
    public AnimalDAO createAnimalDAO() {
        return new TextoAnimal();
    }

    @Override
    public ClienteDAO createClienteDAO() {
        return new TextoCliente();
    }

    @Override
    public ConsultaDAO createConsultaDAO() {
        return new TextoConsulta();
    }

    @Override
    public EspecieDAO createEspecieDAO() {
        return new TextoEspecie();
    }

    @Override
    public ExameDAO createExameDAO() {
        return new TextoExame();
    }

    @Override
    public TratamentoDAO createTratamentoDAO() {
        return new TextoTratamento();
    }

    @Override
    public VeterinarioDAO createVeterinarioDAO() {
        return new TextoVeterinario();
    }
}
