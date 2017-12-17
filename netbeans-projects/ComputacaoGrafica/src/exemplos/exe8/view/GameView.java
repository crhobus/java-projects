package exemplos.exe8.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.j2d.TextRenderer;

import exemplos.exe8.control.GameControl;
import exemplos.exe8.model.GraphicalObject;
import exemplos.exe8.model.impl.StickMan;

/**
 * Classe responsável pela visão do jogo.
 * 
 * @author Thiago.Gesser
 */
public class GameView implements GLEventListener, KeyListener {
    
    private static final double PROJECTION_FAR = 80;
    private static final double PROJECTION_NEAR = 1.5;
    
	private GL gl;
	private GLU glu;
	private GLUT glut;
	
	private GameControl gameControl;
	
	//Posicionamento da câmera.
	private double camX = 0;
	private double camY = 20;
	private double camZ = -30;
	
	private KeyEvent curKeyEvent;
	private TextRenderer livesTextRenderer;
	private TextRenderer commonTextRenderer;
	private TextRenderer detailTextRenderer;
    private boolean showBoundaringBox;
	
	// "render" feito logo após a inicialização do contexto OpenGL.
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL();
		glu = new GLU();
		glut = new GLUT();
		drawable.setGL(new DebugGL(gl));

		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		//Cria o text renderer.
		livesTextRenderer = new TextRenderer(new Font("Arial Black", Font.BOLD, 40));
		commonTextRenderer = new TextRenderer(new Font("Book Antiqua", Font.BOLD, 55));
		detailTextRenderer = new TextRenderer(new Font("Book Antiqua", Font.BOLD, 30));

		// Inicializa o efeito de luz.
		gl.glEnable(GL.GL_LIGHTING);
		gl.glEnable(GL.GL_LIGHT0);
		gl.glEnable(GL.GL_COLOR_MATERIAL);
		
