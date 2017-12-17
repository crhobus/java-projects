package exemplos.exe8.model.impl;


import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;


import com.sun.opengl.util.GLUT;
import exemplos.exe8.model.BoundaringBox;
import exemplos.exe8.model.Color;

/**
 * Representa a cabeça do StickMan.
 * 
 * @author Thiago.Gesser
 */
public final class Head extends AbstractStickManPart {
	
	private final double radius;

	public Head(Color color, double radius) {
		super(color);
		this.radius = radius;
	}

	protected BoundaringBox initBoundaringBox() {
	    return new BoundaringBox(-radius, -radius, -radius, radius, radius, radius);
	}

	protected void innerRender(GL gl, GLU glu, GLUT glut) {
		glut.glutSolidSphere(radius, 50, 50);
	}
}
