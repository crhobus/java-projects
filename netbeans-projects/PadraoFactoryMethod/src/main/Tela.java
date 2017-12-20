package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tela extends JFrame {

    private Controller control;

    public Tela() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 550);
        setTitle("Pizzaria");
        initComponents();

    }

    private void initComponents() {
        control = new Controller();
        setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(panelNorth, BorderLayout.NORTH);

        final JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(panelCenter, BorderLayout.CENTER);

        //Adiciona a imagem
        final Icon[] images = {new ImageIcon("Peperoni.png"), new ImageIcon("Queijo.png")};
        final JLabel imageLabel = new JLabel();
        imageLabel.setIcon(images[0]);
        panelCenter.add(imageLabel);

        final JLabel pizzaPronta = new JLabel();
        add(pizzaPronta, BorderLayout.SOUTH);

        //Sabores
        String[] sabores = {"Peperoni", "Queijo"};
        final JComboBox cbSabor = new JComboBox(sabores);
        cbSabor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int selectedIndex = cbSabor.getSelectedIndex();
                //Troca a imagem de acordo com a op��o do combo.
                imageLabel.setIcon(images[selectedIndex]);
                pizzaPronta.setText("");
            }
        });
        panelNorth.add(cbSabor);

        JButton button = new JButton();
        button.setText("Comprar pizza");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // pega o indice do combo.
                int indiceSabor = cbSabor.getSelectedIndex();
                //chama o met�do para cria a pizza
                control.criarPizza(indiceSabor);
                //seta o texto informando que a pizza est� pronta.
                pizzaPronta.setText("Pizza pronta!!!");
            }
        });
        panelNorth.add(button);

    }

    public static void main(String[] args) {
        new Tela().setVisible(true);
    }
}
