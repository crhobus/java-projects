package CaioRenanHobus.Visao;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class EditorUnidade extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JPanel painel;
    private JTextField tfUnidade;
    private JButton btOk;

    public EditorUnidade() {
        painel = new JPanel();
        painel.setLayout(null);
        tfUnidade = new JTextField();
        tfUnidade.setBounds(3, 3, 40, 20);
        painel.add(tfUnidade);
        btOk = new JButton("OK");
        btOk.setBounds(45, 2, 51, 20);
        painel.add(btOk);
        btOk.addActionListener(this);
    }

    public Object getCellEditorValue() {
        return tfUnidade.getText();
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        tfUnidade.setText(valor.toString());
        return painel;
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk) {
            if ("un".equals(tfUnidade.getText()) || "kg".equals(tfUnidade.getText()) || "m".equals(tfUnidade.getText())) {
                fireEditingStopped();
            } else {
                JOptionPane.showMessageDialog(null, "Campo inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                tfUnidade.grabFocus();
            }
        }
    }

    @Override
    public boolean stopCellEditing() {
        return false;
    }
}
