package Controle;

import Arquivos.*;
import Modelo.Contatos;
import java.util.*;

public class ContatosControl {

    private ArrayList<Contatos> lista = new ArrayList<Contatos>();
    private Arquivo arquivo;

    public ContatosControl(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public void adicionar(String nome, String fone1, String fone2, String fone3, String email, Boolean vip[]) {
        Contatos cadas = new Contatos();
        cadas.setNome(nome);
        cadas.setFone1(fone1);
        cadas.setFone2(fone2);
        cadas.setFone3(fone3);
        cadas.setEmail(email);
        cadas.setVip(vip);
        cadas.setCodigo(insere());
        cadas.setSeleciona(false);
        lista.add(cadas);
        arquivo(arquivo);
    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
        Contatos cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getSeleciona();
            case 1:
                return cadas.getCodigo();
            case 2:
                return cadas.getNome();
            case 3:
                return cadas.getVip();
            case 4:
                return cadas.getFone1();
            case 5:
                return cadas.getFone2();
            case 6:
                return cadas.getFone3();
            default:
                return cadas.getEmail();
        }
    }

    public void alteraLinha(Object valor, int linha, int coluna) {
        if (linha < lista.size()) {
            Contatos cadas = lista.get(linha);
            switch (coluna) {
                case 0:
                    cadas.setSeleciona((Boolean) valor);
                    break;
                case 2:
                    cadas.setNome((String) valor);
                    break;
                case 3:
                    cadas.setVip((Boolean[]) valor);
                    break;
                case 4:
                    cadas.setFone1((String) valor);
                    break;
                case 5:
                    cadas.setFone2((String) valor);
                    break;
                case 6:
                    cadas.setFone3((String) valor);
                    break;
                case 7:
                    cadas.setEmail((String) valor);
                    break;
            }
        }
        arquivo(arquivo);
    }

    public void remover(int linha) {
        lista.remove(linha);
        arquivo(arquivo);
    }

    private boolean existe(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Contatos aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    private int insere() {
        int cod = 1;
        while (existe(cod) == true) {
            cod++;
        }
        return cod;
    }

    public boolean verificaSelecionado() {
        int i = 0;
        while (i < lista.size()) {
            Contatos aux = lista.get(i);
            aux.setSeleciona(false);
            i++;
        }
        return true;
    }

    private void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Contatos");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Contatos");
        } catch (Exception ex) {
        }
    }
}
