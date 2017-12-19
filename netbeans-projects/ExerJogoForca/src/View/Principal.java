package View;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JTextField;

import Control.Jogo;
import Control.LimiteCampo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel panel = null;
    private JLabel lbEscolhaPalavra = null;
    private JButton btNovo = null;
    private JButton btCancelar = null;
    private JLabel lbNumTentativas = null;
    private JLabel lbTentativas = null;
    private JLabel lbPalavra = null;
    private JLabel lbEntrada = null;
    private JTextField tfEntrada = null;
    private JLabel lbResultado = null;
    private JButton btOK = null;
    private Jogo jogo;  //  @jve:decl-index=0:
    private JLabel lbRotuloPalavra = null;

    public Principal() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(525, 246);
        this.setContentPane(getPanel());
        this.setTitle("Jogo da Forca");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel getPanel() {
        if (panel == null) {
            lbRotuloPalavra = new JLabel();
            lbRotuloPalavra.setBounds(new Rectangle(224, 26, 62, 16));
            lbRotuloPalavra.setText("Palavra ?");
            lbResultado = new JLabel();
            lbResultado.setBounds(new Rectangle(14, 126, 252, 16));
            lbResultado.setText("");
            lbEntrada = new JLabel();
            lbEntrada.setBounds(new Rectangle(208, 91, 49, 16));
            lbEntrada.setText("Entrada");
            lbPalavra = new JLabel();
            lbPalavra.setBounds(new Rectangle(239, 50, 268, 16));
            lbPalavra.setText("");
            lbTentativas = new JLabel();
            lbTentativas.setBounds(new Rectangle(14, 101, 111, 16));
            lbTentativas.setText("Tentativa nº 0");
            lbNumTentativas = new JLabel();
            lbNumTentativas.setBounds(new Rectangle(14, 77, 113, 16));
            lbNumTentativas.setText("N° Tentativas : 0");
            lbEscolhaPalavra = new JLabel();
            lbEscolhaPalavra.setBounds(new Rectangle(14, 15, 101, 16));
            lbEscolhaPalavra.setText("Escolher Palavra ");
            panel = new JPanel();
            panel.setLayout(null);
            panel.add(lbEscolhaPalavra, null);
            panel.add(getLbNovo(), null);
            panel.add(getLbCancelar(), null);
            panel.add(lbNumTentativas, null);
            panel.add(lbTentativas, null);
            panel.add(lbPalavra, null);
            panel.add(lbEntrada, null);
            panel.add(getTfEntrada(), null);
            panel.add(lbResultado, null);
            panel.add(getBtOK(), null);
            panel.add(lbRotuloPalavra, null);
        }
        return panel;
    }

    private JButton getLbNovo() {
        if (btNovo == null) {
            btNovo = new JButton();
            btNovo.setBounds(new Rectangle(14, 39, 62, 26));
            btNovo.setText("Novo");
            btNovo.addActionListener(this);
        }
        return btNovo;
    }

    private JButton getLbCancelar() {
        if (btCancelar == null) {
            btCancelar = new JButton();
            btCancelar.setBounds(new Rectangle(87, 39, 85, 26));
            btCancelar.setText("Cancelar");
            btCancelar.addActionListener(this);
        }
        return btCancelar;
    }

    private JTextField getTfEntrada() {
        if (tfEntrada == null) {
            tfEntrada = new JTextField();
            tfEntrada.setBounds(new Rectangle(263, 90, 22, 20));
            tfEntrada.setDocument(new LimiteCampo(1));
            tfEntrada.setBackground(Color.white);
            tfEntrada.setEditable(false);
        }
        return tfEntrada;
    }

    private JButton getBtOK() {
        if (btOK == null) {
            btOK = new JButton();
            btOK.setBounds(new Rectangle(292, 86, 55, 26));
            btOK.setEnabled(false);
            btOK.setText("OK");
            btOK.addActionListener(this);
        }
        return btOK;
    }

    private void cancelar() {
        lbTentativas.setText("Tentativa nº 0");
        lbNumTentativas.setText("N° Tentativas : 0");
        lbPalavra.setText("");
        lbResultado.setText("");
        btOK.setEnabled(false);
        tfEntrada.setText("");
        tfEntrada.setEditable(false);
        btNovo.setEnabled(true);
    }

    private void novo() {
        String palavra = JOptionPane.showInputDialog(null, "Entre com uma palavra", "Entrada", JOptionPane.INFORMATION_MESSAGE);
        if (palavra != null) {
            if (palavra.length() == 0) {
                JOptionPane.showMessageDialog(null, "Entre com uma palavra válida", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (palavra.length() > 25) {
                JOptionPane.showMessageDialog(null, "A palavra deve ter no máximo 25 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int numTentativas = getNumTentativas();
            if (numTentativas != -1) {
                tfEntrada.setEditable(true);
                btOK.setEnabled(true);
                btNovo.setEnabled(false);
                jogo = new Jogo(palavra.toLowerCase());
                String s = "";
                for (int i = 0; i < palavra.length(); i++) {
                    s += "x";
                }
                lbPalavra.setText(s);
                lbNumTentativas.setText("N° Tentativas : " + numTentativas);
                lbTentativas.setText("Tentativa nº " + numTentativas);
                lbResultado.setText("");
            }
        }
    }

    private int getNumTentativas() {
        String s;
        int n;
        while (true) {
            try {
                s = JOptionPane.showInputDialog(null, "Entre com o número de tentativas", "Entrada", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    return -1;
                }
                n = Integer.parseInt(s);
                if (n > 0) {
                    return n;
                }
                JOptionPane.showMessageDialog(null, "Entre com o número de tentativas maior ou igual a um", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Entre com um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jogoEncerado() {
        btOK.setEnabled(false);
        tfEntrada.setText("");
        tfEntrada.setEditable(false);
        btNovo.setEnabled(true);
    }

    private void ok() throws Exception {
        if ("".equals(tfEntrada.getText())) {
            throw new Exception("Entre com uma letra");
        }
        lbPalavra.setText(jogo.getJogar(tfEntrada.getText().toLowerCase().charAt(0)));
        int numTentativas = Integer.parseInt(lbTentativas.getText().substring(13)) - 1;
        lbTentativas.setText("Tentativa nº " + numTentativas);
        if (jogo.getTermino() != null) {
            lbResultado.setText(jogo.getTermino());
            jogoEncerado();
            return;
        }
        if (numTentativas == 0) {
            lbResultado.setText("Tempo limite esgotado, tente novamente");
            jogoEncerado();
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btNovo) {
            novo();
        }
        if (evento.getSource() == btCancelar) {
            cancelar();
        }
        if (evento.getSource() == btOK) {
            try {
                ok();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
} // @jve:decl-index=0:visual-constraint="10,10"
