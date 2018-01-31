package Principal;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class Atalhos implements KeyEventDispatcher {

    private Principal principal;

    public Atalhos(Principal principal) {
        this.principal = principal;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent evento) {
        if (evento.getID() == KeyEvent.KEY_PRESSED) {
            if (evento.isControlDown()) {
                if (KeyEvent.VK_N == evento.getKeyCode()) {
                    principal.novo();
                }
                if (KeyEvent.VK_A == evento.getKeyCode()) {
                    principal.abrir();
                }
                if (KeyEvent.VK_S == evento.getKeyCode()) {
                    principal.salvar();
                }
            }
            if (KeyEvent.VK_F8 == evento.getKeyCode()) {
                principal.compilar();
            }
            if (KeyEvent.VK_F9 == evento.getKeyCode()) {
                principal.gerarCodigo();
            }
            if (KeyEvent.VK_F1 == evento.getKeyCode()) {
                principal.equipe();
            }
        }
        return false;
    }
}
