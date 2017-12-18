package DownloadManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;

public class DownloadsTableModel extends AbstractTableModel implements Observer {

    private static final long serialVersionUID = 1L;
    private List<Download> listaDownload = new ArrayList<Download>();

    public void addDownload(Download download) {// adiciona um novo download a tabela
        download.addObserver(this);// registro a ser notificado quando o download se altera
        listaDownload.add(download);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);// dispara a notificação de inserção de linha na tabela
    }

    public Download getDownload(int linha) {// obtém um download para a linha especificada
        return listaDownload.get(linha);
    }

    public void removeDownload(int linha) {// remove um download da lista
        listaDownload.remove(linha);
        fireTableRowsDeleted(linha, linha);// dispara a notificação de exclussão de linha da tabela
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return listaDownload.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Download download = listaDownload.get(linha);
        switch (coluna) {
            case 0:
                return download.getURL();
            case 1:
                return (download.getTamanho() == -1) ? "" : Integer.toString(download.getTamanho());
            case 2:
                return new Float(download.getProgresso());
            default:
                return download.getStatus(download.getStatusAtual());
        }
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        switch (coluna) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return JProgressBar.class;
            default:
                return String.class;
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Site";
            case 1:
                return "Tamanho";
            case 2:
                return "Progresso";
            default:
                return "Status";
        }
    }

    @Override
    public void update(Observable o, Object arg) {// update é chamado quando um download notifica seus observalores sobre qualquer alteração
        int indice = listaDownload.indexOf(o);
        fireTableRowsUpdated(indice, indice);// dispara a notificação de atualização de linha da tabela
    }
}
