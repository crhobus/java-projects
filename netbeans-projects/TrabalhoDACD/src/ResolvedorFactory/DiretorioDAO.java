package ResolvedorFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Armazem.ArmazemPrincipal;
import Consumidor.GravacaoArquivo;
import LogTela.VariaveisOrdTabela;
import Principal.TipoArquivo;

public class DiretorioDAO {

    private String diretorio;
    private ArmazemPrincipal armazemProd;
    private ArmazemPrincipal armazemProdCons;
    private GravacaoArquivo gravacaoArquivo;
    private VariaveisOrdTabela controleTabela;
    private File[] arqsDiretorio;

    public void setDiretorio(String diretorio) throws Exception {
        this.diretorio = diretorio;
        File dir = new File(diretorio);
        if (!dir.exists()) {
            throw new Exception("Diretório não encontrado");
        }
        arqsDiretorio = dir.listFiles();
    }

    public void setArmazemProd(ArmazemPrincipal armazemProd) {
        this.armazemProd = armazemProd;
    }

    public void setArmazemProdCons(ArmazemPrincipal armazemProdCons) {
        this.armazemProdCons = armazemProdCons;
    }

    public void setGravacaoArquivo(GravacaoArquivo gravacaoArquivo) {
        this.gravacaoArquivo = gravacaoArquivo;
    }

    public void setControleTabela(VariaveisOrdTabela controleTabela) {
        this.controleTabela = controleTabela;
    }

    private void novoArquivo(TipoArquivo tipo, BufferedReader in, File arq) {
        ArquivoDAO arquivo = new ArquivoDAO();
        arquivo.setTipoArquivo(tipo);
        arquivo.setBuffer(in);
        arquivo.setArq(arq);
        arquivo.setGravacaoArquivo(gravacaoArquivo);
        arquivo.setControleTabela(controleTabela);
        switch (tipo) {
            case PRODUTOR:
                arquivo.setArmazemProd(armazemProd);
                arquivo.start();
                break;
            case PRODCONSUMIDOR:
                arquivo.setArmazemProd(armazemProd);
                arquivo.setArmazemProdCons(armazemProdCons);
                arquivo.start();
                break;
        }
    }

    public void lerDiretorio(TipoArquivo tipo) throws Exception {
        for (int i = 0; i < arqsDiretorio.length; i++) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(diretorio, arqsDiretorio[i].getName())));
                novoArquivo(tipo, in, arqsDiretorio[i]);
            } catch (FileNotFoundException ex) {
                throw new FileNotFoundException("Erro ao abrir o " + arqsDiretorio[i].getName() + " para leitura");
            }
        }
    }
}
