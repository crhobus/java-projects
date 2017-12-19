package Aula3;

import Aula1.*;
import java.util.*;
import javax.swing.table.*;

public class AlunosTableModel extends AbstractTableModel {

    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    public void add(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return alunos.size();
    }

    public Object getValueAt(int row, int col) {

        Aluno a = alunos.get(row);
        switch (col) {
            case 0:
                return a.getNome();
            case 1:
                return a.getMedia();
            default:
                return a.isPagou();
        }
    }
    private String titulos[] = new String[]{"nome", "media", "pagou"};

    @Override
    public String getColumnName(int col) {
        return titulos[col];
    }
}










