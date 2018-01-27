package Seguranca;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileFilter;

public class FiltroArquivos extends FileFilter {

    private List<String> listaExtensao = new ArrayList<String>();
    private String descricao;

    public FiltroArquivos(String descricao) {
        this.descricao = descricao;
    }

    private String getExtensao(File arq) {
        if (arq != null) {
            String nomeArq = arq.getName();
            int i = nomeArq.lastIndexOf('.');
            if (i > 0 && i < nomeArq.length() - 1) {
                return nomeArq.substring(i + 1).toLowerCase();
            }
        }
        return null;
    }

    public void addExtencao(String ext) {
        listaExtensao.add(ext);
    }

    @Override
    public boolean accept(File arq) {
        if (arq.isDirectory()) {
            return true;
        }
        String ext = getExtensao(arq);
        return listaExtensao.contains(ext);
    }

    @Override
    public String getDescription() {
        return descricao;
    }
}
