package DownloadManager;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderizaProgresso extends JProgressBar implements TableCellRenderer {

    private static final long serialVersionUID = 1L;

    public RenderizaProgresso(int min, int max) {
        super(min, max);
    }

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {// retorna esse JProgressBar como o renderizado para a dada célula de tabela
        setValue((int) ((Float) valor).floatValue());// configura valor percentual completo de JProgressBar
        return this;
    }
}
