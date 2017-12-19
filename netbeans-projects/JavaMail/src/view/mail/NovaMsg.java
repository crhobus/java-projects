package view.mail;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Contato;
import model.Mensagem;

import control.ContatosDAO;
import control.DiretorioMsgsDAO;

import view.PanelComponentes;
import view.contatos.ConsultaContatos;
import view.contatos.ListenerContatos;

// Classe para envio de nova mensagem
public class NovaMsg extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfPara, tfComCopia, tfComCopiaOculta, tfAssunto;// Remetentes e assunto
    private JComboBox cbAnexo;// Onde estão o nome dos arquivos que serão inseridos em anexo
    private JEditorPane epMensagem;// Onde será exibida a mensagem
    private JButton btContatosPara, btContatosCc, btContatosCco, btEnviar,
            btCancelar, btAnexo;// botões de consulta, enviar e cancelar, anexos
    private boolean fechado;// Flag utilizado para verificar quando a caixa de
    // diálogo é fechada no botão 'X'
    private ContatosDAO contatosDAO;// Conexões dos contatos com Banco de Dados
    private List<File> anexos;// Lista com os anexos

    public NovaMsg(Frame principal, TipoMsg tipoMsg, Mensagem mensagem,
            Connection con, DiretorioMsgsDAO diretorioMsgsDAO) throws Exception {
        super(principal, true);// Chama o superconstrutor, especificando que essa caixa de diálogo é modal
        contatosDAO = new ContatosDAO(con);// Instancia passando a conexão
        anexos = new ArrayList<File>();
        initComponents(principal, tipoMsg, mensagem, diretorioMsgsDAO);
        if (tipoMsg != TipoMsg.NOVO && mensagem.getAnexo() != null) {// Se não for nova mensagem e tiver anexo adiciona a mensagem
            String[] str = mensagem.getAnexo().split(";");// Separa cada nome de arquivo por ';'
            for (String nomeArq : str) {
                anexos.add(diretorioMsgsDAO.getAnexo(nomeArq));
                cbAnexo.addItem(nomeArq);
            }
            cbAnexo.setSelectedItem(null);
        }
    }

    // Cria os componentes gráficos
    private void initComponents(Frame principal, TipoMsg tipoMsg, Mensagem mensagem, DiretorioMsgsDAO diretorioMsgsDAO) throws Exception {
        this.setLayout(null);
        /*Configura título da caixa de diálogo e obtém os valores "Para",
        "Assunto", e "Texto" da mensagem com base no tipo de mensagem*/
        String para = "", assunto = "", texto = "";
        boolean flagPara = false;// Flag que identifica se é resposta para uma mensagem
        switch (tipoMsg) {
            // Responde à mensagem
            case RESPONDER:
                this.setTitle("Resposta a mensagem");
                para = mensagem.getEmail();// Obtém o parâmetro "Para" da mensagem
                assunto = mensagem.getAssunto();// Obtém o assunto da mensagem
                if (!assunto.contains("RES:")) {// Verifica se o assunto não contém RES: de responder
                    assunto = "RES: " + assunto;
                }
                // Obtém o conteúdo da mensagem.
                texto = diretorioMsgsDAO.getConteudoMsg(mensagem.getCodigo()) + "\n--------- RESPOSTA A MENSAGEM ---------\n";
                flagPara = true;
                break;
            // Encaminha a mensagem
            case ENCAMINHAR:
                this.setTitle("Encaminhar Mensagem");
                assunto = mensagem.getAssunto();// Obtém o assunto da mensagem
                if (!assunto.contains("ENC:")) {// Verifica se o assunto não contém ENC: de encaminhar
                    assunto = "ENC: " + assunto;
                }
                // Obtém o conteúdo da mensagem
                texto = diretorioMsgsDAO.getConteudoMsg(mensagem.getCodigo()) + "\n--------- ENCAMINHANDO A MENSAGEM ---------\n";
                break;
            // Nova mensagem
            default:
                this.setTitle("Nova Mensagem");
        }
        PanelComponentes panel = new PanelComponentes(5, 5, 625, 510);
        this.add(panel);

        panel.addJLabel("Para.:", 30, 20, 40, 14);

        tfPara = panel.addJTextField(66, 17, 500, 20);
        tfPara.setText(para);

        btContatosPara = panel.addJButton("...", "Pesquisar contato", 575, 13, 37, 26);
        btContatosPara.addActionListener(this);

        if (flagPara) {
            tfPara.setBackground(Color.WHITE);
            tfPara.setEditable(false);
            btContatosPara.setEnabled(false);
        }

        panel.addJLabel("Cc.:", 40, 50, 40, 14);

        tfComCopia = panel.addJTextField(66, 47, 500, 20);

        btContatosCc = panel.addJButton("...", "Pesquisar contato", 575, 43, 37, 26);
        btContatosCc.addActionListener(this);

        panel.addJLabel("Cco.:", 34, 80, 40, 14);

        tfComCopiaOculta = panel.addJTextField(66, 77, 500, 20);

        btContatosCco = panel.addJButton("...", "Pesquisar contato", 575, 73, 37, 26);
        btContatosCco.addActionListener(this);

        panel.addJLabel("Assunto:", 18, 110, 50, 14);

        tfAssunto = panel.addJTextField(66, 107, 500, 20);
        tfAssunto.setText(assunto);

        btAnexo = panel.addJButton("", "Inserir anexo", 575, 103, 37, 26);
        btAnexo.setIcon(new ImageIcon("Anexo.png"));
        btAnexo.addActionListener(this);

        epMensagem = panel.addJEditorPane(0, 140, 623, 300);
        epMensagem.setText(texto);

        String[] itens = {};
        cbAnexo = panel.addJComboBox(itens, 5, 445, 180, 20);
        cbAnexo.setToolTipText("Anexos atualmente inseridos");

        btEnviar = panel.addJButton("Enviar", "Enviar Mensagem", 225, 460, 70, 26);
        btEnviar.addActionListener(this);

        btCancelar = panel.addJButton("Cancelar", "Limpar os Campos", 335, 460, 70, 26);
        btCancelar.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evento) {
                fechado = true;// Quando fechar no 'X' seta flag para true
            }
        });
        this.setSize(640, 547);
        this.setResizable(false);
        this.setLocationRelativeTo(principal);// Centraliza a caixa de diálogo na aplicação
    }

    // Atualiza todos os campos para modo original
    private void limparTela() {
        tfPara.setText("");
        tfComCopia.setText("");
        tfComCopiaOculta.setText("");
        tfAssunto.setText("");
        epMensagem.setText("");
        tfPara.setEditable(true);
        btContatosPara.setEnabled(true);
        if (!anexos.isEmpty()) {
            for (int i = 0; i < anexos.size(); i++) {
                anexos.remove(i);
                cbAnexo.removeAllItems();
            }
        }
    }

    // Valida configurações de conexão e fecha a caixa de diálogo
    private void enviar() throws Exception {
        if ("".equals(tfPara.getText().trim())) {
            throw new Exception("Campo para obrigatório não preenchido,\nentre com o endereço do destinatário");
        }
        if ("".equals(tfAssunto.getText().trim())) {
            tfAssunto.setText("[Sem Assunto]");
        }
        if (epMensagem.getText().length() == 110) {// verifica se o campo esta vazio, inicialmente está com 110 por causa das tags html
            epMensagem.setText("[Sem Mensagem]");
        }
        // Fecha a caixa de diálogo.
        this.dispose();
    }

    // Consulta contatos e exibe conforme o tipo solicitado
    private void consultaContatos(final int tipo) throws Exception {
        if (contatosDAO.isContatoVazio()) {// verifica se há contatos cadastrados
            throw new Exception("Não há contatos cadastrados\nCadastre um contato primeiro");
        }
        ConsultaContatos consulta = new ConsultaContatos(contatosDAO);
        consulta.setListener(new ListenerContatos() {

            @Override
            public void exibeContato(Contato contato) {
                switch (tipo) {
                    case 1:
                        tfPara.setText(contato.getNome() + " <" + contato.getEmail() + ">");
                        break;
                    case 2:
                        if ("".equals(tfComCopia.getText().trim())) {
                            tfComCopia.setText(contato.getNome() + " <" + contato.getEmail() + ">");
                        } else {
                            tfComCopia.setText(tfComCopia.getText() + "; " + contato.getNome() + " <" + contato.getEmail() + ">");
                        }
                        break;
                    default:
                        if ("".equals(tfComCopiaOculta.getText().trim())) {
                            tfComCopiaOculta.setText(contato.getNome() + " <" + contato.getEmail() + ">");
                        } else {
                            tfComCopiaOculta.setText(tfComCopiaOculta.getText() + "; " + contato.getNome() + " <" + contato.getEmail() + ">");
                        }
                        break;
                }
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    // Abre o seletor de arquivos
    private void anexarArquivo() throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File arq = fileChooser.getSelectedFile();// Obém o arquivo selecionado
            anexos.add(arq);// Adiciona na lista
            cbAnexo.addItem(arq.getName());// adiciona o nome do arquivo no combo box de anexos
        }
    }

    // Obtém a lista com os anexos
    public List<File> getListaAnexos() {
        return anexos;
    }

    // Verifica se a lista de anexos está vazia
    public boolean isListaAnexosVazio() {
        return anexos.isEmpty();
    }

    // Obtém verdadeiro se a caixa de diálogo foi fechada
    public boolean isFechado() {
        return fechado;
    }

    // Obtém o valor do campo "para" da mensagem
    public String getPara() {
        return tfPara.getText();
    }

    // Obtém o valor do campo "cc" da mensagem
    public String getCc() {
        return tfComCopia.getText();
    }

    // Obtém o valor do campo "cco" da mensagem
    public String getCco() {
        return tfComCopiaOculta.getText();
    }

    // Obtém o assunto da mensagem
    public String getAssunto() {
        return tfAssunto.getText();
    }

    // Obtém a mensagem
    public String getMsg() {
        return epMensagem.getText();
    }

    // Trata eventos
    @Override
    public void actionPerformed(ActionEvent evento) {
        // Valida configurações de conexão e fecha a caixa de diálogo
        if (evento.getSource() == btEnviar) {
            try {
                enviar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Atualiza todos os campos para modo original
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        // Consulta contatos do tipo para
        if (evento.getSource() == btContatosPara) {
            try {
                consultaContatos(1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Consulta contatos do tipo com cópia
        if (evento.getSource() == btContatosCc) {
            try {
                consultaContatos(2);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Consulta contatos do tipo com cópia oculta
        if (evento.getSource() == btContatosCco) {
            try {
                consultaContatos(3);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Consulta contatos do tipo com cópia oculta
        if (evento.getSource() == btAnexo) {
            try {
                anexarArquivo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
