package projeto;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import netbula.ORPC.rpc_err;
import projeto.rpc.SistemaBancarioRPC_cln;

//O número da conta deste banco tem final 32 e do banco bradesco tem final 36
public class Agente {

    private SistemaBancarioRPC_cln servRPC;

    public Agente(String ipServidor) {
        try {
            servRPC = new SistemaBancarioRPC_cln(ipServidor, "tcp");
        } catch (rpc_err ex) {
            System.out.println("Servidor RMI indisponível, tente mais tarde");
            System.exit(0);
        }
        System.out.println("Servidor conectado");
        cadastrarContabanco();
    }

    private void cadastrarContabanco() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MC Internet Banking");
        String opcao, str;
        do {
            System.out.println("Cadastro de clientes");
            System.out.print("Entre com o nome: ");
            str = validaString(scanner) + ";";
            System.out.print("Entre com o cpf, somente dígitos: ");
            str += getStrNumero(scanner, 11, false) + ";";
            System.out.print("Entre com o rg, somente dígitos: ");
            str += getStrNumero(scanner, 9, false) + ";";
            if ("" != null) {
                scanner.nextLine();
            }
            System.out.print("Entre com o endereço: ");
            str += validaString(scanner) + ";";
            System.out.print("Entre com o bairro: ");
            str += validaString(scanner) + ";";
            System.out.print("Entre com o cep, somente dígitos: ");
            str += getStrNumero(scanner, 8, false) + ";";
            System.out.print("Entre com a cidade: ");
            str += validaString(scanner) + ";";
            System.out.print("Entre com o estado: ");
            str += validaString(scanner) + ";";
            System.out.print("Entre com uma senha: ");
            str += validaString(scanner) + ";";
            str += getSexo(scanner) + ";";
            str += getNumero(scanner) + ";";
            System.out.print("Entre com o número da conta, 10 dígitos: ");
            str += getStrNumero(scanner, 10, true) + ";";
            str += getData(scanner) + ";";
            str += getSaldo(scanner);
            try {
                System.out.println(servRPC.cadastrarCliente(str));
            } catch (rpc_err ex) {
                System.out.println(ex.getMessage());
            }

            System.out.print("Deseja cadastrar mais um cliente S/N: ");
            opcao = scanner.next();
        } while ("S".equalsIgnoreCase(opcao));
    }

    private String validaString(Scanner scanner) {
        String str;
        while (true) {
            str = scanner.nextLine();
            if (str != null && !"".equals(str)) {
                break;
            }
        }
        return str;
    }

    private String getStrNumero(Scanner scanner, int n, boolean isNrConta) {
        String str;
        while (true) {
            str = scanner.next();
            try {
                validaNumeros(str, n);
                if (isNrConta) {
                    if (str.endsWith("32")) {
                        break;
                    } else {
                        System.out.println("O número da conta deve ter final 32");
                    }
                } else {
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return str;
    }

    private void validaNumeros(String info, int n) throws Exception {
        if (info.length() != n) {
            throw new Exception("Entre com a quantidade correta de dígitos");
        }
        boolean ehDigito = false;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(info.charAt(i))) {
                ehDigito = true;
            } else {
                ehDigito = false;
            }
        }
        if (!ehDigito) {
            throw new Exception("Entre somente com os dígitos");
        }
    }

    private String getSexo(Scanner scanner) {
        String s;
        while (true) {
            System.out.print("Entre com o sexo, M/F: ");
            s = scanner.next();
            if ("M".equalsIgnoreCase(s) || "F".equalsIgnoreCase(s)) {
                break;
            }
            System.out.println("Entre com uma opção válida");
        }
        return s;
    }

    private String getNumero(Scanner scanner) {
        String s;
        while (true) {
            System.out.print("Entre com o número: ");
            s = scanner.next();
            try {
                Integer.parseInt(s);
                break;
            } catch (Exception ex) {
                System.out.println("Entre com um número válido");
            }
        }
        return s;
    }

    private String getSaldo(Scanner scanner) {
        String s;
        while (true) {
            System.out.print("Entre com o saldo inicial: R$ ");
            s = scanner.next();
            try {
                Double.parseDouble(s);
                break;
            } catch (Exception ex) {
                System.out.println("Entre com um valor válido");
            }
        }
        return s;
    }

    private String getData(Scanner scanner) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String s;
        while (true) {
            System.out.print("Entre com a dada de nascimento: ");
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
}
