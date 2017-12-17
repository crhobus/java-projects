package exemplos.exe8.model.impl;


import java.util.ArrayList;
import java.util.Collection;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;


import com.sun.opengl.util.GLUT;
import exemplos.exe8.model.BoundaringBox;
import exemplos.exe8.model.Color;
import exemplos.exe8.model.GraphicalObject;
import exemplos.exe8.model.Point;

/**
 * Objeto que representa um StickMan.<br>
 * Possui dois braços, duas pernas, um tronco e uma cabeça.<br>
 * Tem a sua boundaring box atualizada de acordo com alterações das suas partes.
 * 
 * @author Thiago.Gesser
 */
public final class StickMan extends GraphicalObject {
	
	//Valores básicos (fixos).
	private static final double BODY_RADIUS = 0.25;
	private static final double HEAD_RADIUS = 0.75;
	private static final double TRUNK_SIZE = 4;
    private static final double ON_FOOT_ANGLE = 90;
    private static final double ARM_FRONT_ANGLE = -15;
    private static final double ARM_SIDE_ANGLE = 40;
    private static final double LEG_SIDE_ANGLE = 30; 

    //Valores proporcionais aos valores básicos.
	private static final double ARM_SIZE = TRUNK_SIZE * 0.625;
	private static final double LEG_SIZE = TRUNK_SIZE * 0.75;
	private static final double ARM_X_DISTANCE = BODY_RADIUS;
	private static final double ARM_Y_DISTANCE = BODY_RADIUS * 2.5;
	private static final double ARM_Z_DISTANCE = 1;
	private static final double LEG_X_DISTANCE = 0;
	private static final double LEG_Y_DISTANCE = TRUNK_SIZE * 0.4375;
	private static final double LEG_Z_DISTANCE = TRUNK_SIZE * 0.75;
	private static final double HEAD_DISTANCE = -HEAD_RADIUS * 0.2;
	
    private Limb trunk;
    private Head head;
    private Limb arm1;
    private Limb arm2;
    private Limb leg1;
    private Limb leg2;
    
    public StickMan(Color color) {
		super(color);
	}
    
	protected GraphicalObject[] initChildren(GL gl) {
    	Collection<GraphicalObject> children = new ArrayList<GraphicalObject>();
    	
		//Cria o tronco.
		trunk = new Limb(getColor(), BODY_RADIUS, TRUNK_SIZE);
		children.add(trunk);
		
		//Cria a cabeça.
		head = new Head(getColor(), HEAD_RADIUS);
		children.add(head);
		
		//Cria os braços.
		arm1 = new Limb(getColor(), BODY_RADIUS, ARM_SIZE);
		children.add(arm1);
		arm2 = new Limb(getColor(), BODY_RADIUS, ARM_SIZE);
		children.add(arm2);
		
		//Cria as pernas.
		leg1 = new Limb(getColor(), BODY_RADIUS, LEG_SIZE);
		children.add(leg1);
		leg2 = new Limb(getColor(), BODY_RADIUS, LEG_SIZE);
		children.add(leg2);
		
		return children.toArray(new GraphicalObject[children.size()]);
	}
	
	@Override	
	protected void afterInitChildren(GL gl) {
	    //Coloca os membros em seus lugares.
        trunk.rotate(gl, ON_FOOT_ANGLE, 1, 0, 0);
        
        head.translate(gl, 0, HEAD_DISTANCE, 0);
        
        arm1.rotate(gl, ON_FOOT_ANGLE - ARM_SIDE_ANGLE, 1, 0, 0);
        arm1.rotate(gl, ARM_FRONT_ANGLE, 0, 1, 0);
        arm1.translate(gl, ARM_X_DISTANCE, -ARM_Y_DISTANCE, ARM_Z_DISTANCE);
        
        arm2.rotate(gl, ON_FOOT_ANGLE + ARM_SIDE_ANGLE, 1, 0, 0);
        arm2.rotate(gl, ARM_FRONT_ANGLE, 0, 1, 0);
        arm2.translate(gl, ARM_X_DISTANCE, ARM_Y_DISTANCE, ARM_Z_DISTANCE);
        
        leg1.rotate(gl, ON_FOOT_ANGLE - LEG_SIDE_ANGLE, 1, 0, 0);
        leg1.translate(gl, LEG_X_DISTANCE, -LEG_Y_DISTANCE, LEG_Z_DISTANCE);
        
        leg2.rotate(gl, ON_FOOT_ANGLE + LEG_SIDE_ANGLE, 1, 0, 0);
        leg2.translate(gl, LEG_X_DISTANCE, LEG_Y_DISTANCE, LEG_Z_DISTANCE);
	}
	
	protected BoundaringBox initBoundaringBox() {
        BoundaringBox bb = new BoundaringBox();
        defineStickManBounds(bb);
        
        return bb;
	}
	
	public void innerRender(GL gl, GLU glu, GLUT glut) {}
	
	@Override
	public void updateBoundaringBox(GL gl) {
		//Coloca uma matriz identidade na pilha.
		gl.glPushMatrix();	
		gl.glLoadIdentity();
		
		//Multiplica a matriz deste objeto com a que está no topo da pilha.
		gl.glMultMatrixd(getTransformationMatrix(), 0);
		
		//Atualiza os membros.
        for (GraphicalObject child : getChildren()) {
            child.transformBoundaringBox(gl);
        }
		
		//Remove a matriz da pilha.
		gl.glPopMatrix();	
		
		//Atualiza a boundaring box do stick man.
		if (getBoundaringBox() != null) {
			defineStickManBounds(getBoundaringBox());
		}
	}
	
	private void defineStickManBounds(BoundaringBox bb) {
		//Usa Integer.MAX_VALUE e MIN_VALUE pois o java estava se perdendo para verificar que -15 > Double.MIN_VALUE.
	    double ix = Integer.MAX_VALUE, iy = Integer.MAX_VALUE, iz = Integer.MAX_VALUE;
	    double fx = Integer.MIN_VALUE, fy = Integer.MIN_VALUE, fz = Integer.MIN_VALUE;
    
	    for (GraphicalObject child : getChildren()) {
	    	BoundaringBox childBB = child.getBoundaringBox();
	    	
		    for (Point point : childBB.getPoints()) {
		    	double x = point.getX();
		    	double y = point.getY();
		    	double z = point.getZ();
		    	
		        if (x < ix) {
		            ix = x;
		        } 
		        if (x > fx) {
		            fx = x;
		        }
		        
		        if (y < iy) {
		            iy = y;
		        } 
		        if (y > fy) {
		            fy = y;
		        }
		        
		        if (z < iz) {
	                iz = z;
	            }
		        if (z > fz) {
	                fz = z;
	            }
		    }
	    }
	    
	    bb.updateBounds(ix, iy, iz, fx, fy, fz);
	}
	
	
	public Limb getArm1() {
		return arm1;
	}
	
	public Limb getArm2() {
		return arm2;
	}
	
	public Head getHead() {
		return head;
	}
	
	public Limb getLeg1() {
		return leg1;
	}
	
	public Limb getLeg2() {
		return leg2;
	}
}
