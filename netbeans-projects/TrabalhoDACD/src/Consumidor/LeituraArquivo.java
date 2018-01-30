package Consumidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import javax.naming.TimeLimitExceededException;

import Armazem.ArmazemPrincipal;
import LogTela.VariaveisOrdTabela;
import Principal.TipoArquivo;
import ResolvedorFactory.LinhaDAO;

public class LeituraArquivo extends Thread {

    private Semaphore semaforo;
    private File arq;
    private ArmazemPrincipal armazemProdCons;
    private GravacaoArquivo gravacaoArquivo;
    private VariaveisOrdTabela controleTabela;
    private LinhaDAO linhaDAO;

    public LeituraArquivo(Semaphore semaforo, File arq, ArmazemPrincipal armazemProdCons, GravacaoArquivo gravacaoArquivo, VariaveisOrdTabela controleTabela) {
        this.semaforo = semaforo;
        this.arq = arq;
        this.armazemProdCons = armazemProdCons;
        this.gravacaoArquivo = gravacaoArquivo;
        this.controleTabela = controleTabela;
    }

    public boolean fimThread() throws TimeLimitExceededException {
        try {
            sleep(20);
        } catch (InterruptedException ex) {
        }
        while (!semaforo.tryAcquire());
        try {
            linhaDAO.join(10000);
        } catch (Exception ex) {
        }
        semaforo.release();
        return true;
    }

    @Override
    public void run() {
        try {
            while (!semaforo.tryAcquire());
            BufferedReader in = new BufferedReader(new FileReader(arq));
            linhaDAO = new LinhaDAO();
            linhaDAO.setArmazemProdCons(armazemProdCons);
            linhaDAO.setLinha(in.readLine());
            linhaDAO.setGravacaoArquivo(gravacaoArquivo);
            linhaDAO.setControleTabela(controleTabela);
            linhaDAO.setTipoArquivo(TipoArquivo.CONSUMIDOR);
            in.close();
            linhaDAO.start();
            semaforo.release();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
