package Funcionarios;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Modelo.Funcionario;

public class FuncionarioControl {

    private List<Funcionario> listaFuncionarios;
    private FuncionarioDAO funcionarioDAO;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

    public FuncionarioControl(Connection con) {
        funcionarioDAO = new FuncionarioDAO(con);
    }

    public boolean insertFuncionario(Funcionario funcionario) throws Exception {
        return funcionarioDAO.insertFuncionario(funcionario);
    }

    public boolean updateFuncionario(Funcionario funcionario) throws Exception {
        return funcionarioDAO.updateFuncionario(funcionario);
    }

    public Funcionario selectFuncionario(String cpf) throws Exception {
        return funcionarioDAO.selectFuncionario(cpf);
    }

    public boolean deleteFuncionario(String cpf) throws Exception {
        return funcionarioDAO.deleteFuncionario(cpf);
    }

    public boolean isFuncVazio() throws Exception {
        return funcionarioDAO.isFuncVazio();
    }

    public int getProxCodFunc() throws Exception {
        return funcionarioDAO.getProxCodFunc();
    }

    public boolean isFuncCadas(String nome) throws Exception {
        return funcionarioDAO.isFuncCadas(nome);
    }

    public int getQtdadeFuncCadas() {
        return listaFuncionarios.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Funcionario funcionario = listaFuncionarios.get(linha);
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
                if (funcionario.isAtivo()) {
                    return " -";
                }
                return "";
        }
    }

    public void listarFuncionarios() throws Exception {
        listaFuncionarios = funcionarioDAO.listFuncionarios();
    }

    public void limparLista() {
        listaFuncionarios.clear();
    }

    public Funcionario getListaPosicao(int posicao) {
        return listaFuncionarios.get(posicao);
    }

    public void getListaCod(int codigo) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (codigo == funcionario.getCodigo()) {
                listaFuncionarios.clear();
                listaFuncionarios.add(funcionario);
                return;
            }
        }
    }

    public void getListaNome(String nome) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (nome.equalsIgnoreCase(funcionario.getNome())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaRG(String rg) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (rg.equals(funcionario.getRg())) {
                listaFuncionarios.clear();
                listaFuncionarios.add(funcionario);
                return;
            }
        }
    }

    public void getListaCPF(String cpf) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (cpf.equals(funcionario.getCpf())) {
                listaFuncionarios.clear();
                listaFuncionarios.add(funcionario);
                return;
            }
        }
    }

    public void getListaCarteTrab(String numero) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (numero.equals(funcionario.getCarteiraTrabalho())) {
                listaFuncionarios.clear();
                listaFuncionarios.add(funcionario);
                return;
            }
        }
    }

    public void getListaCargo(String cargo) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (cargo.equalsIgnoreCase(funcionario.getCargo())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaSalario(double salario) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (salario == funcionario.getSalarioLiquido()) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaSexo(String sexo) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (sexo.equalsIgnoreCase(funcionario.getSexo())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaCEP(String cep) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (cep.equals(funcionario.getCep())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaEnd(String endereco) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (endereco.equalsIgnoreCase(funcionario.getEndereco())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaBairro(String bairro) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (bairro.equalsIgnoreCase(funcionario.getBairro())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaNum(int numero) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (numero == funcionario.getNumero()) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaCom(String complemento) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (complemento.equalsIgnoreCase(funcionario.getComplemento())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaCid(String cidade) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (cidade.equalsIgnoreCase(funcionario.getCidade())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaUF(String uf) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (uf.equalsIgnoreCase(funcionario.getUf())) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaFone(String fone) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (fone.equals(funcionario.getFone())) {
                listaFuncionarios.clear();
                listaFuncionarios.add(funcionario);
                return;
            }
        }
    }

    public void getListaCel(String celular) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (celular.equals(funcionario.getCelular1())) {
                listaFuncionarios.clear();
                listaFuncionarios.add(funcionario);
                return;
            }
        }
    }

    public void getListaEMail(String email) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (email.equalsIgnoreCase(funcionario.getEmail())) {
                listaFuncionarios.clear();
                listaFuncionarios.add(funcionario);
                return;
            }
        }
    }

    public void getListaIda(int idade) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (idade == funcionario.getIdade()) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaDataAdDm(String dataAdDm) {
        List<Funcionario> listaAux = new ArrayList<Funcionario>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (dataAdDm.equals(formatDate.format(funcionario.getDataContratacaoDemissao()))) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }

    public void getListaAtivo(String ativo) {
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
        for (Funcionario funcionario : listaFuncionarios) {
            if (ativo.equals(Boolean.toString(funcionario.isAtivo()))) {
                listaAux.add(funcionario);
            }
        }
        if (!listaAux.isEmpty()) {
            listaFuncionarios.clear();
            listaFuncionarios = listaAux;
        }
    }
}
