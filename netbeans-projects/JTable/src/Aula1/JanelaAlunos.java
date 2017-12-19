package Aula1;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class JanelaAlunos extends JFrame{

	public JanelaAlunos() {
		
		setSize(500, 300);
		setTitle("Aula de JTable");
		
		setLocationRelativeTo(null); // centraliza
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initComponents();
		
	}

	
	private void initComponents() {
		
		Aluno a1 = new Aluno("A", 10, true);
		Aluno a2 = new Aluno("B", 8, false);
		
		AlunosTableModel atm = new AlunosTableModel();
		atm.add(a1);
		atm.add(a2);
		
		JTable tabela = new JTable();
		tabela.setModel(atm);
		
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		//scrollPane.setViewportView(tabela);
		
		add(scrollPane);
		
		
		
		
	}

	public static void main(String[] args) {
		
		JanelaAlunos j = new JanelaAlunos();
		j.setVisible(true);
		
	}
	
	
}








