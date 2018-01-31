package FuncionarioDAO;

import java.sql.Connection;
import java.util.List;

import Modelo.Funcionario;

public interface FuncionarioDAO {

    public boolean setFuncionario(Funcionario funcionario, Connection con) throws Exception;

    public Funcionario getFuncionario(String cpf, Connection con) throws Exception;

    public boolean removeFuncionario(String cpf, Connection con) throws Exception;

    public List<Funcionario> listFuncionarios(Connection con) throws Exception;

    public boolean isFuncVazio(Connection con) throws Exception;

    public int getProxCodFunc(Connection con) throws Exception;

    public boolean verificaFuncCadas(String nome, Connection con) throws Exception;
}
