package view.contatos;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.ContatosDAO;

import model.Contato;

import view.PanelComponentes;

// Cadastro de Contatos
public class CadasContatos extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfNome, tfEmail;
    private JButton btConsulta, btOk, btCancelar, btExcluir, btSair;
    private ContatosDAO contatosDAO;// Objeto que conecta as configurações de conexão ao banco de dados
    private int codContato;// Variável que recebe o código do contato que está em uso

    public CadasContatos(Connection con) {
        contatosDAO = new ContatosDAO(con);// Cria uma conexão com o banco de dados
        codContato = -1;
        initComponents();
    }

    // Cria os componentes gráficos
    private void initComponents() {
        setTitle("Cadastro de Contatos");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 414, 205);
        this.add(panel);

        panel.addJLabel("Nome", 50, 20, 50, 14);

        tfNome = panel.addJTextField(50, 38, 300, 20);

        btConsulta = panel.addJButton("...", "Consulta Contatos", 356, 35, 31, 26);
        btConsulta.addActionListener(this);

        panel.addJLabel("E-Mail", 50, 73, 50, 14);

        tfEmail = panel.addJTextField(50, 91, 300, 20);

        panel.addJSeparator(0, 136, 412, 3);

        btOk = panel.addJButtonOK(30, 158, 70, 26);
        btOk.addActionListener(this);

        btCancelar = panel.addJButtonCancelar(125, 158, 70, 26);
        btCancelar.addActionListener(this);

        btExcluir = panel.addJButtonExcuir(220, 158, 70, 26);
        btExcluir.addActionListener(this);

        btSair = panel.addJButtonSair(315, 158, 70, 26);
        btSair.addActionListener(this);

        // Substitui a tecla Tab por Enter nos campos
        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(428, 241);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // Limpa os campos e seta codigo para -1
    private void limparTela() {
        tfNome.setText("");
        tfEmail.setText("");
        codContato = -1;
    }

    // Verifica se está tudo preenchido e grava no banco de dados
    private void gravar() throws Exception {
        if ("".equals(tfNome.getText().trim())) {
            throw new Exception("Campo nome obrigatório não preenchido");
        }
        if ("".equals(tfEmail.getText().trim())) {
            throw new Exception("Campo e-mail obrigatório não preenchido");
        }
        Contato contato = new Contato();
        contato.setCodigo(codContato);
        contato.setNome(tfNome.getText());
        contato.setEmail(tfEmail.getText());
        int codContatoCadas = contatosDAO.getContatoCadastrado(tfEmail.getText());
        if (codContatoCadas != -1) {// Verifica se e-mail ja consta no banco de dados
            JOptionPane.showMessageDialog(null, "Este contato ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (contatosDAO.insertContato(contato)) {
                JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso", "Contato", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    // Realiza uma consulta de todos os contatos
    private void consulta() throws Exception {
        if (contatosDAO.isContatoVazio()) {
            throw new Exception("Não há contatos cadastrados\nCadastre um contato primeiro");
        }
        ConsultaContatos consulta = new ConsultaContatos(contatosDAO);
        consulta.setListener(new ListenerContatos() {

            @Override
            public void exibeContato(Contato contato) {// Exibe o contato na tela
                limparTela();
                codContato = contato.getCodigo();
                tfNome.setText(contato.getNome());
                tfEmail.setText(contato.getEmail());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    // Exclui o contato selecionado na tela
    private void excluir() throws Exception {
        if (contatosDAO.isContatoVazio()) {
            throw new Exception("Não há contatos cadastrados\nCadastre um contato primeiro");
        }
        if (contatosDAO.getContatoCadastrado(tfEmail.getText()) == -1) {
            throw new Exception("Entre com um contato");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse contato", "Contato", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (contatosDAO.deleteContato(tfEmail.getText())) {
                JOptionPane.showMessageDialog(null, "Contato excluído com sucesso", "Contato", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    // Trata eventos
    @Override
    public void actionPerformed(ActionEvent evento) {
        // Grava no banco de dados
        if (evento.getSource() == btOk) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Limpa os campos
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        // Exclui o contato do banco de dados
        if (evento.getSource() == btExcluir) {
            try {
                excluir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Fecha a caixa de diálogo
        if (evento.getSource() == btSair) {
            this.dispose();
        }
        // Realiza uma consulta de todos os contatos cadastrados
        if (evento.getSource() == btConsulta) {
            try {
                consulta();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
