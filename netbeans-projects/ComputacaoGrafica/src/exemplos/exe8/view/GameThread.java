package exemplos.exe8.view;

import javax.media.opengl.GLAutoDrawable;

/**
 * Thread principal do jogo que controla a atualização dos frames.
 * 
 * @author Thiago.Gesser
 */
public final class GameThread extends Thread {
	
	private final GLAutoDrawable glDrawable;
	
	public GameThread(GLAutoDrawable glDrawable) {
		this.glDrawable = glDrawable;
	}
	
	@Override
	public void run() {
		for (;;) {
			try {
				//Da um sleep só para não consumir tanta CPU.
				Thread.sleep(10);
				
				glDrawable.display();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
