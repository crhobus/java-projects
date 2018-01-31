package Principal;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import Analizador.LexicalError;
import Analizador.Lexico;
import Analizador.SemanticError;
import Analizador.Semantico;
import Analizador.Sintatico;
import Analizador.SyntaticError;

public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextArea taEditor, taMensagemErro;
    private JButton btNovo, btAbrir, btSalvar, btCopiar, btColar, btRecortar,
            btCompilar, btGerarCod, btEquipe;
    private JLabel lbStatus, lbDiretorio;
    private Semantico semantico;

    public Principal() {
        super("Compilador");
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(panelBotoes, BorderLayout.NORTH);

        Icon icNovo = new ImageIcon("Novo.png");
        btNovo = new JButton("novo [ctrl-n]", icNovo);
        btNovo.setHorizontalTextPosition(SwingConstants.CENTER);
        btNovo.setVerticalAlignment(SwingConstants.CENTER);
        btNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
        btNovo.setHorizontalAlignment(SwingConstants.LEFT);
        btNovo.setPreferredSize(new Dimension(100, 55));
        btNovo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btNovo.setToolTipText("Novo");
        panelBotoes.add(btNovo);
        btNovo.addActionListener(this);

        Icon icAbrir = new ImageIcon("Abrir.png");
        btAbrir = new JButton("abrir [ctrl-a]", icAbrir);
        btAbrir.setHorizontalTextPosition(SwingConstants.CENTER);
        btAbrir.setVerticalAlignment(SwingConstants.CENTER);
        btAbrir.setVerticalTextPosition(SwingConstants.BOTTOM);
        btAbrir.setHorizontalAlignment(SwingConstants.LEFT);
        btAbrir.setPreferredSize(new Dimension(100, 55));
        btAbrir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btAbrir.setToolTipText("Abrir");
        panelBotoes.add(btAbrir);
        btAbrir.addActionListener(this);

        Icon icSalvar = new ImageIcon("Salvar.png");
        btSalvar = new JButton("salvar [ctrl-s]", icSalvar);
        btSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
        btSalvar.setVerticalAlignment(SwingConstants.CENTER);
        btSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btSalvar.setHorizontalAlignment(SwingConstants.LEFT);
        btSalvar.setPreferredSize(new Dimension(100, 55));
        btSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btSalvar.setToolTipText("Salvar");
        panelBotoes.add(btSalvar);
        btSalvar.addActionListener(this);

        Icon icCopiar = new ImageIcon("Copiar.png");
        btCopiar = new JButton("copiar [ctrl-c]", icCopiar);
        btCopiar.setHorizontalTextPosition(SwingConstants.CENTER);
        btCopiar.setVerticalAlignment(SwingConstants.CENTER);
        btCopiar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btCopiar.setHorizontalAlignment(SwingConstants.LEFT);
        btCopiar.setPreferredSize(new Dimension(100, 55));
        btCopiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btCopiar.setToolTipText("Copiar");
        panelBotoes.add(btCopiar);
        btCopiar.addActionListener(this);

        Icon icColar = new ImageIcon("Colar.png");
        btColar = new JButton("colar [ctrl-v]", icColar);
        btColar.setHorizontalTextPosition(SwingConstants.CENTER);
        btColar.setVerticalAlignment(SwingConstants.CENTER);
        btColar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btColar.setHorizontalAlignment(SwingConstants.LEFT);
        btColar.setPreferredSize(new Dimension(100, 55));
        btColar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btColar.setToolTipText("Colar");
        panelBotoes.add(btColar);
        btColar.addActionListener(this);

        Icon icRecortar = new ImageIcon("Recortar.png");
        btRecortar = new JButton("recortar [ctrl-x]", icRecortar);
        btRecortar.setHorizontalTextPosition(SwingConstants.CENTER);
        btRecortar.setVerticalAlignment(SwingConstants.CENTER);
        btRecortar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btRecortar.setHorizontalAlignment(SwingConstants.LEFT);
        btRecortar.setPreferredSize(new Dimension(100, 55));
        btRecortar.setMargin(new Insets(0, 10, 0, 0));
        btRecortar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btRecortar.setToolTipText("Recortar");
        panelBotoes.add(btRecortar);
        btRecortar.addActionListener(this);

        Icon icCompilar = new ImageIcon("Compilar.png");
        btCompilar = new JButton("compilar [F8]", icCompilar);
        btCompilar.setHorizontalTextPosition(SwingConstants.CENTER);
        btCompilar.setVerticalAlignment(SwingConstants.CENTER);
        btCompilar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btCompilar.setHorizontalAlignment(SwingConstants.LEFT);
        btCompilar.setPreferredSize(new Dimension(100, 55));
        btCompilar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btCompilar.setToolTipText("Compilar");
        panelBotoes.add(btCompilar);
        btCompilar.addActionListener(this);

        Icon icGerarCod = new ImageIcon("GerarCod.png");
        btGerarCod = new JButton("gerar código [F9]", icGerarCod);
        btGerarCod.setHorizontalTextPosition(SwingConstants.CENTER);
        btGerarCod.setVerticalAlignment(SwingConstants.CENTER);
        btGerarCod.setVerticalTextPosition(SwingConstants.BOTTOM);
        btGerarCod.setHorizontalAlignment(SwingConstants.LEFT);
        btGerarCod.setPreferredSize(new Dimension(100, 55));
        btGerarCod.setMargin(new Insets(0, 7, 0, 0));
        btGerarCod.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btGerarCod.setToolTipText("Gerar Código");
        panelBotoes.add(btGerarCod);
        btGerarCod.addActionListener(this);

        Icon icEquipe = new ImageIcon("Equipe.png");
        btEquipe = new JButton("equipe [F1]", icEquipe);
        btEquipe.setHorizontalTextPosition(SwingConstants.CENTER);
        btEquipe.setVerticalAlignment(SwingConstants.CENTER);
        btEquipe.setVerticalTextPosition(SwingConstants.BOTTOM);
        btEquipe.setHorizontalAlignment(SwingConstants.LEFT);
        btEquipe.setPreferredSize(new Dimension(100, 55));
        btEquipe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btEquipe.setToolTipText("Equipe");
        panelBotoes.add(btEquipe);
        btEquipe.addActionListener(this);

        taEditor = new JTextArea();
        taEditor.setBorder(new NumeroBordaEditor());
        taEditor.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent evento) {
                lbStatus.setText("Modificado");
            }

            @Override
            public void insertUpdate(DocumentEvent evento) {
                lbStatus.setText("Modificado");
            }

            @Override
            public void changedUpdate(DocumentEvent evento) {
            }
        });
        JScrollPane scrollEditor = new JScrollPane(taEditor);
        scrollEditor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollEditor.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollEditor, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 100));
        add(panel, BorderLayout.SOUTH);

        taMensagemErro = new JTextArea();
        taMensagemErro.setEditable(false);
        JScrollPane scrollMensagemErro = new JScrollPane(taMensagemErro);
        scrollMensagemErro.setPreferredSize(new Dimension(0, 140));
        scrollMensagemErro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMensagemErro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollMensagemErro, BorderLayout.CENTER);

        JPanel panelBarraStatus = new JPanel();
        panelBarraStatus.setLayout(null);
        panelBarraStatus.setPreferredSize(new Dimension(0, 16));
        panel.add(panelBarraStatus, BorderLayout.SOUTH);

        lbStatus = new JLabel("Não Modificado");
        lbStatus.setBounds(1, 1, 150, 14);
        panelBarraStatus.add(lbStatus);

        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBounds(155, 0, 1, 16);
        panelBarraStatus.add(separator);

        lbDiretorio = new JLabel();
        lbDiretorio.setBounds(160, 1, 1100, 14);
        panelBarraStatus.add(lbDiretorio);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evento) {
                int resposta = salvarAlteracoes();
                if (resposta == 0 || resposta == 1 || resposta == 3) {
                    System.exit(JFrame.EXIT_ON_CLOSE);
                }
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(getAtalhos());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(916, 666);
        setMinimumSize(new Dimension(916, 93));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private KeyEventDispatcher getAtalhos() {
        return new Atalhos(this);
    }

    private JFileChooser abrirArq() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return ".dcm";
            }

            @Override
            public boolean accept(File arq) {
                if (arq.getAbsolutePath().endsWith(".dcm") || arq.isDirectory()) {
                    return true;
                }
                return false;
            }
        });
        return fileChooser;
    }

    private int salvarAlteracoes() {
        if ("Modificado".equals(lbStatus.getText())) {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja salvar as alterações feitas", "Compilador", JOptionPane.YES_NO_CANCEL_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                if (salvar() == 0) {
                    return 0;
                }
                return 2;
            }
            if (resposta == JOptionPane.NO_OPTION) {
                return 1;
            }
            if (resposta == JOptionPane.CANCEL_OPTION) {
                return 2;
            }
        } else {
            return 3;
        }
        return 4;
    }

    public int salvar() {
        if ("".equals(lbDiretorio.getText())) {
            JFileChooser fileChooser = abrirArq();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                if (".dcm".equals(fileChooser.getFileFilter().getDescription())) {
                    if (".dcm".equals(fileChooser.getSelectedFile().getName())) {
                        JOptionPane.showMessageDialog(null, "Nome do arquivo inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                        return 1;
                    }
                    File arq = null;
                    boolean concatenou = false;
                    if (fileChooser.getSelectedFile().toString().substring(fileChooser.getSelectedFile().toString().lastIndexOf('.') + 1).equals("dcm")) {
                        arq = new File(fileChooser.getSelectedFile().toString());
                    } else {
                        arq = new File(fileChooser.getSelectedFile().getAbsolutePath().concat(".dcm"));
                        concatenou = true;
                    }
                    if (arq.exists()) {
                        int existe = JOptionPane.showConfirmDialog(null, "O arquivo já existe deseja substitui-lo?", "Compilador", JOptionPane.YES_NO_OPTION);
                        if (existe != JOptionPane.YES_OPTION) {
                            return 1;
                        }
                    }
                    if (concatenou) {
                        lbDiretorio.setText(fileChooser.getSelectedFile().getAbsolutePath().concat(".dcm"));
                    } else {
                        lbDiretorio.setText(fileChooser.getSelectedFile().toString());
                    }
                } else {
                    String extensaoArq = fileChooser.getSelectedFile().toString().substring(fileChooser.getSelectedFile().toString().lastIndexOf('.') + 1);
                    String extensaoNomeArq = fileChooser.getSelectedFile().getName().substring(fileChooser.getSelectedFile().getName().lastIndexOf('.') + 1);
                    String nomeArq = "";
                    try {
                        nomeArq = fileChooser.getSelectedFile().getName().substring(0, fileChooser.getSelectedFile().getName().indexOf('.'));
                    } catch (Exception ex) {
                    }
                    if (!extensaoArq.equals(extensaoNomeArq) || "".equals(extensaoArq) || "".equals(nomeArq)) {
                        JOptionPane.showMessageDialog(null, "Nome ou extensão do arquivo inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                        return 1;
                    } else {
                        File arq = new File(fileChooser.getSelectedFile().toString());
                        if (arq.exists()) {
                            int existe = JOptionPane.showConfirmDialog(null, "O arquivo já existe deseja substitui-lo?", "Compilador", JOptionPane.YES_NO_OPTION);
                            if (existe != JOptionPane.YES_OPTION) {
                                return 1;
                            }
                        }
                        lbDiretorio.setText(fileChooser.getSelectedFile().toString());
                    }
                }
            }
        }
        if (!"".equals(lbDiretorio.getText())) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(lbDiretorio.getText()))));
                String[] linha = taEditor.getText().split("\n");
                for (int i = 0; i < linha.length; i++) {
                    out.println(linha[i]);
                }
                out.close();
                taMensagemErro.setText("");
                lbStatus.setText("Não Modificado");
                return 0;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return 1;
    }

    public void novo() {
        int resposta = salvarAlteracoes();
        if (resposta == 0 || resposta == 1 || resposta == 3) {
            taEditor.setText("");
            taMensagemErro.setText("");
            lbStatus.setText("Não Modificado");
            lbDiretorio.setText("");
        }
    }

    public void abrir() {
        JFileChooser fileChooser = abrirArq();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            int resposta = salvarAlteracoes();
            if (resposta == 0 || resposta == 1 || resposta == 3) {
                File arq = fileChooser.getSelectedFile();
                try {
                    BufferedReader in = new BufferedReader(new FileReader(arq));
                    taEditor.setText("");
                    while (in.ready()) {
                        taEditor.append(in.readLine() + "\n");
                    }
                    taMensagemErro.setText("");
                    lbStatus.setText("Não Modificado");
                    lbDiretorio.setText(arq.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void copiar() {
        if (taEditor.getSelectedText() != null) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(taEditor.getSelectedText()), null);
        }
    }

    public void colar() {
        try {
            taEditor.replaceSelection(null);
            taEditor.insert(Toolkit.getDefaultToolkit().getSystemClipboard().getData(new DataFlavor(String.class, "text/plain")).toString(), taEditor.getCaretPosition());
        } catch (Exception ex) {
        }
    }

    public void recortar() {
        if (taEditor.getSelectedText() != null) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(taEditor.getSelectedText()), null);
            taEditor.replaceSelection(null);
        }
    }

    public void compilar() {
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        if (lbDiretorio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primeiro salve o arquivo");
            this.salvar();
            if (lbDiretorio.getText().isEmpty()) {
                return;
            }
        }
        semantico = new Semantico(lbDiretorio.getText());
        lexico.setInput(taEditor.getText());
        try {
            sintatico.parse(lexico, semantico);
            taMensagemErro.setText("programa compilado com sucesso");
        } catch (LexicalError ex) {
            taMensagemErro.setText("Erro na linha " + capturaLinha(ex.getPosition()) + ex.getMessage());
        } catch (SyntaticError ex) {
            taMensagemErro.setText("Erro na linha " + capturaLinha(ex.getPosition()) + " - encontrado " + ex.getMessage());
        } catch (SemanticError ex) {
            taMensagemErro.setText("Erro na linha " + capturaLinha(ex.getPosition()) + " - encontrado " + ex.getMessage());
        }
    }

    private int capturaLinha(int posicao) {
        int cont = 1;
        String s = taEditor.getText();
        for (int i = 0; i < posicao; i++) {
            if (s.charAt(i) == Character.DIRECTIONALITY_PARAGRAPH_SEPARATOR) {
                cont++;
            }
        }
        return cont;
    }

    public void gerarCodigo() {
        //taMensagemErro.setText("geração de código ainda não foi implementada");
        try {
            String arquivo = lbDiretorio.getText().substring(0, lbDiretorio.getText().lastIndexOf("."));

            PrintWriter saida;
            saida = new PrintWriter(new BufferedWriter(new FileWriter(new File(arquivo + ".il"))));
            saida.print(semantico.getCodigo());
            saida.close();

            //gera o ponto il
            if (lbDiretorio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primeiro Compile o arquivo");
            } else {
                arquivo = lbDiretorio.getText().substring(0, lbDiretorio.getText().lastIndexOf(".")) + ".il";
                File f = new File(arquivo);
                if (!f.exists()) {
                    return;
                }
            }

            this.taMensagemErro.setText("Código objeto gerado com sucesso!");

            File f = new File("C:\\Windows\\Microsoft.NET\\Framework\\v2.0.50727\\");
            if (!f.exists()) {
                JOptionPane.showMessageDialog(this, "É necessario ter intalado o Microsoft.NET v2.0.50727 ou superior \n"
                        + "Programa não gerado por flta do montador ilasm.exe ");
                return;
            }
            //gera o ponto exe
            ProcessBuilder pb = new ProcessBuilder("ilasm", arquivo);
            Map<String, String> env = pb.environment();
            env.put("PATH", env.get("PATH") + "C:\\Windows\\Microsoft.NET\\Framework\\v2.0.50727\\");
            Process proc = pb.start();

        } catch (IOException e) {
            this.taMensagemErro.setText("Erro ao gerar arquivo");
        }

    }

    public void equipe() {
        JOptionPane.showMessageDialog(null, "André Seiji Kono\nCaio Renan Hobus\nMatheus Bortolon", "Equipe", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btNovo) {
            novo();
        }
        if (evento.getSource() == btAbrir) {
            abrir();
        }
        if (evento.getSource() == btSalvar) {
            salvar();
        }
        if (evento.getSource() == btCopiar) {
            copiar();
        }
        if (evento.getSource() == btColar) {
            colar();
        }
        if (evento.getSource() == btRecortar) {
            recortar();
        }
        if (evento.getSource() == btCompilar) {
            compilar();
        }
        if (evento.getSource() == btGerarCod) {
            gerarCodigo();
        }
        if (evento.getSource() == btEquipe) {
            equipe();
        }
    }
}
