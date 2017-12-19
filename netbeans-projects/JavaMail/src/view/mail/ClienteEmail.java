package view.mail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Conexao;
import model.Mensagem;

import control.ConexaoDAO;
import control.ContatosDAO;
import control.DiretorioMsgsDAO;
import control.MensagensDAO;

import view.contatos.CadasContatos;
import view.contatos.ConsultaContatos;

//O cliente de e-mail
public class ClienteEmail extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextPane epMensagem;// Onde será exibida as mensagens
    private JButton btNovaMsg, btResponder, btEncaminhar, btExcluir;// Botões
    private JMenuItem miCadasContatos, miConsContatos, miSair,
            miBaixarNovasMsg, miConexoes;// Menu
    private JTable tabelaMsg;// Tabela onde serão listadas as mensagens
    private MensagensTableModel tableModel;// Modelo de dados da tabela de mensagens
    private Mensagem MsgSelecionada;// Mensagem atualmente selecionada na tabela
    private Session session;// Essa é a sessão do JavaMail
    private Connection con;// Conexão com o banco de Dados
    private MensagensDAO mensagensDAO;// Objeto que conecta os emails ao banco de dados
    private ConexaoDAO conexaoDAO;// Objeto que conecta as configurações de conexão ao banco de dados
    private Conexao conexao;// Conexão atualmente configurada;
    private boolean alteracaoMsgs;// Flag para notificar a tabela quando é feita uma operação de exclusão, insersão no banco de dados
    private JLabel lbQtdadeMsgs, lbEnviarReceber;// Rótulo da barra de status
    private DiretorioMsgsDAO diretorioMsgsDAO;// Objeto que grava as mensagens no arquivo
    private EditorAnexoTab editorAnexoTab;// Editor da tabela para abrir anexo
    private Properties props;// Propriedades da sessão para a conexão

    public ClienteEmail(Connection con) {
        super("Cliente de E-Mail");
        this.con = con;
        mensagensDAO = new MensagensDAO(con);
        conexaoDAO = new ConexaoDAO(con);
        diretorioMsgsDAO = new DiretorioMsgsDAO();
        initComponents();
    }

    // Cria os componentes gráficos
    private void initComponents() {
        this.setLayout(new BorderLayout());

        JMenu mnArquivo = new JMenu("Arquivo");

        miBaixarNovasMsg = new JMenuItem("Enviar/Receber");
        miBaixarNovasMsg.addActionListener(this);
        mnArquivo.add(miBaixarNovasMsg);

        miConexoes = new JMenuItem("Conexões");
        miConexoes.addActionListener(this);
        mnArquivo.add(miConexoes);

        mnArquivo.addSeparator();

        miSair = new JMenuItem("Sair");
        miSair.addActionListener(this);
        mnArquivo.add(miSair);

        JMenu mnContatos = new JMenu("Contatos");

        miCadasContatos = new JMenuItem("Cadastro");
        miCadasContatos.addActionListener(this);
        mnContatos.add(miCadasContatos);

        miConsContatos = new JMenuItem("Consulta");
        miConsContatos.addActionListener(this);
        mnContatos.add(miConsContatos);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mnArquivo);
        menuBar.add(mnContatos);
        setJMenuBar(menuBar);

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 12));
        panelNorte.setBorder(BorderFactory.createEtchedBorder());
        this.getContentPane().add(panelNorte, BorderLayout.NORTH);

        btNovaMsg = new JButton("Nova Mensagem");
        btNovaMsg.setPreferredSize(new Dimension(100, 26));
        btNovaMsg.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btNovaMsg.setToolTipText("Criar uma nova mensagem");
        btNovaMsg.setMargin(new Insets(0, 0, 0, 0));
        btNovaMsg.addActionListener(this);
        panelNorte.add(btNovaMsg);

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEtchedBorder());
        this.getContentPane().add(panelCentro, BorderLayout.CENTER);

        tableModel = new MensagensTableModel(mensagensDAO);
        tabelaMsg = new JTable(tableModel);
        editorAnexoTab = new EditorAnexoTab(this, diretorioMsgsDAO);
        tabelaMsg.getColumnModel().getColumn(3).setCellEditor(editorAnexoTab);// Cria uma editor para abrir os anexos na coluna 3
        tabelaMsg.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Permite que seja selecionada somente uma linha por vez
        tabelaMsg.getTableHeader().setReorderingAllowed(false);
        tabelaMsg.getSelectionModel().addListSelectionListener(new ListSelectionListener() {// Evento quanto é clicado em uma linha da tabela

            @Override
            public void valueChanged(ListSelectionEvent evento) {
                try {
                    mostraMsgSelecionada();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tabelaMsg.setRowHeight(26);// Altura de cada linha
        JScrollPane scrollTab = new JScrollPane(tabelaMsg);
        scrollTab.setPreferredSize(new Dimension(0, 200));
        scrollTab.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);// Dixa a barra de rolagem sempre visível na vertical

        epMensagem = new JTextPane();
        epMensagem.setContentType("text/html");
        epMensagem.setBorder(BorderFactory.createEtchedBorder());
        epMensagem.setEditable(false);
        JScrollPane scrollMsg = new JScrollPane(epMensagem);
        scrollMsg.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);// Dixa a barra de rolagem sempre visível na vertical
        scrollMsg.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);// Deixa a barra de rolagem sempre visível na horizontal

        panelCentro.add(new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTab, scrollMsg), BorderLayout.CENTER);

        JPanel panelSul = new JPanel();
        panelSul.setLayout(new BorderLayout());
        this.getContentPane().add(panelSul, BorderLayout.SOUTH);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 18));
        panelBotoes.setBorder(BorderFactory.createEtchedBorder());
        panelSul.add(panelBotoes, BorderLayout.NORTH);

        btResponder = new JButton("Responder");
        btResponder.setPreferredSize(new Dimension(75, 26));
        btResponder.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btResponder.setToolTipText("Responder o e-mail");
        btResponder.setMargin(new Insets(0, 0, 0, 0));
        btResponder.addActionListener(this);
        btResponder.setEnabled(false);
        panelBotoes.add(btResponder);

        btEncaminhar = new JButton("Encaminhar");
        btEncaminhar.setPreferredSize(new Dimension(75, 26));
        btEncaminhar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btEncaminhar.setToolTipText("Encaminhar o e-mail");
        btEncaminhar.setMargin(new Insets(0, 0, 0, 0));
        btEncaminhar.addActionListener(this);
        btEncaminhar.setEnabled(false);
        panelBotoes.add(btEncaminhar);

        btExcluir = new JButton("Excluir");
        btExcluir.setPreferredSize(new Dimension(75, 26));
        btExcluir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setToolTipText("Excluir o e-mail");
        btExcluir.setMargin(new Insets(0, 0, 0, 0));
        btExcluir.addActionListener(this);
        btExcluir.setEnabled(false);
        panelBotoes.add(btExcluir);

        JPanel panelInf = new JPanel();
        panelInf.setLayout(new BorderLayout());
        panelInf.setPreferredSize(new Dimension(0, 18));
        panelInf.setBorder(BorderFactory.createEtchedBorder());
        panelSul.add(panelInf, BorderLayout.SOUTH);

        lbQtdadeMsgs = new JLabel(mensagensDAO.getQtdadeMensagensCadas() + " mensagens");// Quantidade de mensagens no banco de dados
        panelInf.add(lbQtdadeMsgs, BorderLayout.WEST);

        lbEnviarReceber = new JLabel("Concluído");
        lbEnviarReceber.setForeground(Color.RED);
        panelInf.add(lbEnviarReceber, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Cria uma nova mensagem
    private void novaMsg() throws Exception {
        enviaMsg(TipoMsg.NOVO, null);
    }

    // Resposta para uma mensagem
    private void responderMsg() throws Exception {
        enviaMsg(TipoMsg.RESPONDER, MsgSelecionada);
    }

    // Encaminha uma mensagem
    private void encaminhaMsg() throws Exception {
        enviaMsg(TipoMsg.ENCAMINHAR, MsgSelecionada);
    }

    // Envia a mensagem especificada
    private void enviaMsg(TipoMsg tipoMsg, Mensagem mensagem) throws Exception {
        lbEnviarReceber.setText("Enviar/Receber...");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));// Mostra cursor do mouse de ampulheta enquanto a  mensagem é carregada
        // Exibe a caixa de diálogo da mensagem para obter os parâmetros da
        // mensagem
        NovaMsg novaMsg;
        try {
            novaMsg = new NovaMsg(this, tipoMsg, mensagem, con, diretorioMsgsDAO);
            novaMsg.setVisible(true);
            if (novaMsg.isFechado()) {
                // Retorna se a caixa de diálogo foi fechada
                return;
            }
            // Cria uma nova mensagem com os parâmetros da caixa de diálogo
            Message novaMensagem = new MimeMessage(session);
            novaMensagem.setFrom(new InternetAddress(conexao.getNome() + " <" + conexao.getEmail() + ">"));// E-mail configurado na tela de conexões
            novaMensagem.setRecipient(Message.RecipientType.TO, new InternetAddress(novaMsg.getPara()));// para
            if (!"".equals(novaMsg.getCc())) {// Verifica se é com cópia
                novaMensagem.setRecipients(Message.RecipientType.CC, getRemetentes(novaMsg.getCc()));// Adiciona os remetentes a mensagem
            }
            if (!"".equals(novaMsg.getCco())) {// Verifica se é com cópia oculta
                novaMensagem.setRecipients(Message.RecipientType.BCC, getRemetentes(novaMsg.getCco()));// Adiciona os remetentes a mensagem
            }
            novaMensagem.setSubject(novaMsg.getAssunto());// assunto
            novaMensagem.setSentDate(new Date());// data atual			
            if (!novaMsg.isListaAnexosVazio()) {// Verifica se tem anexo
                List<File> arqs = novaMsg.getListaAnexos();// Obtém os anexos inseridos
                Multipart multipart = new MimeMultipart();// criando a Multipart
                MimeBodyPart mimeBodyPart = new MimeBodyPart();// criando a primeira parte da mensagem
                mimeBodyPart.setContent(novaMsg.getMsg(), "text/html; charset=UTF-8"); // Adiciona o conteúdo da mensagem
                multipart.addBodyPart(mimeBodyPart);// adicionando a parte na multipart
                for (File arq : arqs) {
                    MimeBodyPart mimeBodyPartAnexo = new MimeBodyPart();// criando a segunda parte da mensagem que contém os anexos
                    mimeBodyPartAnexo.setDataHandler(new DataHandler(new FileDataSource(arq)));// Adiciona o arquivo
                    mimeBodyPartAnexo.setFileName(arq.getName());// configurando o nome do arquivo, deixarei por padrão o nome atual
                    multipart.addBodyPart(mimeBodyPartAnexo);// adicionando o anexo na multipart
                }
                novaMensagem.setContent(multipart); // adicionando a multipart no conteudo da mensagem
                novaMensagem.saveChanges();// salva as alterações feitas a esta mensagem
            } else {// Se não tiver anexo envia apenas a mensagem
                novaMensagem.setContent(novaMsg.getMsg(), "text/html; charset=UTF-8");
            }
            // Envia a mensagem
            Transport.send(novaMensagem);
        } catch (Exception ex) {
            throw new Exception("Não é possível enviar a mensagem\nCertifique-se que os parâmetro de configuração estão corretos\nCertifique-se que está conectato a internet");
        } finally {
            lbEnviarReceber.setText("Concluído");
            this.setCursor(Cursor.getDefaultCursor());// Retorna para o cursor do mouse padrão
        }
    }

    // Obtém os remetentes
    private Address[] getRemetentes(String s) throws Exception {
        String[] str = s.replace(" ", "").split(";");// elimina espaços em branco e separa cada remetente por ;
        Address[] remetentes = new InternetAddress[str.length];// cria uma matriz de remetentes
        for (int i = 0; i < str.length; i++) {
            remetentes[i] = new InternetAddress(str[i]);// cria cada remetente e adiciona na matriz
        }
        return remetentes;
    }

    // Exclui a mensagem selecionada
    private void excluir() throws Exception {
        alteracaoMsgs = true;
        mensagensDAO.deleteMensagem(MsgSelecionada.getCodigo());// Exclui do banco de dados
        diretorioMsgsDAO.deleteMsg(MsgSelecionada.getCodigo());// Exclui a mensagem - arquivo
        if (MsgSelecionada.getAnexo() != null) {
            String[] anexos = MsgSelecionada.getAnexo().split(";");
            for (String anexo : anexos) {
                diretorioMsgsDAO.deleteAnexo(anexo);// Exclui o anexo - arquivo
            }
        }
        mensagensDAO.addMensagensMapa();// Atualiza o hashmap
        tableModel.fireTableDataChanged();// Aciona a notificação de alteração de dados da tabela
        lbQtdadeMsgs.setText(mensagensDAO.getQtdadeMensagensCadas() + " mensagens");// Atualiza a quantidade na tela
        MsgSelecionada = null;
        btEncaminhar.setEnabled(false);
        btResponder.setEnabled(false);
        btExcluir.setEnabled(false);
        epMensagem.setText("");
        alteracaoMsgs = false;
    }

    // Chamado quando uma seleção de linha na tabela é feita
    private void mostraMsgSelecionada() throws Exception {
        /*Se não for no meio de uma operação de exclusão, ou insersão de uma
        mensagem, exibe a mensagem selecionada*/
        if (!alteracaoMsgs) {
            try {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));// Mostra cursor do mouse de ampulheta enquanto a mensagem é carregada
                MsgSelecionada = mensagensDAO.getMensagemMapa(tabelaMsg.getSelectedRow());// Obtém uma mensagem da tabela
                epMensagem.setText(diretorioMsgsDAO.getConteudoMsg(MsgSelecionada.getCodigo()).toString());// Mostra a mensagem na tela
                String anexo = MsgSelecionada.getAnexo();
                if (anexo != null) {
                    editorAnexoTab.setAnexo(anexo);
                }
                btEncaminhar.setEnabled(true);
                btResponder.setEnabled(true);
                btExcluir.setEnabled(true);
                epMensagem.setCaretPosition(0);// Aciona o cursor na primeira linha
            } finally {
                this.setCursor(Cursor.getDefaultCursor());// Retorna para o cursor do mouse padrão
            }
        }
    }

    // Abre ao iniciar o programa
    public void abrirIniciar() throws Exception {
        conexao = conexaoDAO.getConexao();// Lê uma conexão no banco
        if (conexao == null) {// Se não existe uma configuração inicial, é aberta a tela de configurações de conexão
            abrirConfiguracoesConexao(true);// flag true indica que é para finalizar se clicado no 'X'
        } else {
            conectar();// Caso contrário conecta-se com o servidor
        }
    }

    // Abre as configurações de conexão passando uma flag se é para finalizar ou não o programa ao clixcar no 'X'
    private void abrirConfiguracoesConexao(boolean encerar) throws Exception {
        ConfigurarConexao configurarConexao = new ConfigurarConexao(this, encerar, conexaoDAO);
        if (conexao != null) {// Verifica se já existe uma conexão
            configurarConexao.setConexao(conexao);// Adiciona as configurações na tela
        }
        configurarConexao.setVisible(true);// Exibe a caixa de diálogo das configurações de conexão
        if (!configurarConexao.isFechou()) {// Verifica se a caixa de diálogo não foi fechada no 'X'
            conexao = configurarConexao.getConexao();// Obtém as configurações  de conexão
            conectar();// Conecta-se com o servidor
        }
    }

    // Conecta-se ao servidor de e-mail
    private void conectar() throws Exception {
        // Estabelece a sessão do JavaMail ao servidor
        try {
            // Cria as propriedades para inicializar a sessão do JavaMail com o servidor
            props = new Properties();// Propriedades de conexão
            if ("pop3".equals(conexao.getTipo())) {// verifica se pop3 ou imap
                props.put("mail.pop3.port", conexao.getPortaPop3Imap());// porta pop3
                props.put("mail.pop3.socketFactory.fallback", "false");// Cria um socket aspecificado abaixo
                props.put("mail.pop3.socketFactory.port", conexao.getPortaPop3Imap());// Cria soket pop3
                props.put("mail.pop3.starttls.enable", conexao.isConSegura());// conexão segura do pop3
            } else {
                props.put("mail.imap.port", conexao.getPortaPop3Imap());// porta imap
                props.put("mail.imap.socketFactory.fallback", "false");// Cria um socket aspecificado abaixo
                props.put("mail.imap.socketFactory.port", conexao.getPortaPop3Imap());// Cria soket imap
                props.put("mail.imap.starttls.enable", conexao.isConSegura());// conexão segura do imap
            }
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", conexao.getServidorSmtp());// identifica o nome do servidor smtp
            props.put("mail.smtp.port", conexao.getPortaSmtp()); // porta smtp
            props.put("mail.smtp.socketFactory.fallback", "false");// Cria um socket aspecificado abaixo
            props.put("mail.smtp.socketFactory.port", conexao.getPortaSmtp());// Cria soket SMTP
            props.put("mail.smtp.auth", conexao.isAutenticacao());// autenticação com o servidor
            props.put("mail.smtp.starttls.enable", conexao.isConSegura());// conexão segura do smtp
            BaixarMsg baixarMsg = new BaixarMsg(this);// Thread que faz download de novas mensagens a cada 2 minutos
            baixarMsg.start();
        } catch (Exception ex) {
            throw new Exception("Não é possível conectar-se com o servidor\nCertifique-se que os parâmetro de configuração estão corretos\nCertifique-se que está conectato a internet");
        }
    }

    // Conecta-se ao servidor e baixa as mensagens - Thread interna
    public void baixarNovasMsgs() throws Exception {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Store store = null;
                try {
                    lbEnviarReceber.setText("Enviar/Receber...");
                    // Cria um sessão e conecta-se ao servidor de e-mail
                    session = Session.getInstance(props, new Autenticador(conexao.getUsuario(), conexao.getSenha()));// Usuário requer autenticação);
                    store = session.getStore(conexao.getTipo());
                    store.connect(conexao.getServidorPop3Imap(), conexao.getUsuario(), conexao.getSenha());
                    // Download mensagens do servidor
                    try {
                        baixarMsgs(store);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Não é possível conectar-se com o servidor\nCertifique-se que os parâmetro de configuração estão corretos\nCertifique-se que está conectato a internet", "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    lbEnviarReceber.setText("Concluído");
                    try {
                        store.close();// Fecha a conexão
                    } catch (Exception ex) {
                    }
                }
            }
        });
        thread.start();
    }

    private void baixarMsgs(Store store) throws Exception {
        Folder folder = null;// Pasta no servidor
        try {
            // Faz download dos cabeçalhos de mensagem a partir do servidor
            // Abre a pasta principal "INBOX"
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);// Folder.READ_WRITE esta pasta pode
            // ser modificada
            // Obtém a lista de mensagens da pasta
            Message[] mensagens = folder.getMessages();
            // Recupera o cabeçalho de cada mensagem da pasta
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            folder.fetch(mensagens, profile);
            // Adiciona mensagem no banco de dados
            for (Message msg : mensagens) {
                mensagensDAO.insertMensagem(getMensagem(msg));
            }
            alteracaoMsgs = true;
            // Adiciona mensagens na tabela
            mensagensDAO.addMensagensMapa();
            // Aciona a notificação de alteração de dados da tabela
            tableModel.fireTableDataChanged();
            lbQtdadeMsgs.setText(mensagensDAO.getQtdadeMensagensCadas() + " mensagens");// Atualiza a quantidade na tela
            alteracaoMsgs = false;
        } catch (Exception ex) {
            throw new Exception("Não é possível baixar mensagens do servidor\nCertifique-se que os parâmetro de configuração estão corretos\nCertifique-se que está conectato a internet");
        } finally {
            folder.close(true);// fecha a pasta no servidor
        }
    }

    private Mensagem getMensagem(Message msg) throws Exception {
        Mensagem mensagem = new Mensagem();
        mensagem.setCodigo(mensagensDAO.getProxCodMensagem());
        mensagem.setEmail(msg.getFrom()[0].toString());
        String assunto = msg.getSubject();
        if (assunto == null || "".equals(assunto.trim())) {
            assunto = "[Sem Assunto]";
        }
        mensagem.setAssunto(assunto);
        mensagem.setData(msg.getSentDate());
        List<String> anexos = diretorioMsgsDAO.gravarMsg(msg, mensagem.getCodigo());
        if (!anexos.isEmpty()) {
            String anexo = "";
            for (String s : anexos) {
                anexo += s + ";";// Separa cada nome de arquivo por ';'
            }
            mensagem.setAnexo(anexo);
        }
        return mensagem;
    }

    // Abre tela para cadastro de contatos
    private void cadasContatos() {
        CadasContatos contatos = new CadasContatos(con);
        contatos.setModal(true);
        contatos.setVisible(true);
    }

    // Abre a tela para consulta de contatos
    private void consContatos() throws Exception {
        ContatosDAO contatosDAO = new ContatosDAO(con);
        if (contatosDAO.isContatoVazio()) {
            throw new Exception("Não há contatos cadastrados\nCadastre um contato primeiro");
        }
        ConsultaContatos contatos = new ConsultaContatos(contatosDAO);
        contatos.setModal(true);
        contatos.setVisible(true);
    }

    // Trata eventos
    @Override
    public void actionPerformed(ActionEvent evento) {
        // Nova mensagem
        if (evento.getSource() == btNovaMsg) {
            try {
                novaMsg();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Resposta a uma mensagem
        if (evento.getSource() == btResponder) {
            try {
                responderMsg();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Encaminhar uma mensagem
        if (evento.getSource() == btEncaminhar) {
            try {
                encaminhaMsg();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Excluir uma mensagem
        if (evento.getSource() == btExcluir) {
            try {
                excluir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Abrir tela de cadastros de contatos
        if (evento.getSource() == miCadasContatos) {
            cadasContatos();
        }
        // Abrir a tela de consulta de contatos
        if (evento.getSource() == miConsContatos) {
            try {
                consContatos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Finalizar o programa
        if (evento.getSource() == miSair) {
            System.exit(0);
        }
        // Baixar novas mensagens
        if (evento.getSource() == miBaixarNovasMsg) {
            try {
                baixarNovasMsgs();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Abrir tela de configurações, passando flag false indicando que não é para fechar ao clicar no 'X'
        if (evento.getSource() == miConexoes) {
            try {
                abrirConfiguracoesConexao(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
