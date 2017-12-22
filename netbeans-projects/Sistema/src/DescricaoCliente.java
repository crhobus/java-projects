
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DescricaoCliente extends JDialog {

    private InterfaceClientes listener;
    private JTextArea area;

    DescricaoCliente() {
        this.setSize(350, 300);
        this.setLocationRelativeTo(null);//Posiciona no centro da tela
        this.setResizable(false);
        this.criar();
        this.setVisible(true);
    }

    private void criar() {
        Container tela = getContentPane();
        tela.setLayout(null);
        Color cor1 = new Color(0, 0, 0);
        Color cor2 = new Color(250, 250, 250);
        Font fonte = new Font("Arial Black", Font.BOLD, 11);
        JPanel Painel = new JPanel();
        Painel.setBackground(cor2);
        Painel.setLayout(null);
        Painel.setBounds(5, 5, 335, 262);
        tela.add(Painel);
        Painel.setBorder(BorderFactory.createLineBorder(cor1.darkGray, 4));//Borda em volta no JPanel
        JLabel label = new JLabel("Descrição do Cliente");
        label.setBounds(90, 15, 200, 20);
        label.setFont(fonte);
        Painel.add(label);
        JButton ok = new JButton("...");
        ok.setBounds(15, 10, 20, 20);
        Painel.add(ok);
        area = new JTextArea();
        area.setBounds(10, 40, 315, 210);
        area.setBorder(BorderFactory.createLineBorder(cor1.darkGray, 2));//Borda em volta no JPanel
        Painel.add(area);
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                botaoOkPressionado();
            }
        });

        JScrollPane rolagem = new JScrollPane();//barra de rolagem
        area.add(rolagem);
        rolagem.setVisible(true);
    }

    public void setListener(InterfaceClientes listener) {
        this.listener = listener;
    }

    //listener do botão quando ele for pressionado
    private void botaoOkPressionado() {
        //fecha a janela
        setVisible(false);

        //se houver um listener registrado, notifica
        if (listener != null) {
            listener.campoTextoAlterado(area.getText());
        }
    }
}
