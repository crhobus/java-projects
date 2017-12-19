package Setor;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAOFactory.DAOFactory;
import FormatacaoCampos.CriarObjGrafico;
import Modelo.Setor;

public class CadasSetor extends JDialog implements ActionListener, ItemListener {

    private JTextField tfCodigo, tfNome;
    private JButton btVer, btOk, btCancelar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private SetorControl controle;

    public CadasSetor(DAOFactory daoFactory) {
        controle = new SetorControl(daoFactory);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Setor");
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanel(20, 20, 310, 160);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("Codigo", 40, 45, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(85, 43, 110, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodSetor()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        panel.add(tfCodigo);

        btVer = CriarObjGrafico.criarJButton("...", 199, 41, 31, 24);
        btVer.addActionListener(this);
        panel.add(btVer);

        panel.add(CriarObjGrafico.criarJLabel("Nome", 40, 100, 80, 14));
        tfNome = CriarObjGrafico.criarJTextField(85, 98, 145, 20);
        panel.add(tfNome);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 368, 33, 80, 20);
        rbNovo.addItemListener(this);
        rbNovo.setBackground(null);
        add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 368, 53, 80, 20);
        rbAlterar.addItemListener(this);
        rbAlterar.setBackground(null);
        add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 368, 73, 80, 20);
        rbExcluir.addItemListener(this);
        rbExcluir.setBackground(null);
        add(rbExcluir);

        btOk = CriarObjGrafico.criarJButton("OK", 365, 105, 92, 26);
        btOk.addActionListener(this);
        add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 365, 145, 92, 26);
        btCancelar.addActionListener(this);
        add(btCancelar);

        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(525, 225);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodSetor()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfNome.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void gravar() throws Exception {
        if ("".equals(tfNome.getText())) {
            tfNome.grabFocus();
            throw new Exception("Campo nome inválido");
        } else {
            Setor setor = new Setor();
            setor.setCodigo(Integer.parseInt(tfCodigo.getText()));
            setor.setNome(tfNome.getText());
            Setor setorLido = controle.getSetor(tfNome.getText());
            if (setorLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O setor " + setor.getNome() + " ja esta cadastrado deseja substituilo? ", "Setor", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    setor.setCodigo(setorLido.getCodigo());
                    if (controle.setSetor(setor)) {
                        JOptionPane.showMessageDialog(null, "Setor cadastrado com sucesso", "Setor", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.setSetor(setor)) {
                    JOptionPane.showMessageDialog(null, "Setor cadastrado com sucesso", "Setor", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.arqSetorVazio()) {
            JOptionPane.showMessageDialog(null, "Não há setores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String s = JOptionPane.showInputDialog(null, "Informe o nome do setor a ser recuperado:", "Setor", JOptionPane.INFORMATION_MESSAGE);
            if (s != null) {
                Setor setor = controle.getSetor(s);
                if (setor != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(setor.getCodigo()));
                    tfNome.setText(setor.getNome());
                    JOptionPane.showMessageDialog(null, "Setor recuperado com sucesso", "Setor", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Setor não encontrado", "Setor", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void excluir() throws Exception {
        if (controle.arqSetorVazio()) {
            JOptionPane.showMessageDialog(null, "Não há setores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String setor = JOptionPane.showInputDialog(null, "Informe o nome do setor a ser excluído:", "Setor", JOptionPane.INFORMATION_MESSAGE);
            if (setor != null) {
                if (controle.removeSetor(setor)) {
                    JOptionPane.showMessageDialog(null, "Setor excluído com sucesso", "Setor", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Setor não encontrado", "Setor", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaSetor() throws Exception {
        if (controle.arqSetorVazio()) {
            JOptionPane.showMessageDialog(null, "Não há setores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaSetor pse = new PesquisaSetor(controle);
            pse.setModal(true);
            pse.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Setor", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovo.isSelected() == true) {
                    try {
                        gravar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbAlterar.isSelected() == true) {
                    try {
                        recuperar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbExcluir.isSelected() == true) {
                    try {
                        excluir();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btVer) {
            try {
                abrirPesquisaSetor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent evento) {
        if (evento.getSource() == rbNovo) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbAlterar.setSelected(false);
                rbExcluir.setSelected(false);
            }
        }
        if (evento.getSource() == rbAlterar) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbExcluir.setSelected(false);
            }
        }
        if (evento.getSource() == rbExcluir) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbAlterar.setSelected(false);
            }
        }
    }
}
