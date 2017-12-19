package View;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import Control.Cadastrar;
import Model.Funcionario;

public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel panel = null;
    private JPanel panelEntrada = null;
    private JLabel lbNome = null;
    private JTextField tfNome = null;
    private JLabel lbIdade = null;
    private JTextField tfIdade = null;
    private JLabel lbValorHora = null;
    private JTextField tfValorHora = null;
    private JLabel lbHorasTrabalhadas = null;
    private JTextField tfHorasTrabalhadas = null;
    private JLabel lbHorasTrabalhadas50 = null;
    private JTextField tfHorasTrabalhadas50 = null;
    private JLabel lbHorasTrabalhadas100 = null;
    private JTextField tfHorasTrabalhadas100 = null;
    private JLabel lbCargo = null;
    private JComboBox cbCargo = null;
    private JTextArea taSalario = null;
    private JLabel lbSalario = null;
    private JButton btCadastrar = null;
    private JButton btFinalizar = null;
    private JButton btNovo = null;
    private Cadastrar cadastrar;

    public Principal() {
        super();
        cadastrar = new Cadastrar();
        initialize();
    }

    private void initialize() {
        this.setSize(430, 427);
        this.setContentPane(getPanel());
        this.setTitle("Cadastro de Funcionário");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setLayout(null);
            panel.add(getPanelEntrada(), null);
        }
        return panel;
    }

    private JPanel getPanelEntrada() {
        if (panelEntrada == null) {
            lbSalario = new JLabel();
            lbSalario.setBounds(new Rectangle(26, 166, 94, 16));
            lbSalario.setText("Cálculo Salário");
            lbCargo = new JLabel();
            lbCargo.setBounds(new Rectangle(180, 118, 38, 16));
            lbCargo.setText("Cargo");
            lbHorasTrabalhadas100 = new JLabel();
            lbHorasTrabalhadas100.setBounds(new Rectangle(26, 118, 142, 16));
            lbHorasTrabalhadas100.setText("Horas Trabalhadas 100%");
            lbHorasTrabalhadas50 = new JLabel();
            lbHorasTrabalhadas50.setBounds(new Rectangle(224, 72, 130, 16));
            lbHorasTrabalhadas50.setText("Hora Trabalhadas 50%");
            lbHorasTrabalhadas = new JLabel();
            lbHorasTrabalhadas.setBounds(new Rectangle(108, 72, 109, 16));
            lbHorasTrabalhadas.setText("HorasTrabalhadas");
            lbValorHora = new JLabel();
            lbValorHora.setBounds(new Rectangle(26, 72, 66, 16));
            lbValorHora.setText("Valor Hora");
            lbIdade = new JLabel();
            lbIdade.setBounds(new Rectangle(270, 26, 34, 16));
            lbIdade.setText("Idade");
            lbNome = new JLabel();
            lbNome.setText("Nome");
            lbNome.setBounds(new Rectangle(27, 26, 38, 16));
            panelEntrada = new JPanel();
            panelEntrada.setLayout(null);
            panelEntrada.setBounds(new Rectangle(16, 15, 391, 372));
            panelEntrada.setBorder(BorderFactory.createTitledBorder(null, "Entrada de dados", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 12), Color.black));
            panelEntrada.add(lbNome, null);
            panelEntrada.add(getTfNome(), null);
            panelEntrada.add(lbIdade, null);
            panelEntrada.add(getTfIdade(), null);
            panelEntrada.add(lbValorHora, null);
            panelEntrada.add(getTfValorHora(), null);
            panelEntrada.add(lbHorasTrabalhadas, null);
            panelEntrada.add(getTfHorasTrabalhadas(), null);
            panelEntrada.add(lbHorasTrabalhadas50, null);
            panelEntrada.add(getTfHorasTrabalhadas50(), null);
            panelEntrada.add(lbHorasTrabalhadas100, null);
            panelEntrada.add(getTfHorasTrabalhadas100(), null);
            panelEntrada.add(lbCargo, null);
            panelEntrada.add(getCbCargo(), null);
            panelEntrada.add(getTaSalario(), null);
            panelEntrada.add(lbSalario, null);
            panelEntrada.add(getBtCadastrar(), null);
            panelEntrada.add(getBtFinalizar(), null);
            panelEntrada.add(getBtCancelar(), null);
        }
        return panelEntrada;
    }

    private JTextField getTfNome() {
        if (tfNome == null) {
            tfNome = new JTextField();
            tfNome.setBounds(new Rectangle(27, 48, 235, 20));
        }
        return tfNome;
    }

    private JTextField getTfIdade() {
        if (tfIdade == null) {
            tfIdade = new JTextField();
            tfIdade.setBounds(new Rectangle(270, 48, 92, 20));
        }
        return tfIdade;
    }

    private JTextField getTfValorHora() {
        if (tfValorHora == null) {
            tfValorHora = new JTextField();
            tfValorHora.setBounds(new Rectangle(26, 93, 69, 20));
        }
        return tfValorHora;
    }

    private JTextField getTfHorasTrabalhadas() {
        if (tfHorasTrabalhadas == null) {
            tfHorasTrabalhadas = new JTextField();
            tfHorasTrabalhadas.setBounds(new Rectangle(107, 93, 107, 20));
        }
        return tfHorasTrabalhadas;
    }

    private JTextField getTfHorasTrabalhadas50() {
        if (tfHorasTrabalhadas50 == null) {
            tfHorasTrabalhadas50 = new JTextField();
            tfHorasTrabalhadas50.setBounds(new Rectangle(224, 93, 138, 20));
        }
        return tfHorasTrabalhadas50;
    }

    private JTextField getTfHorasTrabalhadas100() {
        if (tfHorasTrabalhadas100 == null) {
            tfHorasTrabalhadas100 = new JTextField();
            tfHorasTrabalhadas100.setBounds(new Rectangle(26, 140, 143, 20));
        }
        return tfHorasTrabalhadas100;
    }

    private JComboBox getCbCargo() {
        if (cbCargo == null) {
            cbCargo = new JComboBox();
            cbCargo.setBounds(new Rectangle(180, 140, 182, 20));
            cbCargo.setBackground(Color.white);
            cbCargo.addItem("Selecione");
            cbCargo.addItem("Gerente");
            cbCargo.addItem("Secretária");
            cbCargo.addItem("Programador");
            cbCargo.addItem("Analista de Sistemas");
            cbCargo.addItem("Web-Designer");
            cbCargo.addItem("suporte");
        }
        return cbCargo;
    }

    private JScrollPane getTaSalario() {
        JScrollPane scroll = null;
        if (taSalario == null) {
            taSalario = new JTextArea();
            taSalario.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            taSalario.setEditable(false);
            taSalario.setBackground(Color.WHITE);
            scroll = new JScrollPane(taSalario);
            scroll.setBounds(new Rectangle(26, 186, 337, 114));
        }
        return scroll;
    }

    private JButton getBtCadastrar() {
        if (btCadastrar == null) {
            btCadastrar = new JButton();
            btCadastrar.setBounds(new Rectangle(82, 318, 92, 26));
            btCadastrar.setText("Cadastrar");
            btCadastrar.addActionListener(this);
        }
        return btCadastrar;
    }

    private JButton getBtFinalizar() {
        if (btFinalizar == null) {
            btFinalizar = new JButton();
            btFinalizar.setBounds(new Rectangle(277, 318, 85, 26));
            btFinalizar.setText("Finalizar");
            btFinalizar.addActionListener(this);
        }
        return btFinalizar;
    }

    private JButton getBtCancelar() {
        if (btNovo == null) {
            btNovo = new JButton();
            btNovo.setBounds(new Rectangle(182, 318, 85, 26));
            btNovo.setText("Novo");
            btNovo.addActionListener(this);
        }
        return btNovo;
    }

    private double getDouble(JTextField tf, String nomeCampo, int condicao, String msg) throws Exception {
        try {
            double d = Double.parseDouble(tf.getText());
            if (d > condicao) {
                return d;
            }
            tf.grabFocus();
            throw new Exception(msg);
        } catch (NumberFormatException ex) {
            tf.grabFocus();
            throw new NumberFormatException("Campo " + nomeCampo + " inválido");
        }
    }

    private int getInteger(JTextField tf, String nomeCampo, int condicao, String msg) throws Exception {
        try {
            int n = Integer.parseInt(tf.getText());
            if (n >= condicao) {
                return n;
            }
            tf.grabFocus();
            throw new Exception(msg);
        } catch (NumberFormatException ex) {
            tf.grabFocus();
            throw new NumberFormatException("Campo " + nomeCampo + " inválido");
        }
    }

    private double getCalculoSalario() throws Exception {
        double valorHora = getDouble(tfValorHora, "valor por hora", 0, "Campo valor por hora deve ter valor superior a 0");
        int horasTrab = getInteger(tfHorasTrabalhadas, "hora trabalhadas", 1, "Campo horas trabalhadas deve ter valor superior a 1");
        int horasTrab50 = getInteger(tfHorasTrabalhadas50, "hora trabalhadas 50%", 0, "Campo horas trabalhadas 50% deve ter valor superior a 0");
        int horasTrab100 = getInteger(tfHorasTrabalhadas100, "hora trabalhadas 100%", 0, "Campo horas trabalhadas 100% deve ter valor superior a 0");
        double salarioBruto = (valorHora * horasTrab) + ((valorHora * 1.5) * horasTrab50) + ((valorHora * 2) * horasTrab100);
        String saida = "Salário bruto: " + NumberFormat.getCurrencyInstance().format(salarioBruto);
        double descInss;
        if (salarioBruto <= 2400) {
            descInss = salarioBruto * 0.08;
        } else {
            descInss = salarioBruto * 0.11;
        }
        saida += "\nDesconto INSS: " + NumberFormat.getCurrencyInstance().format(descInss);
        double descValTrans = salarioBruto * 0.06;
        saida += "\nDesconto Vale Transporte: " + NumberFormat.getCurrencyInstance().format(descValTrans);
        double descImpRenda = 0;
        if (salarioBruto >= 1500 && salarioBruto <= 2500) {
            descImpRenda = salarioBruto * 0.07;
        } else {
            if (salarioBruto > 2500 && salarioBruto <= 3000) {
                descImpRenda = salarioBruto * 0.11;
            } else {
                if (salarioBruto > 3000) {
                    descImpRenda = salarioBruto * 0.25;
                }
            }
        }
        saida += "\nDesconto Imposto de Renda: " + NumberFormat.getCurrencyInstance().format(descImpRenda);
        double salarioLiquido = salarioBruto - descInss - descValTrans - descImpRenda;
        saida += "\nSalário líquido: " + NumberFormat.getCurrencyInstance().format(salarioLiquido);
        taSalario.setText(saida);
        return salarioLiquido;
    }

    private void cadastrar() throws Exception {
        Funcionario funcionario = new Funcionario();
        if ("".equals(tfNome.getText().trim())) {
            tfNome.grabFocus();
            throw new Exception("Campo nome inválido");
        }
        funcionario.setNome(tfNome.getText());
        funcionario.setIdade(getInteger(tfIdade, "idade", 16, "Campo idade deve ter valor superior a 15"));
        funcionario.setValorHora(getDouble(tfValorHora, "valor por hora", 0, "Campo valor por hora deve ter valor superior a 1"));
        funcionario.setHoraTrabalhada(getInteger(tfHorasTrabalhadas, "hora trabalhadas", 1, "Campo horas trabalhadas deve ter valor superior a 1"));
        funcionario.setHoraTrabalhada50(getInteger(tfHorasTrabalhadas50, "hora trabalhadas 50%", 0, "Campo horas trabalhadas 50% deve ter valor superior a 0"));
        funcionario.setHoraTrabalhada100(getInteger(tfHorasTrabalhadas100, "hora trabalhadas 100%", 0, "Campo horas trabalhadas 100% deve ter valor superior a 0"));
        if ("Selecione".equals(cbCargo.getSelectedItem())) {
            cbCargo.grabFocus();
            throw new Exception("Campo cargo inválido");
        }
        funcionario.setCargo((String) cbCargo.getSelectedItem());
        funcionario.setSalarioLiquido(getCalculoSalario());
        cadastrar.addFuncionario(funcionario);
        btCadastrar.setEnabled(false);
    }

    private void novo() {
        tfNome.setText("");
        tfIdade.setText("");
        tfValorHora.setText("");
        tfHorasTrabalhadas.setText("");
        tfHorasTrabalhadas50.setText("");
        tfHorasTrabalhadas100.setText("");
        taSalario.setText("");
        cbCargo.setSelectedItem("Selecione");
        btCadastrar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCadastrar) {
            try {
                cadastrar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btNovo) {
            novo();
        }
        if (evento.getSource() == btFinalizar) {
            this.dispose();
            String saida = cadastrar.getSaida();
            if (saida == null) {
                System.exit(0);
            } else {
                new Dialogo(saida);
            }
        }
    }
}
