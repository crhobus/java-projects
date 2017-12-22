
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class Usuario extends JFrame {

    private ArrayList<ArrayUsuario> lista = new ArrayList();
    private JTextField tf_Codigo, tf_Nome, tf_Permissao;
    private JPasswordField tf_Senha, tf_ConfirmaSenha;
    private JButton Ok, Cancelar, Pesquisar, Ver;
    private JRadioButton Novo, Alterar, Excluir;
    private int INTNovo = 0, INTAlterar = 0, INTExluir = 0;

    Usuario() {
        super("Cadastro de Usuário");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte1 = new Font("Arial", Font.PLAIN, 12);
        Font fonte2 = new Font("Arial Black", Font.BOLD, 11);
        TrataEventos trata = new TrataEventos();
        Manipula manuseia = new Manipula();
        TrataCores cores = new TrataCores();
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 20, 425, 170);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Usuário"));

        JLabel lb_Codigo = new JLabel("Codigo");
        lb_Codigo.setBounds(20, 40, 80, 14);
        lb_Codigo.setFont(fonte1);
        Painel1.add(lb_Codigo);

        tf_Codigo = new JTextField();
        tf_Codigo.setBounds(20, 58, 80, 20);
        int codigoint = lista.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        Painel1.add(tf_Codigo);
        tf_Codigo.addFocusListener(cores);
        tf_Codigo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Codigo();
            }
        });

        Ver = new JButton("...");
        Ver.setBounds(104, 56, 31, 24);
        Painel1.add(Ver);
        Ver.addActionListener(trata);

        JLabel lb_Nome = new JLabel("Nome");
        lb_Nome.setBounds(140, 40, 80, 14);
        lb_Nome.setFont(fonte1);
        Painel1.add(lb_Nome);

        tf_Nome = new JTextField();
        tf_Nome.setBounds(140, 58, 243, 20);
        Painel1.add(tf_Nome);
        tf_Nome.addFocusListener(cores);

        JLabel lb_Senha = new JLabel("Senha");
        lb_Senha.setBounds(20, 78, 90, 14);
        lb_Senha.setFont(fonte1);
        Painel1.add(lb_Senha);

        tf_Senha = new JPasswordField();
        tf_Senha.setBounds(20, 94, 110, 20);
        tf_Senha.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        Painel1.add(tf_Senha);
        tf_Senha.addFocusListener(cores);

        JLabel lb_Permissao = new JLabel("Permissão");
        lb_Permissao.setBounds(20, 114, 80, 14);
        lb_Permissao.setFont(fonte1);
        Painel1.add(lb_Permissao);

        tf_Permissao = new JTextField();
        tf_Permissao.setBounds(20, 130, 110, 20);
        tf_Permissao.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        Painel1.add(tf_Permissao);
        tf_Permissao.addFocusListener(cores);

        JLabel lb_ConfirmaSenha = new JLabel("Confirme sua senha");
        lb_ConfirmaSenha.setBounds(195, 70, 130, 50);
        lb_ConfirmaSenha.setFont(fonte1);
        Painel1.add(lb_ConfirmaSenha);

        tf_ConfirmaSenha = new JPasswordField();
        tf_ConfirmaSenha.setBounds(195, 120, 120, 20);
        tf_ConfirmaSenha.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        Painel1.add(tf_ConfirmaSenha);
        tf_ConfirmaSenha.addFocusListener(cores);

        JLabel lb_Asteristico = new JLabel("*");
        lb_Asteristico.setBounds(180, 120, 10, 10);
        lb_Asteristico.setForeground(Color.RED);//Cor Vermelha no lb_Asteristico
        lb_Asteristico.setFont(fonte2);
        Painel1.add(lb_Asteristico);

        Pesquisar = new JButton("Pesquisar");
        Pesquisar.setBounds(455, 25, 100, 26);
        tela.add(Pesquisar);
        Pesquisar.addActionListener(trata);

        Novo = new JRadioButton("Novo");
        Novo.setBounds(458, 70, 80, 20);
        Novo.setFont(fonte1);
        tela.add(Novo);
        Novo.addItemListener(manuseia);

        Alterar = new JRadioButton("Alterar");
        Alterar.setBounds(458, 90, 80, 20);
        Alterar.setFont(fonte1);
        tela.add(Alterar);
        Alterar.addItemListener(manuseia);

        Excluir = new JRadioButton("Excluir");
        Excluir.setBounds(458, 110, 80, 20);
        Excluir.setFont(fonte1);
        tela.add(Excluir);
        Excluir.addItemListener(manuseia);

        Ok = new JButton("OK");
        Ok.setBounds(455, 150, 100, 26);
        tela.add(Ok);
        Ok.addActionListener(trata);

        Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(455, 190, 100, 26);
        tela.add(Cancelar);
        Cancelar.addActionListener(trata);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivo();
        setResizable(false);
        setSize(585, 255);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setVisible(true);
    }

    private void OK() {
        int senha = 0, confirmasenha = 0;
        String textosenha, textoconfirmasenha;
        if (!"".equals(tf_Senha.getText()) & !"".equals(tf_ConfirmaSenha.getText())) {
            textosenha = tf_Senha.getText();
            senha = Integer.parseInt(textosenha);
            textoconfirmasenha = tf_ConfirmaSenha.getText();
            confirmasenha = Integer.parseInt(textoconfirmasenha);

            if (senha != confirmasenha) {
                JOptionPane.showMessageDialog(null, "Confirme a senha", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            } else {
                if (lista.isEmpty() == true) {//Verifica se lista esta ou não vazia
                    Gravar();
                } else {
                    boolean encontrado = false;
                    for (int i = 0; i < lista.size(); i++) {
                        ArrayUsuario aux = lista.get(i);
                        if (tf_Nome.getText().equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                            encontrado = true;
                        }
                    }
                    if (encontrado == true) {
                        int resposta = JOptionPane.showConfirmDialog(null, "O cliente " + tf_Nome.getText() + " ja esta inserido na lista deseja Substituilo? ", "Cliente", JOptionPane.YES_NO_OPTION);
                        if (resposta == 0) {
                            for (int i = 0; i < lista.size(); i++) {
                                ArrayUsuario aux = lista.get(i);
                                if (tf_Nome.getText().equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                                    lista.remove(aux);
                                }
                            }
                            Gravar();
                        }
                    } else {
                        Gravar();
                    }
                }
            }
        }
    }

    private void LimparTela() {
        int codigoint = lista.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        tf_Nome.setText("");
        tf_Senha.setText("");
        tf_Permissao.setText("");
        tf_ConfirmaSenha.setText("");
        Novo.setSelected(false);
        Alterar.setSelected(false);
        Excluir.setSelected(false);
        INTNovo = 0;
        INTAlterar = 0;
        INTExluir = 0;
    }

    private void Excluir() {
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Usuarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = JOptionPane.showInputDialog(null, "Informe o Nome do Usuario a ser Excluído:", "Saída de dados", JOptionPane.WARNING_MESSAGE);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayUsuario aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    lista.remove(aux);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Usuario Removido com sucesso", "Usuario", JOptionPane.INFORMATION_MESSAGE);
                LimparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario  não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Recuperar() {
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Usuarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Usuario", JOptionPane.WARNING_MESSAGE);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayUsuario aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    int TextoINTCodigo = aux.getCodigo();
                    String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                    tf_Codigo.setText(TextoCodigo);
                    String TextoNome = aux.getNome();
                    tf_Nome.setText(TextoNome);
                    int TextoINTSenha = aux.getSenha();
                    String TextoSenha = Integer.toString(TextoINTSenha);//Conversão int para String
                    tf_Senha.setText(TextoSenha);
                    int TextoINTPermissao = aux.getSenha();
                    String TextoPermissao = Integer.toString(TextoINTPermissao);//Conversão int para String
                    tf_Permissao.setText(TextoPermissao);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Usuario Encontrado e Recuperado da Lista", "Usuario", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Ver() {
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Usuarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            VerUsuario aux = new VerUsuario(lista);
        }
    }

    private void Quem() {
        int Codigo = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Usuarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Usuario", JOptionPane.WARNING_MESSAGE);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayUsuario aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    Codigo = aux.getCodigo();
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "O Usuario " + Texto + " esta inserido com o codigo " + Codigo, "Usuário", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "O Usuario " + Texto + " não esta inserido", "Usuario", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void Gravar() {
        String textocod, textoper, textosen, InserNome = "";
        int InserCodigo = 0, InserPermissao = 0, InserSenha = 0;

        if (!"".equals(tf_Codigo.getText())) {//Se campo não esta vazio
            textocod = tf_Codigo.getText();
            InserCodigo = Integer.parseInt(textocod);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Codigo.setText("");//Limpa o campo
            tf_Codigo.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Nome.getText())) {//Se campo não esta vazio
            InserNome = tf_Nome.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Nome esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Nome.setText("");//Limpa o campo
            tf_Nome.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Senha.getText())) {//Se campo não esta vazio
            textosen = tf_Senha.getText();
            InserSenha = Integer.parseInt(textosen);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Senha esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Senha.setText("");//Limpa o campo
            tf_Senha.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Permissao.getText())) {//Se campo não esta vazio
            textoper = tf_Permissao.getText();
            InserPermissao = Integer.parseInt(textoper);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Permissao esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Permissao.setText("");//Limpa o campo
            tf_Permissao.grabFocus();//Focaliza o campo
        }
        if (!"".equals(tf_Codigo.getText()) & !"".equals(tf_Nome.getText()) & !"".equals(tf_Senha.getText()) & !"".equals(tf_Permissao.getText())) {
            ArrayUsuario aux = new ArrayUsuario(InserNome, InserSenha, InserCodigo, InserPermissao);
            lista.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Usuários cadastrados: " + lista.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
            LimparTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Codigo() {
        if ("".equals(tf_Codigo.getText())) {
            LimparTela();
        } else {
            if (lista.isEmpty() == true) {
            } else {
                String t = tf_Codigo.getText();
                int n = Integer.parseInt(t);
                boolean encontrado = false;
                for (int i = 0; i < lista.size(); i++) {
                    ArrayUsuario aux = lista.get(i);
                    if (n == (aux.getCodigo())) {
                        encontrado = true;
                    }
                }
                if (encontrado == true) {
                    LimparTela();
                }
            }
        }
    }

    private void Arquivo() {
        try {
            FileOutputStream arqDados = new FileOutputStream("Usuario.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Usuario.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayUsuario>) entra.readObject();
            entra.close();
            int codigoint = lista.size();
            String codigoString = Integer.toString(codigoint + 1);
            tf_Codigo.setText(codigoString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Manipula implements ItemListener {

        public void itemStateChanged(ItemEvent evento) {
            if (evento.getSource() == Novo) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 1;
                    INTAlterar = 0;
                    INTExluir = 0;
                    Alterar.setSelected(false);
                    Excluir.setSelected(false);
                }
            }
            if (evento.getSource() == Alterar) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 0;
                    INTAlterar = 1;
                    INTExluir = 0;
                    Novo.setSelected(false);
                    Excluir.setSelected(false);
                }
            }
            if (evento.getSource() == Excluir) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 0;
                    INTAlterar = 0;
                    INTExluir = 1;
                    Novo.setSelected(false);
                    Alterar.setSelected(false);
                }
            }
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == Pesquisar) {
                Quem();
            }
            if (evento.getSource() == Ok) {
                if (Novo.isSelected() == false & Alterar.isSelected() == false & Excluir.isSelected() == false) {
                    JOptionPane.showMessageDialog(null, "Selecione uma opção", "Fornecedor", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (Novo.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                        OK();
                        Arquivo();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTExluir = 0;
                    }
                    if (Alterar.isSelected() == true) {
                        Recuperar();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTExluir = 0;
                    }
                    if (Excluir.isSelected() == true) {
                        Excluir();
                        Arquivo();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTExluir = 0;
                    }
                }
            }
            if (evento.getSource() == Cancelar) {
                LimparTela();
            }
            if (evento.getSource() == Ver) {
                Ver();
            }
        }
    }

    private class TrataCores implements FocusListener {

        public void focusGained(FocusEvent evento) {//Ganha Focus
            if (evento.getSource() == tf_Codigo) {
                tf_Codigo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Nome) {
                tf_Nome.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Senha) {
                tf_Senha.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ConfirmaSenha) {
                tf_ConfirmaSenha.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Permissao) {
                tf_Permissao.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            tf_Nome.setBackground(Color.WHITE);
            tf_Senha.setBackground(Color.WHITE);
            tf_ConfirmaSenha.setBackground(Color.WHITE);
            tf_Permissao.setBackground(Color.WHITE);
        }
    }
}
