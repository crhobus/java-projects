package view.desktop.funcionario;

import control.funcoes.Funcoes;
import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Funcionario;
import view.componentes.table.TableModel;

public class TableModelFuncionario extends TableModel {

    private List<Funcionario> funcionarios;
    private int index;

    public TableModelFuncionario(CadasFuncionario cadasFuncionario) {
        try {
            this.funcionarios = cadasFuncionario.getServidor().getFuncionarioAction().getFuncionarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Funcionario funcionario = funcionarios.get(linha);
        switch (coluna) {
            case 0:
                return Funcoes.validaString(funcionario.getNmFuncionario());
            case 1:
                return Funcoes.validaString(funcionario.getNrCpf());
            case 2:
                return Funcoes.validaString(funcionario.getEndereco().getDsEndereco());
            case 3:
                return Funcoes.validaString(funcionario.getEndereco().getNmBairro());
            case 4:
                return funcionario.getEndereco().getNrResidencia();
            case 5:
                return Funcoes.validaString(funcionario.getEndereco().getNmCidade());
            case 6:
                return Funcoes.validaString(funcionario.getDsCargo());
            case 7:
                return Funcoes.validaString(funcionario.getNrTefefone());
            default:
                return Funcoes.validaString(funcionario.getUsuario().getDsEmail());
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Funcionário";
            case 1:
                return "CPF";
            case 2:
                return "Endereço";
            case 3:
                return "Bairro";
            case 4:
                return "Número";
            case 5:
                return "Cidade";
            case 6:
                return "Cargo";
            case 7:
                return "Telefone";
            default:
                return "Email";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            default:
                return String.class;
        }
    }

    public void addFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        index = funcionarios.size() - 1;
    }

    public Funcionario getFuncionario(int linha) {
        index = linha;
        return funcionarios.get(linha);
    }

    public Funcionario removerFuncionario(int linha) {
        index = linha;
        return funcionarios.remove(linha);
    }

    public Funcionario removerFuncionario(Funcionario funcionario) {
        Funcionario func;
        for (int i = 0; i < funcionarios.size(); i++) {
            func = funcionarios.get(i);
            if (func.getCdFuncionario() == funcionario.getCdFuncionario()) {
                funcionarios.remove(func);
                index = i;
                return func;
            }
        }
        return null;
    }

    public boolean possuiRegistros() {
        return !funcionarios.isEmpty();
    }

    public void atualizaTabela() {
        fireTableDataChanged();
    }

    public int getLinhaSelecionada() {
        if (index > (funcionarios.size() - 1)) {
            index--;
            if (index < 0) {
                index = 0;
            }
            return index;
        }
        return index;
    }
}
