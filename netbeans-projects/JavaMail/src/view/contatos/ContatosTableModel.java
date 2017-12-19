package view.contatos;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import control.ContatosDAO;

//Modelo da tabela de contatos
public class ContatosTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private ContatosDAO contatosDAO;// Objeto que conecta os contatos ao banco de dados

    public ContatosTableModel(ContatosDAO contatosDAO) {
        this.contatosDAO = contatosDAO;
        try {
            contatosDAO.listContatos();// Obtém todos os contatos
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Obtém o número de colunas
    @Override
    public int getColumnCount() {
        return 2;
    }

    // Obtém o número de linhas
    @Override
    public int getRowCount() {
        return contatosDAO.getQtdadeContatosCadas();
    }

    // Obtém o valor de cada célula
    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return contatosDAO.conteudoTableModel(linha, coluna);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Obtém o nome de cada coluna
    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Nome";
            default:
                return "E-Mail";
        }
    }
}
