package Tabela;

import Controle.Controle;
import Principal.Principal;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class EditorMudarFaixa extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton btMudarFaixa;
    private JTable tabela;
    private Controle controle;
    private Principal principal;

    public EditorMudarFaixa(Controle controle, Principal principal) {
        this.controle = controle;
        this.principal = principal;
        btMudarFaixa = new JButton("faixa");
        btMudarFaixa.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return controle;
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        this.tabela = tabela;
        return btMudarFaixa;
    }

    public void actionPerformed(ActionEvent evento) {
        principal.selecionarFaixa(tabela.getSelectedRow());
        stopCellEditing();
    }
}
