package Principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Setor.CadasSetor;
import Transportadora.CadasTransportadora;
import Usuario.CadasUsuario;
import Vendedor.CadasVendedor;

import Cidade.CadasCidade;
import Cliente.CadasCliente;
import FormaPersistencia.FormaPersistencia;
import Fornecedor.CadasFornecedores;
import Funcionario.CadasFuncionario;

public class Principal extends JFrame implements ActionListener {

    private JMenu mnArquivo, mnCadastros;
    private JMenuItem miUsuario, miClientes, miCidade, miFuncionario, miSetor, miVendedor, miFornecedores,
            miTransportadora;
    private FormaPersistencia persistencia;
    private JButton btAgenda, btInternet, btAbrir, btEmail, btEstoque, btArquivos, btPlayer, btNovaVenda, btAjuda,
            btContatos, btPagamentos, btGerarOS;
    private JComboBox cbPercistencia;

    public Principal(String usuario, int permissao, FormaPersistencia persistencia) {
        super("Java System");
        this.persistencia = persistencia;
        initComponents(usuario, permissao);
    }

    private void initComponents(String usuario, int permissao) {
        setBackground(new Color(248, 248, 248));
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panel1.setBorder(BorderFactory.createTitledBorder(""));
        add(panel1, BorderLayout.NORTH);

        Icon icAgenda = new ImageIcon("Agenda.jpg");
        btAgenda = new JButton(icAgenda);
        btAgenda.setPreferredSize(new Dimension(30, 30));
        panel1.add(btAgenda);

        Icon icNovaVenda = new ImageIcon("NovaVenda.jpg");
        btNovaVenda = new JButton(icNovaVenda);
        btNovaVenda.setPreferredSize(new Dimension(30, 30));
        panel1.add(btNovaVenda);

        Icon icPagamentos = new ImageIcon("Pagamentos.jpg");
        btPagamentos = new JButton(icPagamentos);
        btPagamentos.setPreferredSize(new Dimension(30, 30));
        panel1.add(btPagamentos);

        Icon icGerarOS = new ImageIcon("GerarOS.jpg");
        btGerarOS = new JButton(icGerarOS);
        btGerarOS.setPreferredSize(new Dimension(30, 30));
        panel1.add(btGerarOS);

        Icon icInternet = new ImageIcon("internet.jpg");
        btInternet = new JButton(icInternet);
        btInternet.setPreferredSize(new Dimension(30, 30));
        panel1.add(btInternet);

        Icon icAbrir = new ImageIcon("abrir.jpg");
        btAbrir = new JButton(icAbrir);
        btAbrir.setPreferredSize(new Dimension(30, 30));
        panel1.add(btAbrir);

        Icon icEmail = new ImageIcon("email.jpg");
        btEmail = new JButton(icEmail);
        btEmail.setPreferredSize(new Dimension(30, 30));
        panel1.add(btEmail);

        Icon icEstoque = new ImageIcon("Estoque.jpg");
        btEstoque = new JButton(icEstoque);
        btEstoque.setPreferredSize(new Dimension(30, 30));
        panel1.add(btEstoque);

        Icon icArquivos = new ImageIcon("Arquivos.jpg");
        btArquivos = new JButton(icArquivos);
        btArquivos.setPreferredSize(new Dimension(30, 30));
        panel1.add(btArquivos);

        Icon icContatos = new ImageIcon("Contatos.jpg");
        btContatos = new JButton(icContatos);
        btContatos.setPreferredSize(new Dimension(30, 30));
        panel1.add(btContatos);

        Icon icPlayer = new ImageIcon("MidiaPlayer.jpg");
        btPlayer = new JButton(icPlayer);
        btPlayer.setPreferredSize(new Dimension(30, 30));
        panel1.add(btPlayer);

        Icon icAjuda = new ImageIcon("Ajuda.jpg");
        btAjuda = new JButton(icAjuda);
        btAjuda.setPreferredSize(new Dimension(30, 30));
        panel1.add(btAjuda);

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

        cbPercistencia = new JComboBox();
        cbPercistencia.addItem("Binario");
        cbPercistencia.addItem("Texto");
        cbPercistencia.setSelectedItem(persistencia.getDaoFactory().toString());
        cbPercistencia.setBackground(Color.WHITE);
        cbPercistencia.setPreferredSize(new Dimension(120, 22));
        cbPercistencia.addActionListener(this);
        panel3.add(cbPercistencia);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.setBorder(BorderFactory.createTitledBorder(""));
        panel4.setPreferredSize(new Dimension(350, 40));

        JLabel lbUsuario = new JLabel("Usuário: " + usuario);
        lbUsuario.setPreferredSize(new Dimension(150, 14));
        panel4.add(lbUsuario);

        JLabel lbPermissao = new JLabel("Permissão: " + permissao);
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

        mnCadastros = new JMenu("Cadastros");

        miUsuario = new JMenuItem("Usuários");
        mnCadastros.add(miUsuario);
        miUsuario.addActionListener(this);

        miFuncionario = new JMenuItem("Funcionarios");
        mnCadastros.add(miFuncionario);
        miFuncionario.addActionListener(this);

        miVendedor = new JMenuItem("Vendedor");
        mnCadastros.add(miVendedor);
        miVendedor.addActionListener(this);

        miClientes = new JMenuItem("Clientes");
        mnCadastros.add(miClientes);
        miClientes.addActionListener(this);

        miCidade = new JMenuItem("Cidade");
        mnCadastros.add(miCidade);
        miCidade.addActionListener(this);

        miSetor = new JMenuItem("Setor");
        mnCadastros.add(miSetor);
        miSetor.addActionListener(this);

        miFornecedores = new JMenuItem("Fornecedores");
        mnCadastros.add(miFornecedores);
        miFornecedores.addActionListener(this);

        miTransportadora = new JMenuItem("Transportadora");
        mnCadastros.add(miTransportadora);
        miTransportadora.addActionListener(this);

        menuBar.add(mnArquivo);
        menuBar.add(mnCadastros);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == miClientes) {
            CadasCliente cliente = new CadasCliente(persistencia.getDaoFactory());
            cliente.setModal(true);
            cliente.setVisible(true);
        }
        if (evento.getSource() == miUsuario) {
            CadasUsuario usuario = new CadasUsuario(persistencia.getDaoFactory());
            usuario.setModal(true);
            usuario.setVisible(true);
        }
        if (evento.getSource() == miFuncionario) {
            CadasFuncionario funcionario = new CadasFuncionario(persistencia.getDaoFactory());
            funcionario.setModal(true);
            funcionario.setVisible(true);
        }
        if (evento.getSource() == miCidade) {
            CadasCidade cidade = new CadasCidade(persistencia.getDaoFactory());
            cidade.setModal(true);
            cidade.setVisible(true);
        }
        if (evento.getSource() == miSetor) {
            CadasSetor setor = new CadasSetor(persistencia.getDaoFactory());
            setor.setModal(true);
            setor.setVisible(true);
        }
        if (evento.getSource() == miVendedor) {
            CadasVendedor vendedor = new CadasVendedor(persistencia.getDaoFactory());
            vendedor.setModal(true);
            vendedor.setVisible(true);
        }
        if (evento.getSource() == miFornecedores) {
            CadasFornecedores fornecedor = new CadasFornecedores(persistencia.getDaoFactory());
            fornecedor.setModal(true);
            fornecedor.setVisible(true);
        }
        if (evento.getSource() == miTransportadora) {
            CadasTransportadora transportadora = new CadasTransportadora(persistencia.getDaoFactory());
            transportadora.setModal(true);
            transportadora.setVisible(true);
        }
        if (evento.getSource() == cbPercistencia) {
            try {
                persistencia.criarDAO(cbPercistencia.getSelectedIndex());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro na gravação do arquivo de persistência", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
