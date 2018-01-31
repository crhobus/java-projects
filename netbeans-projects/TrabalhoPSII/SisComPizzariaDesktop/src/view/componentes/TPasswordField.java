package view.componentes;

import javax.swing.JPasswordField;

public class TPasswordField extends JPasswordField {

    private boolean obrigatorio;

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }
}
