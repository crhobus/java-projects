package exemplos.exe8.model;

/**
 * Armazena os limites de um objeto 3D. 
 * Pode ter suas cordenadas transformadas de acordo com uma matriz de transformação.
 * 
 * @author Thiago.Gesser
 */
public final class BoundaringBox {
	
	private Point initSWPoint;
	private Point initSEPoint;
	private Point initNWPoint;
	private Point initNEPoint;
	private Point finalSWPoint;
	private Point finalSEPoint;
	private Point finalNWPoint;
	private Point finalNEPoint;
	
	public BoundaringBox() {
		this(0, 0, 0, 0, 0, 0);
	}
	
	public BoundaringBox(double ix, double iy, double iz, double fx, double fy, double fz) {
		updateBounds(ix, iy, iz, fx, fy, fz);
	}
	
	public void updateBounds(double ix, double iy, double iz, double fx, double fy, double fz) {
		this.initSEPoint = new Point(ix, iy, iz);
		this.initSWPoint = new Point(fx, iy, iz);
		this.initNWPoint = new Point(fx, iy, fz);
		this.initNEPoint = new Point(ix, iy, fz);
		this.finalSEPoint = new Point(ix, fy, iz);
		this.finalSWPoint = new Point(fx, fy, iz);
		this.finalNEPoint = new Point(ix, fy, fz);
		this.finalNWPoint = new Point(fx, fy, fz);
	}
	
	public void applyTransformation(double[] transformationMatrix) {
		initSWPoint.transform(transformationMatrix);
		initSEPoint.transform(transformationMatrix);
		initNWPoint.transform(transformationMatrix);
		initNEPoint.transform(transformationMatrix);
		finalSWPoint.transform(transformationMatrix);
		finalSEPoint.transform(transformationMatrix);
		finalNWPoint.transform(transformationMatrix);
		finalNEPoint.transform(transformationMatrix);
	}
	
	public Point getFinalNEPoint() {
		return finalNEPoint;
	}
	
	public Point getFinalNWPoint() {
		return finalNWPoint;
	}
	
	public Point getFinalSEPoint() {
		return finalSEPoint;
	}
	
	public Point getFinalSWPoint() {
		return finalSWPoint;
	}
	
	public Point getInitNEPoint() {
		return initNEPoint;
	}
	
	public Point getInitNWPoint() {
		return initNWPoint;
	}
	
	public Point getInitSEPoint() {
		return initSEPoint;
	}
	
	public Point getInitSWPoint() {
		return initSWPoint;
	}
	
	public Point[] getPoints() {
		return new Point[] {initSWPoint, initSEPoint, initNWPoint, initNEPoint, finalSWPoint, finalSEPoint, finalNWPoint, finalNEPoint};
	}
}
