import java.util.HashMap;
import java.util.Map;

// Controller Class
public class Controller {
    private Map<String, Model> cowDatabase;

    public Controller() {
        cowDatabase = new HashMap<>();
        initializeDatabase();
    }

    // Initialize database with sample cows
    private void initializeDatabase() {
        cowDatabase.put("12345678", new WhiteCowModel("12345678", 5, 6));
        cowDatabase.put("23456789", new BrownCowModel("23456789", 3, 4));
        cowDatabase.put("34567890", new WhiteCowModel("34567890", 2, 3));
        cowDatabase.put("45678901", new BrownCowModel("45678901", 4, 5));
        cowDatabase.put("56789012", new WhiteCowModel("56789012", 1, 8));
        cowDatabase.put("67890123", new BrownCowModel("67890123", 6, 0));
        cowDatabase.put("78901234", new WhiteCowModel("78901234", 7, 9));
        cowDatabase.put("89012345", new BrownCowModel("89012345", 0, 2));
        cowDatabase.put("90123456", new WhiteCowModel("90123456", 3, 1));
        cowDatabase.put("91234567", new BrownCowModel("91234567", 9, 7));
    }

    // Get cow by ID
    public Model getCow(String id) {
        if (id == null || id.length() != 8 || id.startsWith("0")) {
            return null;
        }
        return cowDatabase.get(id);
    }

    // Milk the cow and handle BSOD cases
    public void milkCow(String id) {
        Model cow = getCow(id);
        if (cow != null) {
            if (cow.hasBSOD()) {
                if (cow.getBreed().equals("white")) {
                    System.out.println("White cow produced Soy Milk (must discard)");
                } else if (cow.getBreed().equals("brown")) {
                    System.out.println("Brown cow produced Almond Milk (must discard)");
                }
            } else {
                cow.milk();
            }
        }
    }

    // Reset BSOD for the cow
    public void resetCow(String id) {
        Model cow = getCow(id);
        if (cow != null) {
            cow.resetBSOD();
        }
    }

    // Add lemon to white cows only
    public void addLemonToCow(String id) {
        Model cow = getCow(id);
        if (cow instanceof WhiteCowModel && !cow.hasBSOD()) {
            ((WhiteCowModel) cow).addLemon();
        }
    }

    // Generate summary report
    public String getSummaryReport() {
        int whiteMilkCount = 0;
        int sourMilkCount = 0;
        int chocolateMilkCount = 0;
        int totalBottles = 0;
        StringBuilder individualReport = new StringBuilder();

        for (Model cow : cowDatabase.values()) {
            //pin
            // if (cow.hasBSOD()) {
                //ต้องนับวัวทุกตัวที่ผลิตนมให้
               // continue;
            //}
            if (cow instanceof WhiteCowModel) {
                WhiteCowModel whiteCow = (WhiteCowModel) cow;
                whiteMilkCount += whiteCow.getMilkBottles();
                sourMilkCount += whiteCow.getSourMilkBottles();
                //pin
                //รวมนมเปรี้ยวด้วย
                totalBottles +=whiteCow.getSourMilkBottles();
                individualReport.append("Cow ID: ").append(cow.getId())
                    .append(", Milk Bottles: ").append(cow.getMilkBottles()+whiteCow.getSourMilkBottles())
                    .append("\n");
            } else if (cow instanceof BrownCowModel) {
                BrownCowModel brownCow = (BrownCowModel) cow;
                chocolateMilkCount += brownCow.getMilkBottles();
                individualReport.append("Cow ID: ").append(cow.getId())
                    .append(", Milk Bottles: ").append(cow.getMilkBottles())
                    .append("\n");
            }
            totalBottles += cow.getMilkBottles();
            
        }

        return individualReport.toString() +
               "\nSummary Report:\n" +
               "White Milk Bottles: " + whiteMilkCount + "\n" +
               "Sour Milk Bottles: " + sourMilkCount + "\n" +
               "Chocolate Milk Bottles: " + chocolateMilkCount + "\n" +
               "Total Valid Milk Bottles: " + totalBottles;
    }
}
