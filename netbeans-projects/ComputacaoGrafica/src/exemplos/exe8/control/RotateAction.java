package exemplos.exe8.control;

import exemplos.exe8.model.GraphicalObject;
import javax.media.opengl.GL;



/**
 * Executa a ação de rotação em um objeto.
 * 
 * @author Thiago.Gesser
 */
public final class RotateAction {
    private final GraphicalObject obj;
    private boolean isActive;
    private boolean finishOnEnd;
    
    private final int iterationTimes;
    private int curIterationTimes;
    private final double iterationAmount;
    private final double x;
    private final double y;
    private final double z;
    
    public RotateAction(GraphicalObject obj, double endAngle, int iterationTimes, double x, double y, double z) {
        this.obj = obj;
        this.iterationTimes = iterationTimes;
        this.curIterationTimes = iterationTimes;
        this.iterationAmount = endAngle / iterationTimes;
        this.x = x;
        this.y = y;
        this.z = z;
        this.isActive = true;
    }
    
    public boolean isFinished() {
        if (finishOnEnd) {
            return curIterationTimes == 0;
        }
        
        return curIterationTimes == -iterationTimes;
    }
    
    public void finishOnEnd() {
        this.finishOnEnd = true;
    }
    
    public void nextStep(GL gl) {
        if (isFinished()) {
            throw new IllegalStateException();
        }

        double curAmount;
        if (curIterationTimes-- > 0) {
            curAmount = iterationAmount;
        } else {
            curAmount = -iterationAmount;
        }
        
        obj.rotate(gl, curAmount, x, y, z);
    }
    
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public boolean isActive() {
        return isActive;
    }
}
