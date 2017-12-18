package ListaVinculada;

class NoLista {

    Object dados;
    NoLista proxNo;

    NoLista(Object dados) {
        this(dados, null);
    }

    NoLista(Object dados, NoLista proxNo) {
        this.dados = dados;
        this.proxNo = proxNo;
    }

    Object getDados() {
        return dados;
    }

    NoLista getProxNo() {
        return proxNo;
    }
}
