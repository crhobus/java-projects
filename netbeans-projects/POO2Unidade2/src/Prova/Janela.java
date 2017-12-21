package Prova;

import java.awt.*;
import java.text.*;
import javax.swing.*;

public class Janela extends JFrame {

    public Janela() throws Exception {
        super("Prova");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() throws Exception {

        add(new JButton("Add Evento"), BorderLayout.NORTH);
        EventoTableModel ev = new EventoTableModel();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ev.add(new Evento("ABC", sdf.parse("01/10/2010"), true, false, true));
        ev.add(new Evento("DEF", sdf.parse("02/05/2009"), false, false, true));
        ev.add(new Evento("GHI", sdf.parse("03/07/2009"), true, true, true));
        JTable t = new JTable(ev);
        
        t.setRowHeight(30);
        t.getColumnModel().getColumn(2).setCellRenderer(new CheckBoxesRender());
        t.getColumnModel().getColumn(2).setCellEditor(new CheckBoxesEditor());

        JScrollPane s = new JScrollPane(t);
        add(s, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws Exception {
        new Janela();
    }
}
