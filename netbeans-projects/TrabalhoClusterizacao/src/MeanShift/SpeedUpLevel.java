package MeanShift;

public class SpeedUpLevel {

    private final String name;

    private SpeedUpLevel(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public static final SpeedUpLevel NO_SPEEDUP = new SpeedUpLevel("no speedup");
    public static final SpeedUpLevel MED_SPEEDUP = new SpeedUpLevel("medium speedup");
    public static final SpeedUpLevel HIGH_SPEEDUP = new SpeedUpLevel("high speedup");
} // end SpeedUpLevel class

