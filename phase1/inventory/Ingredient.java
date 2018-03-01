package inventory;

public class Ingredient {
    private String name;
    private int quantity;
    private int thresholdQuanity;
//    private boolean lowStock; //not needed

    public Ingredient(String name, int quantity, int thresholdQuanity) {
        this.name = name;
        this.quantity = quantity;
        this.thresholdQuanity = thresholdQuanity;
    }

    public boolean isLowStock() {
        if (this.quantity < this.thresholdQuanity) {
            return true;
        } else {
            return false;
        }
    }


}
