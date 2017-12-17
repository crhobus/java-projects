package exemplos.exe8.control;

import exemplos.exe8.model.BoundaringBox;
import exemplos.exe8.model.Color;
import exemplos.exe8.model.GraphicalObject;
import exemplos.exe8.model.Point;
import exemplos.exe8.model.impl.Ground;
import exemplos.exe8.model.impl.StickMan;
import javax.media.opengl.GL;


/**
 * Classe responsável pelo controle do jogo.
 * 
 * @author Thiago.Gesser
 */
public final class GameControl {

	private final Ground ground;
	private final StickManInGame stickMan1;
	private final StickManInGame stickMan2;
	private final GraphicalObject[] allObjets;
	private boolean isStarted;
	
    private boolean isOver = false;
    private StickManInGame winner;
	
	//Suporta N stickMans.
	private final StickManInGame[] stickMans;

	public GameControl(GL gl) {
		//Cria os objetos.
		StickMan sm1 = new StickMan(Color.fromAWTColor(java.awt.Color.BLUE));
		StickMan sm2 = new StickMan(Color.fromAWTColor(java.awt.Color.RED));
		
		this.ground = new Ground(Color.fromAWTColor(java.awt.Color.GRAY), 0, 0, 0, 20, 1, 20);
		this.stickMan1 = new StickManInGame("Player 1", sm1, -1, -1);
		this.stickMan2 = new StickManInGame("Player 2", sm2, 1, 1);
		this.allObjets = new GraphicalObject[] {ground, sm1, sm2};
		this.stickMans = new StickManInGame[] {stickMan1, stickMan2};
		
		//Inicializa os objetos.
		sm1.init(gl);
		sm2.init(gl);
		ground.init(gl);
		
		moveToInitialPosition(gl);
	}
	 
	public void moveToInitialPosition(GL gl) {
		ground.translate(gl, 0, stickMan1.getStickMan().getBoundaringBox().getInitSEPoint().getY() - 0.6, 0);
		stickMan1.getStickMan().translate(gl, -5, 0, -0.1);
		stickMan1.getStickMan().rotate(gl, 180, 0, 1, 0);
		stickMan2.getStickMan().translate(gl, 5, 0, 0.1);
	}
	
	public void setShowBoundaringBoxes(boolean show) {
		setShowBoundaringBoxes(show, allObjets);
	}
	
	public void setShowBoundaringBoxes(boolean show, GraphicalObject... objs) {
		for (GraphicalObject obj : objs) {
			obj.setShowBoundaringBox(show);
			
			setShowBoundaringBoxes(show, obj.getChildren());
		}	
	}
	

	public StickManInGame getStickMan1() {
		return stickMan1;
	}
	
	public StickManInGame getStickMan2() {
		return stickMan2;
	}
	
	
	private void stickManDied() {
		//Verifica se ainda há pelo menos dois stickMans vivos. Se não termina o jogo.
		StickManInGame lastStickMan = null;
		for (StickManInGame stickManIG : stickMans) {
			if (stickManIG.isAlive()) {
				if (lastStickMan != null) {
					return;
				}
				
				lastStickMan = stickManIG;
			}
		}
		
		//Termina o jogo.
		winner = lastStickMan;
		isOver = true;
	}
	
	
	public boolean isOver() {
		return isOver;
	}
	
