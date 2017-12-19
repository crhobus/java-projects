package Aula1;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class JanelaTurmaMonica extends JFrame{

	public JanelaTurmaMonica() {
		
		setSize(500, 300);
		setTitle("Aula de JTable");
		
		setLocationRelativeTo(null); // centraliza
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initComponents();
		
	}

	
	private void initComponents() {
		
		JTable tabela = new JTable();
		tabela.setModel(new TurmaMonicaTableModel());
		
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		//scrollPane.setViewportView(tabela);
		
		add(scrollPane);
		
		
	}

	public static void main(String[] args) {
		
		JanelaTurmaMonica j = new JanelaTurmaMonica();
		j.setVisible(true);
		
	}
	
	
}








