package Visao.Agenda;

import Arquivos.*;
import ClassPadrao.MeuDocument;
import Controle.AgendaControl;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class CadasAgenda extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfNome, tfDescricao, tfCodigo;
    private JFormattedTextField ftfData, ftfHora;
    private MaskFormatter mascaraData, mascaraHora;
    private JButton btSalvar, btCancelar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private AgendaControl control;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;
    private String nomeFuncionario;
    private TableModel tableModel;
    private Renderizadora rendener;

    public CadasAgenda() {
        setTitle("Agenda dos Funcionários");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(10, 10, 440, 168);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Agenda"));
        control = new AgendaControl();
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        control.lerArquivo(lerArquivo);
        tableModel = new TableModel(control);
        rendener = new Renderizadora();

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte);
        painel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        tfCodigo.setDocument(new MeuDocument());
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        painel.add(tfCodigo);
        tfCodigo.addFocusListener(this);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(112, 40, 80, 14);
        lbNome.setFont(fonte);
        painel.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(112, 58, 280, 20);
        painel.add(tfNome);
        tfNome.addFocusListener(this);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(20, 78, 80, 14);
        lbDescricao.setFont(fonte);
        painel.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(20, 95, 372, 20);
        painel.add(tfDescricao);
        tfDescricao.addFocusListener(this);

        JLabel lbData = new JLabel("Data");
        lbData.setBounds(20, 114, 80, 14);
        lbData.setFont(fonte);
        painel.add(lbData);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraData = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        ftfData = new JFormattedTextField(mascaraData);
        ftfData.setBounds(20, 130, 110, 20);
        painel.add(ftfData);
        ftfData.addFocusListener(this);

        JLabel lbHora = new JLabel("Horário");
        lbHora.setBounds(140, 114, 80, 14);
        lbHora.setFont(fonte);
        painel.add(lbHora);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraHora = new MaskFormatter("##:##");
        } catch (ParseException excp) {
        }
        ftfHora = new JFormattedTextField(mascaraHora);
        ftfHora.setBounds(140, 130, 110, 20);
        painel.add(ftfHora);
        ftfHora.addFocusListener(this);

        rbNovo = new JRadioButton("Novo");
        rbNovo.setBounds(468, 20, 80, 20);
        rbNovo.setFont(fonte);
        tela.add(rbNovo);
        rbNovo.addItemListener(this);

        rbAlterar = new JRadioButton("Alterar");
        rbAlterar.setBounds(468, 40, 80, 20);
        rbAlterar.setFont(fonte);
        tela.add(rbAlterar);
        rbAlterar.addItemListener(this);

        rbExcluir = new JRadioButton("Excluir");
        rbExcluir.setBounds(468, 60, 80, 20);
        rbExcluir.setFont(fonte);
        tela.add(rbExcluir);
        rbExcluir.addItemListener(this);

        btSalvar = new JButton("Salvar");
        btSalvar.setBounds(465, 100, 100, 26);
        tela.add(btSalvar);
        btSalvar.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(465, 140, 100, 26);
        tela.add(btCancelar);
        btCancelar.addActionListener(this);

        JTable tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(190);
        tabela.getColumnModel().getColumn(2).setMinWidth(80);
        tabela.getColumnModel().getColumn(3).setMinWidth(80);
        tabela.getColumnModel().getColumn(4).setMinWidth(750);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 190, 570, 270);
        tela.add(scroll);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(600, 500);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void gravar() throws Exception {
        String data, nome, descricao, hora;
        int codigo;

        if ("".equals(tfCodigo.getText()) || Integer.parseInt(tfCodigo.getText()) <= 0) {
            tfCodigo.grabFocus();
            throw new Exception("Campo codigo inválido");
        } else {
            codigo = Integer.parseInt(tfCodigo.getText());
            if ("".equals(tfNome.getText())) {
                tfNome.grabFocus();
                throw new Exception("Campo nome inválido");
            } else {
                nome = tfNome.getText();
                if ("".equals(tfDescricao.getText())) {
                    tfDescricao.grabFocus();
                    throw new Exception("Campo descrição inválido");
                } else {
                    descricao = tfDescricao.getText();
                    if ("  /  /    ".equals(ftfData.getText())) {
                        ftfData.grabFocus();
                        throw new Exception("Campo data inválido");
                    } else {
                        data = ftfData.getText();
                        if ("  :  ".equals(ftfHora.getText())) {
                            ftfHora.grabFocus();
                            throw new Exception("Campo hora inválido");
                        } else {
                            hora = ftfHora.getText();
                            control.adicionar(data, nome, descricao, hora, codigo);
                            JOptionPane.showMessageDialog(null, "Numeros de agendas cadastradas " + control.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
                            tableModel.fireTableDataChanged();
                            limparTela();
                        }
                    }
                }
            }
        }
    }

    private void ok() throws Exception {
        if (control.vazio() == true) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            boolean encontrado = false;
            if (control.agendaCadasCod(Integer.parseInt(tfCodigo.getText())) == true) {
                encontrado = true;
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "A Tarefa " + tfCodigo.getText() + " ja esta agendada deseja Substituila?", "Agenda", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    control.removerCod(Integer.parseInt(tfCodigo.getText()));
                    try {
                        gravar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                try {
                    gravar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há tarefas agendas");
        } else {
            boolean erro;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Insira codigo: ", "Agenda", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.recuperar(codigo) == true) {
                            tfNome.setText(control.getNome());
                            ftfData.setText(control.getData());
                            tfDescricao.setText(control.getDescricao());
                            ftfHora.setText(control.getHora());
                            tfCodigo.setText(Integer.toString(control.getCodigo()));
                            JOptionPane.showMessageDialog(null, "Tarefa encontrada e recuperada", "Agenda", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Tarefa não encontrada", "Agenda", JOptionPane.INFORMATION_MESSAGE);
                        }
                        erro = false;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entre com um numero válido", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    }
                }
            } while (erro);
        }
    }

    private void excluir() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há tarefas agendas");
        } else {
            boolean erro = false;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Informe o codigo da tarefa a ser excluída:", "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.agendaCadasCod(codigo) == true) {
                            control.removerCod(codigo);
                            JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso", "Agenda", JOptionPane.INFORMATION_MESSAGE);
                            tableModel.fireTableDataChanged();
                            limparTela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Tarefa não encontrada", "Agenda", JOptionPane.INFORMATION_MESSAGE);
                        }
                        erro = false;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entre com um numero válido", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    }
                }
            } while (erro);
        }
    }

    private void limparTela() {
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfNome.setText("");
        tfDescricao.setText("");
        ftfData.setText("");
        ftfHora.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void codigo() {
        if ("".equals(tfCodigo.getText())) {
            limparTela();
        } else {
            if (control.vazio() == true) {
            } else {
                if (control.agendaCadasCod(Integer.parseInt(tfCodigo.getText())) == true) {
                    limparTela();
                }
            }
        }
    }

    private void nomeFuncionario() {
        NomeAgenda aux = new NomeAgenda();
        aux.setListener(new ListenerAgenda() {

            public void campoAlterado(String texto) {
                nomeFuncionario = texto;
            }
        });
        aux.setModal(true);
        aux.setVisible(true);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btSalvar) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Agenda", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovo.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                    try {
                        ok();
                        control.arquivo(arquivo);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbAlterar.isSelected() == true) {
                    try {
                        recuperar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbExcluir.isSelected() == true) {
                    try {
                        excluir();
                        control.arquivo(arquivo);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
    }

    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
            tfDescricao.grabFocus();
            nomeFuncionario();
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
            tfNome.setText(nomeFuncionario);
            nomeFuncionario = "";
        }
        if (evento.getSource() == ftfData) {
            ftfData.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHora) {
            ftfHora.setBackground(Color.YELLOW);
        }
    }

    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            codigo();
        }
        tfNome.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        ftfData.setBackground(Color.WHITE);
        ftfHora.setBackground(Color.WHITE);
    }

    public void itemStateChanged(ItemEvent evento) {
        if (evento.getSource() == rbNovo) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbAlterar.setSelected(false);
                rbExcluir.setSelected(false);
            }
        }
        if (evento.getSource() == rbAlterar) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbExcluir.setSelected(false);
            }
        }
        if (evento.getSource() == rbExcluir) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbAlterar.setSelected(false);
            }
        }
    }
}
