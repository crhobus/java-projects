package MeanShift;

public class ImageType {

    private final String name;

    private ImageType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public static final ImageType GRAYSCALE = new ImageType("grayscale");
    public static final ImageType COLOR = new ImageType("color");
}
