package OMP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import jomp.runtime.OMP;

public class LeituraArq {

    private List lista;
    private double soma = 0;
    private String ignorado = "";
    private int numLinhas;
    private File arquivo;

    public LeituraArq(File arq) throws Exception {
        arquivo = arq;
        try {
            lista = new ArrayList();
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(new DataInputStream(new FileInputStream(arquivo))));
            reader.skip(arquivo.length());
            numLinhas = reader.getLineNumber() + 1;
            numLinhas--;
            carregaArq();
        } catch (IOException ex) {
            throw new IOException("Erro na leitura do arquivo " + arquivo.getName());
        }
    }

    private void carregaArq() throws Exception {
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            OMP.setNumThreads(numLinhas);
            String linha, resultado;
            //omp parallel
            {
                //omp for schedule(dynamic) private(linha) reduction(+:soma)
                for (int i = 0; i < numLinhas; i++) {
                    //omp critical
                    {
                        linha = in.readLine();
                    }
                    resultado = linha.substring(linha.lastIndexOf("= ") + 2);
                    if (resultado.contains("E")) {
                        ignorado += linha + "\n";
                        continue;
                    }
                    //omp critical
                    {
                        lista.add(resultado);
                    }
                    soma += Double.parseDouble(resultado);
                }
            }
            in.close();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do arquivo " + arquivo.getName());
        }
    }

    public String getCalcula() {
        String strSoma, strMediana, strModa;
        //omp parallel sections
        {
            //omp section
            {
                strSoma = "Soma: " + soma + "\nN�mero de linhas somadas: "
                        + numLinhas + "\nM�dia: " + (soma / numLinhas);
                if (!"".equals(ignorado)) {
                    strSoma += "\nExpress�es ignoradas, pois s�o valores muito altos:\n" + ignorado;
                }
            }
            //omp section
            {
                strMediana = mediana();
            }
            //omp section
            {
                strModa = moda();
            }
        }
        //omp barrier
        return strSoma + "\n-------------------------------------------------------\n\n"
                + strMediana + "\n\n-------------------------------------------------------\n\nModa\n"
                + strModa;
    }

    private String mediana() {
        List listaAux;
        //omp critical
        {
            listaAux = new ArrayList(new HashSet(lista));
        }
        Collections.sort(listaAux);
        String saida;
        double mediana = 0;
        if (listaAux.size() % 2 == 0) {
            int posicao = listaAux.size() / 2;
            mediana = (Double.parseDouble((String) listaAux.get(posicao - 1)) + Double.parseDouble((String) listaAux.get(posicao))) / 2;
            saida = "Tamanho vetor: " + listaAux.size();
            saida += "\nPosicao 1 mediana: " + posicao + ", valor: " + listaAux.get(posicao - 1);
            saida += "\nPosicao 2 mediana: " + (posicao + 1) + ", valor: " + listaAux.get(posicao);
            saida += "\nMediana: " + mediana;
        } else {
            int posicao = listaAux.size() / 2;
            mediana = Double.parseDouble((String) listaAux.get(posicao));
            saida = "Tamanho vetor: " + listaAux.size();
            saida += "\nPosicao mediana: " + (posicao + 1);
            saida += "\nMediana: " + mediana;
        }
        return saida;
    }

    private String moda() {
        List listaAux;
        //omp critical
        {
            listaAux = lista;
        }
        Collections.sort(listaAux);
        int cont = 1;
        String str = "";
        Object val;
        int i = listaAux.size() - 1;
        while (i != -1) {
            val = listaAux.get(i);
            while (i != 0 && Double.parseDouble((String) listaAux.get(i)) == Double.parseDouble((String) listaAux.get(i - 1))) {
                val = listaAux.get(i);
                cont++;
                i--;
            }
            str += "Valor: " + val + ", quantidade de reperti��es " + cont + "\n";
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
        String saida = "";
        for (int y = 0; y < vetor.length; y++) {
            saida += vetor[y] + "\n";
        }
        return saida;
    }
}
