package ArquivoTexto;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class Sistema {

    private static JTextField tfNome, tfCredito;
    private static JFormattedTextField ftfDataNascimento;
    private static MaskFormatter mascaraDataNasc;
    private static JButton btOk, btCancelar;
    private static JDialog tela;
    private static SimpleDateFormat sDataf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        boolean resposta = false;
        do {
            mensagem();
            int n = JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais um cliente?", "Entrada de dados", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                resposta = true;
            } else {
                try {
                    int linha = linha();
                    ClienteDAOTxt clienteDAOTxt = new ClienteDAOTxt("Cliente.txt");
                    if (linha >= 0) {
                        System.out.println(clienteDAOTxt.ler(linha).getNome());
                        System.out.println(clienteDAOTxt.ler(linha).getCredito());
                        System.out.println(sDataf.format(clienteDAOTxt.ler(linha).getDataNasc()) + "\n");
                    }
                    ArrayList<Cliente> lista = new ArrayList<Cliente>();
                    lista = clienteDAOTxt.listarTodos();
                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println("Nome: " + lista.get(i).getNome() + " " + "Credito: " + lista.get(i).getCredito() + " " + "Data Nascimento: " + sDataf.format(lista.get(i).getDataNasc()));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

                System.exit(0);
            }
        } while (resposta == true);
    }

    private static int linha() {
        boolean erro = true;
        int linha = 0;
        do {
            String s = JOptionPane.showInputDialog(null, "Informe a linha a ser lida", "Leitura", JOptionPane.WARNING_MESSAGE);
            if (s == null) {
                return -1;
            }
            try {
                linha = Integer.parseInt(s);
                erro = false;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Entre com um numero válido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (erro);
        return linha;
    }

    public static void mensagem() {
        tela = new JDialog();
        tela.setTitle("Dados do Cliente");
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);

        JLabel lbNome = new JLabel("Nome:");
        lbNome.setBounds(15, 15, 100, 14);
        lbNome.setFont(fonte);
        tela.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(60, 12, 150, 20);
        tela.add(tfNome);

        JLabel lbCredito = new JLabel("Credito:");
        lbCredito.setBounds(215, 15, 100, 14);
        lbCredito.setFont(fonte);
        tela.add(lbCredito);

        tfCredito = new JTextField();
        tfCredito.setBounds(265, 12, 100, 20);
        tela.add(tfCredito);

        JLabel lbDataNascimento = new JLabel("Data Nascimento:");
        lbDataNascimento.setBounds(372, 15, 100, 14);
        lbDataNascimento.setFont(fonte);
        tela.add(lbDataNascimento);

        try {
            mascaraDataNasc = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        ftfDataNascimento = new JFormattedTextField(mascaraDataNasc);
        ftfDataNascimento.setBounds(473, 12, 90, 20);
        tela.add(ftfDataNascimento);

        btOk = new JButton("OK");
        btOk.setBounds(150, 45, 100, 26);
        tela.add(btOk);
        btOk.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent evento) {
                        try {
                            gravar();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(300, 45, 100, 26);
        tela.add(btCancelar);
        btCancelar.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent evento) {
                        System.exit(0);
                    }
                });

        tela.setResizable(false);
        tela.setSize(600, 120);
        tela.setLocationRelativeTo(null);
        tela.setModal(true);
        tela.setVisible(true);
    }

    private static void gravar() throws Exception {
        String nome;
        double credito;
        Date dataNasc;

        if ("".equals(tfNome.getText())) {
            tfNome.grabFocus();
            throw new Exception("Campo nome inválido");
        } else {
            nome = tfNome.getText();
            if ("".equals(tfCredito.getText())) {
                tfCredito.grabFocus();
                throw new Exception("Campo credito inválido");
            } else {
                try {
                    credito = Double.parseDouble(tfCredito.getText());
                } catch (Exception ex) {
                    tfCredito.grabFocus();
                    throw new Exception("Campo credito inválido");
                }
                if ("  /  /    ".equals(ftfDataNascimento.getText())) {
                    ftfDataNascimento.grabFocus();
                    throw new Exception("Campo data nascimento inválido");
                } else {
                    dataNasc = sDataf.parse(ftfDataNascimento.getText());
                    Cliente cliente = new Cliente();
                    cliente.setNome(nome);
                    cliente.setCredito(credito);
                    cliente.setDataNasc(dataNasc);
                    ClienteDAOTxt clienteDAOTxt = new ClienteDAOTxt("Cliente.txt");
                    try {
                        clienteDAOTxt.gravar(cliente);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    tela.setVisible(false);
                }
            }
        }
    }
}
