package exercicio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TableSpace {

    private String[] rotulos;
    private String[] registro;
    private int cont;
    private PrintWriter writer;

    public TableSpace(int qtdadeCampos, String nome) throws Exception {
        try {
            this.writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(nome + ".csv"))));
        } catch (Exception ex) {
            throw new Exception("Erro ao criar o arquivo para gravação");
        }
        this.rotulos = new String[qtdadeCampos];
        this.registro = new String[qtdadeCampos];
        this.cont = 0;
    }

    public void addRotulo(String rotulo) {
        rotulos[cont] = rotulo;
        cont++;
    }

    public String getNomeRotulo(int posicao) {
        return rotulos[posicao];
    }

    public void insereRegistro(String campo) {
        registro[cont] = campo;
        cont++;
    }

    public void gravarRotulo() {
        gravarNoArquivo(rotulos);
    }

    public void gravarRegistro() {
        gravarNoArquivo(registro);
    }

    private void gravarNoArquivo(String[] linha) {
        String str = "";
        for (int i = 0; i < linha.length; i++) {
            if (i == linha.length - 1) {
                str += "\"" + linha[i] + "\"";
            } else {
                str += "\"" + linha[i] + "\",";
            }
        }
        writer.println(str);
        this.cont = 0;
    }

    public void fecharArquivo() {
        writer.close();
    }
}
