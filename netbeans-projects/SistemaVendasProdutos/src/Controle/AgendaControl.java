package Controle;

import Arquivos.*;
import Modelo.Agenda;
import java.util.*;

public class AgendaControl {

    private ArrayList<Agenda> lista = new ArrayList<Agenda>();
    private String data, nome, descricao, hora;
    private int codigo;

    public void adicionar(String data, String nome, String descricao, String hora, int codigo) {
        Agenda cadas = new Agenda();
        cadas.setData(data);
        cadas.setNome(nome);
        cadas.setDescricao(descricao);
        cadas.setHora(hora);
        cadas.setCodigo(codigo);
        lista.add(cadas);
    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
        Agenda cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getData();
            case 3:
                return cadas.getHora();
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
                Agenda aux = lista.get(i);
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

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Agenda aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
            }
        }
    }

    public boolean agendaCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Agenda aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Agenda aux = lista.get(i);
            if (codigo == aux.getCodigo()) {
                nome = aux.getNome();
                data = aux.getData();
                descricao = aux.getDescricao();
                hora = aux.getHora();
                this.codigo = aux.getCodigo();
                return true;
            }
        }
        return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHora() {
        return hora;
    }

    public String getNome() {
        return nome;
    }

    public void arquivo(Arquivo arquivo) {
        try {
            arquivo.arquivo(lista, "Agenda");
        } catch (Exception ex) {
        }
    }

    public void lerArquivo(LerArquivo lerArquivo) {
        try {
            lista = lerArquivo.lerArquivo("Agenda");
        } catch (Exception ex) {
        }
    }
}
