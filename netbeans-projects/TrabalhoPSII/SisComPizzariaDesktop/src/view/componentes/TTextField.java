package view.componentes;

import javax.swing.JTextField;

public class TTextField extends JTextField {

    private boolean obrigatorio;

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }
}
