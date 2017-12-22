
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VerCliente extends JDialog {

    ArrayList<ArrayCliente> lista;

    VerCliente(ArrayList lista) {
        this.lista = lista;
        this.setSize(350, 300);
        this.setLocationRelativeTo(null);//Posiciona no centro da tela
        this.setResizable(false);
        this.criar();
        this.setVisible(true);
    }

    private void criar() {
        Color cor = new Color(200, 200, 200);
        Container tela = getContentPane();
        JLabel label = new JLabel("Abaixo Todos Clientes cadastrados");
        String texto = "";
        for (int i = 0; i < lista.size(); i++) {
            ArrayCliente n = lista.get(i);
            texto += i + 1 + ". Codigo: " + n.getCodigo() + "\n";
            texto += i + 1 + ". Nome: " + n.getNome() + "\n";
            texto += i + 1 + ". RG: " + n.getRg() + "\n";
            texto += i + 1 + ". CPF: " + n.getCpf() + "\n";
            texto += i + 1 + ". Profissão: " + n.getProfissao() + "\n";
            texto += i + 1 + ". Empresa: " + n.getEmpresa() + "\n";
            texto += i + 1 + ". Fone Empresa: " + n.getFone_empresa() + "\n";
            texto += i + 1 + ". Sexo: " + n.getSexo() + "\n";
            texto += i + 1 + ". CEP: " + n.getCep() + "\n";
            texto += i + 1 + ". Endereço: " + n.getEndereco() + "\n";
            texto += i + 1 + ". Bairro: " + n.getBairro() + "\n";
            texto += i + 1 + ". Numero: " + n.getNumero() + "\n";
            texto += i + 1 + ". Complemnto: " + n.getComplemento() + "\n";
            texto += i + 1 + ". Cidade: " + n.getCidade() + "\n";
            texto += i + 1 + ". Estado: " + n.getEstado() + "\n";
            texto += i + 1 + ". Fone: " + n.getFone() + "\n";
            texto += i + 1 + ". Celular: " + n.getCelular() + "\n";
            texto += i + 1 + ". E-Mail: " + n.getEmail() + "\n";
            texto += i + 1 + ". Descrição: " + n.getDescricao() + "\n";
            texto += "" + "\n";
        }
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);//Não editavel
        area.setBackground(cor);//cor fundo
        JScrollPane rolagem = new JScrollPane(area);//barra de rolagem
        tela.add(label, BorderLayout.NORTH);//borderlayout mostra label em cima
        tela.add(rolagem, BorderLayout.CENTER);
    }
}
