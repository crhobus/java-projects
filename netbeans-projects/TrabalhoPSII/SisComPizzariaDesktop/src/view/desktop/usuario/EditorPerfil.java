package view.desktop.usuario;

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class EditorPerfil extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private UsuarioSis usuarioSis;
    private JButton btRemoverFuncao;
    private JTable tabela;

    public EditorPerfil(UsuarioSis usuarioSis) {
        this.usuarioSis = usuarioSis;
        btRemoverFuncao = new JButton("Remover");
        btRemoverFuncao.setMargin(new Insets(0, 0, 0, 0));
        btRemoverFuncao.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        return usuarioSis;
    }

    @Override
    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        this.tabela = tabela;
        return btRemoverFuncao;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        usuarioSis.removeFuncaoPerfil(tabela.getSelectedRow());
        stopCellEditing();
    }
}
