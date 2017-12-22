package Controle;

import Arquivos.*;
import Modelo.Empresa;
import java.io.*;
import java.util.*;

public class EmpresaControl implements Serializable {

    private ArrayList<Empresa> lista = new ArrayList<Empresa>();
    private String nome, cnpj, ie, endereco, bairro, cep, cidade, estado, fone, fax, email, observacoes;
    private int codigo, numero;

    public void adicionar(String nome, String cnpj, String ie, String endereco, String bairro, String cep, String cidade,
            String estado, String fone, String fax, String email, String observacoes, int codigo, int numero) {
        Empresa cadas = new Empresa();
        cadas.setNome(nome);
        cadas.setCnpj(cnpj);
        cadas.setIe(ie);
        cadas.setEndereco(endereco);
        cadas.setBairro(bairro);
        cadas.setCep(cep);
        cadas.setCidade(cidade);
        cadas.setEstado(estado);
        cadas.setFone(fone);
        cadas.setFax(fax);
        cadas.setEmail(email);
        cadas.setObservacoes(observacoes);
        cadas.setCodigo(codigo);
        cadas.setNumero(numero);
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
                Empresa aux = lista.get(i);
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

    public boolean empresaCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Empresa aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean empresaCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Empresa aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                codigo = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Empresa aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public Object conteudoLinha(int linha, int coluna) {
        Empresa cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getCnpj();
            case 3:
                return cadas.getIe();
            case 4:
                return cadas.getEndereco();
            case 5:
                return cadas.getNumero();
            case 6:
                return cadas.getBairro();
            case 7:
                return cadas.getCep();
            case 8:
                return cadas.getCidade();
            case 9:
                return cadas.getEstado();
            case 10:
                return cadas.getFone();
            case 11:
                return cadas.getFax();
            case 12:
                return cadas.getEmail();
            default:
                return cadas.getObservacoes();
        }
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Empresa aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nome = aux.getNome();
                cnpj = aux.getCnpj();
                ie = aux.getIe();
                endereco = aux.getEndereco();
                bairro = aux.getBairro();
                cep = aux.getCep();
                cidade = aux.getCidade();
                estado = aux.getEstado();
                fone = aux.getFone();
                fax = aux.getFax();
                email = aux.getEmail();
                observacoes = aux.getObservacoes();
                this.codigo = aux.getCodigo();
                numero = aux.getNumero();
                return true;
            }
        }
        return false;
    }

    public String getBairro() {
        return bairro;
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

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEstado() {
        return estado;
    }

    public String getFax() {
        return fax;
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

    public String getObservacoes() {
        return observacoes;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Empresa");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Empresa");
        } catch (Exception ex) {
        }
    }
}
