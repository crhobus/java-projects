package FuncionarioDAO;

import java.util.List;

import Modelo.Funcionario;

public interface FuncionarioDAO {

    public boolean setFuncionario(Funcionario funcionario) throws Exception;

    public Funcionario getFuncionario(String cpf) throws Exception;

    public boolean removeFuncionario(String cpf) throws Exception;

    public List<Funcionario> listFuncionario() throws Exception;

    public boolean arqFuncVazio() throws Exception;

    public int getProxCodFunc() throws Exception;
}
