package br.com.app.usuario.dto;

public enum PerfilEnum {

    ROLE_ADMIN(0),
    ROLE_USUARIO(1);

    private final int value;

    PerfilEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PerfilEnum getKey(int value) {
        PerfilEnum[] values = PerfilEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getValue() == value) {
                return values[i];
            }
        }
        return null;
    }
}
