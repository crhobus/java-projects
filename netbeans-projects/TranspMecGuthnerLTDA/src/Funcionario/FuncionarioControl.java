package Funcionario;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Funcionario;

public class FuncionarioControl {

    private List<Funcionario> listaFuncionario;
    private DAOFactory daoFactory;
    private Connection con;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

    public FuncionarioControl(DAOFactory daoFactory, Connection con) {
        this.daoFactory = daoFactory;
        this.con = con;
    }

    public boolean setFuncionario(Funcionario funcionario) throws Exception {
        if (daoFactory.createFuncionarioDAO().setFuncionario(funcionario, con)) {
            return true;
        }
        return false;
    }

    public Funcionario getFuncionario(String cpf) throws Exception {
        return daoFactory.createFuncionarioDAO().getFuncionario(cpf, con);
    }

    public boolean verificaFuncCadas(String nome) throws Exception {
        return daoFactory.createFuncionarioDAO().verificaFuncCadas(nome, con);
    }

    public boolean removeFuncionario(String cpf) throws Exception {
        if (daoFactory.createFuncionarioDAO().removeFuncionario(cpf, con)) {
            return true;
        }
        return false;
    }

    public boolean isFuncVazio() throws Exception {
        return daoFactory.createFuncionarioDAO().isFuncVazio(con);
    }

    public int getProxCodFunc() throws Exception {
        return daoFactory.createFuncionarioDAO().getProxCodFunc(con);
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
                return funcionario.getUf();
            case 15:
                return funcionario.getFone();
            case 16:
                return funcionario.getCelular1();
            case 17:
                return funcionario.getEmail();
            case 18:
                return funcionario.getIdade();
            case 19:
                return formatDate.format(funcionario.getDataContratacaoDemissao());
            default:
                return funcionario.isAtivo();
        }
    }

    public void listaTodos() throws Exception {
        listaFuncionario = daoFactory.createFuncionarioDAO().listFuncionarios(con);
    }

    public void limparLista() {
        listaFuncionario.clear();
    }

    public void getListaFuncCod(int codigo) {
        for (Funcionario funcionario : listaFuncionario) {
            if (codigo == funcionario.getCodigo()) {
                listaFuncionario.clear();
                listaFuncionario.add(funcionario);
                return;
            }
        }
    }

    public void getListaFuncNome(String nome) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (nome.equalsIgnoreCase(funcionario.getNome())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncRG(String rg) {
        for (Funcionario funcionario : listaFuncionario) {
            if (rg.equals(funcionario.getRg())) {
                listaFuncionario.clear();
                listaFuncionario.add(funcionario);
                return;
            }
        }
    }

    public void getListaFuncCPF(String cpf) {
        for (Funcionario funcionario : listaFuncionario) {
            if (cpf.equals(funcionario.getCpf())) {
                listaFuncionario.clear();
                listaFuncionario.add(funcionario);
                return;
            }
        }
    }

    public void getListaFuncCarteTrab(String numero) {
        for (Funcionario funcionario : listaFuncionario) {
            if (numero.equals(funcionario.getCarteiraTrabalho())) {
                listaFuncionario.clear();
                listaFuncionario.add(funcionario);
                return;
            }
        }
    }

    public void getListaFuncCargo(String cargo) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (cargo.equalsIgnoreCase(funcionario.getCargo())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncSalario(double salario) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (salario == funcionario.getSalarioLiquido()) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncSexo(String sexo) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (sexo.equalsIgnoreCase(funcionario.getSexo())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncCEP(String cep) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (cep.equals(funcionario.getCep())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncEnd(String endereco) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (endereco.equalsIgnoreCase(funcionario.getEndereco())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncBairro(String bairro) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (bairro.equalsIgnoreCase(funcionario.getBairro())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncNum(int numero) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (numero == funcionario.getNumero()) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncCom(String complemento) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (complemento.equalsIgnoreCase(funcionario.getComplemento())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncCid(String cidade) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (cidade.equalsIgnoreCase(funcionario.getCidade())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncUF(String uf) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (uf.equalsIgnoreCase(funcionario.getUf())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncFone(String fone) {
        for (Funcionario funcionario : listaFuncionario) {
            if (fone.equals(funcionario.getFone())) {
                listaFuncionario.clear();
                listaFuncionario.add(funcionario);
                return;
            }
        }
    }

    public void getListaFuncCel(String celular) {
        for (Funcionario funcionario : listaFuncionario) {
            if (celular.equals(funcionario.getCelular1())) {
                listaFuncionario.clear();
                listaFuncionario.add(funcionario);
                return;
            }
        }
    }

    public void getListaFuncEMail(String email) {
        for (Funcionario funcionario : listaFuncionario) {
            if (email.equalsIgnoreCase(funcionario.getEmail())) {
                listaFuncionario.clear();
                listaFuncionario.add(funcionario);
                return;
            }
        }
    }

    public void getListaFuncIda(int idade) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (idade == funcionario.getIdade()) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncDataAdDm(String dataAdDm) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (dataAdDm.equals(formatDate.format(funcionario.getDataContratacaoDemissao()))) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }

    public void getListaFuncAtivo(String ativo) {
        if (ativo.equalsIgnoreCase("T")) {
            ativo = "true";
        } else {
            if (ativo.equalsIgnoreCase("F")) {
                ativo = "false";
            } else {
                return;
            }
        }
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionario) {
            if (ativo.equals(Boolean.toString(funcionario.isAtivo()))) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionario.clear();
            listaFuncionario = listaAux;
        }
    }
}
