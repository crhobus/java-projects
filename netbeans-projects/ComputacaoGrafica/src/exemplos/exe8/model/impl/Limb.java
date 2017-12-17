package exemplos.exe8.model.impl;


import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;


import com.sun.opengl.util.GLUT;
import exemplos.exe8.model.BoundaringBox;
import exemplos.exe8.model.Color;

/**
 * Representa um membro do StickMan.
 * 
 * @author Thiago.Gesser
 */
public final class Limb extends AbstractStickManPart {

	private final double radius;
	private final double size;

	public Limb(Color color, double radius, double size) {
		super(color);
		this.radius = radius;
		this.size = size;
	}
	
	protected BoundaringBox initBoundaringBox() {
        return new BoundaringBox(-radius, -radius, 0, radius, radius, size);
	}
	
	public void innerRender(GL gl, GLU glu, GLUT glut) {
		glut.glutSolidCylinder(radius, size, 50, 50);
	}
}
