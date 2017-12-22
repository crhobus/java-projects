
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.net.URI;

public class Principal extends JFrame {

    private ArrayList<ArrayAgenda> lista = new ArrayList();
    private JMenuBar Menubar;
    private JMenu Arquivo, Cadastros, Atendimento, ControleFuncionario, Relatorios, Consulta;
    private JMenuItem Novavenda, Pedido, Orcamentos, Pagamentos, Estoque, Contatos, Financeiro, Sair, Usuario, Funcionarios, Clientes, Fornecedor, Transportadora, Produtos, Vendedor, Setor, Cidade, ConsultaVendas, ConsultaPedidos;
    private JButton Pesquisa, AbrirAgenda, bt_Internet, bt_Abrir, bt_Email, bt_Estoque;
    private final DefaultTableModel Modelo;
    private Desktop desktop;
    private Principal principal;

    Principal() {
        super("Sistema Java");
        principal = this;
        Container tela = getContentPane();
        tela.setLayout(null);
        Color cor = new Color(220, 220, 220);//Bege
        JPanel Painel1 = new JPanel();
        Painel1.setBackground(cor);
        Painel1.setLayout(null);
        Painel1.setBounds(5, 5, 975, 41);
        Painel1.setBorder(BorderFactory.createTitledBorder(""));
        tela.add(Painel1);
        TrataEventos trata = new TrataEventos();
        LerArquivo();

        Icon figura1 = new ImageIcon("java.png");//imagem qualquer
        JButton bt_figura1 = new JButton(figura1);//imagem adicionada ao botão
        bt_figura1.setBounds(980, 5, 280, 50);
        tela.add(bt_figura1);

        Icon figura2 = new ImageIcon("java2.jpg");//imagem qualquer
        JButton bt_figura2 = new JButton(figura2);//imagem adicionada ao botão
        bt_figura2.setBounds(980, 51, 280, 200);
        tela.add(bt_figura2);

        Pesquisa = new JButton("...");
        Pesquisa.setBounds(80, 61, 35, 26);
        tela.add(Pesquisa);
        Pesquisa.addActionListener(trata);

        Icon figura3 = new ImageIcon("imgagenda.jpg");//imagem qualquer
        AbrirAgenda = new JButton(figura3);
        AbrirAgenda.setBounds(10, 5, 30, 30);
        Painel1.add(AbrirAgenda);
        AbrirAgenda.addActionListener(trata);

        Icon figura4 = new ImageIcon("internet.jpg");//imagem qualquer
        bt_Internet = new JButton(figura4);
        bt_Internet.setBounds(56, 5, 30, 30);
        Painel1.add(bt_Internet);
        bt_Internet.addActionListener(trata);

        Icon figura5 = new ImageIcon("abrir.jpg");//imagem qualquer
        bt_Abrir = new JButton(figura5);
        bt_Abrir.setBounds(106, 5, 30, 30);
        Painel1.add(bt_Abrir);
        bt_Abrir.addActionListener(trata);

        Icon figura6 = new ImageIcon("email.jpg");//imagem qualquer
        bt_Email = new JButton(figura6);
        bt_Email.setBounds(156, 5, 30, 30);
        Painel1.add(bt_Email);
        bt_Email.addActionListener(trata);

        Icon figura7 = new ImageIcon("Estoque.jpg");//imagem qualquer
        bt_Estoque = new JButton(figura7);
        bt_Estoque.setBounds(206, 5, 30, 30);
        Painel1.add(bt_Estoque);
        bt_Estoque.addActionListener(trata);

        JLabel Agenda = new JLabel("Agenda:");
        Agenda.setBounds(10, 65, 100, 20);
        tela.add(Agenda);

        Modelo = new DefaultTableModel();
        JTable Tabela = new JTable(Modelo);//Constrói a tabela
        Modelo.addColumn("Codigo");//Cria colunas
        Modelo.addColumn("Nome");
        Modelo.addColumn("Data");
        Modelo.addColumn("Horario");
        Modelo.addColumn("Descricão");
        JScrollPane rolagem = new JScrollPane(Tabela);
        rolagem.setBounds(10, 100, 960, 500);
        tela.add(rolagem);
        RecuperarAgenda();

        Menubar = new JMenuBar();

        Arquivo = new JMenu("Arquivo");
        Novavenda = new JMenuItem("Nova Venda     ");
        Arquivo.add(Novavenda);
        Novavenda.addActionListener(trata);

        Pedido = new JMenuItem("Novo Pedido     ");
        Arquivo.add(Pedido);
        Pedido.addActionListener(trata);

        Orcamentos = new JMenuItem("Orçamentos");
        Arquivo.add(Orcamentos);
        Orcamentos.addActionListener(trata);

        Pagamentos = new JMenuItem("Pagamentos");
        Arquivo.add(Pagamentos);
        Pagamentos.addActionListener(trata);

        Estoque = new JMenuItem("Estoque     ");
        Arquivo.add(Estoque);
        Estoque.addActionListener(trata);

        Consulta = new JMenu("Consulta     ");
        Arquivo.add(Consulta);

        ConsultaVendas = new JMenuItem("Vendas");
        Consulta.add(ConsultaVendas);
        ConsultaVendas.addActionListener(trata);

        ConsultaPedidos = new JMenuItem("Pedidos");
        Consulta.add(ConsultaPedidos);
        ConsultaPedidos.addActionListener(trata);

        Contatos = new JMenuItem("Contatos     ");
        Arquivo.add(Contatos);
        Contatos.addActionListener(trata);

        Financeiro = new JMenuItem("Financeiro     ");
        Arquivo.add(Financeiro);
        Financeiro.addActionListener(trata);

        Sair = new JMenuItem("Sair");
        Arquivo.add(Sair);
        Sair.addActionListener(trata);

        Cadastros = new JMenu("Cadastros");
        Usuario = new JMenuItem("Usuario");
        Cadastros.add(Usuario);
        Usuario.addActionListener(trata);

        Funcionarios = new JMenuItem("Funcionarios");
        Cadastros.add(Funcionarios);
        Funcionarios.addActionListener(trata);

        Vendedor = new JMenuItem("Vendedor");
        Cadastros.add(Vendedor);
        Vendedor.addActionListener(trata);

        Clientes = new JMenuItem("Clientes");
        Cadastros.add(Clientes);
        Clientes.addActionListener(trata);

        Cidade = new JMenuItem("Cidade");
        Cadastros.add(Cidade);
        Cidade.addActionListener(trata);

        Setor = new JMenuItem("Setor");
        Cadastros.add(Setor);
        Setor.addActionListener(trata);

        Fornecedor = new JMenuItem("Fornecedores");
        Cadastros.add(Fornecedor);
        Fornecedor.addActionListener(trata);

        Transportadora = new JMenuItem("Transportadora");
        Cadastros.add(Transportadora);
        Transportadora.addActionListener(trata);

        Produtos = new JMenuItem("Produtos");
        Cadastros.add(Produtos);
        Produtos.addActionListener(trata);

        Atendimento = new JMenu("Atendimento");

        ControleFuncionario = new JMenu("Controle de Funcionários");

        Relatorios = new JMenu("Relatórios");

        Menubar.add(Arquivo);
        Menubar.add(Cadastros);
        Menubar.add(Atendimento);
        Menubar.add(ControleFuncionario);
        Menubar.add(Relatorios);
        setJMenuBar(Menubar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);//Iniciar a Tela Maximizado
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

    public void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Agenda.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayAgenda>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RecuperarAgenda() {
        LerArquivo();
        if (Modelo.getRowCount() != 0) {//Verifica se há linhas na tabela
            for (int i = 0; i < Modelo.getRowCount(); i++) {//Contador
                Modelo.removeRow(i);//Exclui linhas
                i--;
            }
        }
        for (int i = 0; i < lista.size(); i++) {
            ArrayAgenda n = lista.get(i);
            Modelo.addRow(new Object[]{n.getCodigo(), n.getNome(), n.getData(), n.getHora(), n.getDescricao()});//Adiciona linha na tabela
        }
    }

    /*protected void AdicionaLinha() {
        ArrayAgenda n = lista.get(lista.size() - 1);
        Modelo.addRow(new Object[]{n.getCodigo(), n.getNome(), n.getData(), n.getHora(), n.getDescricao()});//Adiciona linha na tabela
    }*/

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == Usuario) {
                Usuario aux = new Usuario();
            }
            if (evento.getSource() == Funcionarios) {
                Funcionarios aux = new Funcionarios();
            }
            if (evento.getSource() == Clientes) {
                Cliente aux = new Cliente();
            }
            if (evento.getSource() == Fornecedor) {
                Fornecedor aux = new Fornecedor();
            }
            if (evento.getSource() == Transportadora) {
                Transportadora aux = new Transportadora();
            }
            if (evento.getSource() == Produtos) {
                Produtos aux = new Produtos();
            }
            if (evento.getSource() == Novavenda) {
                NovaVenda aux = new NovaVenda();
            }
            if (evento.getSource() == Sair) {
                System.exit(0);
            }
            if (evento.getSource() == AbrirAgenda) {
                Agenda aux = new Agenda(principal);
            }
            if (evento.getSource() == ConsultaVendas) {
                ConsultaVenda aux = new ConsultaVenda();
            }
            if (evento.getSource() == ConsultaPedidos) {
                ConsultaPedido aux = new ConsultaPedido();
            }
            if (evento.getSource() == Pedido) {
                Pedidos aux = new Pedidos();
            }
            if (evento.getSource() == Vendedor) {
                Vendedor aux = new Vendedor();
            }
            if (evento.getSource() == Cidade) {
                Cidade aux = new Cidade();
            }
            if (evento.getSource() == Setor) {
                Setor aux = new Setor();
            }
            if (evento.getSource() == bt_Internet) {
                Internet();
            }
            if (evento.getSource() == bt_Abrir) {
                Abrir();
            }
            if (evento.getSource() == bt_Email) {
                Email();
            }
            if (evento.getSource() == Estoque) {
                Estoque aux = new Estoque();
            }
            if (evento.getSource() == bt_Estoque) {
                Estoque aux = new Estoque();
            }
            if (evento.getSource() == Pagamentos) {
                Pagamentos aux = new Pagamentos();
            }
        }
    }
}

