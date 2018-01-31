package view.componentes.table;

import control.funcoes.Funcoes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class TableRenderer extends JLabel implements TableCellRenderer {

    private boolean pintarCelula;
    private DefinirRegraCorCelulaTable corCelula;
    private int nrTabela;

    public TableRenderer(boolean pintarCelula, int nrTabela) {
        this.pintarCelula = pintarCelula;
        this.corCelula = new DefinirRegraCorCelulaTable(this);
        this.nrTabela = nrTabela;
    }

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        JComponent jc;
        setText(valor.toString());
        setFont(new Font("Tahoma", Font.PLAIN, 12));
        setOpaque(true);
        if (valor.getClass() == Integer.class
                || valor.getClass() == Double.class
                || "...".equals(valor.toString())) {
            setHorizontalAlignment(SwingConstants.CENTER);
        } else if (valor.getClass() == Timestamp.class
                || valor.getClass() == Date.class) {
            setText(Funcoes.dateToString((Date) valor, 1));
            setHorizontalAlignment(SwingConstants.CENTER);
        } else if (valor.getClass() == String.class
                && !"...".equals(valor.toString())) {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        if (pintarCelula) {
            switch (nrTabela) {
                case 1:
                    corCelula.regraUsuario(tabela, linha);
                    break;
            }
        }
        jc = this;
        if (valor.getClass() == Boolean.class) {
            JCheckBox check = new JCheckBox();
            check.setSelected((Boolean) valor);
            check.setHorizontalAlignment(SwingConstants.CENTER);
            jc = check;
        }
        if (temFocus) {
            jc.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        } else {
            jc.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        }
        if (!pintarCelula) {
            jc.setBackground(Color.WHITE);
        }
        return jc;
    }
}
