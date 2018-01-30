package ResolvedorFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import Armazem.ArmazemPrincipal;
import Consumidor.GravacaoArquivo;
import LogTela.VariaveisOrdTabela;
import Principal.TipoArquivo;

public class ArquivoDAO extends Thread {

    private BufferedReader in;
    private File arq;
    private ArmazemPrincipal armazemProd;
    private ArmazemPrincipal armazemProdCons;
    private GravacaoArquivo gravacaoArquivo;
    private TipoArquivo tipoArquivo;
    private VariaveisOrdTabela controleTabela;
    private String linha;
    private int numLinha;

    public void setBuffer(BufferedReader in) {
        this.in = in;
    }

    public void setArq(File arq) {
        this.arq = arq;
    }

    public void setArmazemProd(ArmazemPrincipal armazemprod) {
        this.armazemProd = armazemprod;
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

    private void resolveLinha() {
        LinhaDAO linhaDAO = new LinhaDAO();
        linhaDAO.setNumLinha(numLinha);
        linhaDAO.setNomeArq(arq.getName());
        linhaDAO.setLinha(linha);
        linhaDAO.setGravacaoArquivo(gravacaoArquivo);
        linhaDAO.setControleTabela(controleTabela);
        switch (tipoArquivo) {
            case PRODUTOR:
                linhaDAO.setArmazemProd(armazemProd);
                linhaDAO.setTipoArquivo(TipoArquivo.PRODUTOR);
                linhaDAO.start();
                break;
            case PRODCONSUMIDOR:
                linhaDAO.setArmazemProd(armazemProd);
                linhaDAO.setArmazemProdCons(armazemProdCons);
                linhaDAO.setTipoArquivo(TipoArquivo.PRODCONSUMIDOR);
                linhaDAO.start();
                break;
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < arq.length(); i++) {
            try {
                String linha = in.readLine();
                if (linha == null || linha.equals("")) {
                    continue;
                }
                this.linha = linha;
                this.numLinha = i;
                resolveLinha();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro na leitura da linha " + i + " do arquivo " + arq.getName(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        try {
            in.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo " + arq.getName(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
