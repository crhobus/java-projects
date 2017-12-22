package Controle;

import Arquivos.*;
import Modelo.Fornecedor;
import java.io.*;
import java.util.*;

public class FornecedorControl implements Serializable {

    private ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>();
    private String nome, sigla, endereco, bairro, cidade, estado, email, empresa, cnpj, ie, cep, fone, celular;
    private int codigo, numero;
    private double comissao;

    public void adicionar(String nome, String sigla, String endereco, String bairro, String cidade, String estado, String email,
            String empresa, String cnpj, String ie, String cep, String fone, String celular, int codigo, int numero, double comissao) {
        Fornecedor cadas = new Fornecedor();
        cadas.setNome(nome);
        cadas.setSigla(sigla);
        cadas.setEndereco(endereco);
        cadas.setBairro(bairro);
        cadas.setCidade(cidade);
        cadas.setEstado(estado);
        cadas.setEmail(email);
        cadas.setEmpresa(empresa);
        cadas.setCnpj(cnpj);
        cadas.setIe(ie);
        cadas.setCep(cep);
        cadas.setFone(fone);
        cadas.setCelular(celular);
        cadas.setCodigo(codigo);
        cadas.setNumero(numero);
        cadas.setComissao(comissao);
        lista.add(cadas);
    }

    public int tamanho() {
        return lista.size();
    }

    public int ultimoCadasCod() {
        int maior = -1;
        if (vazio() == true) {
            maior = 0;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Fornecedor aux = lista.get(i);
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

    public boolean fornecedorCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Fornecedor aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean fornecedorCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Fornecedor aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                codigo = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Fornecedor aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public Object conteudoLinha(int linha, int coluna) {
        Fornecedor cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getSigla();
            case 3:
                return cadas.getComissao();
            case 4:
                return cadas.getCnpj();
            case 5:
                return cadas.getIe();
            case 6:
                return cadas.getEndereco();
            case 7:
                return cadas.getNumero();
            case 8:
                return cadas.getBairro();
            case 9:
                return cadas.getCep();
            case 10:
                return cadas.getCidade();
            case 11:
                return cadas.getEstado();
            case 12:
                return cadas.getEmpresa();
            case 13:
                return cadas.getFone();
            case 14:
                return cadas.getCelular();
            default:
                return cadas.getEmail();
        }
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Fornecedor aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nome = aux.getNome();
                sigla = aux.getSigla();
                endereco = aux.getEndereco();
                bairro = aux.getBairro();
                cidade = aux.getCidade();
                estado = aux.getEstado();
                email = aux.getEmail();
                empresa = aux.getEmpresa();
                cnpj = aux.getCnpj();
                ie = aux.getIe();
                cep = aux.getCep();
                fone = aux.getFone();
                celular = aux.getCelular();
                this.codigo = aux.getCodigo();
                numero = aux.getNumero();
                comissao = aux.getComissao();
                return true;
            }
        }
        return false;
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

    public String getCnpj() {
        return cnpj;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getComissao() {
        return comissao;
    }

    public String getEmail() {
        return email;
    }

    public String getEmpresa() {
        return empresa;
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

    public String getIe() {
        return ie;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getSigla() {
        return sigla;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Fornecedor");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Fornecedor");
        } catch (Exception ex) {
        }
    }
}
