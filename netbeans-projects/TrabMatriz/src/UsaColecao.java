
import javax.swing.JOptionPane;

public class UsaColecao {

    public static void main(String[] args) {
        boolean deuErro = false;
        int limite = 0;
        do {
            try {
                limite = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero  maximo a ser gerado randomicamente"));
                deuErro = false;
                if (limite < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe este numero", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                deuErro = true;
                JOptionPane.showMessageDialog(null, "Numero Inválido", "Digite Numero do tipo Inteiro", JOptionPane.ERROR_MESSAGE);
            }
        } while (deuErro);
        int quantos = 0;
        do {
            try {
                quantos = Integer.parseInt(JOptionPane.showInputDialog("Digite a Quantidade de Elementos"));
                deuErro = false;
                if (quantos < 1) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Quantidade Invalida", "Erro na Quantidade", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                deuErro = true;
                JOptionPane.showMessageDialog(null, "Quantidade Invalida", "Erro na Quantidade", JOptionPane.ERROR_MESSAGE);
            }
        } while (deuErro);
        Colecao meu = new Colecao(quantos);
        int carrega = 0;
        do {
            try {
                carrega = Integer.parseInt(JOptionPane.showInputDialog("Digite o Tamanho da matriz"));
                deuErro = false;
                if (carrega < 1) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Erro na Quantidade", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                deuErro = true;
                JOptionPane.showMessageDialog(null, "Quantidade Inválida", "Erro na Quantidade", JOptionPane.ERROR_MESSAGE);
            }
        } while (deuErro);
        int coluna = 0;
        do {
            try {
                coluna = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da coluna a ser somada"));
                deuErro = false;
                if (coluna < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não exisre essa coluna", JOptionPane.ERROR_MESSAGE);
                }
                if (coluna >= carrega) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não exisre essa coluna", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                deuErro = true;
                JOptionPane.showMessageDialog(null, "Numero Inválido", "Não existe esta coluna", JOptionPane.ERROR_MESSAGE);
            }
        } while (deuErro);
        int linha = 0;
        do {
            try {
                linha = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da linha a ser somada"));
                deuErro = false;
                if (linha < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa linha", JOptionPane.ERROR_MESSAGE);
                }
                if (linha >= carrega) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa linha", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                deuErro = true;
                JOptionPane.showMessageDialog(null, "Numero Inválido", "Não existe esta linha", JOptionPane.ERROR_MESSAGE);
            }
        } while (deuErro);
        int linhaset = 0;
        int colunaset = 0;
        int quemset = 0;
        do {
            try {
                linhaset = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da linha a ser substituida"));
                deuErro = false;
                if (linhaset < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa linha", JOptionPane.ERROR_MESSAGE);
                }
                deuErro = false;
                if (linhaset >= carrega) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa linha", JOptionPane.ERROR_MESSAGE);
                }
                colunaset = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da coluna a ser substituida"));
                deuErro = false;
                if (colunaset < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa coluna", JOptionPane.ERROR_MESSAGE);
                }
                deuErro = false;
                if (colunaset >= carrega) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa coluna", JOptionPane.ERROR_MESSAGE);
                }
                quemset = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo numero a ser subscrito"));
                deuErro = false;
                if (quemset < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa posição", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                deuErro = true;
                JOptionPane.showMessageDialog(null, "Numero Inválido", "Digite apenas numero do tipo INTEIRO", JOptionPane.ERROR_MESSAGE);
            }
        } while (deuErro);
        int linhaget = 0;
        int colunaget = 0;
        do {
            try {
                linhaset = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da linha a ser retornada"));
                deuErro = false;
                if (linhaset < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa linha", JOptionPane.ERROR_MESSAGE);
                }
                deuErro = false;
                if (linhaset >= carrega) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa linha", JOptionPane.ERROR_MESSAGE);
                }
                colunaset = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da coluna a ser retornada"));
                deuErro = false;
                if (colunaset < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa coluna", JOptionPane.ERROR_MESSAGE);
                }
                deuErro = false;
                if (colunaset >= carrega) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa coluna", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                deuErro = true;
                JOptionPane.showMessageDialog(null, "Numero Inválido", "Digite apenas numero do tipo INTEIRO", JOptionPane.ERROR_MESSAGE);
            }
        } while (deuErro);
        int ordenar_linha = 0;
        do {
            try {
                ordenar_linha = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da linha a ser ordenada"));
                deuErro = false;
                if (ordenar_linha < 0) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa linha", JOptionPane.ERROR_MESSAGE);
                }
                if (ordenar_linha >= carrega) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Numero Invalido", "Não existe essa linha", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                deuErro = true;
                JOptionPane.showMessageDialog(null, "Numero Inválido", "Não existe esta linha", JOptionPane.ERROR_MESSAGE);
            }
        } while (deuErro);
        boolean matriz_normal = true;
        boolean matriz_transposta = false;
        System.out.println("Objeto MEU:");
        System.out.println("A Matriz foi GERADA: ");
        meu.carregar(carrega, limite);
        meu.mostrar(matriz_normal);
        meu.mostrar(matriz_transposta);
        System.out.println("Soma da MATRIZ:  " + meu.somatorio());
        System.out.println("A soma desta coluna da MATRIZ:  " + meu.somacoluna(coluna));
        System.out.println("A soma desta linha da MATRIZ:  " + meu.somalinha(linha));
        System.out.println("A soma da diagonal desta MATRIZ:  " + meu.somadiagonal());
        System.out.println("Um novo elemento foi colocado nesta posição: " + meu.set(linhaset, colunaset, quemset));
        meu.mostrar(matriz_normal);
        meu.mostrar(matriz_transposta);
        System.out.println("O elemento do matriz retornado é: " + meu.get(linhaget, colunaget));
        meu.ordenar(ordenar_linha);
        System.out.println("Raiz Quadrada do somatório da matriz: " + meu.RaizSomatorio());
        System.out.println("Quantidade de Numeros Primos da matriz: " + meu.qtdPrimos());
        System.exit(0);
    }
}
