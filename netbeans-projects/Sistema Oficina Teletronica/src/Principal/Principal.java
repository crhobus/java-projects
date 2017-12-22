package Principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clientes.CadasClientes;
import Clientes.ClienteControl;
import Clientes.PesquisaClientes;
import ConsultaHistoricoOS.AbrirHistorico;
import Fornecedores.CadasFornecedores;
import Fornecedores.FornecedorControl;
import Fornecedores.PesquisaFornecedores;
import Funcionarios.CadasFuncionarios;
import Funcionarios.FuncionarioControl;
import Funcionarios.PesquisaFuncionarios;
import LoginAcesso.RedefinirSenha;
import OrdensServicos.NovaOS;
import OrdensServicos.NovaOSControl;
import OrdensServicos.PesquisaOrdemServico;
import Produtos.CadasProdutos;
import Produtos.PesquisaProdutos;
import Produtos.ProdutoControl;
import Relatorios.RelatorioOS;
import Relatorios.RelatorioVendas;
import Vendas.NovaVenda;
import Vendas.NovaVendaControl;
import Vendas.PesquisaVendas;

public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 7607781422526700084L;
    private JMenu mnSistema, mnCadastros, mnConsultas, mnControle;
    private JMenuItem miClientes, miProdutos, miConsCliente,
            miConsOS, miConsHistoricoOS, miNovaOS, miFuncionarios,
            miConsFuncionario, miSair, miNovaVenda, miRelatorioOS, miRelatorioVendas, miConsEstoque, miConsVendas,
            miFornecedores, miConsFornecedor, miRedefinirSenha;
    private JButton btClientes, btConsultasOS, btOS, btHistorico;
    private Connection con;

    public Principal(Connection con) {
        super("OFICINA E COMÉRCIO TELETRÔNICA LTDA");
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
        btClientes.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btClientes.setToolTipText("Cadastro de Clientes");
        panel1.add(btClientes);
        btClientes.addActionListener(this);

        Icon icConsultas = new ImageIcon("Consultas.jpg");
        btConsultasOS = new JButton(icConsultas);
        btConsultasOS.setPreferredSize(new Dimension(30, 30));
        btConsultasOS.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultasOS.setToolTipText("Consulta Ordens de Serviços");
        panel1.add(btConsultasOS);
        btConsultasOS.addActionListener(this);

        Icon icGerarOS = new ImageIcon("GerarOS.jpg");
        btOS = new JButton(icGerarOS);
        btOS.setPreferredSize(new Dimension(30, 30));
        btOS.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOS.setToolTipText("Nova Ordem de Serviço");
        panel1.add(btOS);
        btOS.addActionListener(this);

        Icon icHistorico = new ImageIcon("Historico.jpg");
        btHistorico = new JButton(icHistorico);
        btHistorico.setPreferredSize(new Dimension(30, 30));
        btHistorico.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btHistorico.setToolTipText("Consulta Histórico das Ordens de Serviços");
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

        mnSistema = new JMenu("Sistema");

        miRedefinirSenha = new JMenuItem("Redefinir Senha");
        mnSistema.add(miRedefinirSenha);
        miRedefinirSenha.addActionListener(this);

        miRelatorioOS = new JMenuItem("Relatório OS");
        mnSistema.add(miRelatorioOS);
        miRelatorioOS.addActionListener(this);

        miRelatorioVendas = new JMenuItem("Relatório Vendas");
        mnSistema.add(miRelatorioVendas);
        miRelatorioVendas.addActionListener(this);

        miSair = new JMenuItem("Sair");
        mnSistema.add(miSair);
        miSair.addActionListener(this);

        mnCadastros = new JMenu("Cadastros");

        miClientes = new JMenuItem("Clientes");
        mnCadastros.add(miClientes);
        miClientes.addActionListener(this);

        miProdutos = new JMenuItem("Produtos");
        mnCadastros.add(miProdutos);
        miProdutos.addActionListener(this);

        miFuncionarios = new JMenuItem("Funcionários");
        mnCadastros.add(miFuncionarios);
        miFuncionarios.addActionListener(this);

        miFornecedores = new JMenuItem("Fornecedores");
        mnCadastros.add(miFornecedores);
        miFornecedores.addActionListener(this);

        mnConsultas = new JMenu("Consultas");

        miConsCliente = new JMenuItem("Clientes");
        mnConsultas.add(miConsCliente);
        miConsCliente.addActionListener(this);

        miConsFuncionario = new JMenuItem("Funcionários");
        mnConsultas.add(miConsFuncionario);
        miConsFuncionario.addActionListener(this);

        miConsFornecedor = new JMenuItem("Fornecedores");
        mnConsultas.add(miConsFornecedor);
        miConsFornecedor.addActionListener(this);

        miConsEstoque = new JMenuItem("Estoque");
        mnConsultas.add(miConsEstoque);
        miConsEstoque.addActionListener(this);

        miConsOS = new JMenuItem("Ordem de Serviço");
        mnConsultas.add(miConsOS);
        miConsOS.addActionListener(this);

        miConsVendas = new JMenuItem("Vendas");
        mnConsultas.add(miConsVendas);
        miConsVendas.addActionListener(this);

        miConsHistoricoOS = new JMenuItem("Histórico OS");
        mnConsultas.add(miConsHistoricoOS);
        miConsHistoricoOS.addActionListener(this);

        mnControle = new JMenu("Controle");

        miNovaOS = new JMenuItem("Nova OS");
        mnControle.add(miNovaOS);
        miNovaOS.addActionListener(this);

        miNovaVenda = new JMenuItem("Nova Venda");
        mnControle.add(miNovaVenda);
        miNovaVenda.addActionListener(this);

        menuBar.add(mnSistema);
        menuBar.add(mnCadastros);
        menuBar.add(mnConsultas);
        menuBar.add(mnControle);
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

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == miClientes || evento.getSource() == btClientes) {
            CadasClientes cliente = new CadasClientes(con);
            cliente.setModal(true);
            cliente.setVisible(true);
            return;
        }
        if (evento.getSource() == miProdutos) {
            CadasProdutos produto = new CadasProdutos(con);
            produto.setModal(true);
            produto.setVisible(true);
            return;
        }
        if (evento.getSource() == miConsCliente) {
            ClienteControl controle = new ClienteControl(con);
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
        if (evento.getSource() == miConsEstoque) {
            ProdutoControl controle = new ProdutoControl(con);
            try {
                if (controle.isProdVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há produtos cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    PesquisaProdutos pesquisaProd = new PesquisaProdutos(controle);
                    pesquisaProd.setModal(true);
                    pesquisaProd.setVisible(true);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == miNovaOS || evento.getSource() == btOS) {
            NovaOS novaOS = new NovaOS(con);
            novaOS.setModal(true);
            novaOS.setVisible(true);
            return;
        }
        if (evento.getSource() == miFuncionarios) {
            CadasFuncionarios funcionario = new CadasFuncionarios(con);
            funcionario.setModal(true);
            funcionario.setVisible(true);
            return;
        }
        if (evento.getSource() == miConsFuncionario) {
            FuncionarioControl controle = new FuncionarioControl(con);
            try {
                if (controle.isFuncVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    PesquisaFuncionarios pesquisaFunc = new PesquisaFuncionarios(controle);
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
        if (evento.getSource() == miConsHistoricoOS || evento.getSource() == btHistorico) {
            try {
                new AbrirHistorico(con);
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miNovaVenda) {
            NovaVenda novaVenda = new NovaVenda(con);
            novaVenda.setModal(true);
            novaVenda.setVisible(true);
            return;
        }
        if (evento.getSource() == miFornecedores) {
            CadasFornecedores fornecedor = new CadasFornecedores(con);
            fornecedor.setModal(true);
            fornecedor.setVisible(true);
            return;
        }
        if (evento.getSource() == miConsFornecedor) {
            FornecedorControl controle = new FornecedorControl(con);
            try {
                if (controle.isFornVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há fornecedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    PesquisaFornecedores pesquisaForn = new PesquisaFornecedores(controle);
                    pesquisaForn.setModal(true);
                    pesquisaForn.setVisible(true);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == miConsOS || evento.getSource() == btConsultasOS) {
            NovaOSControl controle = new NovaOSControl(con);
            try {
                if (controle.isOSVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há ordens de serviços cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    PesquisaOrdemServico pesquisaOS = new PesquisaOrdemServico(controle);
                    pesquisaOS.setModal(true);
                    pesquisaOS.setVisible(true);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == miConsVendas) {
            NovaVendaControl controle = new NovaVendaControl(con);
            try {
                if (controle.isVendaVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há vendas cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    PesquisaVendas pesquisaVendas = new PesquisaVendas(controle);
                    pesquisaVendas.setModal(true);
                    pesquisaVendas.setVisible(true);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == miRelatorioOS) {
            try {
                NovaOSControl controleOS = new NovaOSControl(con);
                if (controleOS.isOSVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há ordens de serviços cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                FuncionarioControl controleFunc = new FuncionarioControl(con);
                if (controleFunc.isFuncVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                RelatorioOS relatorio = new RelatorioOS(controleOS, controleFunc);
                relatorio.setModal(true);
                relatorio.setVisible(true);
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == miRedefinirSenha) {
            RedefinirSenha redefinir = new RedefinirSenha(con);
            redefinir.setModal(true);
            redefinir.setVisible(true);
            return;
        }
        if (evento.getSource() == miRelatorioVendas) {
            try {
                NovaVendaControl controleVendas = new NovaVendaControl(con);
                if (controleVendas.isVendaVazio()) {
                    JOptionPane.showMessageDialog(null, "Não há vandas cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                RelatorioVendas relatorio = new RelatorioVendas(con, controleVendas);
                relatorio.setModal(true);
                relatorio.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
