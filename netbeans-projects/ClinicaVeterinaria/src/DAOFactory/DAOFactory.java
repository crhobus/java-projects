package DAOFactory;

import TratamentoDAO.TratamentoDAO;
import VeterinarioDAO.VeterinarioDAO;
import AnimalDAO.AnimalDAO;
import ClienteDAO.ClienteDAO;
import ConsultaDAO.ConsultaDAO;
import EspecieDAO.EspecieDAO;
import ExameDAO.ExameDAO;

public abstract class DAOFactory {

    public abstract EspecieDAO createEspecieDAO();

    public abstract AnimalDAO createAnimalDAO();

    public abstract ClienteDAO createClienteDAO();

    public abstract ExameDAO createExameDAO();

    public abstract TratamentoDAO createTratamentoDAO();

    public abstract ConsultaDAO createConsultaDAO();

    public abstract VeterinarioDAO createVeterinarioDAO();

    public static DAOFactory getFactory(TipoArquivo tipoArquivo) {
        if (tipoArquivo == TipoArquivo.TEXTO) {
            return new TextoFactory();
        }
        if (tipoArquivo == TipoArquivo.BINARIO) {
            return new BinarioFactory();
        }
        return null;
    }
}
