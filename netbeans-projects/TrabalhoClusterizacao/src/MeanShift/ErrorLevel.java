package MeanShift;

public class ErrorLevel {

    private final String name;

    private ErrorLevel(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public static final ErrorLevel EL_OKAY = new ErrorLevel("okay");
    public static final ErrorLevel EL_ERROR = new ErrorLevel("error");
    public static final ErrorLevel EL_HALT = new ErrorLevel("halt");
}
