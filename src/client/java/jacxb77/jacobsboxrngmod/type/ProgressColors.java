package jacxb77.jacobsboxrngmod.type;

public enum ProgressColors {
    WHITE(0xFFFFFF),
    GREEN(0x55FF55),
    YELLOW(0xFFFF55),
    ORANGE(0xFF6600),
    RED(0xFF5555),
    DARK_RED(0xAA0000);

    public final int color;

    private ProgressColors(int color) {
        this.color = color;
    }
}
