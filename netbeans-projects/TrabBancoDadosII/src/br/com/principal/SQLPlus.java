package br.com.principal;

import br.com.analizador.LexicalError;
import br.com.analizador.Lexico;
import br.com.analizador.SemanticError;
import br.com.analizador.Semantico;
import br.com.analizador.Sintatico;
import br.com.analizador.SyntaticError;
import br.com.baseDados.BancoDados;
import java.util.Scanner;

public class SQLPlus {

    private BancoDados bancoDados;

    public SQLPlus() {
        comandosSQL();
    }

    private void comandosSQL() {
        bancoDados = new BancoDados();
        Scanner scanner = new Scanner(System.in);
        int linha = 1;
        String comando = "";
        while (true) {
            if (linha == 1) {
                System.out.print("sql>");
            } else {
                System.out.print("#" + linha + "- ");
            }
            linha++;
            comando += scanner.nextLine() + "\n";
            if ("exit\n".equalsIgnoreCase(comando) || "exit;\n".equalsIgnoreCase(comando)) {
                System.out.println("fechando conexão com o banco");
                break;
            } else if (comando.endsWith(";\n")) {
                executar(comando);
                comando = "";
                linha = 1;
            }
        }
    }

    private void executar(String comandos) {
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico(bancoDados);
        lexico.setInput(comandos);
        try {
            sintatico.parse(lexico, semantico);
            System.out.println("Comando(s) executado(s) com sucesso");
        } catch (LexicalError ex) {
            System.out.println("Comando(s) executado(s) com erros");
            System.out.println("Erro na linha " + capturaLinha(comandos, ex.getPosition()) + ex.getMessage());
        } catch (SyntaticError ex) {
            System.out.println("Comando(s) executado(s) com erros");
            System.out.println("Erro na linha " + capturaLinha(comandos, ex.getPosition()) + " - encontrado " + ex.getMessage());
        } catch (SemanticError ex) {
            System.out.println("Comando(s) executado(s) com erros");
            System.out.println("Erro na linha " + capturaLinha(comandos, ex.getPosition()) + " - encontrado " + ex.getMessage());
        }
    }

    private int capturaLinha(String comandos, int posicao) {
        int cont = 1;
        for (int i = 0; i < posicao; i++) {
            if (comandos.charAt(i) == Character.DIRECTIONALITY_PARAGRAPH_SEPARATOR) {
                cont++;
            }
        }
        return cont;
    }
}
