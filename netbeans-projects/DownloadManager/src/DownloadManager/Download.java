package DownloadManager;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

//Esta classe faz download de um arquivo a partir de um URL 
public class Download extends Observable implements Runnable {

    private final int TAMMAXBUFFER = 1024;// tamanho máximo do buffer de download
    // status download
    public static final int TRANSFERINDO = 0;
    public static final int PAUSADO = 1;
    public static final int COMPLETO = 2;
    public static final int CANCELADO = 3;
    public static final int ERRO = 4;
    private URL url;// url para download
    private int tamanho;// tamanho do donwload em bytes
    private int transferido;// número de bytes descarregados
    private int status;// status atual do download
    private File arqDownload;//arquivo a ser salvo

    public Download(URL url, File arqDownload) {
        this.url = url;
        this.arqDownload = arqDownload;
        this.tamanho = -1;
        transferido = 0;
        status = TRANSFERINDO;
        download();// inicia download
    }

    public String getStatus(int status) {// obtém status do download
        switch (status) {
            case 0:
                return "Transferindo";
            case 1:
                return "Pausado";
            case 2:
                return "Completo";
            case 3:
                return "Cancelado";
            default:
                return "Erro";
        }
    }

    public String getURL() {// obtém o URL desse download
        return url.toString();
    }

    public int getTamanho() {// obtém o tamanho desse download
        return tamanho;
    }

    public float getProgresso() {// obtém o progresso desse download
        return ((float) transferido / tamanho) * 100;
    }

    public int getStatusAtual() {// obtém status atual desse download
        return status;
    }

    public void pausa() {// pausa esse download
        status = PAUSADO;
        estadoAterado();
    }

    public void reiniciar() {// reiniciar esse download
        status = TRANSFERINDO;
        estadoAterado();
        download();
    }

    public void cancelar() {// cancela esse download
        status = CANCELADO;
        estadoAterado();
    }

    private void erro() {// marca esse download como tendo erro
        status = ERRO;
        estadoAterado();
    }

    private void download() {// inicia ou reinicia o download
        Thread thread = new Thread(this);
        thread.start();
    }

    private void estadoAterado() {// notifica os observadores de que o status desse download mudou
        setChanged();
        notifyObservers();
    }

    @Override
    public void run() {// faz download do arquivo
        RandomAccessFile arquivo = null;
        InputStream stream = null;
        try {
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();// abre conexão com o URL
            conexao.setRequestProperty("Range", "bytes=" + transferido + "-");// especifica a parte do arquivo a descarregar
            conexao.connect();// conecta com o servidor
            if (conexao.getResponseCode() / 100 != 2) {// certifica-se de que o código de resposta está no intervalo de 200
                erro();
            }
            int tamanhoConteudo = conexao.getContentLength();// verifica o comprimento válido de conteúdo
            if (tamanhoConteudo < 1) {
                erro();
            }
            if (tamanho == -1) {// configura o tamanho para esse download se ele ainda não foi configurado
                this.tamanho = tamanhoConteudo;
                estadoAterado();
            }
            //arquivo = new RandomAccessFile(getNomeArq(url), "rw");// abre o arquivo
            arquivo = new RandomAccessFile(arqDownload, "rw");// abre o arquivo
            arquivo.seek(transferido);// busca seu final;
            stream = conexao.getInputStream();
            while (status == TRANSFERINDO) {
                // dimensiona o buffer de acordo com quanto do arquivo resta a fazer download
                byte buffer[];
                if (tamanho - transferido > TAMMAXBUFFER) {
                    buffer = new byte[TAMMAXBUFFER];
                } else {
                    buffer = new byte[tamanho - transferido];
                }
                int leitura = stream.read(buffer);// lê do servidor para o buffer
                if (leitura == -1) {
                    break;
                }
                arquivo.write(buffer, 0, leitura);// grava o buffer no arquivo
                transferido += leitura;
                estadoAterado();
            }
            if (status == TRANSFERINDO) {// altera status para complementar se esse ponto foi alcançado porque o download terminou
                status = COMPLETO;
                estadoAterado();
            }
        } catch (Exception ex) {
            erro();
        } finally {
            if (arquivo != null) {
                try {
                    arquivo.close();// fecha o arquivo
                } catch (Exception ex) {
                }
            }
            if (stream != null) {
                try {
                    stream.close();// fecha conexão com o servidor
                } catch (Exception ex) {
                }
            }
        }
    }
}
