package Funcionario;

import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Funcionario;

public class FuncionarioControl {

    private List<Funcionario> listaFuncionario;
    private DAOFactory daoFactory;

    public FuncionarioControl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean setFuncionario(Funcionario funcionario) throws Exception {
        if (daoFactory.createFuncionarioDAO().setFuncionario(funcionario)) {
            return true;
        }
        return false;
    }

    public Funcionario getFuncionario(String cpf) throws Exception {
        return daoFactory.createFuncionarioDAO().getFuncionario(cpf);
    }

    public boolean removeFuncionario(String cpf) throws Exception {
        if (daoFactory.createFuncionarioDAO().removeFuncionario(cpf)) {
            return true;
        }
        return false;
    }

    public boolean arqFuncVazio() throws Exception {
        return daoFactory.createFuncionarioDAO().arqFuncVazio();
    }

    public int getProxCodFunc() throws Exception {
        return daoFactory.createFuncionarioDAO().getProxCodFunc();
    }

    public int getQtdadeFuncCadas() {
        return listaFuncionario.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Funcionario funcionario = listaFuncionario.get(linha);
        switch (coluna) {
            case 0:
                return funcionario.getCodigo();
            case 1:
                return funcionario.getNome();
            case 2:
                return funcionario.getRg();
            case 3:
                return funcionario.getCpf();
            case 4:
                return funcionario.getCarteiraTrabalho();
            case 5:
                return funcionario.getCargo();
            case 6:
                return funcionario.getSalarioLiquido();
            case 7:
                return funcionario.getSexo();
            case 8:
                return funcionario.getCep();
            case 9:
                return funcionario.getEndereco();
            case 10:
                return funcionario.getBairro();
            case 11:
                return funcionario.getNumero();
            case 12:
                return funcionario.getComplemento();
            case 13:
                return funcionario.getCidade();
            case 14:
                return funcionario.getEstado();
            case 15:
                return funcionario.getFone();
            case 16:
                return funcionario.getCelular1();
            case 17:
                return funcionario.getEmail();
            case 18:
                return funcionario.getDescricao();
            case 19:
                return funcionario.getDataContratacaoDemissao();
            default:
                return funcionario.isAtivo();
        }
    }

    public void listaTodos() throws Exception {
        listaFuncionario = daoFactory.createFuncionarioDAO().listFuncionario();
    }

    public List<Funcionario> getLista() throws Exception {
        return daoFactory.createFuncionarioDAO().listFuncionario();
    }
}
