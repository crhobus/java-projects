package control.action.produto;

import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.util.List;
import model.dao.SaborDAO;
import model.dao.ServidorDAO;
import model.entidades.Sabor;

public class SaborAction {

    private SaborDAO saborDAO;
    private ServidorDAO servidorDAO;

    public SaborAction(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
        this.saborDAO = new SaborDAO(servidorDAO);
    }

    public List<Sabor> getSabores() throws ExceptionError {
        try {
            return saborDAO.getSabores();
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os sabores do sistema."
                    + "\nErro no Servidor");
        }
    }

    public Sabor salvar(Dados dados, Sabor sabor) throws ExceptionInfo, ExceptionError, Exception {
        Sabor p = null;

        try {
            p = saborDAO.getSabor(dados.getInfo("DS_SABOR"));
        } catch (Exception ex) {
        }
        if (p != null) {
            throw new ExceptionInfo("Já existe um sabor com esta descrição!");
        }

        if (sabor == null) {
            sabor = new Sabor();

        }

        //    produto.setCdProduto(Integer.parseInt(dados.getInfo("CD_PRODUTO")));

        sabor.setNmSabor(dados.getInfo("NM_SABOR"));
        sabor.setDsSabor(dados.getInfo("DS_SABOR"));
        
        try {
            servidorDAO.salvar(sabor, sabor.getCdSabor() == 0);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o sabor no sistema."
                    + "\nErro no Servidor");
        }
        return sabor;
    }

    public void excluirSabor(int cdSabor) throws ExceptionError {
        try {
            saborDAO.excluirSabor(cdSabor);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível excluir o sabor do sistema."
                    + "\nErro no Servidor");
        }
    }
}
