package jacxb77.jacobsboxrngmod.model;

public class BossProgressTextModel {
    String bossType;
    int progress;
    int total;
    int color;
    boolean isInRange;

    public BossProgressTextModel(String bossType, int progress, int total, int color, boolean isInRange) {
        this.bossType = bossType;
        this.progress = progress;
        this.total = total;
        this.color = color;
        this.isInRange = isInRange;
    }

    public void setProgress(int progress) { this.progress = progress; }
    public void setTotal(int total) { this.total = total; }
    public void setColor(int color) { this.color = color; }
    public void setInPlayersRange(boolean isInRange) { this.isInRange = isInRange; }

    public int getColor() { return this.color; }

    // Builds and returns a string in format ex. "Zombie: 25/250"
    // If a hologram is out of players render range then its: "Zombie: 25/250 (OOR)"
    public String getBossProgressText() {
        if(this.isInRange) {
            return this.bossType + ": " + this.progress + "/" + this.total;
        } else {
            return this.bossType + ": " + this.progress + "/" + this.total + " (OOR)";
        }
    }
}
