package view.desktop.usuario;

import control.Servidor;
import control.funcoes.ExceptionError;
import control.funcoes.Funcoes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Perfil;
import view.componentes.table.TableModel;

public class TableModelFuncoes extends TableModel {

    private UsuarioSis usuarioSis;
    private List<Perfil> perfis = new ArrayList<>();

    public TableModelFuncoes(UsuarioSis usuarioSis) {
        this.usuarioSis = usuarioSis;
    }

    @Override
    public int getRowCount() {
        return perfis.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Perfil perfil = perfis.get(linha);
        switch (coluna) {
            case 0:
                return Funcoes.validaString(perfil.getFuncao().getNmFuncao());
            case 1:
                return "S".equalsIgnoreCase(perfil.getIeAlterarDados());
            default:
                return "...";
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Função";
            case 1:
                return "Alterar Dados";
            default:
                return "Remover";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if ("Administração".equals(usuarioSis.getUsuario().getTipoUsuario().getNmTipoUsuario())
                || "Gerência".equals(usuarioSis.getUsuario().getTipoUsuario().getNmTipoUsuario())) {
            return coluna == 1 || coluna == 2;
        }
        return false;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        if (coluna == 1) {
            Perfil perfil = perfis.get(linha);
            perfil.setIeAlterarDados((((boolean) valor) ? "S" : "N"));
            try {
                getServidor().getUsuarioAction().alterarPermissao(linha, usuarioSis.getUsuarioExibicao(), perfil);
            } catch (ExceptionError ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
            }
        }
        fireTableDataChanged();
    }

    public Servidor getServidor() {
        return usuarioSis.getServidor();
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
        fireTableDataChanged();
    }

    public void atualizaTabela() {
        fireTableDataChanged();
    }

    public Perfil getPerfil(int linha) {
        return perfis.get(linha);
    }
}
