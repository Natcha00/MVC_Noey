public class BrownCowModel extends Model {
    public BrownCowModel(String id, int ageYears, int ageMonths) {
        super(id, ageYears, ageMonths);
    }

    @Override
    public String getBreed() {
        if(this.hasBSOD){
            return "blue";
        }
        return "brown";
    }

    @Override
    public void milk() {
        if (hasBSOD()) {
            System.out.println("Brown cow produced Almond Milk (must discard)");
            return;
        }

        // Normal milking logic
        super.milk();

        // Add logic to handle potential BSOD case
        if (Math.random() < 0.01 * getAgeYears()) { // 1% chance multiplied by age in years
            System.out.println("Brown cow has BSOD.");
            // Set BSOD status
            super.hasBSOD = true;
        }
    }
    //pin
    @Override
    public String getMilkDefect(){
        return "Almond Milk";
    }
}
