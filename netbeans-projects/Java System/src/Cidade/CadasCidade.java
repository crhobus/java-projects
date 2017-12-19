package Cidade;

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
import Modelo.Cidade;

public class CadasCidade extends JDialog implements ActionListener, ItemListener {

    private JTextField tfCodigo, tfCidade, tfEstado;
    private JButton btVer, btOk, btCancelar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private CidadeControl controle;

    public CadasCidade(DAOFactory daoFactory) {
        controle = new CidadeControl(daoFactory);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Cidade");
        setLayout(null);
        JPanel panel1 = CriarObjGrafico.criarJPanel(20, 20, 310, 190);
        add(panel1);

        panel1.add(CriarObjGrafico.criarJLabel("Codigo", 40, 50, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(85, 48, 110, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodCid()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        panel1.add(tfCodigo);

        btVer = CriarObjGrafico.criarJButton("...", 199, 46, 31, 24);
        btVer.addActionListener(this);
        panel1.add(btVer);

        panel1.add(CriarObjGrafico.criarJLabel("Cidade", 40, 95, 80, 14));
        tfCidade = CriarObjGrafico.criarJTextField(85, 93, 145, 20);
        panel1.add(tfCidade);

        panel1.add(CriarObjGrafico.criarJLabel("Estado", 40, 140, 80, 14));
        tfEstado = CriarObjGrafico.criarJTextField(85, 138, 145, 20);
        panel1.add(tfEstado);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 368, 43, 80, 20);
        rbNovo.addItemListener(this);
        rbNovo.setBackground(null);
        add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 368, 63, 80, 20);
        rbAlterar.addItemListener(this);
        rbAlterar.setBackground(null);
        add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 368, 83, 80, 20);
        rbExcluir.addItemListener(this);
        rbExcluir.setBackground(null);
        add(rbExcluir);

        btOk = CriarObjGrafico.criarJButton("OK", 365, 115, 92, 26);
        btOk.addActionListener(this);
        add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 365, 155, 92, 26);
        btCancelar.addActionListener(this);
        add(btCancelar);

        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(525, 275);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodCid()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCidade.setText("");
        tfEstado.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void gravar() throws Exception {
        if ("".equals(tfCidade.getText())) {
            tfCidade.grabFocus();
            throw new Exception("Campo cidade inválido");
        } else {
            if ("".equals(tfEstado.getText())) {
                tfEstado.grabFocus();
                throw new Exception("Campo estado inválido");
            } else {
                Cidade cidade = new Cidade();
                cidade.setCodigo(Integer.parseInt(tfCodigo.getText()));
                cidade.setCidade(tfCidade.getText());
                cidade.setEstado(tfEstado.getText());
                Cidade cidadeLida = controle.getCidade(tfCidade.getText());
                if (cidadeLida != null) {
                    int resposta = JOptionPane.showConfirmDialog(null, "A cidade " + cidade.getCidade() + " ja esta cadastrado deseja substituila? ", "Cidade", JOptionPane.YES_NO_OPTION);
                    if (resposta == 0) {
                        cidade.setCodigo(cidadeLida.getCodigo());
                        if (controle.setCidade(cidade)) {
                            JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso", "Cidade", JOptionPane.INFORMATION_MESSAGE);
                            limparTela();
                        }
                    } else {
                        return;
                    }
                } else {
                    if (controle.setCidade(cidade)) {
                        JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso", "Cidade", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.arqCidVazio()) {
            JOptionPane.showMessageDialog(null, "Não há cidades cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String s = JOptionPane.showInputDialog(null, "Informe o nome da cidade a ser recuperada:", "Cidade", JOptionPane.INFORMATION_MESSAGE);
            if (s != null) {
                Cidade cidade = controle.getCidade(s);
                if (cidade != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(cidade.getCodigo()));
                    tfCidade.setText(cidade.getCidade());
                    tfEstado.setText(cidade.getEstado());
                    JOptionPane.showMessageDialog(null, "Cidade recuperada com sucesso", "Cidade", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cidade não encontrada", "Cidade", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void excluir() throws Exception {
        if (controle.arqCidVazio()) {
            JOptionPane.showMessageDialog(null, "Não há cidades cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cidade = JOptionPane.showInputDialog(null, "Informe o nome da cidade a ser excluída:", "Cidade", JOptionPane.INFORMATION_MESSAGE);
            if (cidade != null) {
                if (controle.removeCid(cidade)) {
                    JOptionPane.showMessageDialog(null, "Cidade excluída com sucesso", "Cidade", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Cidade não encontrada", "Cidade", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaCid() throws Exception {
        if (controle.arqCidVazio()) {
            JOptionPane.showMessageDialog(null, "Não há cidades cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaCidade pci = new PesquisaCidade(controle);
            pci.setModal(true);
            pci.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Cidade", JOptionPane.ERROR_MESSAGE);
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
                abrirPesquisaCid();
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
