package ReconhecimentoPalavras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Principal extends JFrame implements ActionListener {

    private JTextArea taCampo;
    private JTable tabela;
    private JButton btAnalizar, btLimpar, btEquipe;
    private TableModel tableModel;
    private Renderizadora rendener;

    public Principal() {
        super("Reconhecimento de Palavras da Linguagem Regular");
        tableModel = new TableModel();
        rendener = new Renderizadora();
        initComponents();
    }

    private void initComponents() {
        taCampo = new JTextArea();
        taCampo.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        JScrollPane scrollCampo = new JScrollPane(taCampo);
        scrollCampo.setPreferredSize(new Dimension(0, 315));
        add(scrollCampo, BorderLayout.NORTH);

        Icon icAnalisar = new ImageIcon("analisar.jpg");
        btAnalizar = new JButton("analizar", icAnalisar);
        btAnalizar.setPreferredSize(new Dimension(260, 30));
        btAnalizar.setBackground(new Color(248, 248, 248));
        add(btAnalizar, BorderLayout.WEST);
        btAnalizar.addActionListener(this);

        Icon icLimpar = new ImageIcon("limpar.jpg");
        btLimpar = new JButton("limpar", icLimpar);
        btLimpar.setBackground(new Color(248, 248, 248));
        add(btLimpar, BorderLayout.CENTER);
        btLimpar.addActionListener(this);

        Icon icEquipe = new ImageIcon("equipe.jpg");
        btEquipe = new JButton("equipe", icEquipe);
        btEquipe.setPreferredSize(new Dimension(260, 30));
        btEquipe.setBackground(new Color(248, 248, 248));
        add(btEquipe, BorderLayout.EAST);
        btEquipe.addActionListener(this);

        tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setMinWidth(20);
        tabela.getColumnModel().getColumn(1).setMinWidth(160);
        tabela.getColumnModel().getColumn(2).setMinWidth(290);
        tabela.getColumnModel().getColumn(3).setMinWidth(550);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setPreferredSize(new Dimension(0, 325));
        scrollTabela.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        add(scrollTabela, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void lerPalavras() {
        String texto = taCampo.getText().replace("\t", " ");
        String vet[] = texto.split("\n");
        for (int i = 0; i < vet.length; i++) {
            String str[] = vet[i].split(" ");
            for (int y = 0; y < str.length; y++) {
                if (!"".equals(str[y])) {
                    tableModel.addLista(str[y], i + 1);
                }
            }
            str = null;
        }
        tableModel.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btAnalizar) {
            lerPalavras();
        }
        if (evento.getSource() == btLimpar) {
            taCampo.setText("");
            tableModel.limparLista();
            tableModel.fireTableDataChanged();
        }
        if (evento.getSource() == btEquipe) {
            JOptionPane.showMessageDialog(null, "Equipe: Caio Renan Hobus, Itamara Xavier", "Equipe", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
