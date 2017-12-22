package ConsultaHistoricoOS;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Formatacao.ObjGraficos;
import Modelo.Cliente;
import Modelo.ItensOS;
import Modelo.OrdemServico;
import OrdensServicos.ItensOSControl;

public class HistoricoOS extends ObjGraficos {

    private static final long serialVersionUID = 1726616386259157715L;

    public HistoricoOS(Connection con, Cliente cliente, List<OrdemServico> lista) {
        try {
            initComponents(con, cliente, lista);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents(Connection con, Cliente cliente, List<OrdemServico> lista) throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
        ItensOSControl controlItensOS = new ItensOSControl(con);
        setTitle("Histórico das Ordens de Serviços");
        setLayout(null);
        JPanel panelPri = getJPanelLineBorder(10, 10, 875, 695);
        add(panelPri);

        JPanel panelClie = getJPanelLineBorder(0, 0, 875, 160);
        panelPri.add(panelClie);

        JPanel panelOS = new JPanel();
        panelOS.setLayout(null);
        panelOS.setBackground(new Color(248, 248, 248));
        panelOS.setPreferredSize(new Dimension(0, 535 * lista.size()));

        JScrollPane scrollPanel = new JScrollPane(panelOS);
        scrollPanel.setBounds(0, 160, 875, 535);
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelPri.add(scrollPanel);

        panelClie.add(getJLabel("CPF / CNPJ", 20, 20, 80, 14));
        JTextField tfCPFCNPJ = getJTextField(20, 38, 120, 20);
        tfCPFCNPJ.setText(cliente.getCpfCNPJ());
        tfCPFCNPJ.setEditable(false);
        tfCPFCNPJ.setBackground(Color.WHITE);
        panelClie.add(tfCPFCNPJ);

        panelClie.add(getJLabel("RG / IE", 150, 20, 80, 14));
        JTextField tfRGIE = getJTextField(150, 38, 120, 20);
        if (cliente.getCpfCNPJ().length() == 14) {
            tfRGIE.setText(cliente.getRg());
        } else {
            tfRGIE.setText(cliente.getIe());
        }
        tfRGIE.setEditable(false);
        tfRGIE.setBackground(Color.WHITE);
        panelClie.add(tfRGIE);

        panelClie.add(getJLabel("Nome / Razão Social", 280, 20, 120, 14));
        JTextField tfNomeClie = getJTextField(280, 38, 280, 20);
        tfNomeClie.setText(cliente.getNome());
        tfNomeClie.setEditable(false);
        tfNomeClie.setBackground(Color.WHITE);
        panelClie.add(tfNomeClie);

        panelClie.add(getJLabel("Endereço", 570, 20, 80, 14));
        JTextField tfEndereco = getJTextField(570, 38, 270, 20);
        tfEndereco.setText(cliente.getEndereco());
        tfEndereco.setEditable(false);
        tfEndereco.setBackground(Color.WHITE);
        panelClie.add(tfEndereco);

        panelClie.add(getJLabel("Bairro", 20, 60, 80, 14));
        JTextField tfBairro = getJTextField(20, 78, 200, 20);
        tfBairro.setText(cliente.getBairro());
        tfBairro.setEditable(false);
        tfBairro.setBackground(Color.WHITE);
        panelClie.add(tfBairro);

        panelClie.add(getJLabel("Número", 230, 60, 80, 14));
        JTextField tfNumero = getJTextField(230, 78, 100, 20);
        tfNumero.setText(Integer.toString(cliente.getNumero()));
        tfNumero.setEditable(false);
        tfNumero.setBackground(Color.WHITE);
        panelClie.add(tfNumero);

        panelClie.add(getJLabel("Complemento", 340, 60, 90, 14));
        JTextField tfComplemento = getJTextField(340, 78, 150, 20);
        tfComplemento.setText(cliente.getComplemento());
        tfComplemento.setEditable(false);
        tfComplemento.setBackground(Color.WHITE);
        panelClie.add(tfComplemento);

        panelClie.add(getJLabel("Cidade", 500, 60, 80, 14));
        JTextField tfCidade = getJTextField(500, 78, 170, 20);
        tfCidade.setText(cliente.getCidade());
        tfCidade.setEditable(false);
        tfCidade.setBackground(Color.WHITE);
        panelClie.add(tfCidade);

        panelClie.add(getJLabel("UF", 680, 60, 80, 14));
        JTextField tfUF = getJTextField(680, 78, 160, 20);
        tfUF.setText(cliente.getUf());
        tfUF.setEditable(false);
        tfUF.setBackground(Color.WHITE);
        panelClie.add(tfUF);

        panelClie.add(getJLabel("Referência", 20, 100, 80, 14));
        JTextField tfReferencia = getJTextField(20, 118, 180, 20);
        tfReferencia.setText(cliente.getReferencia());
        tfReferencia.setEditable(false);
        tfReferencia.setBackground(Color.WHITE);
        panelClie.add(tfReferencia);

        panelClie.add(getJLabel("CEP", 210, 100, 80, 14));
        JTextField tfCEP = getJTextField(210, 118, 120, 20);
        tfCEP.setText(cliente.getCep());
        tfCEP.setEditable(false);
        tfCEP.setBackground(Color.WHITE);
        panelClie.add(tfCEP);

        panelClie.add(getJLabel("Fone", 340, 100, 70, 14));
        JTextField tfFone = getJTextField(340, 118, 100, 20);
        tfFone.setText(cliente.getFone());
        tfFone.setEditable(false);
        tfFone.setBackground(Color.WHITE);
        panelClie.add(tfFone);

        panelClie.add(getJLabel("Celular", 450, 100, 80, 14));
        JTextField tfCelular = getJTextField(450, 118, 100, 20);
        tfCelular.setText(cliente.getCelular1());
        tfCelular.setEditable(false);
        tfCelular.setBackground(Color.WHITE);
        panelClie.add(tfCelular);

        panelClie.add(getJLabel("E-Mail", 560, 100, 70, 14));
        JTextField tfEmail = getJTextField(560, 118, 280, 20);
        tfEmail.setText(cliente.geteMail());
        tfEmail.setEditable(false);
        tfEmail.setBackground(Color.WHITE);
        panelClie.add(tfEmail);

        for (int i = 0; i < lista.size(); i++) {
            panelOS.add(getJLabel("Número OS", 20, (i * 535) + 20, 120, 14));
            JTextField tfNumeroOS = getJTextField(20, (i * 535) + 38, 80, 20);
            tfNumeroOS.setText(Integer.toString(lista.get(i).getNumeroOs()));
            tfNumeroOS.setEditable(false);
            tfNumeroOS.setBackground(Color.WHITE);
            panelOS.add(tfNumeroOS);

            panelOS.add(getJLabel("Data Geração", 110, (i * 535) + 20, 120, 14));
            JTextField tfDataGerar = getJTextField(110, (i * 535) + 38, 120, 20);
            tfDataGerar.setText(formatDate.format(lista.get(i).getDataGeracao()) + " as " + formatHora.format(lista.get(i).getDataGeracao()));
            tfDataGerar.setEditable(false);
            tfDataGerar.setBackground(Color.WHITE);
            panelOS.add(tfDataGerar);

            panelOS.add(getJLabel("Situação", 240, (i * 535) + 20, 120, 14));
            JTextField tfSituacao = getJTextField(240, (i * 535) + 38, 100, 20);
            tfSituacao.setText(lista.get(i).getSituacao());
            tfSituacao.setEditable(false);
            tfSituacao.setBackground(Color.WHITE);
            panelOS.add(tfSituacao);

            panelOS.add(getJLabel("Atendente", 350, (i * 535) + 20, 120, 14));
            JTextField tfAtendente = getJTextField(350, (i * 535) + 38, 180, 20);
            tfAtendente.setText(lista.get(i).getNomeAtendente());
            tfAtendente.setEditable(false);
            tfAtendente.setBackground(Color.WHITE);
            panelOS.add(tfAtendente);

            panelOS.add(getJLabel("Operação Realizada Por", 540, (i * 535) + 20, 150, 14));
            JTextField tfNomeTec = getJTextField(540, (i * 535) + 38, 180, 20);
            tfNomeTec.setText(lista.get(i).getNomeAtendente());
            tfNomeTec.setEditable(false);
            tfNomeTec.setBackground(Color.WHITE);
            panelOS.add(tfNomeTec);

            panelOS.add(getJLabel("Data", 730, (i * 535) + 20, 120, 14));
            JTextField tfData = getJTextField(730, (i * 535) + 38, 100, 20);
            tfData.setText(formatDate.format(lista.get(i).getData()));
            tfData.setEditable(false);
            tfData.setBackground(Color.WHITE);
            panelOS.add(tfData);

            panelOS.add(getJLabel("Hora Inicial", 20, (i * 535) + 60, 120, 14));
            JTextField tfHoraInicial = getJTextField(20, (i * 535) + 78, 70, 20);
            tfHoraInicial.setText(lista.get(i).getHoraInicial());
            tfHoraInicial.setEditable(false);
            tfHoraInicial.setBackground(Color.WHITE);
            panelOS.add(tfHoraInicial);

            panelOS.add(getJLabel("Hora Final", 100, (i * 535) + 60, 120, 14));
            JTextField tfHoraFinal = getJTextField(100, (i * 535) + 78, 70, 20);
            tfHoraFinal.setText(lista.get(i).getHoraFinal());
            tfHoraFinal.setEditable(false);
            tfHoraFinal.setBackground(Color.WHITE);
            panelOS.add(tfHoraFinal);

            panelOS.add(getJLabel("Nome / Aparelho", 180, (i * 535) + 60, 120, 14));
            JTextField tfNomeApa = getJTextField(180, (i * 535) + 78, 150, 20);
            tfNomeApa.setText(lista.get(i).getNomeApa());
            tfNomeApa.setEditable(false);
            tfNomeApa.setBackground(Color.WHITE);
            panelOS.add(tfNomeApa);

            panelOS.add(getJLabel("Marca", 340, (i * 535) + 60, 120, 14));
            JTextField tfMarcaApa = getJTextField(340, (i * 535) + 78, 120, 20);
            tfMarcaApa.setText(lista.get(i).getMarcaApa());
            tfMarcaApa.setEditable(false);
            tfMarcaApa.setBackground(Color.WHITE);
            panelOS.add(tfMarcaApa);

            panelOS.add(getJLabel("Modelo", 470, (i * 535) + 60, 120, 14));
            JTextField tfModeloApa = getJTextField(470, (i * 535) + 78, 130, 20);
            tfModeloApa.setText(lista.get(i).getModeloApa());
            tfModeloApa.setEditable(false);
            tfModeloApa.setBackground(Color.WHITE);
            panelOS.add(tfModeloApa);

            panelOS.add(getJLabel("Cor", 610, (i * 535) + 60, 120, 14));
            JTextField tfCorApa = getJTextField(610, (i * 535) + 78, 120, 20);
            tfCorApa.setText(lista.get(i).getCorApa());
            tfCorApa.setEditable(false);
            tfCorApa.setBackground(Color.WHITE);
            panelOS.add(tfCorApa);

            panelOS.add(getJLabel("Número Série", 740, (i * 535) + 60, 120, 14));
            JTextField tfNumeroSerieApa = getJTextField(740, (i * 535) + 78, 90, 20);
            tfNumeroSerieApa.setText(Integer.toString(lista.get(i).getNumeroSerieApa()));
            tfNumeroSerieApa.setEditable(false);
            tfNumeroSerieApa.setBackground(Color.WHITE);
            panelOS.add(tfNumeroSerieApa);

            JSeparator separator1 = new JSeparator();
            separator1.setBounds(0, (i * 535) + 120, 875, 1);
            separator1.setBackground(Color.BLACK);
            panelOS.add(separator1);

            TableModelHistoricoOS tableModel = new TableModelHistoricoOS();
            List<ItensOS> listaItens = controlItensOS.getListaItens(lista.get(i).getNumeroOs());
            for (ItensOS itensOS : listaItens) {
                tableModel.add(itensOS);
            }
            JTable tabela = new JTable(tableModel);
            tabela.getColumnModel().getColumn(0).setMinWidth(77);
            tabela.getColumnModel().getColumn(1).setMinWidth(195);
            tabela.getColumnModel().getColumn(2).setMinWidth(245);
            tabela.getColumnModel().getColumn(3).setMinWidth(95);
            tabela.getColumnModel().getColumn(4).setMinWidth(95);
            tabela.getColumnModel().getColumn(5).setMinWidth(130);
            JScrollPane scrollTabela = new JScrollPane(tabela);
            scrollTabela.setBounds(10, (i * 535) + 140, 840, 300);
            panelOS.add(scrollTabela);

            panelOS.add(getJLabel("Acessorios", 10, (i * 535) + 447, 120, 14));
            JTextArea taAcessorios = getJTextArea(panelOS, 10, (i * 535) + 465, 700, 60);
            taAcessorios.setText(lista.get(i).getAcessoriosApa());
            taAcessorios.setEditable(false);
            taAcessorios.setBackground(Color.WHITE);

            panelOS.add(getJLabel("Assistência", 720, (i * 535) + 447, 120, 14));
            JTextField tfAssistenciaApa = getJTextField(720, (i * 535) + 465, 110, 20);
            tfAssistenciaApa.setText(lista.get(i).getAssistenciaApa());
            tfAssistenciaApa.setEditable(false);
            tfAssistenciaApa.setBackground(Color.WHITE);
            panelOS.add(tfAssistenciaApa);

            panelOS.add(getJLabel("Total", 720, (i * 535) + 487, 120, 14));
            JTextField tfTotal = getJTextField(720, (i * 535) + 505, 110, 20);
            tfTotal.setText(NumberFormat.getCurrencyInstance().format(lista.get(i).getTotal()));
            tfTotal.setEditable(false);
            tfTotal.setBackground(Color.WHITE);
            panelOS.add(tfTotal);

            JSeparator separator2 = new JSeparator();
            separator2.setBounds(0, (i * 535) + 540, 875, 1);
            separator2.setBackground(Color.BLACK);
            panelOS.add(separator2);
        }

        setSize(900, 740);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
