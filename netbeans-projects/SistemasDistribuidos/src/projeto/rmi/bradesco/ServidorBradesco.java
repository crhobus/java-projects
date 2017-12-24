package projeto.rmi.bradesco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;

//O número da conta do bradesco tem final 36
public class ServidorBradesco extends UnicastRemoteObject implements SistemaBancarioBradesco {

    private ContaCliente contaCliente;

    public ServidorBradesco() throws RemoteException {
        super();
        try {
            contaCliente = new ContaCliente();
            cadastrarContas();
        } catch (Exception ex) {
            System.out.println("Erro inespereado, o servidor será desligado");
            System.exit(0);
        }
    }

    @Override
    public boolean deposito(long nrConta, double valor) throws RemoteException {
        try {
            Conta conta = contaCliente.getConta(nrConta);
            double saldo = conta.getSaldo() + valor;
            conta.setSaldo(saldo);
            contaCliente.addConta(nrConta, conta);
            return true;
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage());
        }
    }

    private void cadastrarContas() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Conta conta = new Conta();
        conta.setNome("Caio");
        conta.setCpf("765.698.987-00");
        conta.setRg("976.765.542");
        conta.setEndereco("Av. Brasil");
        conta.setBairro("Centro");
        conta.setCep("87654-999");
        conta.setCidade("São Paulo");
        conta.setEstado("São paulo");
        conta.setSenha("key7657890");
        conta.setSexo("Masculino");
        conta.setNumero(9876);
        conta.setNrConta(765434536);
        conta.setDataNascimento(format.parse("01/07/1960"));
        conta.setSaldo(30000.00);
        contaCliente.addConta(conta.getNrConta(), conta);
    }
}
