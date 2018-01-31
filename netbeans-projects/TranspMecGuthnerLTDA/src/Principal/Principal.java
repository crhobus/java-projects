package Principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import Modelo.Cliente;
import NovaOS.NovaOS;
import NovaOS.NovaOSControl;
import Veiculos.CadasVeiculo;
import Veiculos.PesquisaVeiculo;
import Veiculos.VeiculoControl;

import Clientes.CadasCliente;
import Clientes.ClienteControl;
import Clientes.PesquisaClientes;
import ConsultaHistorico.HistoricoOS;
import DAOFactory.DAOFactory;
import FormatacaoCampos.CamposInt;
import Funcionario.CadasFuncionario;
import Funcionario.FuncionarioControl;
import Funcionario.PesquisaFuncionario;

public class Principal extends JFrame implements ActionListener {

    private JMenu mnArquivo, mnCadastros, mnConsultas, mnControle,
            mnFeramentas, mnAjuda;
    private JMenuItem miClientes, miVeiculos, miConsCliente, miConsVeiculo,
            miConsOS, miConsHistorico, miNovaOS, miOrcamentos, miFuncionarios,
            miConsFuncionario, miSair;
    private JButton btClientes, btConsultas, btOS, btHistorico;
    private DAOFactory daoFactory;
    private Connection con;

    public Principal(DAOFactory daoFactory, Connection con) {
        super("TRANSPORTES E MECÂNICA GUTHNER LTDA");
        this.daoFactory = daoFactory;
        this.con = con;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(248, 248, 248));
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panel1.setBorder(BorderFactory.createTitledBorder(""));
        add(panel1, BorderLayout.NORTH);

        Icon icClientes = new ImageIcon("Clientes.jpg");
        btClientes = new JButton(icClientes);
        btClientes.setPreferredSize(new Dimension(30, 30));
        panel1.add(btClientes);
        btClientes.addActionListener(this);

        Icon icConsultas = new ImageIcon("Consultas.jpg");
        btConsultas = new JButton(icConsultas);
        btConsultas.setPreferredSize(new Dimension(30, 30));
        panel1.add(btConsultas);
        btConsultas.addActionListener(this);

        Icon icGerarOS = new ImageIcon("GerarOS.jpg");
        btOS = new JButton(icGerarOS);
        btOS.setPreferredSize(new Dimension(30, 30));
        panel1.add(btOS);
        btOS.addActionListener(this);

        Icon icHistorico = new ImageIcon("Historico.jpg");
        btHistorico = new JButton(icHistorico);
        btHistorico.setPreferredSize(new Dimension(30, 30));
        panel1.add(btHistorico);
        btHistorico.addActionListener(this);

        ImgFundo panel2 = new ImgFundo("Fundo.jpg");
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.setBorder(BorderFactory.createTitledBorder(""));
        add(panel2, BorderLayout.CENTER);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel3.setBorder(BorderFactory.createTitledBorder(""));
        panel3.setPreferredSize(new Dimension(350, 40));

        JLabel lbPersistencia = new JLabel("Persistência de dados");
        panel3.add(lbPersistencia);

        JTextField tfPersistencia = new JTextField("Banco de Dados: Firebird");
        tfPersistencia.setEditable(false);
        tfPersistencia.setBackground(Color.WHITE);
        tfPersistencia.setPreferredSize(new Dimension(160, 22));
        tfPersistencia.addActionListener(this);
        panel3.add(tfPersistencia);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.setBorder(BorderFactory.createTitledBorder(""));
        panel4.setPreferredSize(new Dimension(350, 40));

        JLabel lbUsuario = new JLabel("Usuário: SYSDBA");
        lbUsuario.setPreferredSize(new Dimension(150, 14));
        panel4.add(lbUsuario);

        JLabel lbPermissao = new JLabel("Permissão: 1");
        panel4.add(lbPermissao);

        JPanel panel5 = new JPanel();
        panel5.setBorder(BorderFactory.createTitledBorder(""));

