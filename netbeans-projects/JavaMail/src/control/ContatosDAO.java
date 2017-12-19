package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Contato;

// Classe que conecta os contatos ao banco de dados
public class ContatosDAO extends DBDAO {

    private Connection con;// Conexão com o banco de dados
    private Map<Integer, Contato> contatos;// Contatos que serão exibidos na tabela, a chave é o número da linha

    public ContatosDAO(Connection con) {
        super(con);// Passa a conexão para super classe
        this.con = con;
        contatos = new HashMap<Integer, Contato>();
    }

    // Insere um contato no banco de dados
    public boolean insertContato(Contato contato) throws Exception {
        // Verifica se contato já cadastrado
        if (isCadastrado("SELECT CODCONTATO FROM TBCONTATOS WHERE CODCONTATO = " + contato.getCodigo(),
                "Erro na leitura do contato no cliente de e-mail")) {
            return updateContato(contato);// Se estiver sobrescreve
        }
        // Insere novo
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBCONTATOS (CODCONTATO, NOME, EMAIL) VALUES (?, ?, ?)");
            stm.setInt(1, getProxCod("SELECT MAX(CODCONTATO) FROM TBCONTATOS", "Erro na leitura de contatos no cliente de e-mail"));// Obtém o próximo código disponível
            stm.setString(2, contato.getNome());
            stm.setString(3, contato.getEmail());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o contato no cliente de e-mail");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    // Sobrescreve o contato no banco de dados
    private boolean updateContato(Contato contato) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBCONTATOS SET NOME = ?, EMAIL = ? WHERE CODCONTATO = ?");
            stm.setString(1, contato.getNome());
            stm.setString(2, contato.getEmail());
            stm.setInt(3, contato.getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do contato no cliente de e-mail");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    // Verifica se e-mail ja está cadastrado, se está retorna o código do respectivo e-mail
    public int getContatoCadastrado(String email) throws SQLException {
        return getCadastrado("SELECT CODCONTATO FROM TBCONTATOS WHERE LOWER(EMAIL) = '"
                + email.toLowerCase() + "'", "Erro na leitura do contato no cliente de e-mail");
    }

    // Verifica se á contatos cadastrados
    public boolean isContatoVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBCONTATOS",
                "Erro na leitura de contatos no cliente de e-mail");
    }

    // Exclui o contato do banco de dados
    public boolean deleteContato(String email) throws SQLException {
        return delete("DELETE FROM TBCONTATOS WHERE LOWER(EMAIL) = '"
                + email.toLowerCase() + "'", "Não foi possível excluir o contato do cliente de e-mail");
    }

    // Obtém todos os contatos do banco de dados
    public void listContatos() throws SQLException {
        addContatosMapa("SELECT * FROM TBCONTATOS ORDER BY CODCONTATO");
    }

    // Filtra do banco de dados conforme condição
    public void listContatosCondicao(int coluna, String s) throws SQLException {
        switch (coluna) {
            case 0:
                addContatosMapa(getSql("LOWER(NOME) like '%" + s.toLowerCase() + "%'"));
                break;
            default:
                addContatosMapa(getSql("LOWER(EMAIL) like '%" + s.toLowerCase() + "%'"));
                break;
        }
    }

    // Código sql para filtro
    private String getSql(String condicao) {
        return "SELECT * FROM TBCONTATOS WHERE " + condicao + " ORDER BY CODCONTATO";
    }

    // Adiciona os contatos no HashMap
    private void addContatosMapa(String sql) throws SQLException {
        contatos.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                contatos.put(linha, getContatoAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de contatos no cliente de e-mail");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    // Obtém um objeto contato do banco de dados
    private Contato getContatoAux(ResultSet rs) throws SQLException {
        try {
            Contato contato = new Contato();
            contato.setCodigo(rs.getInt(1));
            contato.setNome(rs.getString(2));
            contato.setEmail(rs.getString(3));
            return contato;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do contato no cliente de e-mail");
        }
    }

    // Limpa o HashMap
    public void limparMapa() {
        contatos.clear();
    }

    // Conteúdo de cada célula da tabela
    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Contato contato = contatos.get(linha);
        switch (coluna) {
            case 0:
                return contato.getNome();
            default:
                return contato.getEmail();
        }
    }

    // Quantidade de contatos cadastrados
    public int getQtdadeContatosCadas() {
        return contatos.size();
    }

    // Obtém um contato da linha passada por parâmetro da tabela
    public Contato getContatoMapa(int linha) {
        return contatos.get(linha);
    }
}
