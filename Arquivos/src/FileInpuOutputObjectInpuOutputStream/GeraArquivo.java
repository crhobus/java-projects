package FileInpuOutputObjectInpuOutputStream;

import java.io.*;

public class GeraArquivo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Aluno aln1 = new Aluno(111, "João", 132, 'm');
        Aluno aln2 = new Aluno(222, "Maria", 123, 'f');
        FileOutputStream arqDados = new FileOutputStream("aln.dat");
        ObjectOutputStream arqDat = new ObjectOutputStream(arqDados);
        arqDat.writeObject(aln1);
        arqDat.writeObject(aln2);
        System.out.println("Tudo Feito");
        arqDat.close();
        arqDados.close();
        mostraConteudo();
    }

    public static void mostraConteudo() throws IOException, ClassNotFoundException {
        FileInputStream entra = new FileInputStream("aln.dat");
        ObjectInputStream entraobj = new ObjectInputStream(entra);
        Aluno a1 = (Aluno) entraobj.readObject();
        Aluno a2 = (Aluno) entraobj.readObject();
        System.out.println("Leu Registros");
        System.out.println("Matricula do aluno1 " + a1.matric);
        System.out.println("Matricula do aluno2 " + a2.matric);
        entra.close();
        entraobj.close();
    }
}
