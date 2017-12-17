package exemplos.exe8.view;
import java.awt.BorderLayout;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Controles do jogo StickMan Fighter.<br><br><br>
 * Jogador 1:<br>
 *      <b>Andar para a esquerda:</b> A<br>
 *      <b>Andar para a direita:</b> D<br>
 *      <b>Andar para cima:</b> W<br>
 *      <b>Andar para baixo:</b> S<br>
 *      <b>Soco:</b> Ctrl<br>
 *      <b>Chute:</b> Espaço<br>
 *      <br><br>
 *      
 * Jogador 2:<br>
 *      <b>Andar para esquerda:</b> Seta para esquerda<br>
 *      <b>Andar para direita:</b> Seta para direita<br>
 *      <b>Andar para cima:</b> Seta para cima<br>
 *      <b>Andar para baixo:</b> Seta para baixo<br>
 *      <b>Soco:</b> 0 do numpad<br>
 *      <b>Chute:</b> 1 do numpad<br>
 *      <br><br>
 *      
 * Câmera:<br>
 *      <b>Girar para esquerda:</b> J<br>
 *      <b>Girar para direita:</b> L<br>
 *      <b>Girar para cima:</b> I<br>
 *      <b>Girar para baixo:</b> K<br>
 *      <b>Aproximar:</b> +<br>
 *      <b>Afastar:</b> -<br>
 * 
 * 
 * @author Thiago.Gesser
 */
public class StickManFighterFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private GameView renderer = new GameView();
	
	public StickManFighterFrame() {		
		// Cria o frame.
		super("Tela Principal");   
		setBounds(50,100,500,500); 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		/* Cria um objeto GLCapabilities para especificar 
		 * o número de bits por pixel para RGBA
		 */
		GLCapabilities glCaps = new GLCapabilities();
		glCaps.setRedBits(8);
		glCaps.setBlueBits(8);
		glCaps.setGreenBits(8);
		glCaps.setAlphaBits(8); 

		/* Cria um canvas, adiciona ao frame e objeto "ouvinte" 
		 * para os eventos Gl, de mouse e teclado
		 */
		GLCanvas canvas = new GLCanvas(glCaps);
		add(canvas,BorderLayout.CENTER);
		canvas.addGLEventListener(renderer);        
		canvas.addKeyListener(renderer);
		canvas.requestFocus();			
	}		
	
	public static void main(String[] args) {
		new StickManFighterFrame().setVisible(true);
	}
}
