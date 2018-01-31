package Controle;

import Arquivos.*;
import Modelo.Setor;
import java.io.*;
import java.util.*;

public class SetorControl implements Serializable {

    private ArrayList<Setor> lista = new ArrayList<Setor>();
    private int cod;
    private String nome;

    public void adicionar(String nome, int codigo) {
        Setor cadas = new Setor();
        cadas.setNome(nome);
        cadas.setCodigo(codigo);
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
                Setor aux = lista.get(i);
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

    public void removerNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Setor aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                lista.remove(aux);
            }
        }
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Setor aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public boolean setorCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Setor aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                cod = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public boolean setorCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Setor aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean recuperar(String texto) {
        for (int i = 0; i < lista.size(); i++) {
            Setor aux = lista.get(i);
            if (texto.equalsIgnoreCase(aux.getNome())) {
                cod = aux.getCodigo();
                nome = aux.getNome();
                return true;
            }
        }
        return false;
    }

    public int codSetorCads() {
        return cod;
    }

    public String nomSetorCads() {
        return nome;
    }

    public Object conteudoLinha(int linha, int coluna) {
        Setor cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            default:
                return cadas.getNome();
        }
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Setor");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Setor");
        } catch (Exception ex) {
        }
    }

    public ArrayList setor() {
        ArrayList lista2 = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            Setor aux = lista.get(i);
            if (lista.size() != 0) {
                lista2.add(aux.getNome());
            }
        }
        return lista2;
    }
}
