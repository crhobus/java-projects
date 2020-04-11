package br.com.app.auditoria.dto;

public enum StatusEnum {

    SUCESSO(0),
    ERRO(1);

    private final int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StatusEnum getKey(int value) {
        StatusEnum[] values = StatusEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getValue() == value) {
                return values[i];
            }
        }
        return null;
    }
}
