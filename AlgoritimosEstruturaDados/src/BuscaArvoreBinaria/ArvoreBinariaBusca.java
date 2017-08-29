package BuscaArvoreBinaria;

public class ArvoreBinariaBusca {

    private NoArvoreBinaria raiz;

    public ArvoreBinariaBusca() {
        this.raiz = insere(100);
    }

    private NoArvoreBinaria buscaAux(NoArvoreBinaria r, int valor) {
        if (r == null) {
            return null;
        } else {
            if (valor < r.getInfo()) {
                return buscaAux(r.getEsq(), valor);
            } else {
                if (valor > r.getInfo()) {
                    return buscaAux(r.getEsq(), valor);
                } else {
                    return r;
                }
            }
        }
    }

    public NoArvoreBinaria busca(int valor) {
        return buscaAux(this.raiz, valor);
    }

    private NoArvoreBinaria insereAux(NoArvoreBinaria no, int valor) {
        if (no == null) {
            no = new NoArvoreBinaria(valor);
            no.setInfo(valor);
            no.setEsq(null);
            no.setDir(null);
        } else {
            if (valor < no.getInfo()) {
                no.setEsq(insereAux(no.getEsq(), valor));
            } else {
                no.setDir(insereAux(no.getDir(), valor));
            }
        }
        return no;
    }

    public NoArvoreBinaria insere(int valor) {
        return insereAux(this.raiz, valor);
    }

    private NoArvoreBinaria retiraAux(NoArvoreBinaria no, int valor) {
        if (no == null) {
            return null;
        } else {
            if (valor < no.getInfo()) {
                no.setEsq(retiraAux(no.getEsq(), valor));
            } else {
                if (valor > no.getInfo()) {
                    no.setDir(retiraAux(no.getDir(), valor));
                } else {
                    if (no.getEsq() == null && no.getDir() == null) {
                        no = null;
                    } else {
                        if (no.getEsq() == null) {
                            no = no.getDir();
                        } else {
                            if (no.getDir() == null) {
                                no = no.getEsq();
                            } else {
                                NoArvoreBinaria p = no.getEsq();
                                while (p.getDir() != null) {
                                    p = p.getDir();
                                }
                                no.setInfo(p.getInfo());
                                p.setInfo(valor);
                                no.setEsq(retiraAux(no.getEsq(), valor));
                            }
                        }
                    }
                }
            }
        }
        return no;
    }

    public void retira(int valor) {
        retiraAux(this.raiz, valor);
    }

    @Override
    public String toString() {
        return imprimeSim(this.raiz);
    }

    private String imprimeSim(NoArvoreBinaria no) {
        String s = " ";
        if (no != null) {
            s += imprimeSim(no.getEsq());
            s += no.toString();
            s += imprimeSim(no.getDir());
        }
        return s;
    }

    public void toStringDecrescente() {
        toStringDecrescenteAux(this.raiz);
    }

    private void toStringDecrescenteAux(NoArvoreBinaria no) {
        if (no != null) {
            toStringDecrescenteAux(no.getDir());
            System.out.print(no.toString() + " ");
            toStringDecrescenteAux(no.getEsq());
        }
    }

    public int maioresX(int v) {
        return maioresXAux(this.raiz, v);
    }

    private int maioresXAux(NoArvoreBinaria no, int v) {
        int qtd = 0;
        if (no == null) {
            return qtd;
        } else if (no.getInfo() > v) {
            qtd += no.getInfo();
            qtd += maioresXAux(no.getDir(), v);
            qtd += maioresXAux(no.getEsq(), v);
        }
        return qtd;
    }

    public int somaFolhas() {
        return folhasAux(this.raiz);
    }

    private int folhasAux(NoArvoreBinaria no) {
        int folhas = 0;
        if (no == null) {
            return folhas;
        } else if (no.getDir() == null && no.getEsq() == null) {
            folhas += 1;
        } else {
            folhas += folhasAux(no.getDir());
            folhas += folhasAux(no.getEsq());
        }
        return folhas;
    }
}
