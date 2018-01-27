package Departamento;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Modelo.Departamento;
import Seguranca.Seguranca;

public class CadasDepartamento extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDepartamento;
    private JButton btConsulta, btOk, btCancelar, btExcluir, btSair;
    private JLabel lbDepObrig;
    private DepartamentoControl controle;
    private Seguranca seguranca;

    public CadasDepartamento(Connection con, Seguranca seguranca) {
        this.seguranca = seguranca;
        controle = new DepartamentoControl(con, seguranca);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Departamentos");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 400, 206);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(50, 20, 60, 14);
        panel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(50, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodDep()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btConsulta = new JButton("...");
        btConsulta.setBounds(136, 35, 31, 26);
        btConsulta.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsulta.setToolTipText("Consulta Departamentos");
        btConsulta.addActionListener(this);
        panel.add(btConsulta);

        JLabel lbDepartamento = new JLabel("Departamento");
        lbDepartamento.setBounds(50, 73, 120, 14);
        panel.add(lbDepartamento);

        lbDepObrig = new JLabel("");
        lbDepObrig.setBounds(120, 76, 10, 14);
        lbDepObrig.setForeground(Color.RED);
        panel.add(lbDepObrig);

        tfDepartamento = new JTextField();
        tfDepartamento.setBounds(50, 91, 280, 20);
        tfDepartamento.addFocusListener(this);
        panel.add(tfDepartamento);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 136, 400, 3);
        panel.add(separator);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(20, 158, 70, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("Confirma Operação");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(115, 158, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Limpar os Campos");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        Icon icExcluir = new ImageIcon("Excluir.png");
        btExcluir = new JButton("Excluir", icExcluir);
        btExcluir.setBounds(210, 158, 70, 26);
        btExcluir.setMargin(new Insets(0, 0, 0, 0));
        btExcluir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setToolTipText("Excluir Registro");
        btExcluir.addActionListener(this);
        panel.add(btExcluir);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(305, 158, 70, 26);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Fechar");
        btSair.addActionListener(this);
        panel.add(btSair);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(428, 256);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodDep()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDepartamento.setText("");
        lbDepObrig.setText("");
    }

    private void gravar() throws Exception {
        if ("".equals(tfDepartamento.getText().trim())) {
            lbDepObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbDepObrig.setText("");
        Departamento departamento = new Departamento();

        try {
            departamento.setDepartamento(seguranca.encriptarAssimetricamente(tfDepartamento.getText().getBytes(), false));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        departamento.setCodDepartamento(Integer.parseInt(tfCodigo.getText()));

        //int codDepCadas = controle.getDepCadastrado(tfDepartamento.getText());
        //if (codDepCadas != -1) {
        //JOptionPane.showMessageDialog(null, "Este departamento ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        //} else {
        if (controle.insertDep(departamento)) {
            JOptionPane.showMessageDialog(null, "Departamento cadastrado com sucesso", "Departamento", JOptionPane.INFORMATION_MESSAGE);
            limparTela();
        }
        //}
    }

    private void consulta() throws Exception {
        if (controle.isDepVazio()) {
            throw new Exception("Não há departamentos cadastrados");
        }
        ConsultaDepartamentos consulta = new ConsultaDepartamentos(controle);
        consulta.setListener(new ListenerDepartamento() {
            @Override
            public void exibeDep(Departamento departamento) {
                limparTela();

                try {
                    tfDepartamento.setText(new String(seguranca.decriptarAssimetricamente(departamento.getDepartamento(), false)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                tfCodigo.setText(Integer.toString(departamento.getCodDepartamento()));
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (controle.isDepVazio()) {
            throw new Exception("Não há departamentos cadastrados");
        }
        if (!controle.isDepCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um departamento");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse departamento", "Departamento", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (controle.deleteDep(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Departamento excluído com sucesso", "Departamento", JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btExcluir) {
            try {
                excluir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btSair) {
            this.dispose();
        }
        if (evento.getSource() == btConsulta) {
            try {
                consulta();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDepartamento) {
            tfDepartamento.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDepartamento.setBackground(Color.WHITE);
    }
}
