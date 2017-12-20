package Principal;

import Controle.Controle;
import Media.FiltroArquivos;
import Controle.ExecutaMedia;
import FundoFactory.FundoFactory;
import FundoFactory.FundoPadrao;
import FundoFactory.FundoSeven;
import FundoFactory.FundoUbuntu;
import FundoFactory.FundoVista;
import Modelo.Media;
import Tabela.EditorMudarFaixa;
import Tabela.EditorRemover;
import Tabela.Renderizadora;
import Tabela.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import javax.swing.*;
import javax.swing.table.JTableHeader;

public class Principal extends JFrame implements ActionListener {

    private JButton btPlay, btCarregar, btStop, btProximo, btAnterior, btLimpar;
    private JLabel lbImgPanel, lbNomeMedia, lbTamanhoMedia;
    private TableModel tableModel;
    private Icon icPainel, icPause, icPlay;
    private JMenuBar menuBar;
    private JMenuItem miAbrir, miLimpar, miSair, miFundoSeven, miFundoVista, miFundoUbuntu, miFundoPadrao, miVolume, miMudo, miDocumentacao;
    private Container tela;
    private JTableHeader header;
    private Controle controle;
    private Thread thread;
    private ExecutaMedia executaMedia;
    private Media media;
    private int numExecucao = 0;
    private boolean flagParar, flagPausar, esperar, mudo, proximo, anterior;