	public void start() {
		this.isStarted = true;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	
	public StickManInGame getWinner() {
		return winner;
	}
	
	
	
	
	/*
	 * Métodos de verificações dos objetos do mundo.
	 */

	private boolean isOnTheGround(double ix, double iz, double fx, double fz) {
		Point iGroundPoint = ground.getBoundaringBox().getInitSEPoint();
		Point fGroundPoint = ground.getBoundaringBox().getFinalNWPoint();

		return isInside(ix, iz, fx, fz, iGroundPoint.getX(), iGroundPoint.getZ(), fGroundPoint.getX(), fGroundPoint.getZ());
	}
	
	private boolean isInside(double ix1, double iz1, double fx1, double fz1, double ix2, double iz2, double fx2, double fz2) {
		//Como não se sabe se o initPoint está realmente pro lado inicial e o final pro lado final, verifica as duas possibilidades.
		return ix1 >= ix2 && iz1 >= iz2 &&
			   fx1 <= fx2 && fz1 <= fz2 && 
			   fx1 >= ix2 && fz1 >= iz2 &&
			   ix1 <= fx2 && iz1 <= fz2;
	}
			  
	private StickManInGame intersectsWithAnotherStickMan(StickManInGame srcSM, double ix, double iz, double fx, double fz) {
		for (StickManInGame stickManIG : stickMans) {
			if (stickManIG == srcSM) {
				continue;
			}
			
			BoundaringBox bb = stickManIG.getStickMan().getBoundaringBox();
            Point iStickManPoint = bb.getInitSEPoint();
			Point fStickManPoint = bb.getFinalNWPoint();
			
			
			if (intersects(ix, iz, fx, fz, iStickManPoint.getX(), iStickManPoint.getZ(), fStickManPoint.getX(), fStickManPoint.getZ())) {
				return stickManIG;
			}
		}
		
		return null;
	}
	
	private boolean intersects(GraphicalObject obj1, GraphicalObject obj2) {
	    BoundaringBox bb1 = obj1.getBoundaringBox();
	    Point initPoint1 = bb1.getInitSEPoint();
	    Point finalPoint1 = bb1.getFinalNWPoint();
	    
	    BoundaringBox bb2 = obj2.getBoundaringBox();
	    Point initPoint2 = bb2.getInitSEPoint();
	    Point finalPoint2 = bb2.getFinalNWPoint();
	    
	    return intersects(initPoint1.getX(), initPoint1.getZ(), finalPoint1.getX(), finalPoint1.getZ(),
	                      initPoint2.getX(), initPoint2.getZ(), finalPoint2.getX(), finalPoint2.getZ());
	}
	
	private boolean intersects(double ix1, double iz1, double fx1, double fz1, double ix2, double iz2, double fx2, double fz2) {
        return((ix1 >= ix2 && ix1 <= fx2 ||
                fx1 >= ix2 && fx1 <= fx2) &&
               (iz1 >= iz2 && iz1 <= fz2 ||
                fz1 >= iz2 && fz1 <= fz2));
	}
	
	
	public GraphicalObject[] getObjets() {
		return allObjets;
	}
	
	
	
	/**
	 * Representa o estado atual do StickMan dentro do jogo e disponibiliza as ações que ele pode executar. 
	 */
	public final class StickManInGame {
		private final String name;
	    private final StickMan stickMan;
	    private final double orientationX, orientationZ;
	    private int lives;
	    private RotateAction punchAction;
	    private RotateAction kickAction;
	    
	    public StickManInGame(String name, StickMan stickMan, double orientationX, double orientationZ) {
	        this.name = name;
			this.stickMan = stickMan;
			this.orientationX = orientationX;
			this.orientationZ = orientationZ;
			
			this.lives = 5;
	    }
	    
	    private StickMan getStickMan() {
			return stickMan;
		}
	    
	    public String getName() {
			return name;
		}
	    
	    public int getLives() {
			return lives;
		}
	    
	    /*
	     * Controle da vida.
	     */
	    
	    private void wasHit() {
	    	if (--lives == 0) {
	    		stickManDied();
	    	}
	    }
	    
	    private boolean isAlive() {
	    	return lives > 0;
	    }
	    
        /*
         * Métodos de movimentação.
         */
        
    	public void move(GL gl, double x, double z) {
    		//Se o jogo já terminou, retorna.
    		if (isOver) {
    			return;
    		}
    		
    		BoundaringBox bb = stickMan.getBoundaringBox();
    		Point initPoint = bb.getInitSEPoint();
    		Point finalPoint = bb.getFinalNWPoint();
    		
    		double orientedX = x * orientationX;
    		double orientedZ = z * orientationZ;
    		
    		double ix = initPoint.getX() + orientedX;
    		double iz = initPoint.getZ() + orientedZ;
    		double fx = finalPoint.getX() + orientedX;
    		double fz = finalPoint.getZ() + orientedZ;
    		
    		if (!isOnTheGround(ix, iz, fx, fz) ||
    			intersectsWithAnotherStickMan(this, ix, iz, fx, fz) != null) {
    			return;
    		}
    		
    		stickMan.translate(gl, x, 0, z);
    	}
    	
    	/*
    	 * Métodos do soco.
    	 */
    	
    	public boolean isPunching() {
	       return punchAction != null;
		}
    		
        public void startPunch() {
        	//Se o jogo já terminou, retorna.
    		if (isOver) {
    			return;
    		}
    		
            this.punchAction = new RotateAction(stickMan.getArm1(), -80, 5, 1, 1, 0); 
        }    	
    	
		public boolean isPunchFinished() {
	        if (!isPunching()) {
	            throw new IllegalStateException();
	        }
	        
	        boolean isFinished = punchAction.isFinished();
	        if (isFinished) {
	        	punchAction = null;
	        }
	        return isFinished;
		}
    		
		public void nextPunchStep(GL gl) {
	        if (!isPunching()) {
	            throw new IllegalStateException();
	        }
		    
	        punchAction.nextStep(gl);
	        
	        if (!punchAction.isActive()) {
	            return;
	        }
	        
	        //Verifica se acertou algum outro stickman.
	        for (StickManInGame stickManIG : stickMans) {
	            if (stickManIG == this) {
	                continue;
	            }
	            
	            if (intersects(stickMan.getArm1(), stickManIG.getStickMan())) {
	            	punchAction.setActive(false);
	                
	                stickManIG.wasHit();
	                if (!stickManIG.isAlive()) {
	                	punchAction.finishOnEnd();
	                }
	            }
	        }
	        
		}
		
		
		/*
		 * Métodos do chute.
		 */
		
		public boolean isKicking() {
	       return kickAction != null;
		}
			
        public void startKick() {
        	//Se o jogo já terminou, retorna.
    		if (isOver) {
    			return;
    		}
    		
            this.kickAction = new RotateAction(stickMan.getLeg2(), -100, 15, 0, 1, 0); 
        }
		
		public boolean isKickFinished() {
	        if (!isKicking()) {
	            throw new IllegalStateException();
	        }
	        
	        boolean isFinished = kickAction.isFinished();
	        if (isFinished) {
	        	kickAction = null;
	        }
	        return isFinished;
		}
		
		public void nextKickStep(GL gl) {
	        if (!isKicking()) {
	            throw new IllegalStateException();
	        }
		    
	        kickAction.nextStep(gl);
	        
	        if (!kickAction.isActive()) {
	            return;
	        }
	        
	        //Verifica se acertou algum outro stickman.
	        for (StickManInGame stickManIG : stickMans) {
	            if (stickManIG == this) {
	                continue;
	            }
	            
	            if (intersects(stickMan.getLeg2(), stickManIG.getStickMan())) {
	                kickAction.setActive(false);
	                
	                stickManIG.wasHit();
	                if (!stickManIG.isAlive()) {
	                	kickAction.finishOnEnd();
	                }
	            }
	        }
		}
	}
}
