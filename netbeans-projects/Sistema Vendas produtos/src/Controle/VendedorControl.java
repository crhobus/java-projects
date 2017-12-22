package Controle;

import Arquivos.*;
import Modelo.Vendedor;
import java.io.*;
import java.util.*;

public class VendedorControl implements Serializable {

    private ArrayList<Vendedor> lista = new ArrayList<Vendedor>();
    private String nome, empresa, endereco, bairro, email, cep, fone, fax, ie, cnpj, setor, cidade, estado;
    private int codigo, numero;
    private double comissao;

    public void adicionar(String nome, double comissao, String empresa, String endereco, String bairro, String email, String cep,
            String fone, String fax, String ie, String cnpj, String setor, String cidade, String estado, int codigo, int numero) {
        Vendedor cadas = new Vendedor();
        cadas.setNome(nome);
        cadas.setComissao(comissao);
        cadas.setEmpresa(empresa);
        cadas.setEndereco(endereco);
        cadas.setBairro(bairro);
        cadas.setEmail(email);
        cadas.setCep(cep);
        cadas.setFone(fone);
        cadas.setFax(fax);
        cadas.setIe(ie);
        cadas.setCnpj(cnpj);
        cadas.setSetor(setor);
        cadas.setCidade(cidade);
        cadas.setEstado(estado);
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
                Vendedor aux = lista.get(i);
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

    public boolean vendedorCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Vendedor aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean vendedorCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Vendedor aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                codigo = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Vendedor aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public Object conteudoLinha(int linha, int coluna) {
        Vendedor cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getSetor();
            case 3:
                return cadas.getComissao();
            case 4:
                return cadas.getEmpresa();
            case 5:
                return cadas.getIe();
            case 6:
                return cadas.getCnpj();
            case 7:
                return cadas.getEndereco();
            case 8:
                return cadas.getBairro();
            case 9:
                return cadas.getNumero();
            case 10:
                return cadas.getCep();
            case 11:
                return cadas.getCidade();
            case 12:
                return cadas.getEstado();
            case 13:
                return cadas.getFone();
            case 14:
                return cadas.getFax();
            default:
                return cadas.getEmail();
        }
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Vendedor aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nome = aux.getNome();
                empresa = aux.getEmpresa();
                endereco = aux.getEndereco();
                bairro = aux.getBairro();
                email = aux.getEmail();
                cep = aux.getCep();
                fone = aux.getFone();
                fax = aux.getFax();
                ie = aux.getIe();
                cnpj = aux.getCnpj();
                setor = aux.getSetor();
                cidade = aux.getCidade();
                estado = aux.getEstado();
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

    public String getSetor() {
        return setor;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Vendedor");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Vendedor");
        } catch (Exception ex) {
        }
    }
}
