package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import model.Mensagem;

//Classe que conecta os emails ao banco de dados
public class MensagensDAO extends DBDAO {

    private Connection con;// Conexão com o banco de dados
    private Map<Integer, Mensagem> mensagens;// Mensagens que serão exibidas na tabela, a chave é o número da linha
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;

    public MensagensDAO(Connection con) {
        super(con);// Passa a conexão para super classe
        this.con = con;
        mensagens = new HashMap<Integer, Mensagem>();// Mensagens do banco de dados
        formatDate = new SimpleDateFormat("dd/MM/yyyy");// formatando data
        formatHora = new SimpleDateFormat("HH:mm");// formatando hora
    }

    // Insere uma mensagem no banco de dados
    public boolean insertMensagem(Mensagem mensagem) throws Exception {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBEMAIL (CODEMAIL, EMAIL, ASSUNTO, ANEXO, "
                    + "DATAREC) VALUES (?, ?, ?, ?, ?)");
            stm.setInt(1, mensagem.getCodigo());
            stm.setString(2, mensagem.getEmail());
            stm.setString(3, mensagem.getAssunto());
            stm.setString(4, mensagem.getAnexo());
            stm.setTimestamp(5, new Timestamp(mensagem.getData().getTime()));
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a mensagem no cliente de e-mail");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    // Obtém o próximo código disponível
    public int getProxCodMensagem() throws SQLException {
        return getProxCod("SELECT MAX(CODEMAIL) FROM TBEMAIL",
                "Erro na leitura de mensagens no cliente de e-mail");
    }

    // Exclui uma mensagem do banco de dados
    public boolean deleteMensagem(int codMensagem) throws SQLException {
        return delete("DELETE FROM TBEMAIL WHERE CODEMAIL = " + codMensagem,
                "Não foi possível excluir a mensagem do cliente de e-mail");
    }

    // Adiciona todas as mensagens do banco de dados no HashMap
    public void addMensagensMapa() throws SQLException {
        mensagens.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement("SELECT * FROM TBEMAIL ORDER BY CODEMAIL DESC").executeQuery();
            int linha = 0;
            while (rs.next()) {
                mensagens.put(linha, getMensagemAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de mensagens no cliente de e-mail");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    // Obtém um objeto mensagem do banco de dados
    private Mensagem getMensagemAux(ResultSet rs) throws SQLException {
        try {
            Mensagem mensagem = new Mensagem();
            mensagem.setCodigo(rs.getInt(1));
            mensagem.setEmail(rs.getString(2));
            mensagem.setAssunto(rs.getString(3));
            mensagem.setAnexo(rs.getString(4));
            mensagem.setData(rs.getTimestamp(5));
            return mensagem;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da mensagem no cliente de e-mail");
        }
    }

    // Conteúdo de cada célula da tabela
    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Mensagem mensagem = mensagens.get(linha);
        switch (coluna) {
            case 0:
                return mensagem.getEmail();
            case 1:
                return mensagem.getAssunto();
            case 2:
                return formatDate.format(mensagem.getData()) + " as " + formatHora.format(mensagem.getData());
            default:
                if (mensagem.getAnexo() != null) {
                    return new ImageIcon("Anexo.png");
                } else {
                    return null;
                }
        }
    }

    // Quantidade de mensagens cadastradas
    public int getQtdadeMensagensCadas() {
        return mensagens.size();
    }

    // Obtém uma mensagem da linha passada por parâmetro da tabela
    public Mensagem getMensagemMapa(int linha) {
        return mensagens.get(linha);
    }
}
