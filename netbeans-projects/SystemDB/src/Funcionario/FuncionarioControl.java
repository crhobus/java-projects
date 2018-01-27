package Funcionario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Funcionario;

public class FuncionarioControl {

    private List<Funcionario> listaFunc;
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioControl(Connection con) {
        funcionarioDAO = new FuncionarioDAO(con);
    }

    public boolean insertFunc(Funcionario funcionario) throws SQLException {
        return funcionarioDAO.insertFunc(funcionario);
    }

    public boolean updateFunc(Funcionario funcionario) throws SQLException {
        return funcionarioDAO.updateFunc(funcionario);
    }

    public boolean isFuncCadastrado(int codFunc) throws SQLException {
        return funcionarioDAO.isFuncCadastrado(codFunc);
    }

    public int getFuncCadastrado(String cpf, int codFunc) throws SQLException {
        return funcionarioDAO.getFuncCadastrado(cpf, codFunc);
    }

    public int getFuncCadastrado(String cpf) throws SQLException {
        return funcionarioDAO.getFuncCadastrado(cpf);
    }

    public boolean isFuncVazio() throws SQLException {
        return funcionarioDAO.isFuncVazio();
    }

    public int getProxCodFunc() throws SQLException {
        return funcionarioDAO.getProxCodFunc();
    }

    public boolean deleteFunc(int codFunc) throws SQLException {
        return funcionarioDAO.deleteFunc(codFunc);
    }

    public int getQtdadeFuncCadas() {
        return listaFunc.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Funcionario func = listaFunc.get(linha);
        switch (coluna) {
            case 0:
                return func.getCodFunc();
            case 1:
                return func.getNome();
            case 2:
                return func.getCpfCnpj();
            case 3:
                return func.getRgIe();
            case 4:
                return func.getEndereco();
            case 5:
                return func.getBairro();
            case 6:
                return func.getNumero();
            case 7:
                return func.getCep();
            case 8:
                return func.getCidade();
            case 9:
                return func.getEstado();
            case 10:
                return func.getFone();
            case 11:
                return func.getCargo();
            case 12:
                return func.getSalario();
            default:
                if (func.isAtivo()) {
                    return "T";
                } else {
                    return "F";
                }
        }
    }

    public void limparLista() {
        listaFunc.clear();
    }

    public void listarFunc() throws SQLException {
        listaFunc = funcionarioDAO.listFunc();
    }

    public void getLista(final int coluna, final String s, final int n, final double d) throws SQLException {
        listaFunc = funcionarioDAO.getLista(coluna, s, n, d);
    }

    public Funcionario getListaPosicao(int posicao) {
        return listaFunc.get(posicao);
    }
}
