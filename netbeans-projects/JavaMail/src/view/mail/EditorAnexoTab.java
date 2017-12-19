package view.mail;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import control.DiretorioMsgsDAO;

//Classe editora da célula com anexo
public class EditorAnexoTab extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton btAnexo;
    private Frame principal;
    private String anexo;
    private DiretorioMsgsDAO diretorioMsgsDAO;

    public EditorAnexoTab(Frame principal, DiretorioMsgsDAO diretorioMsgsDAO) {
        this.diretorioMsgsDAO = diretorioMsgsDAO;
        this.principal = principal;
        btAnexo = new JButton(new ImageIcon("Anexo.png"));
        btAnexo.addActionListener(this);
    }

    // Após encerrado volta ao estado inicial
    public Object getCellEditorValue() {
        return new ImageIcon("Anexo.png");
    }

    // Botão que aparece na hora do click na célula
    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        return btAnexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public void actionPerformed(ActionEvent evento) {
        if (anexo != null) {
            String[] anexos = anexo.split(";");
            AbrirAnexo abrirAnexo = new AbrirAnexo(principal, anexos, diretorioMsgsDAO);
            abrirAnexo.setVisible(true);
            stopCellEditing();// Finaliza a edição
        }
        cancelCellEditing();// Finaliza a edição
    }
}
