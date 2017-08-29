package TabelaHash;

public class TabelaHashMain {

    public static void main(String[] args) {
        TabelaHash tabelaHash = new TabelaHash(13);
        tabelaHash.set("Caio", 2345, 9);
        tabelaHash.set("Renan", 74, 8);
        tabelaHash.set("João", 25, 6);
        tabelaHash.set("Sousa", 4545, 5);
        tabelaHash.set("Carla", 25, 6);
        tabelaHash.set("Morais", 253, 2);
        System.out.println("Conteúdo da Tabela Hash");
        System.out.println(tabelaHash.toString());
        System.out.println();
        int n = 25;
        tabelaHash.remove(25);
        System.out.println("Conteúdo da Tabela Hash após ter removido o aluno com a matrícula " + n);
        System.out.println(tabelaHash.toString());
        int y = 253;
        if (tabelaHash.get(y) == null) {
            System.out.println("Aluno não encontrado");
        } else {
            System.out.println("Aluno Recuperado: ");
            System.out.println("Matrícula: " + tabelaHash.get(y).getMatricula());
            System.out.println("Nome: " + tabelaHash.get(y).getNome());
            System.out.println("Média Geral: " + tabelaHash.get(y).getMediaGeral());
        }
        System.out.println("Tamanho da Tabela Hash: " + tabelaHash.size());
        System.out.println("Tabela ordenada pela matrícula");
        Aluno aluno[] = tabelaHash.ordena();
        for (int i = 0; i < aluno.length; i++) {
            System.out.println("Matrícula: " + aluno[i].getMatricula() + ", Nome: " + aluno[i].getNome() + ", Média Geral: " + aluno[i].getMediaGeral());
        }
        tabelaHash.set("sgdh", 987, 4);
        System.out.println("Conteúdo da Tabela Hash");
        System.out.println(tabelaHash.toString());
        System.out.println();
        Aluno aluno2[] = tabelaHash.ordena();
        for (int i = 0; i < aluno2.length; i++) {
            System.out.println("Matríula: " + aluno2[i].getMatricula() + ", Nome: " + aluno2[i].getNome() + ", Média Geral: " + aluno2[i].getMediaGeral());
        }
    }
}
