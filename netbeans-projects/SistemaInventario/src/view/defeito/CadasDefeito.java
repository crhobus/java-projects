package view.defeito;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.Defeito;

import dbOracle.DefeitoDAO;

import view.PanelComponentes;

public class CadasDefeito extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDefeito;
    private JButton btConsulta, btOk, btCancelar, btExcluir, btSair;
    private DefeitoDAO defeitoDAO;

    public CadasDefeito(Connection con) {
        defeitoDAO = new DefeitoDAO(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Defeitos");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 414, 205);
        this.add(panel);

        panel.addJLabel("Codigo", 50, 20, 50, 14);

        tfCodigo = panel.addJTextFieldNaoEdit(50, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(defeitoDAO.getProxCodDefeito()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.addFocusListener(this);

        btConsulta = panel.addJButton("...", "Consulta Defeitos", 136, 35, 31, 26);
        btConsulta.addActionListener(this);

        panel.addJLabel("Defeito", 50, 73, 50, 14);

        tfDefeito = panel.addJTextField(50, 91, 320, 20);
        tfDefeito.addFocusListener(this);

        panel.addJSeparator(JSeparator.HORIZONTAL, 0, 136, 412, 3);

        btOk = panel.addJButtonOK(30, 158, 70, 26);
        btOk.addActionListener(this);

        btCancelar = panel.addJButtonCancelar(125, 158, 70, 26);
        btCancelar.addActionListener(this);

        btExcluir = panel.addJButtonExcuir(220, 158, 70, 26);
        btExcluir.addActionListener(this);

        btSair = panel.addJButtonSair(315, 158, 70, 26);
        btSair.addActionListener(this);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(428, 241);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(defeitoDAO.getProxCodDefeito()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfDefeito.setText("");
    }

    private void gravar() throws Exception {
        if ("".equals(tfDefeito.getText().trim())) {
            throw new Exception("Campo defeito obrigatório não preenchido");
        }
        Defeito defeito = new Defeito();
        defeito.setCodigo(Integer.parseInt(tfCodigo.getText()));
        defeito.setDefeito(tfDefeito.getText());
        int codDefeitoCadas = defeitoDAO.getDefeitoCadastrado(tfDefeito.getText());
        if (codDefeitoCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este defeito ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (defeitoDAO.insertDefeito(defeito)) {
                JOptionPane.showMessageDialog(null, "Defeito cadastrado com sucesso", "Defeito", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    private void consulta() throws Exception {
        if (defeitoDAO.isDefeitoVazio()) {
            throw new Exception("Não há defeitos cadastrados\nCadastre um defeito primeiro");
        }
        ConsultaDefeito consulta = new ConsultaDefeito(defeitoDAO);
        consulta.setListener(new ListenerDefeito() {

            @Override
            public void exibeDefeito(Defeito defeito) {
                limparTela();
                tfCodigo.setText(Integer.toString(defeito.getCodigo()));
                tfDefeito.setText(defeito.getDefeito());

            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (defeitoDAO.isDefeitoVazio()) {
            throw new Exception("Não há defeitos cadastrados\nCadastre um defeito primeiro");
        }
        if (!defeitoDAO.isDefeitoCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um defeito");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse defeito", "Defeito", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (defeitoDAO.deleteDefeito(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Defeito excluído com sucesso", "Defeito", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btExcluir) {
            try {
                excluir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btSair) {
            this.dispose();
        }
        if (evento.getSource() == btConsulta) {
            try {
                consulta();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDefeito) {
            tfDefeito.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDefeito.setBackground(Color.WHITE);
    }
}
