package view.ambiente;

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

import model.Ambiente;

import dbOracle.AmbienteDAO;

import view.PanelComponentes;

public class CadasAmbiente extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfAmbiente;
    private JButton btConsulta, btOk, btCancelar, btExcluir, btSair;
    private AmbienteDAO ambienteDAO;

    public CadasAmbiente(Connection con) {
        ambienteDAO = new AmbienteDAO(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Ambientes");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 414, 205);
        this.add(panel);

        panel.addJLabel("Codigo", 50, 20, 50, 14);

        tfCodigo = panel.addJTextFieldNaoEdit(50, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(ambienteDAO.getProxCodAmbiente()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.addFocusListener(this);

        btConsulta = panel.addJButton("...", "Consulta Ambientes", 136, 35, 31, 26);
        btConsulta.addActionListener(this);

        panel.addJLabel("Ambiente", 50, 73, 50, 14);

        tfAmbiente = panel.addJTextField(50, 91, 280, 20);
        tfAmbiente.addFocusListener(this);

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
            tfCodigo.setText(Integer.toString(ambienteDAO.getProxCodAmbiente()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfAmbiente.setText("");
    }

    private void gravar() throws Exception {
        if ("".equals(tfAmbiente.getText().trim())) {
            throw new Exception("Campo ambiente obrigatório não preenchido");
        }
        Ambiente ambiente = new Ambiente();
        ambiente.setCodigo(Integer.parseInt(tfCodigo.getText()));
        ambiente.setAmbiente(tfAmbiente.getText());
        int codAmbienteCadas = ambienteDAO.getAmbienteCadastrado(tfAmbiente.getText());
        if (codAmbienteCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este ambiente ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (ambienteDAO.insertAmbiente(ambiente)) {
                JOptionPane.showMessageDialog(null, "Ambiente cadastrado com sucesso", "Ambiente", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    private void consulta() throws Exception {
        if (ambienteDAO.isAmbienteVazio()) {
            throw new Exception("Não há ambientes cadastrados\nCadastre um ambiente primeiro");
        }
        ConsultaAmbiente consulta = new ConsultaAmbiente(ambienteDAO);
        consulta.setListener(new ListenerAmbiente() {

            @Override
            public void exibeAmbiente(Ambiente ambiente) {
                limparTela();
                tfCodigo.setText(Integer.toString(ambiente.getCodigo()));
                tfAmbiente.setText(ambiente.getAmbiente());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (ambienteDAO.isAmbienteVazio()) {
            throw new Exception("Não há ambientes cadastrados\nCadastre um ambiente primeiro");
        }
        if (!ambienteDAO.isAmbienteCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um ambiente");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse ambiente", "Ambiente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (ambienteDAO.deleteAmbiente(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Ambiente excluído com sucesso", "Ambiente", JOptionPane.INFORMATION_MESSAGE);
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
        if (evento.getSource() == tfAmbiente) {
            tfAmbiente.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfAmbiente.setBackground(Color.WHITE);
    }
}
