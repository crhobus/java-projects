package br.com.app.usuario.dto;

public enum SituacaoUserEnum {

    ATIVO(0),
    INATIVO(1);

    private final int value;

    SituacaoUserEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SituacaoUserEnum getKey(int value) {
        SituacaoUserEnum[] values = SituacaoUserEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getValue() == value) {
                return values[i];
            }
        }
        return null;
    }
}
