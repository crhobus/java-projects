package exemplos.exe8.model.impl;

import exemplos.exe8.model.Color;
import exemplos.exe8.model.GraphicalObject;
import javax.media.opengl.GL;


/**
 * Classe abstrata para as partes do StickMan.
 * 
 * @author Thiago.Gesser
 */
public abstract class AbstractStickManPart extends GraphicalObject {
	
	public AbstractStickManPart() {
		super();
	}
	
	public AbstractStickManPart(Color color) {
		super(color);
	}
	
	@Override
	public final void updateBoundaringBox(GL gl) {
		//Chama o update do stick man.
		getParent().updateBoundaringBox(gl);
	}
}
