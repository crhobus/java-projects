package exemplos.exe8.model;

/**
 * Define um ponto em um universo 3D. Suporta a transformação do ponto de acordo com uma matriz
 * de transformação, mas ainda mantém as cordenadas originais para que as futuras transformações sejam
 * aplicadas na posição original do ponto.
 * 
 * @author Thiago.Gesser
 */
public final class Point {
	private final double originalX;
	private final double originalY;
	private final double originalZ;
	private double x;
	private double y;
	private double z;

	public Point() {
		this(0, 0, 0);
	}
	
	public Point(double x, double y, double z) {
		this.originalX = x;
		this.originalY = y;
		this.originalZ = z;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void transform(double[] tm) {
		setX(originalX * tm[0] + originalY * tm[4] + originalZ * tm[8] + tm[12]);
		setY(originalX * tm[1] + originalY * tm[5] + originalZ * tm[9] + tm[13]);
		setZ(originalX * tm[2] + originalY * tm[6] + originalZ * tm[10] + tm[14]);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double getOriginalX() {
		return originalX;
	}
	
	public double getOriginalY() {
		return originalY;
	}
	
	public double getOriginalZ() {
		return originalZ;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
}
