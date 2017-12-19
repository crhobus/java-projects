package Principal;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel lbNota1 = null;
    private JTextField tfNota1 = null;
    private JLabel lbNota2 = null;
    private JTextField tfNota2 = null;
    private JLabel lbNota3 = null;
    private JTextField tfNota3 = null;
    private JLabel lbNota4 = null;
    private JTextField tfNota4 = null;
    private JButton btCalcular = null;
    private JLabel lbMedia = null;
    private JButton btCancelar = null;

    public Principal() {
        super();
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(390, 211);
        this.setContentPane(getJContentPane());
        this.setTitle("Sistema de Médias");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            lbMedia = new JLabel();
            lbMedia.setBounds(new Rectangle(215, 112, 152, 16));
            lbMedia.setText("Média:");
            lbNota4 = new JLabel();
            lbNota4.setBounds(new Rectangle(26, 115, 38, 16));
            lbNota4.setText("Nota 4");
            lbNota3 = new JLabel();
            lbNota3.setBounds(new Rectangle(25, 85, 38, 16));
            lbNota3.setText("Nota 3");
            lbNota2 = new JLabel();
            lbNota2.setBounds(new Rectangle(25, 55, 38, 16));
            lbNota2.setText("Nota 2");
            lbNota1 = new JLabel();
            lbNota1.setBounds(new Rectangle(25, 23, 38, 16));
            lbNota1.setText("Nota 1");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(lbNota1, null);
            jContentPane.add(getTfNota1(), null);
            jContentPane.add(lbNota2, null);
            jContentPane.add(getTfNota2(), null);
            jContentPane.add(lbNota3, null);
            jContentPane.add(getTfNota3(), null);
            jContentPane.add(lbNota4, null);
            jContentPane.add(getTfNota4(), null);
            jContentPane.add(getBtCalcular(), null);
            jContentPane.add(lbMedia, null);
            jContentPane.add(getBtCancelar(), null);
        }
        return jContentPane;
    }

    private JTextField getTfNota1() {
        if (tfNota1 == null) {
            tfNota1 = new JTextField();
            tfNota1.setBounds(new Rectangle(75, 21, 80, 20));
        }
        return tfNota1;
    }

    private JTextField getTfNota2() {
        if (tfNota2 == null) {
            tfNota2 = new JTextField();
            tfNota2.setBounds(new Rectangle(75, 53, 80, 20));
        }
        return tfNota2;
    }

    private JTextField getTfNota3() {
        if (tfNota3 == null) {
            tfNota3 = new JTextField();
            tfNota3.setBounds(new Rectangle(75, 83, 80, 20));
        }
        return tfNota3;
    }

    private JTextField getTfNota4() {
        if (tfNota4 == null) {
            tfNota4 = new JTextField();
            tfNota4.setBounds(new Rectangle(75, 114, 80, 20));
        }
        return tfNota4;
    }

    private JButton getBtCalcular() {
        if (btCalcular == null) {
            btCalcular = new JButton();
            btCalcular.setBounds(new Rectangle(208, 30, 81, 26));
            btCalcular.setText("Calcular");
            btCalcular.setName("btCalcular");
            btCalcular.addActionListener(this);
        }
        return btCalcular;
    }

    private JButton getBtCancelar() {
        if (btCancelar == null) {
            btCancelar = new JButton();
            btCancelar.setBounds(new Rectangle(208, 68, 85, 26));
            btCancelar.setText("Cancelar");
            btCancelar.addActionListener(this);
        }
        return btCancelar;
    }

    private double verificaCampo(JTextField tf, String campo) throws Exception {
        try {
            double d = Double.parseDouble(tf.getText());
            if (d >= 0 && d <= 10) {
                return d;
            }
            tf.grabFocus();
            throw new Exception("Entre com uma nota entre 0 e 10 para o campo " + campo);
        } catch (NumberFormatException ex) {
            tf.grabFocus();
            throw new NumberFormatException("Campo " + campo + " inválido");
        }
    }

    private void calcular() throws Exception {
        double nota1 = verificaCampo(tfNota1, "nota 1");
        double nota2 = verificaCampo(tfNota2, "nota 2");
        double nota3 = verificaCampo(tfNota3, "nota 3");
        double nota4 = verificaCampo(tfNota4, "nota 4");
        lbMedia.setText("Média: " + ((nota1 + nota2 + nota3 + nota4) / 4));
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCalcular) {
            try {
                calcular();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            tfNota1.setText("");
            tfNota2.setText("");
            tfNota3.setText("");
            tfNota4.setText("");
            lbMedia.setText("Média:");
        }
    }
}
