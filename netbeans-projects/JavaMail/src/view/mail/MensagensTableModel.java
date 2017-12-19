package view.mail;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import control.MensagensDAO;

//Modelo da tabela de mensagens
public class MensagensTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private MensagensDAO mensagensDAO;// Conexões das mensagens com o Banco de Dados

    public MensagensTableModel(MensagensDAO mensagensDAO) {
        this.mensagensDAO = mensagensDAO;
        try {
            mensagensDAO.addMensagensMapa();// Obtém todas as mensagens do banco de dados
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Obtém a quantidade de colunas da tabela
    @Override
    public int getColumnCount() {
        return 4;
    }

    // Obtém a quantidade de linhas da tabela
    @Override
    public int getRowCount() {
        return mensagensDAO.getQtdadeMensagensCadas();
    }

    // Obtém o valor de cada célula
    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return mensagensDAO.conteudoTableModel(linha, coluna);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Tipo de cada coluna
    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                return ImageIcon.class;
        }
    }

    // Quando há anexo a célula fica editável
    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (getValueAt(linha, coluna) != null) {
            return coluna == 3;
        }
        return false;
    }

    // Obtém o nome de cada coluna
    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Remetente";
            case 1:
                return "Assunto";
            case 2:
                return "Data";
            default:
                return "Anexo";
        }
    }
}
