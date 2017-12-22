package view;

import javax.swing.JDialog;

public class Versao extends JDialog {

    private static final long serialVersionUID = 1L;

    public Versao() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Versão do Sistema");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(2, 2, 619, 369);
        this.add(panel);

        panel.addJLabelImg("Java.png", 440, 10, 200, 200);

        panel.addJLabelImg("Oracle Sun.png", 100, 80, 250, 133);

        panel.addJLabelImg("SQL Developer.png", 475, 215, 140, 150);

        panel.addJLabel("Versão 1.0", 165, 220, 60, 14);

        panel.addJLabel("Copyright 2011 Java - Oracle.", 165, 235, 150, 14);

        panel.addJLabel("Sistema Inventário", 165, 250, 90, 14);

        panel.addJLabel("Caio Renan Hobus", 165, 265, 90, 14);

        setSize(628, 400);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
