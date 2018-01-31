package Sistema;

import Visao.Agenda.CadasAgenda;
import Visao.ArquivosDados.ArquivosDados;
import Visao.Cidade.CadasCidade;
import Visao.Cliente.CadasCliente;
import Visao.Contatos.CadasContatos;
import Visao.Empresa.CadasEmpresa;
import Visao.Estoque.Estoque;
import Visao.Fornecedores.CadasFornecedor;
import Visao.Funcionarios.CadasFuncionario;
import Visao.Pagamentos.NovoPagamento;
import Visao.Produto.CadasProduto;
import Visao.Setor.CadasSetor;
import Visao.Transportadora.CadasTransportadora;
import Visao.Usuario.CadasUsuario;
import Visao.Vendas.ConsultaVendas;
import Visao.Vendas.NovaVenda;
import Visao.Vendedor.CadasVendedor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class Principal extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu mnArquivo, mnCadastros, mnAtendimento, mnControleFuncionario, mnRelatorios, mnConsulta;
    private JMenuItem miNovaVenda, miPedido, miOrcamentos, miPagamentos, miEstoque, miContatos, miFinanceiro, miSair, miUsuario, miFuncionarios, miClientes, miFornecedor, miTransportadora, miProdutos, miVendedor, miSetor, miCidade, miConsultaVendas, miConsultaPedidos, miDados, miEmpresa, miAgenda;
    private JButton btPesquisa, btAbrirAgenda, btInternet, btAbrir, btEmail, btEstoque;
    private Desktop desktop;
    private Principal principal;

    public Principal() {
        super("Sistema Java");
        principal = this;
        Container tela = getContentPane();
        tela.setLayout(null);
        Color cor = new Color(220, 220, 220);//Bege
        JPanel painel1 = new JPanel();
        painel1.setBackground(cor);
        painel1.setLayout(null);
        painel1.setBounds(0, 0, 975, 41);
        painel1.setBorder(BorderFactory.createTitledBorder(""));
        tela.add(painel1);

        JPanel painel2 = new JPanel();
        painel2.setBackground(cor);
        painel2.setLayout(null);
        painel2.setBounds(976, 0, 303, 41);
        painel2.setBorder(BorderFactory.createTitledBorder(""));
        tela.add(painel2);

        ImageIcon img = new ImageIcon("Fundo.jpg");
        JPanel painel3 = new JPanel();
        painel3.setBackground(cor);
        painel3.add(new JLabel(img));
        painel3.setBounds(0, 42, 1280, 640);
        painel3.setBorder(BorderFactory.createTitledBorder(""));
        tela.add(painel3);

        JPanel painel4 = new JPanel();
        painel4.setBackground(cor);
        painel4.setLayout(null);
        painel4.setBounds(0, 683, 1280, 30);
        painel4.setBorder(BorderFactory.createTitledBorder(""));
        tela.add(painel4);

        Icon figura3 = new ImageIcon("imgagenda.jpg");//imagem qualquer
        btAbrirAgenda = new JButton(figura3);
        btAbrirAgenda.setBounds(10, 5, 30, 30);
        painel1.add(btAbrirAgenda);
        btAbrirAgenda.addActionListener(this);

        Icon icFigura4 = new ImageIcon("internet.jpg");//imagem qualquer
        btInternet = new JButton(icFigura4);
        btInternet.setBounds(56, 5, 30, 30);
        painel1.add(btInternet);
        btInternet.addActionListener(this);

        Icon icFigura5 = new ImageIcon("abrir.jpg");//imagem qualquer
        btAbrir = new JButton(icFigura5);
        btAbrir.setBounds(106, 5, 30, 30);
        painel1.add(btAbrir);
        btAbrir.addActionListener(this);

        Icon figura6 = new ImageIcon("email.jpg");//imagem qualquer
        btEmail = new JButton(figura6);
        btEmail.setBounds(156, 5, 30, 30);
        painel1.add(btEmail);
        btEmail.addActionListener(this);

        Icon icFigura7 = new ImageIcon("Estoque.jpg");//imagem qualquer
        btEstoque = new JButton(icFigura7);
        btEstoque.setBounds(206, 5, 30, 30);
        painel1.add(btEstoque);
        btEstoque.addActionListener(this);

        menuBar = new JMenuBar();

        mnArquivo = new JMenu("Arquivo");
        miNovaVenda = new JMenuItem("Nova Venda     ");
        mnArquivo.add(miNovaVenda);
        miNovaVenda.addActionListener(this);

        miPedido = new JMenuItem("Novo Pedido     ");
        mnArquivo.add(miPedido);
        miPedido.addActionListener(this);

        miOrcamentos = new JMenuItem("Orçamentos");
        mnArquivo.add(miOrcamentos);
        miOrcamentos.addActionListener(this);

        miPagamentos = new JMenuItem("Pagamentos");
        mnArquivo.add(miPagamentos);
        miPagamentos.addActionListener(this);

        miEstoque = new JMenuItem("Estoque     ");
        mnArquivo.add(miEstoque);
        miEstoque.addActionListener(this);

        mnConsulta = new JMenu("Consulta     ");
        mnArquivo.add(mnConsulta);

        miConsultaVendas = new JMenuItem("Vendas");
        mnConsulta.add(miConsultaVendas);
        miConsultaVendas.addActionListener(this);

        miConsultaPedidos = new JMenuItem("Pedidos");
        mnConsulta.add(miConsultaPedidos);
        miConsultaPedidos.addActionListener(this);

        miAgenda = new JMenuItem("Agenda     ");
        mnArquivo.add(miAgenda);
        miAgenda.addActionListener(this);

        miContatos = new JMenuItem("Contatos     ");
        mnArquivo.add(miContatos);
        miContatos.addActionListener(this);

        miFinanceiro = new JMenuItem("Financeiro     ");
        mnArquivo.add(miFinanceiro);
        miFinanceiro.addActionListener(this);

        miDados = new JMenuItem("Arquivos Dados");
        mnArquivo.add(miDados);
        miDados.addActionListener(this);

        miSair = new JMenuItem("Sair");
        mnArquivo.add(miSair);
        miSair.addActionListener(this);

        mnCadastros = new JMenu("Cadastros");
        miUsuario = new JMenuItem("Usuario");
        mnCadastros.add(miUsuario);
        miUsuario.addActionListener(this);

        miFuncionarios = new JMenuItem("Funcionarios");
        mnCadastros.add(miFuncionarios);
        miFuncionarios.addActionListener(this);

        miVendedor = new JMenuItem("Vendedor");
        mnCadastros.add(miVendedor);
        miVendedor.addActionListener(this);

        miEmpresa = new JMenuItem("Empresa");
        mnCadastros.add(miEmpresa);
        miEmpresa.addActionListener(this);

        miClientes = new JMenuItem("Clientes");
        mnCadastros.add(miClientes);
        miClientes.addActionListener(this);

        miCidade = new JMenuItem("Cidade");
        mnCadastros.add(miCidade);
        miCidade.addActionListener(this);

        miSetor = new JMenuItem("Setor");
        mnCadastros.add(miSetor);
        miSetor.addActionListener(this);

        miFornecedor = new JMenuItem("Fornecedores");
        mnCadastros.add(miFornecedor);
        miFornecedor.addActionListener(this);

        miTransportadora = new JMenuItem("Transportadora");
        mnCadastros.add(miTransportadora);
        miTransportadora.addActionListener(this);

        miProdutos = new JMenuItem("Produtos");
        mnCadastros.add(miProdutos);
        miProdutos.addActionListener(this);

        mnAtendimento = new JMenu("Atendimento");

        mnControleFuncionario = new JMenu("Controle de Funcionários");

        mnRelatorios = new JMenu("Relatórios");

        menuBar.add(mnArquivo);
        menuBar.add(mnCadastros);
        menuBar.add(mnAtendimento);
        menuBar.add(mnControleFuncionario);
        menuBar.add(mnRelatorios);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = getMaximumSize();
        setSize(d.width, d.height);
        setResizable(false);
        setVisible(true);
    }

    private void Internet() {
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        } else {
            JOptionPane.showMessageDialog(null, "Classe Desktop não é suportado", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            URI browseURI = new URI("www.msn.com.br");
            desktop.browse(browseURI);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Pagina não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Abrir() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                desktop.open(chooser.getSelectedFile().getAbsoluteFile());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void Email() {
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        } else {
            JOptionPane.showMessageDialog(null, "Classe Desktop não é suportado", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            URI mailURI = new URI("mailto:caio.rh@hotmail.com");
            desktop.mail(mailURI);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Pagina não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == miUsuario) {
            CadasUsuario usuario = new CadasUsuario();
            usuario.setModal(true);
            usuario.setVisible(true);
        }
        if (evento.getSource() == miFuncionarios) {
            CadasFuncionario func = new CadasFuncionario();
            func.setModal(true);
            func.setVisible(true);
        }
        if (evento.getSource() == miClientes) {
            CadasCliente cliente = new CadasCliente();
            cliente.setModal(true);
            cliente.setVisible(true);
        }
        if (evento.getSource() == miFornecedor) {
            CadasFornecedor fornecedor = new CadasFornecedor();
            fornecedor.setModal(true);
            fornecedor.setVisible(true);
        }
        if (evento.getSource() == miTransportadora) {
            CadasTransportadora transportadora = new CadasTransportadora();
            transportadora.setModal(true);
            transportadora.setVisible(true);
        }
        if (evento.getSource() == miProdutos) {
            CadasProduto produto = new CadasProduto();
            produto.setModal(true);
            produto.setVisible(true);
        }
        if (evento.getSource() == miNovaVenda) {
            NovaVenda vendas = new NovaVenda();
            vendas.setModal(true);
            vendas.setVisible(true);
        }
        if (evento.getSource() == miSair) {
            int resposta = JOptionPane.showConfirmDialog(null, "Encerrar Software?", "Finalizar", JOptionPane.YES_NO_OPTION);
            if (resposta == 0) {
                System.exit(0);
            }
        }
        if (evento.getSource() == btAbrirAgenda) {
        }
        if (evento.getSource() == miConsultaVendas) {
            ConsultaVendas consulta = new ConsultaVendas();
            consulta.setModal(true);
            consulta.setVisible(true);
        }
        if (evento.getSource() == miConsultaPedidos) {
        }
        if (evento.getSource() == miPedido) {
        }
        if (evento.getSource() == miVendedor) {
            CadasVendedor vendedor = new CadasVendedor();
            vendedor.setModal(true);
            vendedor.setVisible(true);
        }
        if (evento.getSource() == miCidade) {
            CadasCidade cidade = new CadasCidade();
            cidade.setModal(true);
            cidade.setVisible(true);
        }
        if (evento.getSource() == miSetor) {
            CadasSetor setor = new CadasSetor();
            setor.setModal(true);
            setor.setVisible(true);
        }
        if (evento.getSource() == btInternet) {
            Internet();
        }
        if (evento.getSource() == btAbrir) {
            Abrir();
        }
        if (evento.getSource() == btEmail) {
            Email();
        }
        if (evento.getSource() == miEstoque) {
            Estoque estoque = new Estoque();
            estoque.setModal(true);
            estoque.setVisible(true);
        }
        if (evento.getSource() == btEstoque) {
        }
        if (evento.getSource() == miPagamentos) {
            NovoPagamento pagamento = new NovoPagamento();
            pagamento.setModal(true);
            pagamento.setVisible(true);
        }
        if (evento.getSource() == miDados) {
            ArquivosDados arq = new ArquivosDados();
            arq.setModal(true);
            arq.setVisible(true);
        }
        if (evento.getSource() == miEmpresa) {
            CadasEmpresa empresa = new CadasEmpresa();
            empresa.setModal(true);
            empresa.setVisible(true);
        }
        if (evento.getSource() == miAgenda) {
            CadasAgenda agenda = new CadasAgenda();
            agenda.setModal(true);
            agenda.setVisible(true);
        }
        if (evento.getSource() == miContatos) {
            CadasContatos contatos = new CadasContatos();
            contatos.setModal(true);
            contatos.setVisible(true);
        }
    }
}


