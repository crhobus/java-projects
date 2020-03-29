package br.com.app.lancamento.dto;

public enum TipoEnum {

    INICIO_TRABALHO(0),
    TERMINO_TRABALHO(1),
    INICIO_ALMOCO(2),
    TERMINO_ALMOCO(3),
    INICIO_PAUSA(4),
    TERMINO_PAUSA(5);

    private final int value;

    TipoEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TipoEnum getKey(int value) {
        TipoEnum[] values = TipoEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getValue() == value) {
                return values[i];
            }
        }
        return null;
    }
}
