package MeanShift;

public class KernelType {

    private final String name;

    private KernelType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public static final KernelType UNIFORM = new KernelType("uniform");
    public static final KernelType GAUSSIAN = new KernelType("gaussian");
    public static final KernelType USER_DEFINED = new KernelType("user defined");
}