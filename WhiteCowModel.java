public class WhiteCowModel extends Model {
    private int sourMilkBottles;
    public WhiteCowModel(String id, int ageYears, int ageMonths) {
        super(id, ageYears, ageMonths);
        this.sourMilkBottles = 0;
    }

    @Override
    public String getBreed() {
        if(this.hasBSOD){
            return "blue";
        }
        return "white";
    }

    // Method to add lemon and produce sour milk
    public void addLemon() {
        if (!hasBSOD()) {
            sourMilkBottles++;
        }
    }

    @Override
    public void milk() {
        if (hasBSOD()) {
            System.out.println("White cow produced Soy Milk (must discard)");
            return;
        }

        // Normal milking logic
        super.milk();

        // Add logic to handle potential BSOD case
        if (Math.random() < 0.005*getAgeMonths()) { // 0.5% chance of BSOD
            System.out.println("White cow has BSOD.");
            // Set BSOD status
            super.hasBSOD = true;
        }
    }

    public int getSourMilkBottles() {
        return sourMilkBottles;
    }

    //pin
    @Override
    public String getMilkDefect(){
        return "Soy Milk";
    }
}
