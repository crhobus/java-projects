package view.componentes;

import javax.swing.JFormattedTextField;

public class TFormattedTextField extends JFormattedTextField {

    private boolean obrigatorio;

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }
}
