package control.action.produto;

import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.util.List;
import model.dao.ServidorDAO;
import model.dao.TamanhoDAO;
import model.entidades.Tamanho;

public class TamanhoAction {

    private TamanhoDAO tamanhoDAO;
    private ServidorDAO servidorDAO;

    public TamanhoAction(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
        this.tamanhoDAO = new TamanhoDAO(servidorDAO);
    }

    public List<Tamanho> getTamanhos() throws ExceptionError {
        try {
            return tamanhoDAO.getTamanhos();
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os tamanhos do sistema."
                    + "\nErro no Servidor");
        }
    }

    public Tamanho getTamanho(int cd_tamanho) throws ExceptionError {
        try {
            return tamanhoDAO.getTamanho(cd_tamanho);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os tamanhos do sistema."
                    + "\nErro no Servidor");
        }
    }

    public Tamanho salvar(Dados dados, Tamanho tamanho) throws ExceptionInfo, ExceptionError, Exception {
        Tamanho p = null;

        try {
            p = tamanhoDAO.getTamanho(dados.getInfo("DS_TAMANHO"));
        } catch (Exception ex) {
        }
        if (p != null) {
            throw new ExceptionInfo("Já existe um tamanho com esta descrição!");
        }

        if (tamanho == null) {
            tamanho = new Tamanho();

        }

        tamanho.setDsTamanho(dados.getInfo("DS_TAMANHO"));
        System.out.println(dados.getInfo("TM_ATIVO"));

        if ("S".equals(dados.getInfo("TM_ATIVO"))) {
            tamanho.setTmAtivo(1);
        } else {
            tamanho.setTmAtivo(0);
        }


        try {
            servidorDAO.salvar(tamanho, tamanho.getCdTamanho() == 0);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o tamanho no sistema."
                    + "\nErro no Servidor");
        }
        return tamanho;
    }

    public void excluirTamanho(int cdTamanho) throws ExceptionError {
        try {
            tamanhoDAO.excluirTamanho(cdTamanho);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível excluir o tamanho do sistema."
                    + "\nErro no Servidor");
        }
    }
}
