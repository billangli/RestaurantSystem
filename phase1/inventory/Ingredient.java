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

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }


    public String getName(){
        return this.name;
    }

    public boolean allowed(int n){
        if(n> thresholdQuanity[0] && n < thresholdQuanity[1]){
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


}
