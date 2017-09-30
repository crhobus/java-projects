package com.utils.java8.cap3InterfacesFuncionais.listener;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author crhobus
 */
public class Tela extends JFrame {

    public Tela() {
        super("Exemplo Listeners");

        setLayout(new FlowLayout());

        JButton button = new JButton("Teste1");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "Evento action listener", "Exemplo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(button, FlowLayout.LEFT);

        JButton button2 = new JButton("Teste2");
        button2.addActionListener((evt) -> {
            JOptionPane.showMessageDialog(null, "Evento action listener 2", "Exemplo", JOptionPane.INFORMATION_MESSAGE);
        });
        add(button2, FlowLayout.CENTER);

        JButton button3 = new JButton("Teste3");
        button3.addActionListener(evt -> JOptionPane.showMessageDialog(null, "Evento action listener 3", "Exemplo", JOptionPane.INFORMATION_MESSAGE));
        add(button3, FlowLayout.RIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setLocationRelativeTo(this);
        setVisible(true);
    }
}
