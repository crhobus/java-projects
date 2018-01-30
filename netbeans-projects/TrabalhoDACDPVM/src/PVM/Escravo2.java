package PVM;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import jpvm.jpvmBuffer;
import jpvm.jpvmEnvironment;
import jpvm.jpvmException;
import jpvm.jpvmMessage;
import jpvm.jpvmTaskId;

public class Escravo2 {

    public static void main(String args[]) {
        try {
            // inicia o jpvm...
            jpvmEnvironment jpvm = new jpvmEnvironment();
            jpvmTaskId parent = jpvm.pvm_parent();
            //inicializado como escravo
            jpvmMessage message = jpvm.pvm_recv();
            String recebeuMestre = message.buffer.upkstr();
            //recebeu do mestre
            jpvmBuffer buf;
            switch (message.messageTag) {
                case 1:
                    //preparando resposta
                    buf = new jpvmBuffer();
                    buf.pack(getMedia(recebeuMestre));
                    jpvm.pvm_send(buf, parent, message.messageTag);
                    //mandou resposta
                    break;
                case 2:
                    //preparando resposta
                    buf = new jpvmBuffer();
                    buf.pack(getMediana(recebeuMestre));
                    jpvm.pvm_send(buf, parent, message.messageTag);
                    //mandou resposta
                    break;
                case 3:
                    //preparando resposta
                    buf = new jpvmBuffer();
                    buf.pack(getModa(recebeuMestre));
                    jpvm.pvm_send(buf, parent, message.messageTag);
                    //mandou resposta
                    break;
                default:
                    //preparando resposta
                    buf = new jpvmBuffer();
                    buf.pack("Tag inválida");
                    jpvm.pvm_send(buf, parent, message.messageTag);
                    //mandou resposta
                    break;
            }
            // sai do jpvm
            jpvm.pvm_exit();
        } catch (jpvmException ex) {
            JOptionPane.showMessageDialog(null, "Error - jpvm exception", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String getMedia(String linhasArq) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt_BR"));
        numberFormat.setMaximumFractionDigits(2);
        String[] linhas = linhasArq.split("\n");
        double result = 0;
        for (String linha : linhas) {
            result += Double.parseDouble(linha);
        }
        result = result / linhas.length;
        return "Media: " + numberFormat.format(result);
    }

    private static String getMediana(String linhasArq) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt_BR"));
        numberFormat.setMaximumFractionDigits(2);
        String[] linhas = linhasArq.split("\n");
        List<Double> lista = new ArrayList<Double>();
        for (String linha : linhas) {
            lista.add(Double.parseDouble(linha));
        }
        lista = new ArrayList<Double>(new HashSet<Double>(lista));
        Collections.sort(lista);
        String saida;
        double mediana = 0;
        if (lista.size() % 2 == 0) {
            int posicao = lista.size() / 2;
            mediana = (lista.get(posicao - 1) + lista.get(posicao)) / 2;
            saida = "Tamanho vetor: " + lista.size();
            saida += "\nPosicao 1 mediana: " + posicao + ", valor: " + lista.get(posicao - 1);
            saida += "\nPosicao 2 mediana: " + (posicao + 1) + ", valor: " + lista.get(posicao);
            saida += "\nMediana: " + numberFormat.format(mediana);
        } else {
            int posicao = lista.size() / 2;
            mediana = lista.get(posicao);
            saida = "Tamanho vetor: " + lista.size();
            saida += "\nPosicao mediana: " + (posicao + 1);
            saida += "\nMediana: " + numberFormat.format(mediana);
        }
        return saida;
    }

    private static String getModa(String linhasArq) {
        String[] linhas = linhasArq.split("\n");
        List<String> lista = new ArrayList<String>();
        for (String linha : linhas) {
            lista.add(linha);
        }
        Collections.sort(lista);
        int cont = 1;
        String str = "";
        Object val;
        int i = lista.size() - 1;
        while (i != -1) {
            val = lista.get(i);
            while (i != 0 && Double.parseDouble(lista.get(i)) == Double.parseDouble(lista.get(i - 1))) {
                val = lista.get(i);
                cont++;
                i--;
            }
            str += "Valor: " + val + ", quantidade de reperticoes " + cont + "\n";
            cont = 1;
            i--;
        }
        String[] vetor = str.split("\n");
        for (int y = vetor.length - 1; y > 1; y--) {
            for (int j = 0; j < y; j++) {
                int atual = Integer.parseInt(vetor[j].substring(vetor[j].indexOf("s ") + 2));
                int proximo = Integer.parseInt(vetor[j + 1].substring(vetor[j + 1].indexOf("s ") + 2));
                if (atual < proximo) {
                    String temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
        String saida = "Moda\n";
        for (int y = 0; y < vetor.length; y++) {
            saida += vetor[y] + "\n";
        }
        return saida;
    }
}
