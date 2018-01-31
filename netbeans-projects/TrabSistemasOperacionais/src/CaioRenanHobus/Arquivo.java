package CaioRenanHobus;

import java.io.RandomAccessFile;
import java.util.Arrays;

public class Arquivo {

    private RandomAccessFile arqAlunos, arqDisciplinas;

    public Arquivo(String aluno, String disciplinas) throws Exception {
        arqAlunos = new RandomAccessFile(aluno + ".so", "rw");
        arqDisciplinas = new RandomAccessFile(disciplinas + ".so", "rw");
    }

    public void gravarAlunos(Alunos alunos) throws Exception {
        arqAlunos.seek(arqAlunos.length());
        arqAlunos.writeInt(alunos.getNumero());
        String nome = new String(alunos.getNome(), 0, 30);
        arqAlunos.writeBytes(nome);
        String discCursadas = new String(alunos.getDiscCursadas(), 0, 10);
        arqAlunos.writeBytes(discCursadas);
    }

    public void gravarDisciplinas(Disciplinas disciplinas) throws Exception {
        arqDisciplinas.seek(arqDisciplinas.length());
        String nome = new String(disciplinas.getDisciplinas(), 0, 30);
        arqDisciplinas.writeBytes(nome);
    }

    public Disciplinas lerDisciplinas(int posicao) throws Exception {
        Disciplinas disciplinas = new Disciplinas();
        try {
            arqDisciplinas.seek((posicao - 1) * 30);
            byte[] nome = new byte[30];
            arqDisciplinas.read(nome);
            int tam = 30;
            for (int i = 0; i < nome.length; i++) {
                if (nome[i] == 0) {
                    tam = i;
                    break;
                }
            }
            String nomes = new String(Arrays.copyOf(nome, tam));
            char vetorDisc[] = new char[30];
            int qtdade = nomes.length();
            int i = 0;
            while (i < qtdade) {
                vetorDisc[i] = nomes.charAt(i);
                i++;
            }
            disciplinas.setDisciplinas(vetorDisc);
        } catch (Exception ex) {
            throw new Exception("Disciplina não encontrada");
        }
        return disciplinas;
    }

    public Alunos lerAlunos(int posicao) throws Exception {
        Alunos alunos = new Alunos();
        try {
            arqAlunos.seek((posicao - 1) * 44);
            alunos.setNumero(arqAlunos.readInt());
            byte[] nome = new byte[30];
            arqAlunos.read(nome);
            int tam = 30;
            for (int i = 0; i < nome.length; i++) {
                if (nome[i] == 0) {
                    tam = i;
                    break;
                }
            }
            String nomes = new String(Arrays.copyOf(nome, tam));
            char vetorNome[] = new char[30];
            int qtdade = nomes.length();
            int i = 0;
            while (i < qtdade) {
                vetorNome[i] = nomes.charAt(i);
                i++;
            }
            alunos.setNome(vetorNome);
            byte disciplina[] = new byte[10];
            for (int j = 0; j < 10; j++) {
                disciplina[j] = arqAlunos.readByte();
            }
            alunos.setDiscCursadas(disciplina);
        } catch (Exception ex) {
            throw new Exception("Aluno não encontrado");
        }
        return alunos;
    }

    public void fecharArquivos() throws Exception {
        arqAlunos.close();
        arqDisciplinas.close();
    }
}
