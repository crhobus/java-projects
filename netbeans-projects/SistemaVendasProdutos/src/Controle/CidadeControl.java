package Controle;

import Arquivos.*;
import Modelo.Cidade;
import java.io.*;
import java.util.*;

public class CidadeControl implements Serializable {

    private ArrayList<Cidade> lista = new ArrayList<Cidade>();
    private int cod;
    private String cidade, estado;

    public void adicionar(int codigo, String cidade, String estado) {
        Cidade cadas = new Cidade();
        cadas.setCidade(cidade);
        cadas.setEstado(estado);
        cadas.setCodigo(codigo);
        lista.add(cadas);
    }

    public int tamanho() {
        return lista.size();
    }

    public int ultimoCadasCod(){
        int maior = -1;
        if (vazio() == true) {
            maior = 0;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Cidade aux = lista.get(i);
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

    public boolean cidadeCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cidade aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cidade aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cidade aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                cod = aux.getCodigo();
                cidade = aux.getCidade();
                estado = aux.getEstado();
                return true;
            }
        }
        return false;
    }

    public boolean cidadeCadasNome(String cidade) {
        for (int i = 0; i < lista.size(); i++) {
            Cidade aux = lista.get(i);
            if (cidade.equalsIgnoreCase(aux.getCidade())) {
                cod = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public int codCidadeCads() {
        return cod;
    }

    public String cidCidadeCads() {
        return cidade;
    }

    public String estCidadeCads() {
        return estado;
    }

    public Object conteudoLinha(int linha, int coluna) {
        Cidade cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getCidade();
            default:
                return cadas.getEstado();
        }
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Cidade");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo){
        try {
            lista = lerArquivo.lerArquivo("Cidade");
        } catch (Exception ex) {
        }
    }

    public ArrayList cidade() {
        ArrayList lista2 = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            Cidade aux = lista.get(i);
            if (lista.size() != 0) {
                lista2.add(aux.getCidade());
            }
        }
        return lista2;
    }

    public ArrayList estado() {
        ArrayList lista2 = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            Cidade aux = lista.get(i);
            if (lista.size() != 0) {
                lista2.add(aux.getEstado());
            }
        }
        return lista2;
    }
}
