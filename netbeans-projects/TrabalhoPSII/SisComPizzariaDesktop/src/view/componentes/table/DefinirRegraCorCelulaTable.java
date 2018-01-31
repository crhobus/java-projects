package view.componentes.table;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;

public class DefinirRegraCorCelulaTable {

    private JLabel label;

    public DefinirRegraCorCelulaTable(JLabel label) {
        this.label = label;
    }

    public void regraUsuario(JTable tabela, int linha) {
        switch ((Integer) tabela.getModel().getValueAt(linha, 4)) {
            case 1://Administração
                label.setBackground(new Color(0, 191, 255));
                break;
            case 2://Gerencia
                label.setBackground(new Color(84, 255, 159));
                break;
            case 3://Funcionário
                label.setBackground(new Color(238, 197, 145));
                break;
            case 4://Cliente
                label.setBackground(new Color(207, 207, 207));
                break;
        }
    }
}
