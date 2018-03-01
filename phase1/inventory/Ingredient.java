package inventory;

public class Ingredient {
    private String name;
    private int quantity;
    private int[] thresholdQuanity;
//    private boolean lowStock; //not needed

    public Ingredient(String name, int quantity, int[] thresholdQuanity) {
        this.name = name;
        this.quantity = quantity;
        this.thresholdQuanity = thresholdQuanity;
    }

    public boolean allowed(int n, Ingredient in){
        if(n> thresholdQuanity[0] && n < thresholdQuanity[1]&& n<= in.getQuantity()){
            return true;
        }
        return false;
    }

    public boolean isLowStock() {
        if (this.quantity < this.thresholdQuanity[0]) {
            return true;
        } else {
            return false;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void adjust(int n){
        quantity += n;
    }
}
