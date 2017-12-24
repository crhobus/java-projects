package projeto;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import netbula.ORPC.rpc_err;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StringHolder;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import projeto.corba.sistemaBancarioCorba.InterfaceCorba;
import projeto.corba.sistemaBancarioCorba.InterfaceCorbaHelper;
import projeto.rmi.rmi.RMIInterface;
import projeto.rpc.SistemaBancarioRPC_cln;
import projeto.rpc.params;

//O número da conta deste banco tem final 32 e do banco bradesco tem final 36
public class Cliente {

    public Cliente(String ipServidorRMI, String ipServidorRPC, String[] args) {
        RMIInterface rmiInterface = null;
        SistemaBancarioRPC_cln servRPC = null;
        InterfaceCorba servCorba = null;
        StringHolder retorno = new StringHolder();
        try {
            rmiInterface = (RMIInterface) Naming.lookup("//" + ipServidorRMI + "/RMIInterface");
            servRPC = new SistemaBancarioRPC_cln(ipServidorRPC, "tcp");
            // Cria e inicializa o ORB
            ORB orb = ORB.init(args, null);

            // Obtem referencia para o servico de nomes
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Obtem referencia para o servidor
            String name = "SistemaBancario";
            servCorba = InterfaceCorbaHelper.narrow(ncRef.resolve_str(name));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        Scanner scanner = new Scanner(System.in);
        params p = new params();
        long nrConta, nrContaDestino, nrTitulo;
        String senha;
        double valor;
        int opcao = 0;
        boolean sair = false;
        do {
            System.out.println("MC Internet Banking");
            System.out.println("\nEntre com uma das opções");
            System.out.println("1 - Saque");
            System.out.println("2 - Deposito");
            System.out.println("3 - Transferência");
            System.out.println("4 - Extrato");
            System.out.println("5 - Visualizar limite empréstimo");
            System.out.println("6 - Pagamento de titulo");
            System.out.println("7 - Emprestimo pré aprovado");
            System.out.println("8 - Sair");
            opcao = validaOpcao(scanner);
            switch (opcao) {
                case 1:
                    nrConta = validaNrConta(scanner, "Entre com o número da conta: ");
                    senha = getSenha(scanner);
                    valor = validaValor(scanner, "Entre com o valor a ser sacado: R$ ");
                    try {
                        rmiInterface.saque(nrConta, senha, valor);
                        System.out.println("Saque realizado com sucesso\n");
                    } catch (RemoteException ex) {
                        System.out.println("\n" + ex.getMessage());
                        System.out.println("Tente novamente\n");
                    }
                    break;
                case 2:
                    nrConta = validaNrConta(scanner, "Entre com o número da conta: ");
                    valor = validaValor(scanner, "Entre com o valor a ser depositado: R$ ");
                    try {
                        rmiInterface.deposito(nrConta, valor);
                        System.out.println("Depósito realizado com sucesso\n");
                    } catch (RemoteException ex) {
                        System.out.println("\n" + ex.getMessage());
                        System.out.println("Tente novamente\n");
                    }
                    break;
                case 3:
                    nrConta = validaNrConta(scanner, "Entre com o número da conta: ");
                    senha = getSenha(scanner);
                    valor = validaValor(scanner, "Entre com o valor a ser transferido: R$ ");
                    nrContaDestino = validaNrConta(scanner, "Entre com o número da conta a ser transferida: ");
                    try {
                        rmiInterface.transferencia(nrConta, senha, valor, nrContaDestino);
                        System.out.println("Transferência realizada com sucesso\n");
                    } catch (RemoteException ex) {
                        System.out.println("\n" + ex.getMessage());
                        System.out.println("Tente novamente\n");
                    }
                    break;
                case 4:
                    p.param1 = Long.toString(validaNrConta(scanner, "Entre com o número da conta: "));
                    p.param2 = getSenha(scanner);
                    p.param3 = getData(scanner, "Entre com a data inicial: ");
                    p.param4 = getData(scanner, "Entre com a data final: ");
                    try {
                        String s = servRPC.extrato(p);
                        if (!s.startsWith("Extrato")) {
                            System.out.println("\n" + s);
                            System.out.println("Tente novamente\n");
                        } else {
                            System.out.println("\n" + s + "\n");
                        }
                    } catch (rpc_err ex) {
                        System.out.println("\n" + ex.getMessage());
                        System.out.println("Tente novamente\n");
                    }
                    break;
                case 5:
                    nrConta = validaNrConta(scanner, "Entre com o número da conta: ");
                    senha = getSenha(scanner);
                    servCorba.visualizarLimite((int) nrConta, senha, retorno);
                    System.out.println("\n" + retorno.value + "\n");
                    System.out.println("Tente novamente\n");
                    break;
                case 6:
                    nrConta = validaNrConta(scanner, "Entre com o número da conta: ");
                    senha = getSenha(scanner);
                    nrTitulo = validaNrConta(scanner, "Entre com o número do título: ");
                    valor = validaValor(scanner, "Entre com o valor do título: R$ ");
                    servCorba.pagamento((int) nrConta, senha, (int) nrTitulo, valor, retorno);
                    System.out.println("\n" + retorno.value + "\n");
                    System.out.println("Tente novamente\n");
                    break;
                case 7:
                    nrConta = validaNrConta(scanner, "Entre com o número da conta: ");
                    senha = getSenha(scanner);
                    valor = validaValor(scanner, "Entre com o valor do emprestimo: R$ ");
                    servCorba.emprestimo((int) nrConta, senha, valor, retorno);
                    System.out.println("\n" + retorno.value + "\n");
                    System.out.println("Tente novamente\n");
                    break;
                case 8:
                    sair = true;
                    break;
            }
        } while (!sair);
    }

    private long validaNrConta(Scanner scanner, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Long.parseLong(scanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("\nEntre somente com os dígitos da conta");
            }
        }
    }

    private String getSenha(Scanner scanner) {
        System.out.print("Entre com a senha: ");
        return scanner.next();
    }

    private double validaValor(Scanner scanner, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("\nEntre um valor válido. ex: 0.0");
            }
        }
    }

    private String getData(Scanner scanner, String msg) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String s;
        while (true) {
            System.out.print(msg);
            s = scanner.next();
            try {
                format.parse(s);
                break;
            } catch (Exception ex) {
                System.out.println("Entre com uma data válida");
            }
        }
        return s;
    }

    private int validaOpcao(Scanner scanner) {
        int opcao;
        while (true) {
            try {
                System.out.print("Opção: ");
                opcao = Integer.parseInt(scanner.next());
                if (opcao >= 1 && opcao <= 8) {
                    return opcao;
                } else {
                    System.out.println("\nEntre com uma opção válida");
                }
            } catch (Exception ex) {
                System.out.println("\nEntre com um número de 1 a 5");
            }
        }
    }
}