    public Principal() {
        super("Media Player");
        controle = Controle.getInstance();
        executaMedia = ExecutaMedia.getInstance();
        tableModel = new TableModel(controle);
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        tela = getContentPane();
        JPanel painel = new JPanel();
        painel.setBackground(new Color(220, 220, 220));
        painel.setLayout(null);
        painel.setBounds(5, 10, 675, 350);
        painel.setBorder(BorderFactory.createTitledBorder(""));
        tela.add(painel);

        menuBar = new JMenuBar();

        JMenu mnArquivo = new JMenu("Arquivo");
        miAbrir = new JMenuItem("Abrir     ");
        mnArquivo.add(miAbrir);
        miAbrir.addActionListener(this);

        miLimpar = new JMenuItem("Limpar");
        mnArquivo.add(miLimpar);
        miLimpar.addActionListener(this);

        miSair = new JMenuItem("Sair");
        mnArquivo.add(miSair);
        miSair.addActionListener(this);

        JMenu mnExibir = new JMenu("Exibir");
        JMenu mnFundo = new JMenu("Fundo");
        mnExibir.add(mnFundo);

        miFundoPadrao = new JMenuItem("Padrão        ");
        mnFundo.add(miFundoPadrao);
        miFundoPadrao.addActionListener(this);

        miFundoSeven = new JMenuItem("Seven");
        mnFundo.add(miFundoSeven);
        miFundoSeven.addActionListener(this);

        miFundoVista = new JMenuItem("Vista");
        mnFundo.add(miFundoVista);
        miFundoVista.addActionListener(this);

        miFundoUbuntu = new JMenuItem("Ubuntu");
        mnFundo.add(miFundoUbuntu);
        miFundoUbuntu.addActionListener(this);

        JMenu mnReproduzir = new JMenu("Reproduzir");
        miVolume = new JMenuItem("Volume   ");
        mnReproduzir.add(miVolume);
        miVolume.addActionListener(this);

        miMudo = new JMenuItem("Mudo");
        mnReproduzir.add(miMudo);
        miMudo.addActionListener(this);

        JMenu mnFerramentas = new JMenu("Feramentas");

        JMenu mnAjuda = new JMenu("Ajuda");
        miDocumentacao = new JMenuItem("Documentação");
        mnAjuda.add(miDocumentacao);
        miDocumentacao.addActionListener(this);

        menuBar.add(mnArquivo);
        menuBar.add(mnExibir);
        menuBar.add(mnReproduzir);
        menuBar.add(mnFerramentas);
        menuBar.add(mnAjuda);
        setJMenuBar(menuBar);

        icPause = new ImageIcon("pause.jpg");

        icPainel = new ImageIcon("fundo.jpg");
        lbImgPanel = new JLabel(icPainel);
        lbImgPanel.setBounds(0, 0, 675, 350);
        lbImgPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        painel.add(lbImgPanel);

        lbNomeMedia = new JLabel();
        lbNomeMedia.setBounds(20, 5, 900, 14);
        lbImgPanel.add(lbNomeMedia);

        lbTamanhoMedia = new JLabel();
        lbTamanhoMedia.setBounds(600, 5, 70, 14);
        lbImgPanel.add(lbTamanhoMedia);

        btCarregar = new JButton("carregar", new ImageIcon("carregar.jpg"));
        btCarregar.setBounds(10, 370, 108, 30);
        btCarregar.setBackground(new Color(248, 248, 248));
        tela.add(btCarregar);
        btCarregar.addActionListener(this);

        btAnterior = new JButton("anterior", new ImageIcon("anterior.jpg"));
        btAnterior.setBounds(122, 370, 108, 30);
        btAnterior.setBackground(new Color(248, 248, 248));
        tela.add(btAnterior);
        btAnterior.addActionListener(this);

        icPlay = new ImageIcon("play.jpg");
        btPlay = new JButton("play", icPlay);
        btPlay.setBounds(234, 370, 108, 30);
        btPlay.setBackground(new Color(248, 248, 248));
        tela.add(btPlay);
        btPlay.addActionListener(this);

        btProximo = new JButton("próximo", new ImageIcon("proximo.jpg"));
        btProximo.setBounds(346, 370, 108, 30);
        btProximo.setBackground(new Color(248, 248, 248));
        tela.add(btProximo);
        btProximo.addActionListener(this);

        btStop = new JButton("stop", new ImageIcon("stop.jpg"));
        btStop.setBounds(458, 370, 108, 30);
        btStop.setBackground(new Color(248, 248, 248));
        tela.add(btStop);
        btStop.addActionListener(this);

        btLimpar = new JButton("limpar", new ImageIcon("limpar.jpg"));
        btLimpar.setBounds(570, 370, 108, 30);
        btLimpar.setBackground(new Color(248, 248, 248));
        tela.add(btLimpar);
        btLimpar.addActionListener(this);

        Renderizadora rendener = new Renderizadora();
        JTable tabela = new JTable(tableModel);
        for (int y = 0; y < tabela.getColumnModel().getColumnCount(); y++) {
            tabela.getColumnModel().getColumn(y).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setCellEditor(new EditorMudarFaixa(controle, this));
        tabela.getColumnModel().getColumn(8).setCellEditor(new EditorRemover(controle));
        tabela.getColumnModel().getColumn(0).setMinWidth(40);
        tabela.getColumnModel().getColumn(1).setMinWidth(320);
        tabela.getColumnModel().getColumn(2).setMinWidth(80);
        tabela.getColumnModel().getColumn(3).setMinWidth(70);
        tabela.getColumnModel().getColumn(4).setMinWidth(50);
        tabela.getColumnModel().getColumn(5).setMinWidth(50);
        tabela.getColumnModel().getColumn(6).setMinWidth(50);
        tabela.getColumnModel().getColumn(7).setMinWidth(160);
        tabela.getColumnModel().getColumn(8).setMinWidth(90);
        tabela.setRowHeight(26);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        header = tabela.getTableHeader();

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(5, 420, 675, 150);
        scroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        tela.add(scroll);

        setSize(695, 625);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void carregarMusica() {
        JFileChooser fileChooser = new JFileChooser();
        FiltroArquivos audio = new FiltroArquivos("Audio");
        audio.addExtencao("wave");
        audio.addExtencao("mp3");
        fileChooser.addChoosableFileFilter(audio);
        FiltroArquivos imagem = new FiltroArquivos("Imagem");
        imagem.addExtencao("jpg");
        imagem.addExtencao("png");
        fileChooser.addChoosableFileFilter(imagem);
        FiltroArquivos video = new FiltroArquivos("Video");
        video.addExtencao("mpeg");
        fileChooser.addChoosableFileFilter(video);
        fileChooser.setFileFilter(audio);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Abrir");
        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] arquivo = fileChooser.getSelectedFiles();
            for (File arq : arquivo) {
                controle.addLista(arq, fileChooser.getFileFilter().getDescription());
            }
            tableModel.fireTableDataChanged();
        }
    }

    private void reproduzMedia() {
        int tipo;
        btPlay.setIcon(icPause);
        btPlay.setText("pause");
        media = controle.mediaAtual(numExecucao);
        if (media.isAudio() || media.isVideo()) {
            tipo = 0;
            lbImgPanel.setIcon(icPainel);
        } else {
            tipo = 1;
        }
        executaMedia.executar(tipo, media, lbNomeMedia, lbTamanhoMedia, lbImgPanel, thread);
        numExecucao++;
    }

    private void executar() {
        thread = new Thread(new Runnable() {

            public void run() {
                while (numExecucao < controle.tamanho()) {
                    if (flagParar == true) {
                        break;
                    }
                    reproduzMedia();
                }
                stop();
                flagPausar = false;
                btPlay.setIcon(icPlay);
                btPlay.setText("play");
                lbImgPanel.setIcon(icPainel);
                lbNomeMedia.setText("");
                lbTamanhoMedia.setText("");
            }
        });
        thread.start();
    }

