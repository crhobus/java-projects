package NovaOS;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import FormatacaoCampos.CriarObjGrafico;
import Modelo.ItensOS;
import Modelo.OrdemServico;

public class RelatorioOS extends JDialog {

    private JLabel lbPaginaImpressao, lbNumero, lbNomeClie;

    public RelatorioOS(OrdemServico ordemServico, List<ItensOS> itens) {
        initComponents(ordemServico, itens);
    }

    private void initComponents(OrdemServico ordemServico, List<ItensOS> itens) {
        setTitle("Ordem de Serviço");
        setLayout(null);

        lbPaginaImpressao = CriarObjGrafico.criarJLabel("   Página Impressão   ", 20, 20, 125, 26);
        lbPaginaImpressao.setForeground(Color.RED);
        lbPaginaImpressao.setBorder(BorderFactory.createLoweredBevelBorder());
        lbPaginaImpressao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbPaginaImpressao.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
            }
        });
        add(lbPaginaImpressao);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 248));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(801, 1000));

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBounds(10, 80, 820, 605);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(scroll);

        JLabel lbLogoMarca1 = new JLabel("TRANSPORTES E MECÂNICA");
        lbLogoMarca1.setBounds(50, 50, 490, 30);
        lbLogoMarca1.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(lbLogoMarca1);

        JLabel lbLogoMarca2 = new JLabel("GUTHNER LTDA - ME");
        lbLogoMarca2.setBounds(90, 100, 330, 30);
        lbLogoMarca2.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(lbLogoMarca2);

        JLabel lbLogoMarcaEnd = new JLabel("Rua Uberaba, 2389");
        lbLogoMarcaEnd.setBounds(510, 30, 190, 30);
        lbLogoMarcaEnd.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lbLogoMarcaEnd);

        JLabel lbLogoMarcaBai = new JLabel("Bairro Mulde");
        lbLogoMarcaBai.setBounds(510, 55, 190, 30);
        lbLogoMarcaBai.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lbLogoMarcaBai);

        JLabel lbLogoMarcaCid = new JLabel("89130-000 Indaial/SC");
        lbLogoMarcaCid.setBounds(510, 80, 190, 30);
        lbLogoMarcaCid.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lbLogoMarcaCid);

        JLabel lbLogoMarcaCNPJ = new JLabel("Inscr. CNPJ 11.256.357/0001-51");
        lbLogoMarcaCNPJ.setBounds(510, 105, 270, 30);
        lbLogoMarcaCNPJ.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lbLogoMarcaCNPJ);

        JLabel lbLogoMarcaIE = new JLabel("Inscr. Est. 255.969.007");
        lbLogoMarcaIE.setBounds(510, 130, 270, 30);
        lbLogoMarcaIE.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lbLogoMarcaIE);

        panel.add(CriarObjGrafico.criarJLabel("Número :", 30, 170, 90, 14));

        lbNumero = CriarObjGrafico.criarJLabel(Integer.toString(ordemServico.getCodigo()), 90, 170, 90, 14);
        panel.add(lbNumero);

        setSize(845, 720);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