        JPanel panel6 = new JPanel();
        panel6.setBorder(BorderFactory.createTitledBorder(""));
        panel6.setLayout(new BorderLayout());
        panel6.add(panel4, BorderLayout.WEST);
        panel6.add(panel5, BorderLayout.CENTER);
        panel6.add(panel3, BorderLayout.EAST);
        add(panel6, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        mnArquivo = new JMenu("Arquivo");

        miSair = new JMenuItem("Sair");
        mnArquivo.add(miSair);
        miSair.addActionListener(this);

        mnCadastros = new JMenu("Cadastros");

        miClientes = new JMenuItem("Clientes");
        mnCadastros.add(miClientes);
        miClientes.addActionListener(this);

        miVeiculos = new JMenuItem("Veículos");
        mnCadastros.add(miVeiculos);
        miVeiculos.addActionListener(this);

        miFuncionarios = new JMenuItem("Funcionários");
        mnCadastros.add(miFuncionarios);
        miFuncionarios.addActionListener(this);

        mnConsultas = new JMenu("Consultas");

        miConsCliente = new JMenuItem("Clientes");
        mnConsultas.add(miConsCliente);
        miConsCliente.addActionListener(this);

        miConsVeiculo = new JMenuItem("Veículos");
        mnConsultas.add(miConsVeiculo);
        miConsVeiculo.addActionListener(this);

        miConsFuncionario = new JMenuItem("Funcionários");
        mnConsultas.add(miConsFuncionario);
        miConsFuncionario.addActionListener(this);

        miConsOS = new JMenuItem("Ordem de Serviço");
        mnConsultas.add(miConsOS);
        miConsOS.addActionListener(this);

        miConsHistorico = new JMenuItem("Histórico");
        mnConsultas.add(miConsHistorico);
        miConsHistorico.addActionListener(this);

        mnControle = new JMenu("Controle");

        miNovaOS = new JMenuItem("Nova OS");
        mnControle.add(miNovaOS);
        miNovaOS.addActionListener(this);

        miOrcamentos = new JMenuItem("Orçamentos");
        mnControle.add(miOrcamentos);
        miOrcamentos.addActionListener(this);

        mnFeramentas = new JMenu("Ferramentas");

        mnAjuda = new JMenu("Ajuda");

        menuBar.add(mnArquivo);
        menuBar.add(mnCadastros);
        menuBar.add(mnConsultas);
        menuBar.add(mnControle);
        menuBar.add(mnFeramentas);
        menuBar.add(mnAjuda);
        setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evento) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar a conecção", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void encerar() {
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja encerrar o programa", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resultado == 0) {
            try {
                con.close();
                System.exit(0);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conecção com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirHistorico() throws Exception {
        NovaOSControl controlNovaOS = new NovaOSControl(daoFactory, con);
        if (controlNovaOS.isOSVazio()) {
            throw new Exception("Não há ordens de serviços cadastradas");
        }
        final JTextField tfOpcao = new JTextField();
        tfOpcao.setDocument(new CamposInt());
        final MaskFormatter mascara = new MaskFormatter();
        final JFormattedTextField tfEntrada = new JFormattedTextField(mascara);
        tfEntrada.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent evento) {
                if (!"".equals(tfOpcao.getText())) {
                    if (Integer.parseInt(tfOpcao.getText()) == 1) {
                        mudaMascara(mascara, tfEntrada, "###.###.###-##");
                    } else {
                        if (Integer.parseInt(tfOpcao.getText()) == 2) {
                            mudaMascara(mascara, tfEntrada, "##.###.###/####-##");
                        } else {
                            if (Integer.parseInt(tfOpcao.getText()) == 3) {
                                mudaMascara(mascara, tfEntrada, "***-####");
                            } else {
                                mudaMascara(mascara, tfEntrada, "");
                            }
                        }
                    }
                }
            }
        });
        final Object[] mensagem = {"Digite 1 para consultar histórico do cliente pelo CPF,\nDigite 2 para consultar histórico do cliente pelo CNPJ ou\nDigite 3 para consultar histórico do veículo pela placa", tfOpcao, tfEntrada};
        final String[] opcoes = {"OK", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Cliente / Veículo", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        if (result == 0) {
            if (!"".equals(tfOpcao.getText()) && !"".equals(tfEntrada.getText())) {
                if (Integer.parseInt(tfOpcao.getText()) == 1) {
                    abrir(0);
                } else {
                    if (Integer.parseInt(tfOpcao.getText()) == 2) {
                        abrir(0);
                    } else {
                        if (Integer.parseInt(tfOpcao.getText()) == 3) {
                            abrir(1);
                        }
                    }
                }
            } else {
                throw new Exception("Entre com uma das opções no primeiro campo\nInforme a entrada correspondente no segundo campo");
            }
        }
    }

    private void mudaMascara(MaskFormatter mascara, JFormattedTextField tfEntrada, String str) {
        try {
            tfEntrada.setFormatterFactory(null); // desabilita a mascara aplicada
            mascara.setMask(str); //configura a nova mascara
            tfEntrada.setFormatterFactory(new DefaultFormatterFactory(mascara)); //aplica a nova mascara  
            tfEntrada.setValue(null);
        } catch (ParseException ex) {
        }
    }

    private void abrir(int flag) {
        HistoricoOS historico = new HistoricoOS(daoFactory, con, flag);
        historico.setModal(true);
        historico.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == miClientes || evento.getSource() == btClientes) {
            CadasCliente cliente = new CadasCliente(daoFactory, con);
            cliente.setModal(true);
            cliente.setVisible(true);
            return;
        }
        if (evento.getSource() == miVeiculos) {
            CadasVeiculo veiculo = new CadasVeiculo(daoFactory, con);
            veiculo.setModal(true);
            veiculo.setVisible(true);
            return;
        }
        if (evento.getSource() == miConsCliente) {
            ClienteControl controle = new ClienteControl(daoFactory, con);
            try {
                if (controle.isClieVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    PesquisaClientes pesquisaClie = new PesquisaClientes(controle);
                    pesquisaClie.setModal(true);
                    pesquisaClie.setVisible(true);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == miConsVeiculo) {
            VeiculoControl controle = new VeiculoControl(daoFactory, con);
            try {
                if (controle.isVeiVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há veículos cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    PesquisaVeiculo pesquisaVei = new PesquisaVeiculo(controle);
                    pesquisaVei.setModal(true);
                    pesquisaVei.setVisible(true);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == miNovaOS || evento.getSource() == btOS) {
            NovaOS novaOS = new NovaOS(daoFactory, con);
            novaOS.setModal(true);
            novaOS.setVisible(true);
            return;
        }
        if (evento.getSource() == miFuncionarios) {
            CadasFuncionario funcionario = new CadasFuncionario(daoFactory, con);
            funcionario.setModal(true);
            funcionario.setVisible(true);
            return;
        }
        if (evento.getSource() == miConsFuncionario) {
            FuncionarioControl controle = new FuncionarioControl(daoFactory, con);
            try {
                if (controle.isFuncVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    PesquisaFuncionario pesquisaFunc = new PesquisaFuncionario(controle);
                    pesquisaFunc.setModal(true);
                    pesquisaFunc.setVisible(true);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == miSair) {
            encerar();
        }
        if (evento.getSource() == miConsHistorico || evento.getSource() == btHistorico) {
            try {
                abrirHistorico();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
