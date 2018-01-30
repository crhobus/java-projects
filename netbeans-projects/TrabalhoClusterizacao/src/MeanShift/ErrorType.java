package MeanShift;

public class ErrorType {

    private final String name;

    private ErrorType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public static final ErrorType NONFATAL = new ErrorType("nonfatal");
    public static final ErrorType FATAL = new ErrorType("fatal");
}