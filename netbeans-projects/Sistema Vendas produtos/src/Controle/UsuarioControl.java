package Controle;

import Arquivos.*;
import Modelo.Usuario;
import java.io.*;
import java.util.*;

public class UsuarioControl implements Serializable {

    private ArrayList<Usuario> lista = new ArrayList<Usuario>();
    private int cod, senha, permissao;
    private String nome;

    public void adicionar(String nome, int senha, int codigo, int permissao, Date data) {
        Usuario cadas = new Usuario();
        cadas.setNome(nome);
        cadas.setSenha(senha);
        cadas.setCodigo(codigo);
        cadas.setPermissao(permissao);
        cadas.setData(data);
        lista.add(cadas);
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Usuario aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public void removerNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Usuario aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                lista.remove(aux);
            }
        }
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
                Usuario aux = lista.get(i);
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

    public boolean usuarioCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Usuario aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean usarioCadasNome(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Usuario aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                cod = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public boolean recuperar(String texto) {
        for (int i = 0; i < lista.size(); i++) {
            Usuario aux = lista.get(i);
            if (texto.equalsIgnoreCase(aux.getNome())) {
                cod = aux.getCodigo();
                nome = aux.getNome();
                senha = aux.getSenha();
                permissao = aux.getPermissao();
                return true;
            }
        }
        return false;
    }

    public int codUsuarioCads() {
        return cod;
    }

    public String nomUsuarioCads() {
        return nome;
    }

    public int senUsuarioCads() {
        return senha;
    }

    public int permUsuarioCadas() {
        return permissao;
    }

    public Object conteudoLinha(int linha, int coluna) {
        Usuario cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getPermissao();
            case 3:
                return cadas.getData();
            default:
                return cadas.getData();
        }
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Usuario");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo){
        try {
            lista = lerArquivo.lerArquivo("Usuario");
        } catch (Exception ex) {
        }
    }
}
