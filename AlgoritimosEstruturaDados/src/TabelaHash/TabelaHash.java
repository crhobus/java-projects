package TabelaHash;

public class TabelaHash {

    private Aluno tabela[];

    public TabelaHash(int n) {
        int aux;
        boolean ehprimo = false;
        while (ehprimo == false) {
            aux = n - 1;
            while (aux > 1) {
                if ((n % aux) == 0) {
                    ehprimo = false;
                    aux = 1;
                } else {
                    ehprimo = true;
                }
                aux--;
            }
            if (ehprimo == true) {
                break;
            } else {
                n++;
            }
        }
        tabela = new Aluno[n];
    }

    private int hash(int k) {
        return k % tabela.length;
    }

    public Aluno get(int k) {
        int h = hash(k);
        Aluno p = tabela[h];
        while (p != null) {
            if (p.getMatricula() == k) {
                return p;
            }
            p = p.getProx();
        }
        return null;
    }

    public void set(String nome, int matricula, float mediaGeral) {
        int h = hash(matricula);
        Aluno p = tabela[h];
        while (p != null) {
            if (p.getMatricula() == matricula) {
                break;
            }
            p = p.getProx();
        }
        if (p == null) {
            p = new Aluno();
            p.setMatricula(matricula);
            p.setProx(tabela[h]);
            tabela[h] = p;
        }
        p.setNome(nome);
        p.setMediaGeral(mediaGeral);
    }

    public void remove(int k) {
        int h = hash(k);
        Aluno p = tabela[h];
        Aluno ant = null;
        while (p != null) {
            if (p.getMatricula() == k) {
                if (ant != null) {
                    ant.setProx(p.getProx());
                } else {
                    tabela[h] = p.getProx();
                }
                break;
            }
            ant = p;
            p = p.getProx();
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tabela.length; i++) {
            Aluno p = tabela[i];
            while (p != null) {
                s += "Aluno: " + "Nº Matrícula: " + p.getMatricula() + ", Nome: " + p.getNome() + ", Média Geral: " + p.getMediaGeral() + "\n";
                p = p.getProx();
            }
        }
        return s;
    }

    public int size() {
        int tamanho = 0;
        for (int i = 0; i < tabela.length; i++) {
            Aluno p = tabela[i];
            while (p != null) {
                tamanho++;
                p = p.getProx();
            }
        }
        return tamanho;
    }

    public Aluno[] ordena() {
        Aluno vetorAux[] = new Aluno[size()];
        int j = 0;
        for (int i = 0; i < tabela.length; i++) {
            Aluno p = tabela[i];
            while (p != null) {
                vetorAux[j] = p;
                p = p.getProx();
                j++;
            }
        }
        quickSort(vetorAux, 0, vetorAux.length - 1);
        return vetorAux;
    }

    private void troca(Aluno v[], int a, int b) {
        Aluno temp = v[a];
        v[a] = v[b];
        v[b] = temp;
    }

    private void quickSort(Aluno v[], int a, int b) {
        if (a >= b) {
            return;
        }
        int indicePivo = particiona(v, a, b);
        quickSort(v, a, indicePivo);
        quickSort(v, indicePivo + 1, b);
    }

    private int particiona(Aluno v[], int a, int b) {
        Aluno x = v[a];
        while (a < b) {
            while (v[a].getMediaGeral() < x.getMediaGeral()) {
                a++;
            }
            while (v[b].getMediaGeral() > x.getMediaGeral()) {
                b--;
            }
            troca(v, a, b);
        }
        return a;
    }
}
