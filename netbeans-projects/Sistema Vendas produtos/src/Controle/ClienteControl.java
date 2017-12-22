package Controle;

import Arquivos.*;
import Modelo.Cliente;
import java.io.*;
import java.util.*;

public class ClienteControl implements Serializable {

    private ArrayList<Cliente> lista = new ArrayList<Cliente>();
    private String nome, rg, cpf, profissao, empresa, foneEmpresa, sexo, cep, endereco, bairro, complemento, cidade, estado, email, fone, celular, descricao;
    private int codigo, numero;

    public void adicionar(String nome, String rg, String cpf, String profissao, String empresa, String foneEmpresa,
            String sexo, String cep, String endereco, String bairro, String complemento, String cidade, String estado,
            String email, String fone, String celular, String descricao, int codigo, int numero) {
        Cliente cadas = new Cliente();
        cadas.setNome(nome);
        cadas.setRg(rg);
        cadas.setCpf(cpf);
        cadas.setProfissao(profissao);
        cadas.setEmpresa(empresa);
        cadas.setFoneEmpresa(foneEmpresa);
        cadas.setSexo(sexo);
        cadas.setCep(cep);
        cadas.setEndereco(endereco);
        cadas.setBairro(bairro);
        cadas.setComplemento(complemento);
        cadas.setCidade(cidade);
        cadas.setEstado(estado);
        cadas.setEmail(email);
        cadas.setFone(fone);
        cadas.setCelular(celular);
        cadas.setDescricao(descricao);
        cadas.setCodigo(codigo);
        cadas.setNumero(numero);
        lista.add(cadas);
    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
        Cliente cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getRg();
            case 3:
                return cadas.getCpf();
            case 4:
                return cadas.getProfissao();
            case 5:
                return cadas.getEmpresa();
            case 6:
                return cadas.getFoneEmpresa();
            case 7:
                return cadas.getSexo();
            case 8:
                return cadas.getCep();
            case 9:
                return cadas.getEndereco();
            case 10:
                return cadas.getBairro();
            case 11:
                return cadas.getNumero();
            case 12:
                return cadas.getComplemento();
            case 13:
                return cadas.getCidade();
            case 14:
                return cadas.getEstado();
            case 15:
                return cadas.getFone();
            case 16:
                return cadas.getCelular();
            case 17:
                return cadas.getEmail();
            default:
                return cadas.getDescricao();
        }
    }

    public int ultimoCadasCod() {
        int maior = -1;
        if (vazio() == true) {
            maior = 0;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Cliente aux = lista.get(i);
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

    public boolean clienteCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean clienteCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                codigo = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nome = aux.getNome();
                rg = aux.getRg();
                cpf = aux.getCpf();
                profissao = aux.getProfissao();
                empresa = aux.getEmpresa();
                foneEmpresa = aux.getFoneEmpresa();
                sexo = aux.getSexo();
                cep = aux.getCep();
                endereco = aux.getEndereco();
                bairro = aux.getBairro();
                complemento = aux.getComplemento();
                cidade = aux.getCidade();
                estado = aux.getEstado();
                email = aux.getEmail();
                fone = aux.getFone();
                celular = aux.getCelular();
                descricao = aux.getDescricao();
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

    public String getDescricao() {
        return descricao;
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

    public String getFoneEmpresa() {
        return foneEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getProfissao() {
        return profissao;
    }

    public String getRg() {
        return rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Cliente");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Cliente");
        } catch (Exception ex) {
        }
    }
}
