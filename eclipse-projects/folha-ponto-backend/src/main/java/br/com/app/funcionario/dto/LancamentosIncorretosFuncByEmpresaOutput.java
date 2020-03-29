package br.com.app.funcionario.dto;

import java.io.Serializable;

public class LancamentosIncorretosFuncByEmpresaOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
