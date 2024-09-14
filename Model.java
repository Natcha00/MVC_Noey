public abstract class Model {
    private String id;
    private int ageYears;
    private int ageMonths;
    private int milkBottles;
    protected boolean hasBSOD;

    public Model(String id, int ageYears, int ageMonths) {
        this.id = id;
        this.ageYears = ageYears;
        this.ageMonths = ageMonths;
        this.milkBottles = 0;
        this.hasBSOD = false;
    }

    public String getId() {
        return id;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public int getMilkBottles() {
        return milkBottles;
    }

    public boolean hasBSOD() {
        return hasBSOD;
    }

    public void milk() {
        milkBottles++;
    }

    public void resetBSOD() {
        hasBSOD = false;
    }

    public abstract String getBreed();
    //pin
    public abstract String getMilkDefect();

}
