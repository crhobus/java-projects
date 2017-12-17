package exemplos.exe8.model;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

/**
 * Classe base para todos os objetos gráficos. O objeto pode ou não possuir filhos.<br>
 * Armazena a matriz de transformação do objeto e aplica-a antes de renderizar o mesmo.<br>
 * A matriz é aplicada nos filhos também, fazendo um efeito igual a um nó de um grafo de cena.<br>
 * Suporta as seguintes transformações: translação, escala e rotação.<br>
 * Possui uma boundaring box, cujo é atualizada de acordo com as transformações que o objeto sofre.
 * 
 * 
 * @author Thiago.Gesser
 */
public abstract class GraphicalObject {
	
	//Começa com uma matriz identidade.
	private double[] transformationMatrix = {1, 0, 0, 0,
											 0, 1, 0, 0,
											 0, 0, 1, 0,
											 0, 0, 0, 1};
	
	private BoundaringBox boundaringBox;
	private Color color;
	private boolean showBoundaringBox;
	
	private GraphicalObject parent;
	private GraphicalObject[] children;
	
	public GraphicalObject() {
		this(new Color());
	}
	
	public GraphicalObject(Color color) {
		this.color = color;
	}
	
	public final void render(GL gl, GLU glu, GLUT glut) {
        gl.glPushMatrix();
        
        //Multiplica a matriz de transformação do objeto com a corrente.
        gl.glMultMatrixd(transformationMatrix, 0);
        
        //Aplica a cor do objeto.
        gl.glColor4d(color.getRed(), color.getGreen(),
        			 color.getBlue(), color.getAlpha());
        
        //Renderiza o objeto
        innerRender(gl, glu, glut);
        
	    if (showBoundaringBox) {
	        renderBoundaringBox(gl);
	    }
        
        //Renderiza os filhos.
        for (GraphicalObject child : children) {
            child.render(gl, glu, glut);
        }
        
        gl.glPopMatrix();
	}
	
	private void renderBoundaringBox(GL gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity();
        
	    gl.glColor3d(0, 0, 0);
	
        //Desenha a parte de cima.
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3d(boundaringBox.getInitSWPoint().getX(), boundaringBox.getInitSWPoint().getY(), boundaringBox.getInitSWPoint().getZ());
        gl.glVertex3d(boundaringBox.getInitSEPoint().getX(), boundaringBox.getInitSEPoint().getY(), boundaringBox.getInitSEPoint().getZ());
        gl.glVertex3d(boundaringBox.getInitNEPoint().getX(), boundaringBox.getInitNEPoint().getY(), boundaringBox.getInitNEPoint().getZ());
        gl.glVertex3d(boundaringBox.getInitNWPoint().getX(), boundaringBox.getInitNWPoint().getY(), boundaringBox.getInitNWPoint().getZ());
        gl.glEnd();
        
        //Desenha a parte de baixo.
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3d(boundaringBox.getFinalSWPoint().getX(), boundaringBox.getFinalSWPoint().getY(), boundaringBox.getFinalSWPoint().getZ());
        gl.glVertex3d(boundaringBox.getFinalSEPoint().getX(), boundaringBox.getFinalSEPoint().getY(), boundaringBox.getFinalSEPoint().getZ());
        gl.glVertex3d(boundaringBox.getFinalNEPoint().getX(), boundaringBox.getFinalNEPoint().getY(), boundaringBox.getFinalNEPoint().getZ());
        gl.glVertex3d(boundaringBox.getFinalNWPoint().getX(), boundaringBox.getFinalNWPoint().getY(), boundaringBox.getFinalNWPoint().getZ());
        gl.glEnd();
        
        //Desenha as ligações entre a parte de cima e a de baixo.
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3d(boundaringBox.getInitSWPoint().getX(), boundaringBox.getInitSWPoint().getY(), boundaringBox.getInitSWPoint().getZ());
        gl.glVertex3d(boundaringBox.getFinalSWPoint().getX(), boundaringBox.getFinalSWPoint().getY(), boundaringBox.getFinalSWPoint().getZ());
        
        gl.glVertex3d(boundaringBox.getInitSEPoint().getX(), boundaringBox.getInitSEPoint().getY(), boundaringBox.getInitSEPoint().getZ());
        gl.glVertex3d(boundaringBox.getFinalSEPoint().getX(), boundaringBox.getFinalSEPoint().getY(), boundaringBox.getFinalSEPoint().getZ());
        
        gl.glVertex3d(boundaringBox.getInitNWPoint().getX(), boundaringBox.getInitNWPoint().getY(), boundaringBox.getInitNWPoint().getZ());
        gl.glVertex3d(boundaringBox.getFinalNWPoint().getX(), boundaringBox.getFinalNWPoint().getY(), boundaringBox.getFinalNWPoint().getZ());
        
        gl.glVertex3d(boundaringBox.getInitNEPoint().getX(), boundaringBox.getInitNEPoint().getY(), boundaringBox.getInitNEPoint().getZ());
        gl.glVertex3d(boundaringBox.getFinalNEPoint().getX(), boundaringBox.getFinalNEPoint().getY(), boundaringBox.getFinalNEPoint().getZ());
        gl.glEnd();
        
        gl.glPopMatrix();
	}
	
