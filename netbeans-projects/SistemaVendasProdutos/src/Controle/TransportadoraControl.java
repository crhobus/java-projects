package Controle;

import Arquivos.*;
import Modelo.Transportadora;
import java.io.*;
import java.util.*;

public class TransportadoraControl implements Serializable {

    private ArrayList<Transportadora> lista = new ArrayList<Transportadora>();
    private String nome, endereco, bairro, cidade, estado, email, cep, fone;
    private int codigo, numero;

    public void adicionar(String nome, String endereco, String bairro, String cidade, String estado,
            String email, String cep, String fone, int codigo, int numero) {
        Transportadora cadas = new Transportadora();
        cadas.setNome(nome);
        cadas.setEndereco(endereco);
        cadas.setBairro(bairro);
        cadas.setCidade(cidade);
        cadas.setEstado(estado);
        cadas.setEmail(email);
        cadas.setCep(cep);
        cadas.setFone(fone);
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
                Transportadora aux = lista.get(i);
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

    public boolean transportadoraCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Transportadora aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean transportadoraCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Transportadora aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                codigo = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Transportadora aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public Object conteudoLinha(int linha, int coluna) {
        Transportadora cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getEndereco();
            case 3:
                return cadas.getNumero();
            case 4:
                return cadas.getBairro();
            case 5:
                return cadas.getCep();
            case 6:
                return cadas.getCidade();
            case 7:
                return cadas.getEstado();
            case 8:
                return cadas.getFone();
            default:
                return cadas.getEmail();
        }
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Transportadora aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nome = aux.getNome();
                endereco = aux.getEndereco();
                bairro = aux.getBairro();
                cidade = aux.getCidade();
                estado = aux.getEstado();
                email = aux.getEmail();
                cep = aux.getCep();
                fone = aux.getFone();
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

    public String getFone() {
        return fone;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Transportadora");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Transportadora");
        } catch (Exception ex) {
        }
    }
}
