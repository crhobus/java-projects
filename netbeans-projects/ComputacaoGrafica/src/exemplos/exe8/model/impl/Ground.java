package exemplos.exe8.model.impl;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;


import com.sun.opengl.util.GLUT;
import exemplos.exe8.model.BoundaringBox;
import exemplos.exe8.model.Color;
import exemplos.exe8.model.GraphicalObject;



/**
 * Representa um pedaço de chão.
 * 
 * @author thiago.gesser
 */
public final class Ground extends GraphicalObject {
    
    private final double x;
    private final double y;
    private final double z;
    private final double width;
    private final double height;
    private final double depth;

    public Ground(Color color, double x, double y, double z, double width, double height, double depth) {
    	super(color);
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    protected BoundaringBox initBoundaringBox() {
        return new BoundaringBox(-0.5, -0.5, -0.5, 0.5, 0.5, 0.5);
    }
    
    @Override
    protected void afterInitBoundaringBox(GL gl) {
    	scale(gl, width, height, depth);
    	translate(gl, x, y, z);
    }

    protected void innerRender(GL gl, GLU glu, GLUT glut) {
        glut.glutSolidCube(1);
    }
}
