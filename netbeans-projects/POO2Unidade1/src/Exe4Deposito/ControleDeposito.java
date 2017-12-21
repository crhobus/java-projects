package Exe4Deposito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ControleDeposito extends JFrame implements Listener {

    private JTextField tfQtdMaxima, tfQtdCritica, tfQtd;
    private JButton btCriarDeposito, btAdd, btRetirar;
    private JLabel lbRecebeQtdAtual;
    private JList list;
    private Deposito deposito;
    private DepositoListModel depositoListModel;

    public ControleDeposito() {
        super("Controle de Depósito");
        Container tela = getContentPane();
        tela.setLayout(null);
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(10, 10, 470, 100);
        tela.add(painel1);
        painel1.setBorder(BorderFactory.createTitledBorder("Dados do depósito"));
        JPanel painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(10, 110, 230, 180);
        tela.add(painel2);
        painel2.setBorder(BorderFactory.createTitledBorder("Fuxo"));
        JPanel painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(250, 110, 230, 180);
        tela.add(painel3);
        painel3.setBorder(BorderFactory.createTitledBorder("Log"));
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        TrataEventos trata = new TrataEventos();
        depositoListModel = new DepositoListModel();

        JLabel lbQtdMaxima = new JLabel("Quantidade Máxima:");
        lbQtdMaxima.setFont(fonte);
        lbQtdMaxima.setBounds(10, 30, 120, 14);
        painel1.add(lbQtdMaxima);

        tfQtdMaxima = new JTextField();
        tfQtdMaxima.setBounds(130, 27, 110, 20);
        painel1.add(tfQtdMaxima);

        JLabel lbQtdCritica = new JLabel("Quantidade Crítica:");
        lbQtdCritica.setFont(fonte);
        lbQtdCritica.setBounds(10, 65, 120, 14);
        painel1.add(lbQtdCritica);

        tfQtdCritica = new JTextField();
        tfQtdCritica.setBounds(130, 63, 110, 20);
        painel1.add(tfQtdCritica);

        btCriarDeposito = new JButton("Criar depósito");
        btCriarDeposito.setBounds(257, 45, 130, 26);
        btCriarDeposito.setFont(fonte);
        painel1.add(btCriarDeposito);
        btCriarDeposito.addActionListener(trata);

        JLabel lbQtd = new JLabel("Quantidade:");
        lbQtd.setFont(fonte);
        lbQtd.setBounds(10, 20, 120, 14);
        painel2.add(lbQtd);

        tfQtd = new JTextField();
        tfQtd.setBounds(90, 17, 110, 20);
        painel2.add(tfQtd);

        btAdd = new JButton("Add");
        btAdd.setBounds(20, 45, 60, 26);
        btAdd.setFont(fonte);
        painel2.add(btAdd);
        btAdd.addActionListener(trata);

        btRetirar = new JButton("Retirar");
        btRetirar.setBounds(90, 45, 75, 26);
        btRetirar.setFont(fonte);
        painel2.add(btRetirar);
        btRetirar.addActionListener(trata);

        JLabel lbQtdAtual = new JLabel("Quantidade Atual:");
        lbQtdAtual.setFont(fonte);
        lbQtdAtual.setBounds(10, 20, 120, 14);
        painel3.add(lbQtdAtual);

        lbRecebeQtdAtual = new JLabel("0");
        lbRecebeQtdAtual.setFont(fonte);
        lbRecebeQtdAtual.setBounds(115, 20, 120, 14);
        painel3.add(lbRecebeQtdAtual);

        JLabel lbFluxo = new JLabel("Fluxo:");
        lbFluxo.setFont(fonte);
        lbFluxo.setBounds(10, 40, 120, 14);
        painel3.add(lbFluxo);

        list = new JList(depositoListModel);
        JScrollPane rolagem1 = new JScrollPane(list);
        rolagem1.setBounds(10, 57, 211, 114);
        painel3.add(rolagem1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 328);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    public void qtdadeAtual(int quantidadeAtual) {
        lbRecebeQtdAtual.setText(quantidadeAtual + "");
    }

    public void qtdadeAdd(int qtdade) {
        depositoListModel.addTexto("Add " + qtdade);
    }

    public void qtdadeRetirada(int qtdade) {
        depositoListModel.addTexto("Retira " + qtdade);
    }

    public void qtdadeCritica() {
        depositoListModel.addTexto("Atenção: Comprar mais");
    }

    public void criarDeposito() {
        int qtdadeMax = Integer.parseInt(tfQtdMaxima.getText());
        int qtdadeCri = Integer.parseInt(tfQtdCritica.getText());
        deposito = new Deposito(qtdadeCri, qtdadeMax);
        deposito.addListener(this);
    }

    public void addDeposito() throws Exception {
        int qtdade = Integer.parseInt(tfQtd.getText());
        deposito.add(qtdade);
    }

    public void retiraDeposito() {
        int qtdade = Integer.parseInt(tfQtd.getText());
        deposito.retirar(qtdade);
    }

    private class DepositoListModel extends AbstractListModel {

        private ArrayList<String> texto = new ArrayList<String>();

        public int getSize() {
            return texto.size();
        }

        public Object getElementAt(int index) {
            return texto.get(index);
        }

        public void addTexto(String texto) {
            this.texto.add(texto);
            fireContentsChanged(this, 0, getSize() - 1);
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == btAdd) {
                try {
                    addDeposito();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
               }
            }
            if (evento.getSource() == btRetirar) {
                retiraDeposito();
            }
            if (evento.getSource() == btCriarDeposito) {
                criarDeposito();
            }
        }
    }
}
