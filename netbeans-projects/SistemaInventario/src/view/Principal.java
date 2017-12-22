package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dbOracle.ChamadaDAO;
import dbOracle.EmpresaDAO;
import dbOracle.FornecedorDAO;
import dbOracle.FuncionarioDAO;
import dbOracle.PatrimonioDAO;

import view.ambiente.CadasAmbiente;
import view.chamada.ConsultaChamada;
import view.chamada.NovaChamada;
import view.defeito.CadasDefeito;
import view.empresa.CadasEmpresa;
import view.empresa.ConsultaEmpresa;
import view.endereco.CadasEndereco;
import view.fornecedor.CadasFornecedor;
import view.fornecedor.ConsultaFornecedor;
import view.funcionario.CadasFuncionario;
import view.funcionario.ConsultaFuncionario;
import view.patrimonio.CadasPatrimonio;
import view.patrimonio.ConsultaPatrimonio;

public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JMenuItem miCadasFunc, miCadasForn, miCadasEmp, miCadasEnd,
            miCadasPat, miCadasAmb, miSair, miChamada, miConsFunc, miConsForn,
            miConsEmp, miConsPatr, miConsCha, miVersao, miCadasDef;
    private JLabel lbHorarioSistema;
    private String permissao;
    private Connection con;

    public Principal(Connection con, String usuario, String permissao) {
        super("Sistema Iventário");
        this.con = con;
        this.permissao = permissao;
        initComponents(usuario);
    }

    private void initComponents(String usuario) {
        setLayout(new BorderLayout());

        JMenu mnSistema = new JMenu("Sistema");

        miChamada = new JMenuItem("Chamada");
        miChamada.addActionListener(this);
        mnSistema.add(miChamada);

        miSair = new JMenuItem("Sair");
        miSair.addActionListener(this);
        mnSistema.add(miSair);

        JMenu mnCadastros = new JMenu("Cadastros");

        miCadasEnd = new JMenuItem("Endereço");
        miCadasEnd.addActionListener(this);
        mnCadastros.add(miCadasEnd);

        miCadasFunc = new JMenuItem("Funcionário");
        miCadasFunc.addActionListener(this);
        mnCadastros.add(miCadasFunc);

        miCadasForn = new JMenuItem("Fornecedor");
        miCadasForn.addActionListener(this);
        mnCadastros.add(miCadasForn);

        miCadasEmp = new JMenuItem("Empresa");
        miCadasEmp.addActionListener(this);
        mnCadastros.add(miCadasEmp);

        miCadasAmb = new JMenuItem("Ambiente");
        miCadasAmb.addActionListener(this);
        mnCadastros.add(miCadasAmb);

        miCadasDef = new JMenuItem("Defeito");
        miCadasDef.addActionListener(this);
        mnCadastros.add(miCadasDef);

        miCadasPat = new JMenuItem("Patrimônio");
        miCadasPat.addActionListener(this);
        mnCadastros.add(miCadasPat);

        JMenu mnConsultas = new JMenu("Consultas");

        miConsFunc = new JMenuItem("Funcionários");
        miConsFunc.addActionListener(this);
        mnConsultas.add(miConsFunc);

        miConsForn = new JMenuItem("Fornecedores");
        miConsForn.addActionListener(this);
        mnConsultas.add(miConsForn);

        miConsEmp = new JMenuItem("Empresas");
        miConsEmp.addActionListener(this);
        mnConsultas.add(miConsEmp);

        miConsPatr = new JMenuItem("Patrimônios");
        miConsPatr.addActionListener(this);
        mnConsultas.add(miConsPatr);

        miConsCha = new JMenuItem("Chamadas");
        miConsCha.addActionListener(this);
        mnConsultas.add(miConsCha);

        JMenu mnUtilitarios = new JMenu("Utilitários");

        miVersao = new JMenuItem("Versão");
        miVersao.addActionListener(this);
        mnUtilitarios.add(miVersao);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mnSistema);
        menuBar.add(mnCadastros);
        menuBar.add(mnConsultas);
        menuBar.add(mnUtilitarios);
        setJMenuBar(menuBar);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelBotoes.setBorder(BorderFactory.createTitledBorder(""));
        add(panelBotoes, BorderLayout.NORTH);

        PanelImgFundo panelImg = new PanelImgFundo("Fundo.png");
        panelImg.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelImg.setBorder(BorderFactory.createTitledBorder(""));
        add(panelImg, BorderLayout.CENTER);

        JPanel panelPersistencia = new JPanel();
        panelPersistencia.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelPersistencia.setBorder(BorderFactory.createTitledBorder(""));
        panelPersistencia.setPreferredSize(new Dimension(350, 40));

        JLabel lbPersistencia = new JLabel("Persistência de dados");
        panelPersistencia.add(lbPersistencia);

        JTextField tfPersistencia = new JTextField("Banco de Dados: Oracle");
        tfPersistencia.setEditable(false);
        tfPersistencia.setBackground(Color.WHITE);
        tfPersistencia.setPreferredSize(new Dimension(160, 22));
        tfPersistencia.addActionListener(this);
        panelPersistencia.add(tfPersistencia);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelUsuario.setBorder(BorderFactory.createTitledBorder(""));
        panelUsuario.setPreferredSize(new Dimension(310, 40));

        JLabel lbUsuario = new JLabel("Usuário: " + usuario);
        lbUsuario.setPreferredSize(new Dimension(150, 14));
        panelUsuario.add(lbUsuario);

        JLabel lbPermissao = new JLabel("Permissão: " + permissao);
        panelUsuario.add(lbPermissao);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(""));

        lbHorarioSistema = new JLabel("");
        lbHorarioSistema.setFont(new Font("Arial", Font.BOLD, 15));
        ThreadHoraSistema thread = new ThreadHoraSistema(this, lbHorarioSistema);
        thread.start();
        panel.add(lbHorarioSistema);

        JPanel panelBarraStatus = new JPanel();
        panelBarraStatus.setBorder(BorderFactory.createTitledBorder(""));
        panelBarraStatus.setLayout(new BorderLayout());
        panelBarraStatus.add(panelUsuario, BorderLayout.WEST);
        panelBarraStatus.add(panel, BorderLayout.CENTER);
        panelBarraStatus.add(panelPersistencia, BorderLayout.EAST);
        add(panelBarraStatus, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evento) {
                sair();
            }
        });

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setHorarioSistema(String horario) {
        lbHorarioSistema.setText(horario);
    }

    private void sair() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema", "Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                con.close();
                System.exit(JFrame.EXIT_ON_CLOSE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro de conexão", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void verificaPermissaoAdmin() throws Exception {
        if (!"Administrador".equals(permissao)) {
            throw new Exception("Você não tem permissão suficiente para efetuar o cadastro,\nContate o administrador do sistema");
        }
    }

    private void verificaPermissaoAdminSuport() throws Exception {
        if ("Outros".equals(permissao)) {
            throw new Exception("Você não tem permissão suficiente para efetuar o cadastro,\nContate o administrador do sistema");
        }
    }

    private void cadasFuncionario() throws Exception {
        verificaPermissaoAdmin();
        CadasFuncionario func = new CadasFuncionario(con);
        func.setModal(true);
        func.setVisible(true);
    }

    private void cadasFornecedor() throws Exception {
        verificaPermissaoAdmin();
        CadasFornecedor forn = new CadasFornecedor(con);
        forn.setModal(true);
        forn.setVisible(true);
    }

    private void cadasEmpresa() throws Exception {
        verificaPermissaoAdmin();
        CadasEmpresa emp = new CadasEmpresa(con);
        emp.setModal(true);
        emp.setVisible(true);
    }

    private void cadasEndereco() throws Exception {
        verificaPermissaoAdmin();
        CadasEndereco end = new CadasEndereco(con);
        end.setModal(true);
        end.setVisible(true);
    }

    private void cadasPatrimonio() throws Exception {
        verificaPermissaoAdmin();
        CadasPatrimonio pat = new CadasPatrimonio(con);
        pat.setModal(true);
        pat.setVisible(true);
    }

    private void cadasDefeito() throws Exception {
        verificaPermissaoAdminSuport();
        CadasDefeito def = new CadasDefeito(con);
        def.setModal(true);
        def.setVisible(true);
    }

    private void cadasAmbiente() throws Exception {
        verificaPermissaoAdmin();
        CadasAmbiente amb = new CadasAmbiente(con);
        amb.setModal(true);
        amb.setVisible(true);
    }

    private void chamada() throws Exception {
        verificaPermissaoAdminSuport();
        NovaChamada cha = new NovaChamada(con);
        cha.setModal(true);
        cha.setVisible(true);
    }

    private void consFuncionarios() throws Exception {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(con);
        if (funcionarioDAO.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados\nCadastre um funcionário primeiro");
        }
        ConsultaFuncionario funcionario = new ConsultaFuncionario(funcionarioDAO);
        funcionario.setModal(true);
        funcionario.setVisible(true);
    }

    private void consFornecedores() throws Exception {
        FornecedorDAO fornecedorDAO = new FornecedorDAO(con);
        if (fornecedorDAO.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados\nCadastre um fornecedor primeiro");
        }
        ConsultaFornecedor fornecedor = new ConsultaFornecedor(fornecedorDAO);
        fornecedor.setModal(true);
        fornecedor.setVisible(true);
    }

    private void consEmpresas() throws Exception {
        EmpresaDAO empresaDAO = new EmpresaDAO(con);
        if (empresaDAO.isEmpresaVazio()) {
            throw new Exception("Não há empresas cadastradas\nCadastre uma empresa primeiro");
        }
        ConsultaEmpresa empresa = new ConsultaEmpresa(empresaDAO);
        empresa.setModal(true);
        empresa.setVisible(true);
    }

    private void consPatrimonios() throws Exception {
        PatrimonioDAO patrimonioDAO = new PatrimonioDAO(con);
        if (patrimonioDAO.isPatrimonioVazio()) {
            throw new Exception("Não há patrimonios cadastrados\nCadastre um patrimonio primeiro");
        }
        ConsultaPatrimonio patrimonio = new ConsultaPatrimonio(patrimonioDAO);
        patrimonio.setModal(true);
        patrimonio.setVisible(true);
    }

    private void consChamadas() throws Exception {
        ChamadaDAO chamadaDAO = new ChamadaDAO(con);
        if (chamadaDAO.isChamadaVazio()) {
            throw new Exception("Não há chamadas cadastradas\nCadastre uma chamada primeiro");
        }
        ConsultaChamada chamada = new ConsultaChamada(chamadaDAO);
        chamada.setModal(true);
        chamada.setVisible(true);
    }

    private void versao() {
        Versao versao = new Versao();
        versao.setModal(true);
        versao.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == miCadasFunc) {
            try {
                cadasFuncionario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miCadasForn) {
            try {
                cadasFornecedor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miCadasEmp) {
            try {
                cadasEmpresa();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miCadasEnd) {
            try {
                cadasEndereco();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miCadasDef) {
            try {
                cadasDefeito();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miCadasPat) {
            try {
                cadasPatrimonio();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miCadasAmb) {
            try {
                cadasAmbiente();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miSair) {
            sair();
        }
        if (evento.getSource() == miChamada) {
            try {
                chamada();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsFunc) {
            try {
                consFuncionarios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsForn) {
            try {
                consFornecedores();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsEmp) {
            try {
                consEmpresas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsPatr) {
            try {
                consPatrimonios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsCha) {
            try {
                consChamadas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miVersao) {
            versao();
        }
    }
}
