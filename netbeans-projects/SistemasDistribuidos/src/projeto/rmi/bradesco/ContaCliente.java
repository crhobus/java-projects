package projeto.rmi.bradesco;

import java.util.HashMap;

public class ContaCliente {

    private HashMap<Long, Conta> contaCliente;

    public ContaCliente() {
        contaCliente = new HashMap();
    }

    public void addConta(long nrConta, Conta conta) {
        contaCliente.put(nrConta, conta);
    }

    public Conta removeConta(long nrConta) throws Exception {
        if (contaCliente.containsKey(nrConta)) {
            return contaCliente.remove(nrConta);
        }
        throw new Exception("Esta conta não existe");
    }

    public Conta getConta(long nrConta) throws Exception {
        if (contaCliente.containsKey(nrConta)) {
            return contaCliente.get(nrConta);
        }
        throw new Exception("Esta conta não existe");
    }
}
