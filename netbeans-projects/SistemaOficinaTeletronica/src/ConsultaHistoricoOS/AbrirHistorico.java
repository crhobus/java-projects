package ConsultaHistoricoOS;

import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Clientes.ClienteControl;
import Clientes.ListenerCliente;
import Clientes.PesquisaClientes;
import Modelo.Cliente;
import OrdensServicos.NovaOSControl;

public class AbrirHistorico {

    private Connection con;

    public AbrirHistorico(Connection con) throws Exception {
        this.con = con;
        ClienteControl controlClie = new ClienteControl(con);
        final NovaOSControl controlOS = new NovaOSControl(con);
        if (controlClie.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        if (controlOS.isOSVazio()) {
            throw new Exception("Não há ordens de serviços cadastradas");
        }
        JTextField entrada = new JTextField();
        final Object[] mensagem = {"Informe o CPF / CNPJ do cliente", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Cliente", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                abrir(controlClie.selectCliente(entrada.getText()), controlOS);
                break;
            case 1: // Consultar
                PesquisaClientes pesquisaClie = new PesquisaClientes(controlClie);
                pesquisaClie.setListener(new ListenerCliente() {

                    @Override
                    public void exibeCliente(Cliente cliente) {
                        try {
                            abrir(cliente, controlOS);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                });
                pesquisaClie.setModal(true);
                pesquisaClie.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void abrir(Cliente cliente, NovaOSControl controlOS) throws Exception {
        if (cliente != null) {
            HistoricoOS historico = new HistoricoOS(con, cliente, controlOS.listaOSClie(cliente.getCpfCNPJ()));
            historico.setModal(true);
            historico.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
