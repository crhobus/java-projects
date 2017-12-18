package DownloadManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DownloadManager extends JFrame implements Observer, ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfURL, tfDiretorio, tfNomeArq, tfExrensaoArq;
    private JButton btPausar, btReiniciar, btCancelar, btRemover, btOK, btLimpar, btSalvarPasta;
    private JMenuItem miSair;
    private DownloadsTableModel tableModel;
    private JTable tabela;
    private Download downloadSelecionado;
    private URL url;
    private boolean remover;

    public DownloadManager() {
        super("Download Manager");
        setLayout(new BorderLayout());

        JPanel panelAdd = new JPanel();
        panelAdd.setLayout(null);
        panelAdd.setPreferredSize(new Dimension(0, 120));
        add(panelAdd, BorderLayout.NORTH);

        JLabel lbURL = new JLabel("URL");
        lbURL.setBounds(75, 20, 40, 14);
        panelAdd.add(lbURL);

        tfURL = new JTextField();
        tfURL.setBounds(103, 18, 500, 20);
        tfURL.setBackground(Color.WHITE);
        tfURL.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent evento) {
                if (!"".equals(tfURL.getText().trim())) {
                    validaURL();
                    if (url != null) {
                        tfURL.setEditable(false);
                    }
                }
            }
        });
        panelAdd.add(tfURL);

        JLabel lbDiretorio = new JLabel("Salvar na Pasta");
        lbDiretorio.setBounds(20, 56, 75, 14);
        panelAdd.add(lbDiretorio);

        tfDiretorio = new JTextField();
        tfDiretorio.setBounds(103, 53, 465, 20);
        panelAdd.add(tfDiretorio);

        btSalvarPasta = new JButton("...");
        btSalvarPasta.setBounds(575, 50, 30, 26);
        btSalvarPasta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btSalvarPasta.setToolTipText("Selecionar Pasta");
        btSalvarPasta.addActionListener(this);
        panelAdd.add(btSalvarPasta);

        JLabel lbNomeArq = new JLabel("Nome do Arquivo");
        lbNomeArq.setBounds(15, 92, 90, 14);
        panelAdd.add(lbNomeArq);

        tfNomeArq = new JTextField();
        tfNomeArq.setBounds(103, 89, 190, 20);
        panelAdd.add(tfNomeArq);

        JLabel lbExtensaoArq = new JLabel("Extensão do Arquivo");
        lbExtensaoArq.setBounds(300, 92, 105, 14);
        panelAdd.add(lbExtensaoArq);

        tfExrensaoArq = new JTextField();
        tfExrensaoArq.setEditable(false);
        tfExrensaoArq.setBackground(Color.WHITE);
        tfExrensaoArq.setBounds(405, 89, 70, 20);
        panelAdd.add(tfExrensaoArq);

        btOK = new JButton("OK");
        btOK.setBounds(480, 85, 46, 26);
        btOK.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btOK.setToolTipText("Adicionar Download");
        btOK.addActionListener(this);
        panelAdd.add(btOK);

        btLimpar = new JButton("Limpar");
        btLimpar.setBounds(530, 85, 75, 26);
        btLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btLimpar.setToolTipText("Limpar Campos");
        btLimpar.addActionListener(this);
        panelAdd.add(btLimpar);

        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(new BorderLayout());
        panelTabela.setBorder(BorderFactory.createTitledBorder("Downloads"));
        add(panelTabela, BorderLayout.CENTER);

        tableModel = new DownloadsTableModel();
        tabela = new JTable(tableModel);
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evento) {
                alteraSelecaoTabela();
            }
        });
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//permite só uma linha por vez seja selecionada
        RenderizaProgresso rendener = new RenderizaProgresso(0, 100);//configura ProgressBar como renderizador da coluna de progresso
        rendener.setStringPainted(true);//mostra texto de progresso
        tabela.setDefaultRenderer(JProgressBar.class, rendener);
        tabela.setRowHeight((int) rendener.getPreferredSize().getHeight());//configura a tabela como uma altura suficiente para ajustar JProgressBar
        panelTabela.add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setPreferredSize(new Dimension(0, 50));
        add(panelBotoes, BorderLayout.SOUTH);

        btPausar = new JButton("Pausar");
        btPausar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btPausar.setToolTipText("Pausar Download");
        btPausar.setEnabled(false);
        btPausar.addActionListener(this);
        panelBotoes.add(btPausar);

        btReiniciar = new JButton("Reiniciar");
        btReiniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btReiniciar.setToolTipText("Reiniciar Download");
        btReiniciar.setEnabled(false);
        btReiniciar.addActionListener(this);
        panelBotoes.add(btReiniciar);

        btCancelar = new JButton("Cancelar");
        btCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar Download");
        btCancelar.setEnabled(false);
        btCancelar.addActionListener(this);
        panelBotoes.add(btCancelar);

        btRemover = new JButton("Remover");
        btRemover.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btRemover.setToolTipText("Remover Download");
        btRemover.setEnabled(false);
        btRemover.addActionListener(this);
        panelBotoes.add(btRemover);

        JMenuBar menuBar = new JMenuBar();

        JMenu mnArquivo = new JMenu("Arquivo");

        miSair = new JMenuItem("Sair");
        mnArquivo.add(miSair);
        miSair.addActionListener(this);

        menuBar.add(mnArquivo);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void salvarPasta() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            tfDiretorio.setText(fileChooser.getSelectedFile().toString());
        }
    }

    private void addDownload() {
        File arq = new File(tfDiretorio.getText());
        if (!arq.exists()) {
            JOptionPane.showMessageDialog(null, "Diretório inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ("".equals(tfNomeArq.getText().trim()) || "".equals(tfExrensaoArq.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Nome ou extensão do arquivo inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (url != null) {
            tableModel.addDownload(new Download(url, new File(tfDiretorio.getText() + "\\" + tfNomeArq.getText() + tfExrensaoArq.getText())));
            limparCampos();
        }
    }

    private void validaURL() {
        if (!tfURL.getText().toLowerCase().startsWith("http://")) {//somente permite URL de http
            erroURL();
            return;
        }
        try {//verificao formato de URL
            url = new URL(tfURL.getText());
        } catch (Exception ex) {
            erroURL();
            return;
        }
        if (url.getFile().length() < 2) {//certifica-se de que url especifica um arquivo
            url = null;
            erroURL();
            return;
        }
        try {
            String nomeURL = url.getFile();
            String nomeArq = nomeURL.substring(nomeURL.lastIndexOf('/') + 1, nomeURL.lastIndexOf('.'));
            String extensaoArq = nomeURL.substring(nomeURL.lastIndexOf('.'));
            if (nomeArq.trim().length() >= 1 && extensaoArq.trim().length() >= 2) {
                tfNomeArq.setText(nomeArq.trim());
                tfExrensaoArq.setText(extensaoArq.replace(" ", ""));
            } else {
                url = null;
                erroURL();
            }
        } catch (Exception ex) {
            url = null;
            erroURL();
        }
    }

    private void erroURL() {
        JOptionPane.showMessageDialog(null, "URL download inválido", "Erro", JOptionPane.ERROR_MESSAGE);
        tfNomeArq.setText("");
        tfExrensaoArq.setText("");
    }

    private void alteraSelecaoTabela() {//chamado quando uma seleção da tabela se altera
        if (downloadSelecionado != null) {//remove o registro para receber notificações do último download selecionado
            downloadSelecionado.deleteObserver(DownloadManager.this);
        }
        if (!remover) {//se nao no meio da limpeza de um download, configura o download selecionado e registra para receber notificações dele
            downloadSelecionado = tableModel.getDownload(tabela.getSelectedRow());
            downloadSelecionado.addObserver(DownloadManager.this);
            atualizaBotoes();
        }
    }

    private void remover() {
        remover = true;
        tableModel.removeDownload(tabela.getSelectedRow());
        remover = false;
        downloadSelecionado = null;
        atualizaBotoes();
    }

    private void cancelar() {
        downloadSelecionado.cancelar();
        atualizaBotoes();
    }

    private void reinicinar() {
        downloadSelecionado.reiniciar();
        atualizaBotoes();
    }

    private void pausar() {
        downloadSelecionado.pausa();
        atualizaBotoes();
    }

    private void atualizaBotoes() {//atualiza o status de cada botão com base no status do download atualmente selecionado
        if (downloadSelecionado != null) {
            int status = downloadSelecionado.getStatusAtual();
            switch (status) {
                case Download.TRANSFERINDO:
                    btPausar.setEnabled(true);
                    btReiniciar.setEnabled(false);
                    btCancelar.setEnabled(true);
                    btRemover.setEnabled(false);
                    break;
                case Download.PAUSADO:
                    btPausar.setEnabled(false);
                    btReiniciar.setEnabled(true);
                    btCancelar.setEnabled(true);
                    btRemover.setEnabled(false);
                    break;
                case Download.ERRO:
                    btPausar.setEnabled(false);
                    btReiniciar.setEnabled(true);
                    btCancelar.setEnabled(false);
                    btRemover.setEnabled(true);
                    break;
                default://COMPLETO ou CANCELADO
                    btPausar.setEnabled(false);
                    btReiniciar.setEnabled(false);
                    btCancelar.setEnabled(false);
                    btRemover.setEnabled(true);
            }
        } else {
            btPausar.setEnabled(false);
            btReiniciar.setEnabled(false);
            btCancelar.setEnabled(false);
            btRemover.setEnabled(false);
        }
    }

    private void limparCampos() {
        tfURL.setText("");
        tfDiretorio.setText("");
        tfNomeArq.setText("");
        tfExrensaoArq.setText("");
        tfURL.setEditable(true);
        url = null;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == miSair) {
            System.exit(JFrame.EXIT_ON_CLOSE);
        }
        if (evento.getSource() == btPausar) {
            pausar();
        }
        if (evento.getSource() == btReiniciar) {
            reinicinar();
        }
        if (evento.getSource() == btCancelar) {
            cancelar();
        }
        if (evento.getSource() == btRemover) {
            remover();
        }
        if (evento.getSource() == btOK) {
            addDownload();
        }
        if (evento.getSource() == btSalvarPasta) {
            salvarPasta();
        }
        if (evento.getSource() == btLimpar) {
            limparCampos();
        }
    }

    @Override
    public void update(Observable o, Object arg) {// update é chamado quando um download notifica seus observalores sobre qualquer alterações
        if (downloadSelecionado != null && downloadSelecionado.equals(o)) {//atualiza botões se o download selecionado tiver sido alterado
            atualizaBotoes();
        }
    }
}
