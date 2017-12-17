package n4;

import com.sun.opengl.util.GLUT;
import java.util.ArrayList;
import java.util.List;
import javax.media.opengl.GL;

public class ObjetoGrafico {

    private float[] rgb;
    private Transform matrix;
    private int tipoObjeto;
    private List<Posicoes> posicoes;

    public ObjetoGrafico() {
        rgb = new float[3];
        matrix = new Transform();
        posicoes = new ArrayList<>();
    }

    public Transform getMatrix() {
        return matrix;
    }

    public void setMatrix(Transform matrix) {
        this.matrix = matrix;
    }

    public void setRed() {
        this.rgb[0] = 1;
        this.rgb[1] = 0;
        this.rgb[2] = 0;
    }

    public void setGreen() {
        this.rgb[0] = 0;
        this.rgb[1] = 1;
        this.rgb[2] = 0;
    }

    public void setBlue() {
        this.rgb[0] = 0;
        this.rgb[1] = 0;
        this.rgb[2] = 1;
    }

    public void setCian() {
        this.rgb[0] = 0;
        this.rgb[1] = 1;
        this.rgb[2] = 1;
    }

    public void setYellow() {
        this.rgb[0] = 1;
        this.rgb[1] = 1;
        this.rgb[2] = 0;
    }

    public void addPosicoes(Posicoes posicoes) {
        this.posicoes.add(posicoes);
    }

    public List<Posicoes> getPosicoes() {
        return posicoes;
    }

    public int getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(int tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public void desenhaCubo(GL gl, GLUT glut) {
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, rgb, 0);
        gl.glEnable(GL.GL_LIGHTING);

        gl.glPushMatrix();
        gl.glMultMatrixd(matrix.getDate(), 0);
        glut.glutSolidCube(1.0f);
        gl.glPopMatrix();
        gl.glDisable(GL.GL_LIGHTING);
    }

    public void desenhaT(GL gl, GLUT glut) {
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, rgb, 0);
        gl.glEnable(GL.GL_LIGHTING);

        gl.glPushMatrix();
        gl.glMultMatrixd(matrix.getDate(), 0);
        glut.glutSolidCube(1.0f);

        gl.glTranslatef(1, 0, 0);
        glut.glutSolidCube(1.0f);

        gl.glTranslatef(-2, 0, 0);
        glut.glutSolidCube(1.0f);

        gl.glTranslatef(1, 0, -1);
        glut.glutSolidCube(1.0f);

        gl.glPopMatrix();
        gl.glDisable(GL.GL_LIGHTING);
    }

    public void desenhaI(GL gl, GLUT glut) {
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, rgb, 0);
        gl.glEnable(GL.GL_LIGHTING);

        gl.glPushMatrix();
        gl.glMultMatrixd(matrix.getDate(), 0);
        glut.glutSolidCube(1.0f);

        gl.glTranslatef(0, 0, -1);
        glut.glutSolidCube(1.0f);

        gl.glTranslatef(0, 0, -1);
        glut.glutSolidCube(1.0f);

        gl.glPopMatrix();
        gl.glDisable(GL.GL_LIGHTING);
    }
}
