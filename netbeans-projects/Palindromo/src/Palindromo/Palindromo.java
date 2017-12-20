package Palindromo;


import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Palindromo extends JFrame {

    private JTextField tfAlfabeto, tfPalavra;
    private JButton btOk, btCancelar, btSair;
    private HashSet hashset;

    public Palindromo() {
        super("Verifica Palindromo");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(10, 10, 325, 200);
        painel.setBorder(BorderFactory.createTitledBorder(""));
        tela.add(painel);
        TrataEventos trata = new TrataEventos();

        JLabel lbNome = new JLabel("Caio Renan Hobus");
        lbNome.setBounds(209, 5, 140, 14);
        lbNome.setFont(fonte);
        painel.add(lbNome);

        JLabel lbAlfabeto = new JLabel("Alfabeto:");
        lbAlfabeto.setBounds(10, 40, 80, 14);
        lbAlfabeto.setFont(fonte);
        painel.add(lbAlfabeto);

        tfAlfabeto = new JTextField();
        tfAlfabeto.setBounds(70, 38, 110, 20);
        painel.add(tfAlfabeto);

        JLabel lbPalavra = new JLabel("Palavra:");
        lbPalavra.setBounds(10, 75, 80, 14);
        lbPalavra.setFont(fonte);
        painel.add(lbPalavra);

        tfPalavra = new JTextField();
        tfPalavra.setBounds(70, 73, 110, 20);
        painel.add(tfPalavra);

        btOk = new JButton("OK");
        btOk.setBounds(20, 140, 85, 26);
        painel.add(btOk);
        btOk.addActionListener(trata);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(120, 140, 85, 26);
        painel.add(btCancelar);
        btCancelar.addActionListener(trata);

        btSair = new JButton("Sair");
        btSair.setBounds(220, 140, 85, 26);
        painel.add(btSair);
        btSair.addActionListener(trata);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);//Posiciona no centro da tela;
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    public void insereConjunto(String conjunto) {
        hashset = new HashSet<Character>();
        for (int i = 0; i < conjunto.length(); i++) {
            hashset.add(new Character(conjunto.charAt(i)));
        }
    }

    public boolean verifica(String palavra) {
        int i = 0, j = palavra.length() - 1;
        while (i < j) {
            if ((palavra.charAt(i) == palavra.charAt(j)) && hashset.contains(palavra.charAt(j))) {
                palavra.substring(i, j);
            } else {
                return false;
            }
            i++;
            j--;
        }

        if ((i > j) || (i == j && hashset.contains(palavra.charAt(i))) || palavra.length() == 0) {
            return true;
        }
        return false;
    }

    public void ok() {
        if ("".equals(tfAlfabeto.getText()) || "".equals(tfPalavra.getText())) {
            JOptionPane.showMessageDialog(null, "Campo Alfabeto ou Palavra esta em branco", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            insereConjunto(tfAlfabeto.getText());
            if (verifica(tfPalavra.getText()) == true) {
                JOptionPane.showMessageDialog(null, "É palindromo", "Você acertou", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não é palindromo", "Você errou", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == btOk) {
                ok();
            }
            if (evento.getSource() == btCancelar) {
                tfAlfabeto.setText("");
                tfPalavra.setText("");
            }
            if (evento.getSource() == btSair) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Palindromo aux = new Palindromo();
    }
}
