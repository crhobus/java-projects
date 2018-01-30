package PVM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

import com.primalworld.math.MathEvaluator;

import jpvm.jpvmBuffer;
import jpvm.jpvmEnvironment;
import jpvm.jpvmException;
import jpvm.jpvmMessage;
import jpvm.jpvmTaskId;

public class MestreEscravo {

    static int num_workers = 15;

    public static void main(String[] args) {
        try {
            jpvmEnvironment jpvm = new jpvmEnvironment();

            jpvmTaskId myid = jpvm.pvm_mytid();
            jpvmTaskId masterTaskId = jpvm.pvm_parent();
            System.out.println("task id = " + myid.toString());
            jpvmBuffer buf;
            if (masterTaskId == jpvm.PvmNoParent) {// verifica se é mestre
                try {
                    // é o master
                    //inicializado como master
                    jpvmTaskId tids[] = new jpvmTaskId[num_workers];
                    jpvm.pvm_spawn("PVM.MestreEscravo", num_workers, tids);

                    String linhasArquivo = "";
                    System.out.println("Lendo arquivos...");
                    File dir = new File("C:\\Users\\Caio\\Documents\\Programação\\Java\\Programas Java\\ProjetosEclipse\\TrabalhoDACDPVM\\Formulas");
                    File[] arqs = dir.listFiles();
                    String linha;
                    for (int x = 0; x < arqs.length; x++) {
                        BufferedReader in = new BufferedReader(new FileReader(arqs[x]));
                        for (int i = 0; i < arqs[x].length(); i++) {
                            linha = in.readLine();
                            if (linha == null || linha.equals("")) {
                                continue;
                            }
                            linhasArquivo += linha + "\n";
                        }
                        System.out.println("Arquivo " + arqs[x].getName() + " Lido");
                        System.out.println("Mandando as formulas para o escravo...");
                        //Mandada as linhas para o escravo
                        buf = new jpvmBuffer();
                        buf.pack(linhasArquivo);
                        jpvm.pvm_send(buf, tids[x], 0);
                        linhasArquivo = "";
                    }
                    System.out.println("Todos os arquivos lidos");
                    jpvmMessage message;
                    String respostaEscravo = "";
                    for (int x = 0; x < arqs.length; x++) {
                        //mestre recebe algo do escravo
                        message = jpvm.pvm_recv();
                        respostaEscravo += message.buffer.upkstr();
                        //recebeu resposta
                        System.out.println("Recebeu as formulas calculadas do escravo " + (x + 1));
                    }
                    jpvm.pvm_spawn("PVM.Escravo2", num_workers, tids);
                    buf = new jpvmBuffer();
                    buf.pack(respostaEscravo);
                    for (int tag = 1; tag < 4; tag++) {
                        jpvm.pvm_send(buf, tids[tag], tag);
                        System.out.println();
                        System.out.println("Mandando a tag " + tag + " para o escravo");
                        message = jpvm.pvm_recv();
                        respostaEscravo = message.buffer.upkstr();
                        System.out.println("Resultado obtido a partir da tag " + tag);
                        System.out.println(respostaEscravo);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // executa o escravo.
                //inicializado como escravo
                jpvmMessage message = jpvm.pvm_recv();
                String recebeuMestre = message.buffer.upkstr();
                String[] linhas = recebeuMestre.split("\n");
                String resultados = "";
                MathEvaluator resolvedor = new MathEvaluator();
                for (String linha : linhas) {
                    resolvedor.setExpression(linha);
                    resultados += resolvedor.getValue() + "\n";
                }
                //preparando resposta
                buf = new jpvmBuffer();
                buf.pack(resultados);
                jpvm.pvm_send(buf, masterTaskId, 0);
                //mandou resposta
            }
            // sai do jpvm
            jpvm.pvm_exit();
        } catch (jpvmException e) {
            e.printStackTrace(System.out);
        }
    }
}
