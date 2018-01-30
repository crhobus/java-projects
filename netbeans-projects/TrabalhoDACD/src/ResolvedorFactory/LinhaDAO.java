package ResolvedorFactory;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import com.primalworld.math.MathEvaluator;

import Armazem.ArmazemPrincipal;
import Consumidor.GravacaoArquivo;
import LogTela.VariaveisOrdTabela;
import Principal.TipoArquivo;
import ProdConsumidor.AddVariaveisExpressao;
import ProdConsumidor.BufferVariaveisExpressao;
import ProdConsumidor.LeituraArmazemDir1;

public class LinhaDAO extends Thread {

    private String linha, nomeArq;
    private int numLinha;
    private ArmazemPrincipal armazemProd;
    private ArmazemPrincipal armazemProdCons;
    private GravacaoArquivo gravacaoArquivo;
    private TipoArquivo tipoArquivo;
    private VariaveisOrdTabela controleTabela;

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public void setNumLinha(int numLinha) {
        this.numLinha = numLinha;
    }

    public void setArmazemProd(ArmazemPrincipal armazemProd) {
        this.armazemProd = armazemProd;
    }

    public void setArmazemProdCons(ArmazemPrincipal armazemProdCons) {
        this.armazemProdCons = armazemProdCons;
    }

    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public void setGravacaoArquivo(GravacaoArquivo gravacaoArquivo) {
        this.gravacaoArquivo = gravacaoArquivo;
    }

    public void setControleTabela(VariaveisOrdTabela controleTabela) {
        this.controleTabela = controleTabela;
    }

    private void armazena() {
        double valor = 0;
        String chave = "";
        String nLinha = "" + numLinha;
        if (numLinha < 10) {
            nLinha = "0" + nLinha;
        }
        if (numLinha < 100) {
            nLinha = "0" + nLinha;
        }
        while (gravacaoArquivo.isArqSaida() == null) {
            try {
                sleep(8);
            } catch (InterruptedException ex) {
            }
        }
        switch (tipoArquivo) {
            case PRODUTOR:
                chave = nomeArq.replace(".txt", "") + nLinha;
                valor = new MathEvaluator(linha).getValue();
                armazemProd.insere(chave, valor);
                gravacaoArquivo.gravarLinha(chave + ": " + linha + " = " + valor);
                controleTabela.insere(chave, linha, valor);
                break;
            case PRODCONSUMIDOR:
                chave = nomeArq.replace(".txt", "") + nLinha;
                valor = resolve();
                armazemProdCons.insere(chave, valor);
                gravacaoArquivo.gravarLinha(chave + ": " + linha + " = " + valor);
                controleTabela.insere(chave, linha, valor);
                break;
            case CONSUMIDOR:
                MathEvaluator resolveLinha = new MathEvaluator();
                resolveLinha.setExpression(linha);
                String[] vet = variaveis();
                for (int i = 0; i < vet.length; i++) {
                    while (armazemProdCons.leitura(vet[i]) == null) {
                        try {
                            sleep(100);
                        } catch (InterruptedException ex) {
                        }
                    }
                    resolveLinha.addVariable(vet[i], armazemProdCons.leitura(vet[i]));
                }
                gravacaoArquivo.gravarLinha("Conta Final: " + linha + " = " + resolveLinha.getValue());
                controleTabela.insere("|Conta Final|", linha, resolveLinha.getValue());
                break;
        }
    }

    private double resolve() {
        MathEvaluator resolveLinha = new MathEvaluator();
        resolveLinha.setExpression(linha);
        String[] vet = variaveis();
        BufferVariaveisExpressao buffer = new BufferVariaveisExpressao(resolveLinha, vet);
        LeituraArmazemDir1 leitura = new LeituraArmazemDir1(buffer, vet, armazemProd);
        AddVariaveisExpressao add = new AddVariaveisExpressao(buffer, vet);
        leitura.start();
        add.start();
        try {
            leitura.join();
            add.join();
        } catch (InterruptedException ex) {
        }
        return resolveLinha.getValue();
    }

    private String[] variaveis() {
        char[] c = linha.toCharArray();
        String token = "";
        for (int i = 0; i < c.length; i++) {
            if (Character.isLetter(c[i])) {
                token += c[i];
                i++;
                while (i < c.length && Character.isLetter(c[i])) {
                    token += c[i];
                    i++;
                }
                while (i < c.length && Character.isDigit(c[i])) {
                    token += c[i];
                    i++;
                }
            }
            token += " ";
        }
        StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
        token = "";
        while (tokenizer.hasMoreElements()) {
            token += tokenizer.nextElement() + " ";
        }
        return token.split(" ");
    }

    @Override
    public void run() {
        try {
            armazena();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao resolver a fórmula " + linha + " na linha " + (numLinha + 1) + " no arquivo " + nomeArq, "Erro no Arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
