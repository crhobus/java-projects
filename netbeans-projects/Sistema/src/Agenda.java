
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.io.*;

public class Agenda extends JFrame {

    private ArrayList<ArrayAgenda> lista = new ArrayList();
    private JTextField tfNome, tfDescricao, tfCodigo;
    private JFormattedTextField data, hora;
    private MaskFormatter mascaraData, mascaraHora;
    private JButton btSalvar, btCancelar;
    private String nome;
    private JRadioButton novo, alterar, excluir;
    private int intNovo = 0, intAlterar = 0, intExluir = 0;
    private Principal principal;

    public Agenda(Principal principal) {
        super("Agenda dos Funcionários");
        this.principal = principal;
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(10, 10, 440, 168);
        tela.add(painel1);
        painel1.setBorder(BorderFactory.createTitledBorder("Agenda"));
        TrataEventos trata = new TrataEventos();
        Manipula manuseia = new Manipula();
        TrataCores cores = new TrataCores();

        JLabel lb_Codigo = new JLabel("Codigo");
        lb_Codigo.setBounds(20, 40, 80, 14);
        lb_Codigo.setFont(fonte);
        painel1.add(lb_Codigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        int codigoint = lista.size();
        String codigoString = Integer.toString(codigoint + 1);
        tfCodigo.setText(codigoString);
        tfCodigo.setDocument(new MeuDocument());
        painel1.add(tfCodigo);
        tfCodigo.addFocusListener(cores);
        tfCodigo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Codigo();
            }
        });

        JLabel lb_Nome = new JLabel("Nome");
        lb_Nome.setBounds(112, 40, 80, 14);
        lb_Nome.setFont(fonte);
        painel1.add(lb_Nome);

        tfNome = new JTextField();
        tfNome.setBounds(112, 58, 280, 20);
        painel1.add(tfNome);
        tfNome.addFocusListener(cores);
        tfNome.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent evento) {
                tfDescricao.grabFocus();
                botaoAbrirPressionado();//Quando tf_Nome estiver focalizado executa método botaoAbrirPressionado();
            }
        });

        JLabel lb_Descricao = new JLabel("Descrição");
        lb_Descricao.setBounds(20, 78, 80, 14);
        lb_Descricao.setFont(fonte);
        painel1.add(lb_Descricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(20, 95, 372, 20);
        painel1.add(tfDescricao);
        tfDescricao.addFocusListener(cores);
        tfDescricao.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent evento) {
                tfNome.setText(nome);
                nome = "";
            }
        });

        JLabel lb_Data = new JLabel("Data");
        lb_Data.setBounds(20, 114, 80, 14);
        lb_Data.setFont(fonte);
        painel1.add(lb_Data);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraData = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        data = new JFormattedTextField(mascaraData);
        data.setBounds(20, 130, 110, 20);
        painel1.add(data);
        data.addFocusListener(cores);

        JLabel lb_Hora = new JLabel("Horário");
        lb_Hora.setBounds(140, 114, 80, 14);
        lb_Hora.setFont(fonte);
        painel1.add(lb_Hora);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraHora = new MaskFormatter("##:##");
        } catch (ParseException excp) {
        }
        hora = new JFormattedTextField(mascaraHora);
        hora.setBounds(140, 130, 110, 20);
        painel1.add(hora);
        hora.addFocusListener(cores);

        novo = new JRadioButton("Novo");
        novo.setBounds(468, 20, 80, 20);
        novo.setFont(fonte);
        tela.add(novo);
        novo.addItemListener(manuseia);

        alterar = new JRadioButton("Alterar");
        alterar.setBounds(468, 40, 80, 20);
        alterar.setFont(fonte);
        tela.add(alterar);
        alterar.addItemListener(manuseia);

        excluir = new JRadioButton("Excluir");
        excluir.setBounds(468, 60, 80, 20);
        excluir.setFont(fonte);
        tela.add(excluir);
        excluir.addItemListener(manuseia);

        btSalvar = new JButton("Salvar");
        btSalvar.setBounds(465, 100, 100, 26);
        tela.add(btSalvar);
        btSalvar.addActionListener(trata);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(465, 140, 100, 26);
        tela.add(btCancelar);
        btCancelar.addActionListener(trata);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivo();
        setSize(600, 222);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void Ok() {
        if (lista.isEmpty() == true) {
            Gravar();
        } else {
            int n = Integer.parseInt(tfCodigo.getText());
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayAgenda aux = lista.get(i);
                if (n == (aux.getCodigo())) {
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "A Tarefa " + tfCodigo.getText() + " ja esta agendada na lista deseja Substituila? ", "Agenda", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    for (int i = 0; i < lista.size(); i++) {
                        ArrayAgenda aux = lista.get(i);
                        if (n == (aux.getCodigo())) {
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

    private void LimparTela() {
        int codigoint = lista.size();
        String codigoString = Integer.toString(codigoint + 1);
        tfCodigo.setText(codigoString);
        tfNome.setText("");
        tfDescricao.setText("");
        data.setText("");
        hora.setText("");
        novo.setSelected(false);
        alterar.setSelected(false);
        excluir.setSelected(false);
        intNovo = 0;
        intAlterar = 0;
        intExluir = 0;
    }

    private void Excluir() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Tarefas agendadas na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String StringTexto = JOptionPane.showInputDialog(null, "Informe o Codigo da Tarefa a ser Excluída:", "Saída de dados", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(StringTexto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayAgenda aux = lista.get(i);
                if (INTTexto == aux.getCodigo()) {
                    lista.remove(aux);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Tarefa Removida com sucesso", "Agenda", JOptionPane.INFORMATION_MESSAGE);
                LimparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Tarefa não encontrada na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Recuperar() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Tarefas Agendadas na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String Texto = JOptionPane.showInputDialog(null, "Insira codigo: ", "Agenda", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(Texto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayAgenda aux = lista.get(i);
                if (INTTexto == aux.getCodigo()) {
                    tfCodigo.setText(Integer.toString(aux.getCodigo()));//Conversão int para String
                    tfNome.setText(aux.getNome());
                    tfDescricao.setText(aux.getDescricao());
                    data.setText(aux.getData());
                    hora.setText(aux.getHora());
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Tarefa Encontrada e Recuperada da Lista", "Agenda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Tarefa não encontrada na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Gravar() {
        String textocod, InserNome = "", InserDescricao = "", InserData = "", InserHora = "";
        int InserCodigo = 0;

        if (!"".equals(tfCodigo.getText())) {//Se campo não esta vazio
            InserCodigo = Integer.parseInt(tfCodigo.getText());//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tfCodigo.setText("");//Limpa o campo
            tfCodigo.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tfNome.getText())) {//Se campo não esta vazio
            InserNome = tfNome.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Nome esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tfNome.setText("");//Limpa o campo
            tfNome.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tfDescricao.getText())) {//Se campo não esta vazio
            InserDescricao = tfDescricao.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Descrição esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tfDescricao.setText("");//Limpa o campo
            tfDescricao.grabFocus();//Focaliza o campo
        }

        if (!"  /  /    ".equals(data.getText())) {//Se campo não esta vazio
            InserData = data.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Data esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            data.setText("");//Limpa o campo
            data.grabFocus();//Focaliza o campo
        }

        if (!"  :  ".equals(hora.getText())) {//Se campo não esta vazio
            InserHora = hora.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Horário esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            hora.setText("");//Limpa o campo
            hora.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tfCodigo.getText()) & !"".equals(tfNome.getText()) & !"".equals(tfDescricao.getText()) & !"  /  /    ".equals(data.getText()) & !"  :  ".equals(hora.getText())) {
            ArrayAgenda aux = new ArrayAgenda(InserData, InserNome, InserDescricao, InserHora, InserCodigo);
            lista.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Tarefas Agendadas: " + lista.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
            LimparTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Codigo() {
        if ("".equals(tfCodigo.getText())) {
            LimparTela();
        } else {
            if (lista.isEmpty() == true) {
            } else {
                int n = Integer.parseInt(tfCodigo.getText());
                boolean encontrado = false;
                for (int i = 0; i < lista.size(); i++) {
                    ArrayAgenda aux = lista.get(i);
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
            FileOutputStream arqDados = new FileOutputStream("Agenda.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Agenda.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayAgenda>) entra.readObject();
            entra.close();
            int codigoint = lista.size();
            String codigoString = Integer.toString(codigoint + 1);
            tfCodigo.setText(codigoString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void botaoAbrirPressionado() {
        NomeAgenda aux = new NomeAgenda();
        aux.setListener(new InterfaceAgenda() {

            public void campoTextoAlterado(String novoTexto) {
                nome = novoTexto;
            }
        });
        aux.setVisible(true);
    }

    private class Manipula implements ItemListener {

        public void itemStateChanged(ItemEvent evento) {
            if (evento.getSource() == novo) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    intNovo = 1;
                    intAlterar = 0;
                    intExluir = 0;
                    alterar.setSelected(false);
                    excluir.setSelected(false);
                }
            }
            if (evento.getSource() == alterar) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    intNovo = 0;
                    intAlterar = 1;
                    intExluir = 0;
                    novo.setSelected(false);
                    excluir.setSelected(false);
                }
            }
            if (evento.getSource() == excluir) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    intNovo = 0;
                    intAlterar = 0;
                    intExluir = 1;
                    novo.setSelected(false);
                    alterar.setSelected(false);
                }
            }
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == btSalvar) {
                if (novo.isSelected() == false & alterar.isSelected() == false & excluir.isSelected() == false) {
                    JOptionPane.showMessageDialog(null, "Selecione uma opção", "Fornecedor", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (novo.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                        Ok();
                        Arquivo();
                        principal.RecuperarAgenda();
                        intNovo = 0;
                        intAlterar = 0;
                        intExluir = 0;
                    }
                    if (alterar.isSelected() == true) {
                        Recuperar();
                        intNovo = 0;
                        intAlterar = 0;
                        intExluir = 0;
                    }
                    if (excluir.isSelected() == true) {
                        Excluir();
                        Arquivo();
                        principal.RecuperarAgenda();
                        intNovo = 0;
                        intAlterar = 0;
                        intExluir = 0;
                    }
                }
            }
            if (evento.getSource() == btCancelar) {
                LimparTela();
            }
        }
    }

    private class TrataCores implements FocusListener {

        public void focusGained(FocusEvent evento) {//Ganha Focus
            if (evento.getSource() == tfCodigo) {
                tfCodigo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tfNome) {
                tfNome.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tfDescricao) {
                tfDescricao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == data) {
                data.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == hora) {
                hora.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tfCodigo.setBackground(Color.WHITE);
            tfNome.setBackground(Color.WHITE);
            tfDescricao.setBackground(Color.WHITE);
            data.setBackground(Color.WHITE);
            hora.setBackground(Color.WHITE);
        }
    }
}