	public final void init(GL gl) {
		//Inicializa os filhos.
		this.children = initChildren(gl);
		for (GraphicalObject child : children) {
			child.setParent(this);
			child.init(gl);
		}
		afterInitChildren(gl);
		
		//Inicia a boundaringBox
		this.boundaringBox = initBoundaringBox();
		afterInitBoundaringBox(gl);
	}
	
	//Pode ser sobescrito.
	protected GraphicalObject[] initChildren(GL gl) {
	    return new GraphicalObject[0]; 
    }
	
	//Pode ser sobescrito.
	protected void afterInitChildren(GL gl) {
	}
	
    //Pode ser sobescrito.    
    protected void afterInitBoundaringBox(GL gl) {
    }

	protected abstract BoundaringBox initBoundaringBox();
	
	protected abstract void innerRender(GL gl, GLU glu, GLUT glut);
	
	//Pode ser sobrescrito.
	public void updateBoundaringBox(GL gl) {
		transformBoundaringBox(gl);
	}
	
	public void rotate(GL gl, double angle, double x, double y, double z) {
		initTransformation(gl);
		
		//Aplica a rotação.
		gl.glRotated(angle, x, y, z);
		
		endTransformation(gl);
		
		//Atualiza a boundaring box.
		updateBoundaringBox(gl);
	}
	
	public void translate(GL gl, double x, double y, double z) {
		initTransformation(gl);
		
		//Aplica a translação.
		gl.glTranslated(x, y, z);
		
		endTransformation(gl);
		
		//Atualiza a boundaring box.
		updateBoundaringBox(gl);
	}
	
	public void scale(GL gl, double x, double y, double z) {
		initTransformation(gl);
		
		//Aplica a escala.
		gl.glScaled(x, y, z);
		
		endTransformation(gl);
		
		//Atualiza a boundaring box.
		updateBoundaringBox(gl);
	}
	
	public void transformBoundaringBox(GL gl) {
		//Coloca uma matriz na pilha.
		gl.glPushMatrix();
		
		//Multiplica a matriz deste objeto com a que está no topo da pilha.
		gl.glMultMatrixd(transformationMatrix, 0);
		
		//Obtém a matriz transformada.
		double[] tempMatrix = new double[16];
		gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, tempMatrix, 0);
		
		//Aplica a transformação na boundaringBox.
		boundaringBox.applyTransformation(tempMatrix);
		
        for (GraphicalObject child : children) {
            child.transformBoundaringBox(gl);
        }

		//Remove a matriz da pilha.
		gl.glPopMatrix();
	}
	
	private void initTransformation(GL gl) {
		//Coloca uma matriz na pilha.
		gl.glPushMatrix();
		
		//Coloca a matriz atual no seu lugar.
		gl.glLoadMatrixd(transformationMatrix, 0);
	}
	
	private void endTransformation(GL gl) {
		//Obtém a matriz transformada.
		gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, transformationMatrix, 0);
		
		//Remove a matriz da pilha.
		gl.glPopMatrix();
	}
	
	
	
	
	public final GraphicalObject[] getChildren() {
		return children;
	}
	
	private void setParent(GraphicalObject parent) { 
		this.parent = parent;
	}
	
    public final void setShowBoundaringBox(boolean showBoundaringBox) {
        this.showBoundaringBox = showBoundaringBox;
    }
    
    public final BoundaringBox getBoundaringBox() {
		return boundaringBox;
	}
    
    public final Color getColor() {
		return color;
	}
    
    protected final GraphicalObject getParent() {
		return parent;
	}
    
    protected final double[] getTransformationMatrix() {
		return transformationMatrix;
	}
}
