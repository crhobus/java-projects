package CaioRenanHobus;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CadasAlunos extends JFrame implements ActionListener {

    private JTextField tfDiciplinas, tfNumero, tfNome, tfDisc1, tfDisc2, tfDisc3, tfDisc4, tfDisc5, tfDisc6, tfDisc7, tfDisc8, tfDisc9, tfDisc10, tfLeituraNum;
    private JButton btGravarDisc, btGravarAlunos, btOk, btCancelar;
    private JTextArea taArea;
    private Arquivo arquivo;

    public CadasAlunos() {
        super("Cadastro de Alunos");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(10, 10, 500, 70);
        tela.add(painel1);
        painel1.setBorder(BorderFactory.createTitledBorder("Cadastro de Disciplinas"));
        JPanel painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(10, 80, 500, 195);
        tela.add(painel2);
        painel2.setBorder(BorderFactory.createTitledBorder("Cadastro de Alunos"));
        JPanel painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(10, 275, 500, 290);
        tela.add(painel3);
        painel3.setBorder(BorderFactory.createTitledBorder("Leitura dos Registros"));
        try {
            arquivo = new Arquivo("Alunos", "Disciplinas");
        } catch (Exception ex) {
        }

        JLabel lbDisciplina = new JLabel("Disciplina");
        lbDisciplina.setBounds(20, 35, 80, 14);
        lbDisciplina.setFont(fonte);
        painel1.add(lbDisciplina);

        tfDiciplinas = new JTextField(new LimiteCampo(30), "", 30);
        tfDiciplinas.setBounds(80, 33, 280, 20);
        painel1.add(tfDiciplinas);

        btGravarDisc = new JButton("Gravar");
        btGravarDisc.setBounds(370, 30, 80, 26);
        painel1.add(btGravarDisc);
        btGravarDisc.addActionListener(this);

        JLabel lbNumero = new JLabel("Numero");
        lbNumero.setBounds(20, 35, 80, 14);
        lbNumero.setFont(fonte);
        painel2.add(lbNumero);

        tfNumero = new JTextField();
        tfNumero.setBounds(70, 33, 80, 20);
        tfNumero.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfNumero);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(157, 35, 80, 14);
        lbNome.setFont(fonte);
        painel2.add(lbNome);

        tfNome = new JTextField(new LimiteCampo(30), "", 30);
        tfNome.setBounds(197, 33, 280, 20);
        painel2.add(tfNome);

        JLabel lbDisc1 = new JLabel("Disciplina 1");
        lbDisc1.setBounds(20, 65, 80, 14);
        lbDisc1.setFont(fonte);
        painel2.add(lbDisc1);

        tfDisc1 = new JTextField();
        tfDisc1.setBounds(90, 63, 80, 20);
        tfDisc1.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc1);

        JLabel lbDisc2 = new JLabel("Disciplina 2");
        lbDisc2.setBounds(174, 65, 80, 14);
        lbDisc2.setFont(fonte);
        painel2.add(lbDisc2);

        tfDisc2 = new JTextField();
        tfDisc2.setBounds(243, 63, 80, 20);
        tfDisc2.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc2);

        JLabel lbDisc3 = new JLabel("Disciplina 3");
        lbDisc3.setBounds(328, 65, 80, 14);
        lbDisc3.setFont(fonte);
        painel2.add(lbDisc3);

        tfDisc3 = new JTextField();
        tfDisc3.setBounds(397, 63, 80, 20);
        tfDisc3.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc3);

        JLabel lbDisc4 = new JLabel("Disciplina 4");
        lbDisc4.setBounds(20, 95, 80, 14);
        lbDisc4.setFont(fonte);
        painel2.add(lbDisc4);

        tfDisc4 = new JTextField();
        tfDisc4.setBounds(90, 93, 80, 20);
        tfDisc4.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc4);

        JLabel lbDisc5 = new JLabel("Disciplina 5");
        lbDisc5.setBounds(174, 95, 80, 14);
        lbDisc5.setFont(fonte);
        painel2.add(lbDisc5);

        tfDisc5 = new JTextField();
        tfDisc5.setBounds(243, 93, 80, 20);
        tfDisc5.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc5);

        JLabel lbDisc6 = new JLabel("Disciplina 6");
        lbDisc6.setBounds(328, 95, 80, 14);
        lbDisc6.setFont(fonte);
        painel2.add(lbDisc6);

        tfDisc6 = new JTextField();
        tfDisc6.setBounds(397, 93, 80, 20);
        tfDisc6.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc6);

        JLabel lbDisc7 = new JLabel("Disciplina 7");
        lbDisc7.setBounds(20, 125, 80, 14);
        lbDisc7.setFont(fonte);
        painel2.add(lbDisc7);

        tfDisc7 = new JTextField();
        tfDisc7.setBounds(90, 123, 80, 20);
        tfDisc7.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc7);

        JLabel lbDisc8 = new JLabel("Disciplina 8");
        lbDisc8.setBounds(174, 125, 80, 14);
        lbDisc8.setFont(fonte);
        painel2.add(lbDisc8);

        tfDisc8 = new JTextField();
        tfDisc8.setBounds(243, 123, 80, 20);
        tfDisc8.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc8);

        JLabel lbDisc9 = new JLabel("Disciplina 9");
        lbDisc9.setBounds(328, 125, 80, 14);
        lbDisc9.setFont(fonte);
        painel2.add(lbDisc9);

        tfDisc9 = new JTextField();
        tfDisc9.setBounds(397, 123, 80, 20);
        tfDisc9.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc9);

        JLabel lbDisc10 = new JLabel("Disciplina 10");
        lbDisc10.setBounds(19, 155, 80, 14);
        lbDisc10.setFont(fonte);
        painel2.add(lbDisc10);

        tfDisc10 = new JTextField();
        tfDisc10.setBounds(92, 153, 80, 20);
        tfDisc10.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel2.add(tfDisc10);

        btGravarAlunos = new JButton("Gravar");
        btGravarAlunos.setBounds(190, 149, 80, 26);
        painel2.add(btGravarAlunos);
        btGravarAlunos.addActionListener(this);

        JLabel lbLeituraNum = new JLabel("Linha");
        lbLeituraNum.setBounds(20, 35, 80, 14);
        lbLeituraNum.setFont(fonte);
        painel3.add(lbLeituraNum);

        tfLeituraNum = new JTextField();
        tfLeituraNum.setBounds(70, 33, 80, 20);
        tfLeituraNum.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel3.add(tfLeituraNum);
        tfLeituraNum.addActionListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(160, 29, 55, 26);
        painel3.add(btOk);
        btOk.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(240, 29, 85, 26);
        painel3.add(btCancelar);
        btCancelar.addActionListener(this);

        taArea = new JTextArea();
        taArea.setBounds(20, 60, 460, 220);
        taArea.setEditable(false);
        taArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        painel3.add(taArea);

        addWindowListener(new WindowListener() {

            public void windowClosing(WindowEvent e) {
                try {
                    arquivo.fecharArquivos();
                } catch (Exception ex) {
                }
            }

            public void windowOpened(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        painel2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(530, 602);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setVisible(true);
    }

    private void gravarDisciplina() throws Exception {
        if ("".equals(tfDiciplinas.getText())) {
            tfDiciplinas.grabFocus();
            throw new Exception("Campo diciplinas inválido");
        } else {
            Disciplinas disciplinas = new Disciplinas();
            char vetorDisc[] = new char[30];
            String nome = tfDiciplinas.getText();
            int qtdade = nome.length();
            int i = 0;
            while (i < qtdade) {
                vetorDisc[i] = nome.charAt(i);
                i++;
            }
            disciplinas.setDisciplinas(vetorDisc);
            arquivo.gravarDisciplinas(disciplinas);
            JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso", "Aluno", JOptionPane.INFORMATION_MESSAGE);
            limparTela();
        }
    }

    private void gravarAlunos() throws Exception {
        Alunos alunos = new Alunos();
        if ("".equals(tfNumero.getText()) || Integer.parseInt(tfNumero.getText()) <= 0) {
            tfNumero.grabFocus();
            throw new Exception("Campo numero inválido");
        } else {
            alunos.setNumero(Integer.parseInt(tfNumero.getText()));
        }
        if ("".equals(tfNome.getText())) {
            tfNome.grabFocus();
            throw new Exception("Campo nome inválido");
        } else {
            char vetorNomeAluno[] = new char[30];
            String nomeAlu = tfNome.getText();
            int qtdadeAlu = nomeAlu.length();
            int y = 0;
            while (y < qtdadeAlu) {
                vetorNomeAluno[y] = nomeAlu.charAt(y);
                y++;
            }
            alunos.setNome(vetorNomeAluno);
        }
        byte disciCur[] = new byte[10];
        if (!"".equals(tfDisc1.getText())) {
            if (Integer.parseInt(tfDisc1.getText()) <= 0 || Integer.parseInt(tfDisc1.getText()) > 127) {
                tfDisc1.grabFocus();
                throw new Exception("Campo disciplina 1 inválido");
            } else {
                disciCur[0] = Byte.parseByte(tfDisc1.getText());
            }
        }
        if (!"".equals(tfDisc2.getText())) {
            if (Integer.parseInt(tfDisc2.getText()) <= 0 || Integer.parseInt(tfDisc2.getText()) > 127) {
                tfDisc2.grabFocus();
                throw new Exception("Campo disciplina 2 inválido");
            } else {
                disciCur[1] = Byte.parseByte(tfDisc2.getText());
            }
        }
        if (!"".equals(tfDisc3.getText())) {
            if (Integer.parseInt(tfDisc3.getText()) <= 0 || Integer.parseInt(tfDisc3.getText()) > 127) {
                tfDisc3.grabFocus();
                throw new Exception("Campo disciplina 3 inválido");
            } else {
                disciCur[2] = Byte.parseByte(tfDisc3.getText());
            }
        }
        if (!"".equals(tfDisc4.getText())) {
            if (Integer.parseInt(tfDisc4.getText()) <= 0 || Integer.parseInt(tfDisc4.getText()) > 127) {
                tfDisc4.grabFocus();
                throw new Exception("Campo disciplina 4 inválido");
            } else {
                disciCur[3] = Byte.parseByte(tfDisc4.getText());
            }
        }
        if (!"".equals(tfDisc5.getText())) {
            if (Integer.parseInt(tfDisc5.getText()) <= 0 || Integer.parseInt(tfDisc5.getText()) > 127) {
                tfDisc5.grabFocus();
                throw new Exception("Campo disciplina 5 inválido");
            } else {
                disciCur[4] = Byte.parseByte(tfDisc5.getText());
            }
        }
        if (!"".equals(tfDisc6.getText())) {
            if (Integer.parseInt(tfDisc6.getText()) <= 0 || Integer.parseInt(tfDisc6.getText()) > 127) {
                tfDisc6.grabFocus();
                throw new Exception("Campo disciplina 6 inválido");
            } else {
                disciCur[5] = Byte.parseByte(tfDisc6.getText());
            }
        }
        if (!"".equals(tfDisc7.getText())) {
            if (Integer.parseInt(tfDisc7.getText()) <= 0 || Integer.parseInt(tfDisc7.getText()) > 127) {
                tfDisc7.grabFocus();
                throw new Exception("Campo disciplina 7 inválido");
            } else {
                disciCur[6] = Byte.parseByte(tfDisc7.getText());
            }
        }
        if (!"".equals(tfDisc8.getText())) {
            if (Integer.parseInt(tfDisc8.getText()) <= 0 || Integer.parseInt(tfDisc8.getText()) > 127) {
                tfDisc8.grabFocus();
                throw new Exception("Campo disciplina 8 inválido");
            } else {
                disciCur[7] = Byte.parseByte(tfDisc8.getText());
            }
        }
        if (!"".equals(tfDisc9.getText())) {
            if (Integer.parseInt(tfDisc9.getText()) <= 0 || Integer.parseInt(tfDisc9.getText()) > 127) {
                tfDisc9.grabFocus();
                throw new Exception("Campo disciplina 9 inválido");
            } else {
                disciCur[8] = Byte.parseByte(tfDisc9.getText());
            }
        }
        if (!"".equals(tfDisc10.getText())) {
            if (Integer.parseInt(tfDisc10.getText()) <= 0 || Integer.parseInt(tfDisc10.getText()) > 127) {
                tfDisc10.grabFocus();
                throw new Exception("Campo disciplina 10 inválido");
            } else {
                disciCur[9] = Byte.parseByte(tfDisc10.getText());
            }
        }
        alunos.setDiscCursadas(disciCur);
        arquivo.gravarAlunos(alunos);
        JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso", "Aluno", JOptionPane.INFORMATION_MESSAGE);
        limparTela();
    }

    private void ler(int numero) throws Exception {
        String texto = "";
        int ler = numero;
        texto += "Número: " + arquivo.lerAlunos(ler).getNumero() + "\n";
        texto += "Nome: ";
        char nomeAlun[] = arquivo.lerAlunos(ler).getNome();
        for (int i = 0; i < nomeAlun.length; i++) {
            texto += nomeAlun[i];
        }
        texto += "\n";
        byte discCur[] = arquivo.lerAlunos(ler).getDiscCursadas();
        int dis1 = discCur[0];
        int dis2 = discCur[1];
        int dis3 = discCur[2];
        int dis4 = discCur[3];
        int dis5 = discCur[4];
        int dis6 = discCur[5];
        int dis7 = discCur[6];
        int dis8 = discCur[7];
        int dis9 = discCur[8];
        int dis10 = discCur[9];
        try {
            texto += "Disciplina 1: " + dis1 + " = " + nomeDisciplina(dis1) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 2: " + dis2 + " = " + nomeDisciplina(dis2) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 3: " + dis3 + " = " + nomeDisciplina(dis3) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 4: " + dis4 + " = " + nomeDisciplina(dis4) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 5: " + dis5 + " = " + nomeDisciplina(dis5) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 6: " + dis6 + " = " + nomeDisciplina(dis6) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 7: " + dis7 + " = " + nomeDisciplina(dis7) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 8: " + dis8 + " = " + nomeDisciplina(dis8) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 9: " + dis9 + " = " + nomeDisciplina(dis9) + "\n";
        } catch (Exception ex) {
        }
        try {
            texto += "Disciplina 10: " + dis10 + " = " + nomeDisciplina(dis10) + "\n";
        } catch (Exception ex) {
        }
        taArea.setText(texto);
    }

    private String nomeDisciplina(int dis) throws Exception {
        String s = "";
        char nomeDisc[] = arquivo.lerDisciplinas(dis).getDisciplinas();
        for (int i = 0; i < nomeDisc.length; i++) {
            s += nomeDisc[i];
        }
        return s;
    }

    private void limparTela() {
        tfDiciplinas.setText("");
        tfNumero.setText("");
        tfNome.setText("");
        tfDisc1.setText("");
        tfDisc2.setText("");
        tfDisc3.setText("");
        tfDisc4.setText("");
        tfDisc5.setText("");
        tfDisc6.setText("");
        tfDisc7.setText("");
        tfDisc8.setText("");
        tfDisc9.setText("");
        tfDisc10.setText("");
        tfLeituraNum.setText("");
        taArea.setText("");
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btGravarDisc) {
            try {
                gravarDisciplina();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btGravarAlunos) {
            try {
                gravarAlunos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOk || evento.getSource() == tfLeituraNum) {
            if ("".equals(tfLeituraNum.getText())) {
                tfLeituraNum.grabFocus();
                JOptionPane.showMessageDialog(null, "Campo número invalido", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ler(Integer.parseInt(tfLeituraNum.getText()));
                } catch (Exception ex) {
                    limparTela();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
    }
}
