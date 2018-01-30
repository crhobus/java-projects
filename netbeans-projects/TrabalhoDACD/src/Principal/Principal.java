package Principal;

import java.awt.AWTKeyStroke;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Armazem.ArmazemPrincipal;
import Consumidor.GravacaoArquivo;
import Consumidor.LeituraArquivo;
import LogTela.Ordena;
import LogTela.VariaveisOrdTabela;
import OMP.LeituraArq;
import OMP.LeituraArq_jomp;
import ResolvedorFactory.DiretorioDAO;

public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfDiretorio1, tfDiretorio2, tfArquivo;
    private JButton btOK, btCancelar, btSair, btRecalcular, btAbrirDir1, btAbrirDir2, btAbrirArq, btCalFuncoes;
    private TableModel tableModel;
    private VariaveisOrdTabela controleTabela;
    private boolean terminaOrdenacao;
    private LeituraArq_jomp leituraArq;

    public Principal(VariaveisOrdTabela controleTabela) {
        super("Trabalho Desenvolvimento de Aplicações Concorrentes e Distribuídas");
        this.controleTabela = controleTabela;
        tableModel = new TableModel(controleTabela);
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        JLabel lbDiretorio1 = new JLabel("Diretório 1");
        lbDiretorio1.setBounds(20, 40, 60, 14);
        add(lbDiretorio1);

        tfDiretorio1 = new JTextField("C:\\Users\\Caio\\Documents\\Programação\\Java\\Programas Java\\ProjetosEclipse\\TrabalhoDACD\\Arquivos\\dir 1");
        tfDiretorio1.setBounds(80, 37, 610, 20);
        add(tfDiretorio1);

        btAbrirDir1 = new JButton("...");
        btAbrirDir1.setBounds(695, 34, 31, 26);
        btAbrirDir1.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btAbrirDir1.setToolTipText("Abrir Diretório 1");
        add(btAbrirDir1);
        btAbrirDir1.addActionListener(this);

        JLabel lbDiretorio2 = new JLabel("Diretório 2");
        lbDiretorio2.setBounds(20, 75, 60, 14);
        add(lbDiretorio2);

        tfDiretorio2 = new JTextField("C:\\Users\\Caio\\Documents\\Programação\\Java\\Programas Java\\ProjetosEclipse\\TrabalhoDACD\\Arquivos\\dir 2");
        tfDiretorio2.setBounds(80, 72, 610, 20);
        add(tfDiretorio2);

        btAbrirDir2 = new JButton("...");
        btAbrirDir2.setBounds(695, 69, 31, 26);
        btAbrirDir2.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btAbrirDir2.setToolTipText("Abrir Diretório 2");
        add(btAbrirDir2);
        btAbrirDir2.addActionListener(this);

        JLabel lbArquivo = new JLabel("Arquivo");
        lbArquivo.setBounds(20, 110, 60, 14);
        add(lbArquivo);

        tfArquivo = new JTextField("C:\\Users\\Caio\\Documents\\Programação\\Java\\Programas Java\\ProjetosEclipse\\TrabalhoDACD\\Arquivos\\dir 3\\w.txt");
        tfArquivo.setBounds(80, 107, 610, 20);
        add(tfArquivo);

        btAbrirArq = new JButton("...");
        btAbrirArq.setBounds(695, 104, 31, 26);
        btAbrirArq.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btAbrirArq.setToolTipText("Abrir arquivo");
        add(btAbrirArq);
        btAbrirArq.addActionListener(this);

        Icon icOK = new ImageIcon("Ok.png");
        btOK = new JButton("OK", icOK);
        btOK.setBounds(194, 135, 90, 30);
        btOK.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOK.setToolTipText("OK");
        add(btOK);
        btOK.addActionListener(this);

        Icon icRecalcular = new ImageIcon("Recalcular.png");
        btRecalcular = new JButton("Recalcular", icRecalcular);
        btRecalcular.setBounds(296, 135, 105, 30);
        btRecalcular.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btRecalcular.setToolTipText("Recalcula o arquivo final");
        add(btRecalcular);
        btRecalcular.addActionListener(this);

        Icon icCalFuncoes = new ImageIcon("CalFuncoes.png");
        btCalFuncoes = new JButton("Cal. Funções", icCalFuncoes);
        btCalFuncoes.setBounds(413, 135, 105, 30);
        btCalFuncoes.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCalFuncoes.setMargin(new Insets(0, 0, 0, 0));
        btCalFuncoes.setEnabled(false);
        btCalFuncoes.setToolTipText("Calcular funções de média, mediana e moda do arquivo final");
        add(btCalFuncoes);
        btCalFuncoes.addActionListener(this);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(530, 135, 98, 30);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        add(btCancelar);
        btCancelar.addActionListener(this);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(640, 135, 90, 30);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Sair");
        add(btSair);
        btSair.addActionListener(this);

        JTable tabela = new JTable(tableModel);
        tabela.getColumnModel().getColumn(0).setMinWidth(100);
        tabela.getColumnModel().getColumn(1).setMinWidth(390);
        tabela.getColumnModel().getColumn(2).setMinWidth(220);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 180, 711, 320);
        add(scroll);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(770, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void executa() throws Exception {
        DiretorioDAO diretorio;
        File arq = new File(tfArquivo.getText());
        if (!arq.exists()) {
            throw new Exception("Arquivo não existe");
        } else {
            if (!arq.isFile()) {
                throw new Exception("Não é arquivo");
            }
            if (arq.length() == 0) {
                throw new Exception("Arquivo conta final vazio");
            } else {
                ArmazemPrincipal armazemProd = new ArmazemPrincipal();
                ArmazemPrincipal armazemProdCons = new ArmazemPrincipal();

                Semaphore semaforo = new Semaphore(1);
                GravacaoArquivo gravacaoArquivo = new GravacaoArquivo(semaforo, arq);
                LeituraArquivo leituraArquivo = new LeituraArquivo(semaforo, arq, armazemProdCons, gravacaoArquivo, controleTabela);
                leituraArquivo.setPriority(Thread.MAX_PRIORITY);
                leituraArquivo.start();
                gravacaoArquivo.start();

                Ordena ordena = new Ordena(tableModel, controleTabela, this);
                ordena.start();

                diretorio = new DiretorioDAO();
                diretorio.setDiretorio(tfDiretorio1.getText());
                diretorio.setArmazemProd(armazemProd);
                diretorio.setGravacaoArquivo(gravacaoArquivo);
                diretorio.setControleTabela(controleTabela);
                diretorio.lerDiretorio(TipoArquivo.PRODUTOR);

                diretorio = new DiretorioDAO();
                diretorio.setDiretorio(tfDiretorio2.getText());
                diretorio.setArmazemProd(armazemProd);
                diretorio.setArmazemProdCons(armazemProdCons);
                diretorio.setGravacaoArquivo(gravacaoArquivo);
                diretorio.setControleTabela(controleTabela);
                diretorio.lerDiretorio(TipoArquivo.PRODCONSUMIDOR);

                if (leituraArquivo.fimThread()) {
                    gravacaoArquivo.fecharArquivo();
                    terminaOrdenacao = true;
                    btRecalcular.setEnabled(true);
                    btOK.setEnabled(false);
                    leituraArq = new LeituraArq_jomp(arq);
                    btCalFuncoes.setEnabled(true);
                }
            }
        }
    }

    public boolean isTerminaOrdenacao() {
        return terminaOrdenacao;
    }

    private void recalcular() throws Exception {
        if (!"".equals(tfArquivo.getText().trim())) {
            String formula = JOptionPane.showInputDialog(null, "Entre com a nova fórmula", "Entrada de dados", JOptionPane.QUESTION_MESSAGE);
            if (formula == null) {
                return;
            }
            if (!"".equals(formula.trim())) {
                File arq = new File(tfArquivo.getText());
                if (!arq.exists()) {
                    throw new Exception("Arquivo não existe");
                } else {
                    if (arq.length() == 0) {
                        throw new Exception("Arquivo conta final vazio");
                    } else {
                        if (!arq.isFile()) {
                            throw new Exception("Não é arquivo");
                        }
                        PrintWriter saida = new PrintWriter(new BufferedWriter(new FileWriter(arq)));
                        saida.println(formula);
                        saida.close();
                        btOK.setEnabled(true);
                        btCalFuncoes.setEnabled(false);
                    }
                }
            }
        } else {
            throw new Exception("Campo diretório 3 em branco");
        }
    }

    private String getDiretorioArqs(int n) {
        JFileChooser fileChooser = new JFileChooser();
        if (n == 1) {
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().toString();
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOK) {
            if ("".equals(tfDiretorio1.getText().trim()) || "".equals(tfDiretorio2.getText().trim()) || "".equals(tfArquivo.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Campo(s) em branco", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    controleTabela.cancelar();
                    terminaOrdenacao = false;
                    tableModel.fireTableDataChanged();
                    executa();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            tfDiretorio1.setText("");
            tfDiretorio2.setText("");
            tfArquivo.setText("");
            controleTabela.cancelar();
            terminaOrdenacao = false;
            btOK.setEnabled(true);
            btCalFuncoes.setEnabled(false);
            tableModel.fireTableDataChanged();
        }
        if (evento.getSource() == btRecalcular) {
            try {
                recalcular();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btSair) {
            System.exit(1);
        }
        if (evento.getSource() == btAbrirDir1) {
            tfDiretorio1.setText(getDiretorioArqs(1));
        }
        if (evento.getSource() == btAbrirDir2) {
            tfDiretorio2.setText(getDiretorioArqs(1));
        }
        if (evento.getSource() == btAbrirArq) {
            tfArquivo.setText(getDiretorioArqs(0));
        }
        if (evento.getSource() == btCalFuncoes) {
            MostraCalculo mostraCalculo = new MostraCalculo(leituraArq.getCalcula());
            mostraCalculo.setModal(true);
            mostraCalculo.setVisible(true);
        }
    }
}
