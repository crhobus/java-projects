package Controle;

import Arquivos.*;
import Modelo.Funcionario;
import java.io.*;
import java.util.*;

public class FuncionarioControl implements Serializable {

    private ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
    private String nome, funcao, endereco, bairro, cidade, estado, email, dataAdmissao, cpf, rg, cep, fone, celular, dataNascimento, complemento, sexo;
    private int codigo, numero;
    private double salarioIncial, aumento, inss, salarioTotal;

    public void adicionar(String nome, String funcao, String endereco, String bairro, String cidade, String estado, String email, String dataAdmissao,
            String cpf, String rg, String cep, String fone, String celular, String dataNascimento, String complemento, String sexo, int codigo, int numero,
            double salarioIncial, double aumento, double inss, double salarioTotal) {
        Funcionario cadas = new Funcionario();
        cadas.setNome(nome);
        cadas.setFuncao(funcao);
        cadas.setEndereco(endereco);
        cadas.setBairro(bairro);
        cadas.setCidade(cidade);
        cadas.setEstado(estado);
        cadas.setEmail(email);
        cadas.setDataAdmissao(dataAdmissao);
        cadas.setCpf(cpf);
        cadas.setRg(rg);
        cadas.setCep(cep);
        cadas.setFone(fone);
        cadas.setCelular(celular);
        cadas.setDataNascimento(dataNascimento);
        cadas.setComplemento(complemento);
        cadas.setSexo(sexo);
        cadas.setCodigo(codigo);
        cadas.setNumero(numero);
        cadas.setSalarioIncial(salarioIncial);
        cadas.setAumento(aumento);
        cadas.setInss(inss);
        cadas.setSalarioTotal(salarioTotal);
        lista.add(cadas);
    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
        Funcionario cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getDataAdmissao();
            case 3:
                return cadas.getFuncao();
            case 4:
                return cadas.getCpf();
            case 5:
                return cadas.getRg();
            case 6:
                return cadas.getSexo();
            case 7:
                return cadas.getEndereco();
            case 8:
                return cadas.getNumero();
            case 9:
                return cadas.getBairro();
            case 10:
                return cadas.getCep();
            case 11:
                return cadas.getCidade();
            case 12:
                return cadas.getEstado();
            case 13:
                return cadas.getComplemento();
            case 14:
                return cadas.getFone();
            case 15:
                return cadas.getCelular();
            case 16:
                return cadas.getDataNascimento();
            case 17:
                return cadas.getSalarioTotal();
            default:
                return cadas.getEmail();
        }
    }

    public int ultimoCadasCod() {
        int maior = -1;
        if (vazio() == true) {
            maior = 0;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Funcionario aux = lista.get(i);
                if (aux.getCodigo() > maior) {
                    maior = aux.getCodigo();
                }
            }
        }
        return maior;
    }

    public boolean vazio() {
        if (lista.isEmpty() == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean funcionarioCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Funcionario aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean funcionarioCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Funcionario aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                codigo = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Funcionario aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Funcionario aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nome = aux.getNome();
                funcao = aux.getFuncao();
                endereco = aux.getEndereco();
                bairro = aux.getBairro();
                cidade = aux.getCidade();
                estado = aux.getEstado();
                email = aux.getEmail();
                dataAdmissao = aux.getDataAdmissao();
                cpf = aux.getCpf();
                rg = aux.getRg();
                cep = aux.getCep();
                fone = aux.getFone();
                celular = aux.getCelular();
                dataNascimento = aux.getDataNascimento();
                complemento = aux.getComplemento();
                sexo = aux.getSexo();
                this.codigo = aux.getCodigo();
                numero = aux.getNumero();
                salarioIncial = aux.getSalarioIncial();
                aumento = aux.getAumento();
                inss = aux.getInss();
                salarioTotal = aux.getSalarioTotal();
                return true;
            }
        }
        return false;
    }

    public double getAumento() {
        return aumento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCelular() {
        return celular;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEstado() {
        return estado;
    }

    public String getFone() {
        return fone;
    }

    public String getFuncao() {
        return funcao;
    }

    public double getInss() {
        return inss;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getRg() {
        return rg;
    }

    public double getSalarioIncial() {
        return salarioIncial;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public String getSexo() {
        return sexo;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Funcionario");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Funcionario");
        } catch (Exception ex) {
        }
    }
}
