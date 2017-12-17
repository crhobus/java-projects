package n3;

public class Transform {

    public static final double RAS_DEG_TO_RAD = 0.017453292519943295769236907684886;
    private double[] matrix = {1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 1};

    public Transform() {
    }

    public void makeIdentity() {
        for (int i = 0; i < 16; ++i) {
            matrix[i] = 0.0;
            matrix[0] = matrix[5] = matrix[10] = matrix[15] = 1.0;
        }
    }

    public void makeTranslation(Ponto translationVector) {
        makeIdentity();
        matrix[12] = translationVector.getX();
        matrix[13] = translationVector.getY();
        matrix[14] = translationVector.getZ();
    }

    public void makeXRotation(double radians) {
        makeIdentity();
        matrix[5] = Math.cos(radians);
        matrix[9] = -Math.sin(radians);
        matrix[6] = Math.sin(radians);
        matrix[10] = Math.cos(radians);
    }

    public void makeYRotation(double radians) {
        makeIdentity();
        matrix[0] = Math.cos(radians);
        matrix[8] = Math.sin(radians);
        matrix[2] = -Math.sin(radians);
        matrix[10] = Math.cos(radians);
    }

    public void makeZRotation(double radians) {
        makeIdentity();
        matrix[0] = Math.cos(radians);
        matrix[4] = -Math.sin(radians);
        matrix[1] = Math.sin(radians);
        matrix[5] = Math.cos(radians);
    }

    public void makeScale(double sX, double sY, double sZ) {
        makeIdentity();
        matrix[0] = sX;
        matrix[5] = sY;
        matrix[10] = sZ;
    }

    public Ponto transformPoint(Ponto ponto) {
        Ponto pointResult = new Ponto(
                matrix[0] * ponto.getX() + matrix[4] * ponto.getY() + matrix[8] * ponto.getZ() + matrix[12] * ponto.getW(),
                matrix[1] * ponto.getX() + matrix[5] * ponto.getY() + matrix[9] * ponto.getZ() + matrix[13] * ponto.getW(),
                matrix[2] * ponto.getX() + matrix[6] * ponto.getY() + matrix[10] * ponto.getZ() + matrix[14] * ponto.getW(),
                matrix[3] * ponto.getX() + matrix[7] * ponto.getY() + matrix[11] * ponto.getZ() + matrix[15] * ponto.getW());
        return pointResult;
    }

    public Transform transformMatrix(Transform t) {
        Transform result = new Transform();
        for (int i = 0; i < 16; ++i) {
            result.matrix[i] =
                    matrix[i % 4] * t.matrix[i / 4 * 4] + matrix[(i % 4) + 4] * t.matrix[i / 4 * 4 + 1]
                    + matrix[(i % 4) + 8] * t.matrix[i / 4 * 4 + 2] + matrix[(i % 4) + 12] * t.matrix[i / 4 * 4 + 3];
        }
        return result;
    }

    public double getElement(int index) {
        return matrix[index];
    }

    public void setElement(int index, double value) {
        matrix[index] = value;
    }

    public double[] getDate() {
        return matrix;
    }

    public void setData(double[] data) {
        for (int i = 0; i < 16; i++) {
            matrix[i] = (data[i]);
        }
    }
}