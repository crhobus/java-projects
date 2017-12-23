package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Cliente.CadasCliente;
import View.Endereco.CadasEndereco;

public class Locadora extends JFrame implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JMenuItem miSair, miCadasClie, miCadasEnd, miCadasFilmes;
    private Connection con;

    public Locadora(Connection con) {
        super("Sistema Locadora");
        this.con = con;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu mnSistema = new JMenu("Sistema");

        miSair = new JMenuItem("Sair");
        mnSistema.add(miSair);
        miSair.addActionListener(this);

        JMenu mnCadastros = new JMenu("Cadastros");

        miCadasClie = new JMenuItem("Clientes");
        mnCadastros.add(miCadasClie);
        miCadasClie.addActionListener(this);

        miCadasEnd = new JMenuItem("Endereço");
        mnCadastros.add(miCadasEnd);
        miCadasEnd.addActionListener(this);

        miCadasFilmes = new JMenuItem("Filmes");
        mnCadastros.add(miCadasFilmes);
        miCadasFilmes.addActionListener(this);

        menuBar.add(mnSistema);
        menuBar.add(mnCadastros);
        setJMenuBar(menuBar);

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelNorte.setBorder(BorderFactory.createTitledBorder(""));
        add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(null);
        panelCentro.setBorder(BorderFactory.createTitledBorder(""));
        add(panelCentro, BorderLayout.CENTER);

        JPanel panelPersistencia = new JPanel();
        panelPersistencia.setLayout(null);
        panelPersistencia.setBorder(BorderFactory.createTitledBorder(""));
        panelPersistencia.setPreferredSize(new Dimension(350, 40));

        JLabel lbPersistencia = new JLabel("Persistência de dados");
        lbPersistencia.setBounds(30, 10, 125, 14);
        panelPersistencia.add(lbPersistencia);

        JTextField tfPersistencia = new JTextField("Banco de Dados: Oracle");
        tfPersistencia.setBounds(160, 4, 150, 28);
        tfPersistencia.setEditable(false);
        panelPersistencia.add(tfPersistencia);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(null);
        panelUsuario.setBorder(BorderFactory.createTitledBorder(""));
        panelUsuario.setPreferredSize(new Dimension(350, 40));

        JLabel lbUsuario = new JLabel("Usuário: SYSDBA");
        lbUsuario.setBounds(50, 10, 100, 14);
        panelUsuario.add(lbUsuario);

        JLabel lbPermissao = new JLabel("Permissão: 1");
        lbPermissao.setBounds(190, 10, 100, 14);
        panelUsuario.add(lbPermissao);

        JPanel panelCentroSul = new JPanel();
        panelCentroSul.setBorder(BorderFactory.createTitledBorder(""));

        JPanel panelSul = new JPanel();
        panelSul.setBorder(BorderFactory.createTitledBorder(""));
        panelSul.setLayout(new BorderLayout());
        panelSul.add(panelUsuario, BorderLayout.WEST);
        panelSul.add(panelCentroSul, BorderLayout.CENTER);
        panelSul.add(panelPersistencia, BorderLayout.EAST);
        add(panelSul, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evento) {
                sair();
            }
        });

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(850, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void sair() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema", "Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                con.close();
                System.exit(JFrame.EXIT_ON_CLOSE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cadasEndereco() {
        CadasEndereco endereco = new CadasEndereco(con);
        endereco.setModal(true);
        endereco.setVisible(true);
    }

    private void cadasCliente() {
        CadasCliente cliente = new CadasCliente(con);
        cliente.setModal(true);
        cliente.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == miSair) {
            sair();
        }
        if (evento.getSource() == miCadasEnd) {
            cadasEndereco();
        }
        if (evento.getSource() == miCadasClie) {
            cadasCliente();
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
    }

    @Override
    public void focusLost(FocusEvent evento) {
    }
}
