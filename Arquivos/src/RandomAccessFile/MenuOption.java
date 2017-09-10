package RandomAccessFile;

public enum MenuOption {

    IMPRESSAO(1),
    RECUPERAR(2),
    NOVO(3),
    DELETE(4),
    FIM(5);
    private final int valor;

    MenuOption(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
