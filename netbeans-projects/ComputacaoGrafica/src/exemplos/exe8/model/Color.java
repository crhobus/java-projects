package exemplos.exe8.model;


/**
 * Define uma cor de openGL.
 * 
 * @author Thiago.Gesser
 */
public final class Color {
    
    private final double red;
    private final double green;
    private final double blue;
    private final double alpha;
    
    public Color() {
        this(0, 0, 0);
    }
    
    public Color(double red, double green, double blue) {
        this(red, green, blue, 1);
    }
    
    public Color(double red, double green, double blue, double alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
    
    public static final Color fromAWTColor(java.awt.Color color) {
    	return new Color((double) color.getRed()/255, (double)  color.getGreen()/255, (double) color.getBlue()/255, (double) color.getAlpha()/255);
    }
    
    public java.awt.Color toAWTColor() {
    	return new java.awt.Color((float) getRed(), (float) getGreen(), (float) getBlue(), (float) getAlpha());
    }
    
    public double getRed() {
        return red;
    }
    
    
    public double getAlpha() {
        return alpha;
    }
    
    
    public double getBlue() {
        return blue;
    }
    
    
    public double getGreen() {
        return green;
    }
}