		//Configura os efeitos de luz.
		float[] ambientLight = new float[] { 0.2f, 0.2f, 0.2f, 1.0f };
		float[] diffuseLight = new float[] { 0.8f, 0.8f, 0.8f, 1.0f };
		float[] specularLight = new float[] { 0.5f, 0.5f, 0.5f, 1.0f };
		float[] position = new float[] { 0, 5.0f, 0, 1.0f };
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambientLight, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuseLight, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, specularLight, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);
		
		//Inicializa o teste de profundidade.
		gl.glEnable(GL.GL_DEPTH_TEST);
		
		//Inicializa o control.
		gameControl = new GameControl(gl);
		
		//Inicia a thread do jogo.
		new GameThread(drawable).start();
		
		//Mostra a boundaring box se foi setado o parâmetro de VM "showBB".
		showBoundaringBox = System.getProperty("showBB") != null;
        if (showBoundaringBox) { 
    		gameControl.setShowBoundaringBoxes(true);
		}
	}

	// método definido na interface GLEventListener.
	// "render" feito pelo cliente OpenGL.
	public void display(GLAutoDrawable arg0) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		//Obtém as dimensoes do viewport.
		int[] viewportInfo = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, viewportInfo, 0);
		int viewportWidth = viewportInfo[2];
		int viewportHeight = viewportInfo[3];
		
		if (gameControl.isStarted()) {
			displayGame(viewportWidth, viewportHeight);
		} else {
			displayStartScreen(viewportWidth, viewportHeight);
		}
		
		gl.glFlush();
	}
	
	private void displayGame(int viewportWidth, int viewportHeight) {
		//Trata a última tecla apertada.
		handleKeyPress();
		
		//Aplica as definições da câmera.
		gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        
        gl.glFrustum(-1.0, 1.0, -1.0, 1.0, PROJECTION_NEAR, PROJECTION_FAR);
        gl.glTranslated(0, 0, camZ);
        gl.glRotated(camX, 0, 1, 0);
        gl.glRotated(camY, 1, 0, 0);
        
		//Inicia o tratamento do modelo.
        gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		//Faz o tratamento das ações atuais (golpes).
		handleActions();

		//Desenha os objetos.
		for (GraphicalObject obj : gameControl.getObjets()) {
			obj.render(gl, glu, glut);
		}
		
		//Verifica se o jogo acabou.
		if (gameControl.isOver()) {
			drawGameOver(viewportWidth, viewportHeight);
		} else {
			//Desenha as vidas dos stickmans.
			drawLives(viewportWidth, viewportHeight);
		}
	}
	
	private void drawGameOver(int viewportWidth, int viewportHeight) {
		String gameOverString = gameControl.getWinner().getName() + " wins!";
		Rectangle2D gameOverBounds = commonTextRenderer.getBounds(gameOverString);
		commonTextRenderer.beginRendering(viewportWidth, viewportHeight);
		commonTextRenderer.setColor(Color.RED);
		commonTextRenderer.draw(gameOverString, (int) (viewportWidth/2 - gameOverBounds.getWidth()/2),
											    (int) (viewportHeight/2 + gameOverBounds.getHeight()/2));
		commonTextRenderer.endRendering();
	}
	
	private void drawLives(int viewportWidth, int viewportHeight) {
		String livesString = getLivesString(gameControl.getStickMan1().getLives());
		Rectangle2D livesBounds = livesTextRenderer.getBounds(livesString);
		livesTextRenderer.beginRendering(viewportWidth, viewportHeight);
		livesTextRenderer.setColor(Color.BLUE);
		livesTextRenderer.draw(livesString, 0, (int) (viewportHeight - livesBounds.getHeight()));
		livesTextRenderer.endRendering();
		
		livesString = getLivesString(gameControl.getStickMan2().getLives());
		livesBounds = livesTextRenderer.getBounds(livesString);
		livesTextRenderer.beginRendering(viewportWidth, viewportHeight);
		livesTextRenderer.setColor(Color.RED);
		livesTextRenderer.draw(livesString, (int) (viewportWidth - livesBounds.getWidth()), (int) (viewportHeight - livesBounds.getHeight()));
		livesTextRenderer.endRendering();
	}
	
    private void displayStartScreen(int viewportWidth, int viewportHeight) {
        //Posiciona a câmera.
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustum(-1.0, 1.0, -1.0, 1.0, PROJECTION_NEAR, PROJECTION_FAR);
        gl.glTranslated(0, 0, -20);
        
        //Desenha os stick mans de demonstração
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        StickMan stickMan1 = new StickMan(exemplos.exe8.model.Color.fromAWTColor(Color.BLUE));
        StickMan stickMan2 = new StickMan(exemplos.exe8.model.Color.fromAWTColor(Color.RED));
        stickMan1.init(gl);
        stickMan2.init(gl);
        gameControl.setShowBoundaringBoxes(showBoundaringBox, stickMan1, stickMan2);
        
        stickMan1.getArm1().rotate(gl, -80, 1, 1, 0);
        stickMan2.getLeg2().rotate(gl, -100, 0, 1, 0);
        stickMan1.translate(gl, -8, 2, 0);
        stickMan2.translate(gl, 8, 2, 0);
        
        camX += 5;
        stickMan1.rotate(gl, 100 + camX, 0, 1, 0);
        stickMan2.rotate(gl, 70 + camX, 0, 1, 0);
        
        stickMan1.render(gl, glu, glut);
        stickMan2.render(gl, glu, glut);
        
        //Escreve os textos
        String textString = "Stick Man Fighter";
        Rectangle2D textBounds = commonTextRenderer.getBounds(textString);
        commonTextRenderer.beginRendering(viewportWidth, viewportHeight);
        commonTextRenderer.setColor(Color.RED);
        commonTextRenderer.draw(textString, (int) (viewportWidth/2 - textBounds.getWidth()/2),
                                                (int) (viewportHeight - textBounds.getHeight()*2));
        commonTextRenderer.endRendering();
        
        textString = "Press any key to start!";
        textBounds = detailTextRenderer.getBounds(textString);
        detailTextRenderer.beginRendering(viewportWidth, viewportHeight);
        if (camX % 20 == 0) {
            detailTextRenderer.setColor(Color.RED);
        } else {
            detailTextRenderer.setColor(Color.BLUE);
        }
        detailTextRenderer.draw(textString, (int) (viewportWidth/2 - textBounds.getWidth()/2),
                                            (int) (textBounds.getHeight()*2));
        detailTextRenderer.endRendering();
        
        if (curKeyEvent != null) {
            curKeyEvent = null;
            camX = 0;
            gameControl.start();
        }
    }
	
	private void handleKeyPress() {
		if (curKeyEvent == null) {
			return;
		}

		switch (curKeyEvent.getKeyCode()) {
			/*
			 * Controles do stickMan 1.
			 */
            case KeyEvent.VK_A:
                gameControl.getStickMan1().move(gl, 0.2, 0); break;
            case KeyEvent.VK_D:
                gameControl.getStickMan1().move(gl, -0.2, 0); break;
            case KeyEvent.VK_W:
                gameControl.getStickMan1().move(gl, 0, 0.2); break;
            case KeyEvent.VK_S:
                gameControl.getStickMan1().move(gl, 0, -0.2); break;
            case KeyEvent.VK_CONTROL:
                if (!gameControl.getStickMan1().isPunching()) {
                    gameControl.getStickMan1().startPunch();
                }
                break;
            case KeyEvent.VK_SPACE:
                if (!gameControl.getStickMan1().isKicking()) {
                    gameControl.getStickMan1().startKick();
                }
                break;
                
            	
            /*
             * Controles do stickMan 2.
             */
            case KeyEvent.VK_LEFT:
                gameControl.getStickMan2().move(gl, -0.2, 0); break;
            case KeyEvent.VK_RIGHT:
                gameControl.getStickMan2().move(gl, 0.2, 0); break;
            case KeyEvent.VK_UP:
                gameControl.getStickMan2().move(gl, 0, -0.2); break;
            case KeyEvent.VK_DOWN:
                gameControl.getStickMan2().move(gl, 0, 0.2); break;
            case KeyEvent.VK_NUMPAD0:
                if (!gameControl.getStickMan2().isPunching()) {
                    gameControl.getStickMan2().startPunch();
                }
                break;
            case KeyEvent.VK_NUMPAD1:
                if (!gameControl.getStickMan2().isKicking()) {
                    gameControl.getStickMan2().startKick();
                }
                break;
		
		
            /*
             * Controles da câmera.
             */
            case KeyEvent.VK_L:
                camX += 5; break;
            case KeyEvent.VK_J:
                camX -= 5; break;
            case KeyEvent.VK_I:
                camY += 5; break;
            case KeyEvent.VK_K:
                camY -= 5; break;
                
            case KeyEvent.VK_EQUALS:
            case KeyEvent.VK_ADD:
                if (camZ + 1 < 0) {
                    camZ++;
                }
                break;
                
            case KeyEvent.VK_MINUS:
            case KeyEvent.VK_SUBTRACT:
                if (camZ - 1 > -(PROJECTION_FAR - 20)) {
                    camZ--;
                }
                break;
		}
		
		curKeyEvent = null;
	}
	
	private void handleActions() {
	    /*
	     * Tratamento das ações do stickMan 1.
	     */
		if (gameControl.getStickMan1().isPunching()) {
			if (!gameControl.getStickMan1().isPunchFinished()) {
				gameControl.getStickMan1().nextPunchStep(gl);
			}
		}
		if (gameControl.getStickMan1().isKicking()) {
			if (!gameControl.getStickMan1().isKickFinished()) {
				gameControl.getStickMan1().nextKickStep(gl);
			}
		}
		
        /*
         * Tratamento das ações do stickMan 2.
         */
        if (gameControl.getStickMan2().isPunching()) {
            if (!gameControl.getStickMan2().isPunchFinished()) {
                gameControl.getStickMan2().nextPunchStep(gl);
            }
        }
        if (gameControl.getStickMan2().isKicking()) {
            if (!gameControl.getStickMan2().isKickFinished()) {
                gameControl.getStickMan2().nextKickStep(gl);
            }
        }
	}

	// método definido na interface GLEventListener.
	// "render" feito depois que a janela foi redimensionada.
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		gl.glViewport(0, 0, w, h);
	}

	public void keyPressed(KeyEvent e) {
		curKeyEvent = e;
		
//		glDrawable.display();
	}

	// método definido na interface GLEventListener.
	// "render" feito quando o modo ou dispositivo de exibição associado foi
	// alterado.
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
	
	
	private String getLivesString(int numberOfLives) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numberOfLives; i++) {
			builder.append("O");
		}
		
		return builder.toString();
	}
}
