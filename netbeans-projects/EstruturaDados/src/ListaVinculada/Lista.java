package ListaVinculada;

public class Lista {

    private NoLista primeiroNo;
    private NoLista ultimoNo;
    private String nome;

    public Lista() {
        this("Lista");
    }

    public Lista(String nome) {
        this.nome = nome;
        this.primeiroNo = this.ultimoNo = null;
    }

    public void inserirComeco(Object inserirItem) {
        if (vazio()) {
            this.primeiroNo = this.ultimoNo = new NoLista(inserirItem);
        } else {
            this.primeiroNo = new NoLista(inserirItem, this.primeiroNo);
        }
    }

    public void inserirFinal(Object inserirItem) {
        if (vazio()) {
            this.primeiroNo = this.ultimoNo = new NoLista(inserirItem);
        } else {
            this.ultimoNo = this.ultimoNo.proxNo = new NoLista(inserirItem);
        }
    }

    public Object removerComeco() throws EmptyListException {
        if (vazio()) {
            throw new EmptyListException(nome);
        }
        Object removerItem = primeiroNo.dados;
        if (primeiroNo == ultimoNo) {
            primeiroNo = ultimoNo = null;
        } else {
            primeiroNo = primeiroNo.proxNo;
        }
        return removerItem;
    }

    public Object removerUltimo() throws EmptyListException {
        if (vazio()) {
            throw new EmptyListException(nome);
        }
        Object removerItem = ultimoNo.dados;
        if (primeiroNo == ultimoNo) {
            primeiroNo = ultimoNo = null;
        } else {
            NoLista atual = primeiroNo;
            while (atual.proxNo != ultimoNo) {
                atual = atual.proxNo;
            }
            ultimoNo = atual;
            atual.proxNo = null;
        }
        return removerItem;
    }

    public boolean vazio() {
        return primeiroNo == null;
    }

    public void imprimir() {
        if (vazio()) {
            System.out.printf("Vazio %s\n", nome);
            return;
        }
        System.out.printf("A %s é: ", nome);
        NoLista atual = primeiroNo;
        while (atual != null) {
            System.out.printf("%s ", atual.dados);
            atual = atual.proxNo;
        }
        System.out.println("\n");
    }
}