    private void stop() {
        lbNomeMedia.setText("");
        lbTamanhoMedia.setText("");
        lbImgPanel.setIcon(icPainel);
        executaMedia.stop();
        flagParar = true;
        numExecucao = 0;
    }

    private void play() {
        flagParar = false;
        if (flagPausar) {
            if (esperar == false) {
                executaMedia.pause();
                esperar = true;
                btPlay.setIcon(icPlay);
                btPlay.setText("play");
            } else {
                executaMedia.reiniciar();
                btPlay.setIcon(icPause);
                btPlay.setText("pause");
                esperar = false;
                flagPausar = true;
            }
        } else {
            flagPausar = true;
            executar();
        }
    }

    private void anterior() {
        if (anterior == false) {
            if (numExecucao >= 1) {
                executaMedia.stop();
                if (media != null && (media.isAudio() || media.isVideo())) {
                    numExecucao--;
                    esperar = false;
                    executar();
                } else {
                    numExecucao = numExecucao - 2;
                    esperar = false;
                }
            }
            try {
                anterior = true;
                thread.sleep(400);
                anterior = false;
            } catch (InterruptedException ex) {
            }
        }
    }

    private void proxima() {
        if (proximo == false) {
            if (numExecucao < controle.tamanho()) {
                executaMedia.stop();
                esperar = false;
                if (media != null && (media.isAudio() || media.isVideo())) {
                    numExecucao++;
                    executar();
                }
            }
            try {
                proximo = true;
                thread.sleep(400);
                proximo = false;
            } catch (InterruptedException ex) {
            }
        }
    }

    public void selecionarFaixa(int faixa) {
        executaMedia.stop();
        numExecucao = faixa;
        esperar = false;
        if (media != null && (media.isAudio() || media.isVideo())) {
            executar();
        } else {
            numExecucao--;
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCarregar || evento.getSource() == miAbrir) {
            carregarMusica();
        }
        if (evento.getSource() == btPlay) {
            play();
        }
        if (evento.getSource() == btStop) {
            stop();
            executar();
        }

        if (evento.getSource() == btAnterior) {
            anterior();
        }
        if (evento.getSource() == btProximo) {
            proxima();
        }
        if (evento.getSource() == btLimpar || evento.getSource() == miLimpar) {
            stop();
            controle.limpar();
            tableModel.fireTableDataChanged();
        }
        if (evento.getSource() == miSair) {
            System.exit(0);
        }
        if (evento.getSource() == miFundoPadrao) {
            tipoFundo(4);
        }
        if (evento.getSource() == miFundoSeven) {
            tipoFundo(1);
        }
        if (evento.getSource() == miFundoVista) {
            tipoFundo(2);
        }
        if (evento.getSource() == miFundoUbuntu) {
            tipoFundo(3);
        }
        if (evento.getSource() == miVolume) {
            Volume volume = new Volume(executaMedia);
            volume.setModal(true);
            volume.setVisible(true);
        }
        if (evento.getSource() == miMudo) {
            if (mudo) {
                executaMedia.mudo(false);
                mudo = false;
            } else {
                executaMedia.mudo(true);
                mudo = true;
            }
        }
        if (evento.getSource() == miDocumentacao) {
            try {
                Desktop.getDesktop().browse(new URI("www.oracle.com/technetwork/java/javase/tech/index-jsp-140239.html"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir a pagina", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void tipoFundo(int tipo) {
        FundoFactory factory = layoutFundo(tipo);
        lbImgPanel.setIcon(factory.getFundo().getImgFundo());
        icPainel = factory.getFundo().getImgFundo();
        btCarregar.setBackground(factory.getCores().getCoresComponentes());
        btAnterior.setBackground(factory.getCores().getCoresComponentes());
        btPlay.setBackground(factory.getCores().getCoresComponentes());
        btProximo.setBackground(factory.getCores().getCoresComponentes());
        btStop.setBackground(factory.getCores().getCoresComponentes());
        btLimpar.setBackground(factory.getCores().getCoresComponentes());
        header.setBackground(factory.getCores().getCoresComponentes());
        tela.setBackground(factory.getCores().getCoresFundo());
    }

    private FundoFactory layoutFundo(int tipo) {
        switch (tipo) {
            case 1:
                return FundoSeven.getInstance();
            case 2:
                return FundoVista.getInstance();
            case 3:
                return FundoUbuntu.getInstance();
            default:
                return FundoPadrao.getInstance();
        }
    }
}
