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

public class LeituraArq_jomp {

    private List lista;
    private double soma = 0;
    private String ignorado = "";
    private int numLinhas;
    private File arquivo;

    public LeituraArq_jomp(File arq) throws Exception {
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

// OMP PARALLEL BLOCK BEGINS
            {
                __omp_Class0 __omp_Object0 = new __omp_Class0();
                // shared variables
                __omp_Object0.in = in;
                IOException ex = null;
                __omp_Object0.ex = ex;
                File arq = null;
                __omp_Object0.arq = arq;
                // firstprivate variables
                try {
                    jomp.runtime.OMP.doParallel(__omp_Object0);
                } catch (Throwable __omp_exception) {
                    System.err.println("OMP Warning: Illegal thread exception ignored!");
                    System.err.println(__omp_exception);
                }
                // reduction variables
                // shared variables
                resultado = __omp_Object0.resultado;
                linha = __omp_Object0.linha;
                in = __omp_Object0.in;
                ex = __omp_Object0.ex;
                arq = __omp_Object0.arq;
            }
// OMP PARALLEL BLOCK ENDS

            in.close();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do arquivo " + arquivo.getName());
        }
    }

    public String getCalcula() {
        String strSoma, strMediana, strModa;

// OMP PARALLEL BLOCK BEGINS
        {
            __omp_Class4 __omp_Object4 = new __omp_Class4();
            IOException ex = null;
// shared variables
            __omp_Object4.ex = ex;
            File arq = null;
            __omp_Object4.arq = arq;
            // firstprivate variables
            try {
                jomp.runtime.OMP.doParallel(__omp_Object4);
            } catch (Throwable __omp_exception) {
                System.err.println("OMP Warning: Illegal thread exception ignored!");
                System.err.println(__omp_exception);
            }
            // reduction variables
            // shared variables
            strModa = __omp_Object4.strModa;
            strMediana = __omp_Object4.strMediana;
            strSoma = __omp_Object4.strSoma;
            ex = __omp_Object4.ex;
            arq = __omp_Object4.arq;
        }
// OMP PARALLEL BLOCK ENDS

        // OMP BARRIER BLOCK BEGINS
        jomp.runtime.OMP.doBarrier();
        // OMP BARRIER BLOCK ENDS

        return strSoma + "\n-------------------------------------------------------\n\n"
                + strMediana + "\n\n-------------------------------------------------------\n\nModa\n"
                + strModa;
    }

    private String mediana() {
        List listaAux;
        // OMP CRITICAL BLOCK BEGINS
        synchronized (jomp.runtime.OMP.getLockByName("")) {
            // OMP USER CODE BEGINS

            {
                listaAux = new ArrayList(new HashSet(lista));
            }
            // OMP USER CODE ENDS
        }
        // OMP CRITICAL BLOCK ENDS

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
        // OMP CRITICAL BLOCK BEGINS
        synchronized (jomp.runtime.OMP.getLockByName("")) {
            // OMP USER CODE BEGINS

            {
                listaAux = lista;
            }
            // OMP USER CODE ENDS
        }
        // OMP CRITICAL BLOCK ENDS

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
            str += "Valor: " + val + ", quantidade de reperti\u00e7\u00f5es " + cont + "\n";
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

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
    private class __omp_Class4 extends jomp.runtime.BusyTask {
        // shared variables

        String strModa;
        String strMediana;
        String strSoma;
        IOException ex;
        File arq;
        // firstprivate variables
        // variables to hold results of reduction

        public void go(int __omp_me) throws Throwable {
            // firstprivate variables + init
            // private variables
            // reduction variables, init to default
            // OMP USER CODE BEGINS

            { // OMP SECTIONS BLOCK BEGINS
                // copy of firstprivate variables, initialized
                // copy of lastprivate variables
                // variables to hold result of reduction
                boolean amLast = false;
                {
                    // firstprivate variables + init
                    // [last]private variables
                    // reduction variables + init to default
                    // -------------------------------------
                    __ompName_5:
                    while (true) {
                        switch ((int) jomp.runtime.OMP.getTicket(__omp_me)) {
                            // OMP SECTION BLOCK 0 BEGINS
                            case 0: {
                                // OMP USER CODE BEGINS

                                {
                                    strSoma = "Soma: " + soma + "\nN\u00famero de linhas somadas: "
                                            + numLinhas + "\nM\u00e9dia: " + (soma / numLinhas);
                                    if (!"".equals(ignorado)) {
                                        strSoma += "\nExpress\u00f5es ignoradas, pois s\u00e3o valores muito altos:\n" + ignorado;
                                    }
                                }
                                // OMP USER CODE ENDS
                            }
                            ;
                            break;
                            // OMP SECTION BLOCK 0 ENDS
                            // OMP SECTION BLOCK 1 BEGINS
                            case 1: {
                                // OMP USER CODE BEGINS

                                {
                                    strMediana = mediana();
                                }
                                // OMP USER CODE ENDS
                            }
                            ;
                            break;
                            // OMP SECTION BLOCK 1 ENDS
                            // OMP SECTION BLOCK 2 BEGINS
                            case 2: {
                                // OMP USER CODE BEGINS

                                {
                                    strModa = moda();
                                }
                                // OMP USER CODE ENDS
                                amLast = true;
                            }
                            ;
                            break;
                            // OMP SECTION BLOCK 2 ENDS

                            default:
                                break __ompName_5;
                        } // of switch
                    } // of while
                    // call reducer
                    jomp.runtime.OMP.resetTicket(__omp_me);
                    jomp.runtime.OMP.doBarrier(__omp_me);
                    // copy lastprivate variables out
                    if (amLast) {
                    }
                }
                // update lastprivate variables
                if (amLast) {
                }
                // update reduction variables
                if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                }
            } // OMP SECTIONS BLOCK ENDS

            // OMP USER CODE ENDS
            // call reducer
            // output to _rd_ copy
            if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
            }
        }
    }
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
    private class __omp_Class0 extends jomp.runtime.BusyTask {
        // shared variables

        String resultado;
        String linha;
        BufferedReader in;
        IOException ex;
        File arq;
        // firstprivate variables
        // variables to hold results of reduction

        public void go(int __omp_me) throws Throwable {
            // firstprivate variables + init
            // private variables
            // reduction variables, init to default
            // OMP USER CODE BEGINS

            {
                { // OMP FOR BLOCK BEGINS
                    // copy of firstprivate variables, initialized
                    // copy of lastprivate variables
                    // variables to hold result of reduction
                    boolean amLast = false;
                    {
                        // firstprivate variables + init
                        // [last]private variables
                        String linha = new String();
                        // reduction variables + init to default
                        // -------------------------------------
                        jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                        jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                        __omp_WholeData2.start = (long) (0);
                        __omp_WholeData2.stop = (long) (numLinhas);
                        __omp_WholeData2.step = (long) (1);
                        __omp_WholeData2.chunkSize = 1;
                        jomp.runtime.OMP.initTicket(__omp_me, __omp_WholeData2);
                        while (!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopDynamic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
                            for (int i = (int) __omp_ChunkData1.start; i < __omp_ChunkData1.stop; i += __omp_ChunkData1.step) {
                                // OMP USER CODE BEGINS
                                {
                                    // OMP CRITICAL BLOCK BEGINS
                                    synchronized (jomp.runtime.OMP.getLockByName("")) {
                                        // OMP USER CODE BEGINS

                                        {
                                            linha = in.readLine();
                                        }
                                        // OMP USER CODE ENDS
                                    }
                                    // OMP CRITICAL BLOCK ENDS

                                    resultado = linha.substring(linha.lastIndexOf("= ") + 2);
                                    if (resultado.contains("E")) {
                                        ignorado += linha + "\n";
                                        continue;
                                    }
                                    // OMP CRITICAL BLOCK BEGINS
                                    synchronized (jomp.runtime.OMP.getLockByName("")) {
                                        // OMP USER CODE BEGINS

                                        {
                                            lista.add(resultado);
                                        }
                                        // OMP USER CODE ENDS
                                    }
                                    // OMP CRITICAL BLOCK ENDS

                                    soma += Double.parseDouble(resultado);
                                }
                                // OMP USER CODE ENDS
                                if (i == (__omp_WholeData2.stop - 1)) {
                                    amLast = true;
                                }
                            } // of for 
                        } // of while
                        // call reducer
                        jomp.runtime.OMP.resetTicket(__omp_me);
                        jomp.runtime.OMP.doBarrier(__omp_me);
                        // copy lastprivate variables out
                        if (amLast) {
                        }
                    }
                    // set global from lastprivate variables
                    if (amLast) {
                    }
                    // set global from reduction variables
                    if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                    }
                } // OMP FOR BLOCK ENDS

            }
            // OMP USER CODE ENDS
            // call reducer
            // output to _rd_ copy
            if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
            }
        }
    }
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS
}
