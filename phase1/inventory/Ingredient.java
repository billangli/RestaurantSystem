package inventory;

public class Ingredient {
    private String name;
    private int quantity;
    private int[] thresholdQuantity;
//    private boolean lowStock; //not needed

    public Ingredient(String name, int quantity, int[] thresholdQuantity) {
        this.name = name;
        this.quantity = quantity;
        this.thresholdQuantity = thresholdQuantity;
    }

    public int getQuantity(){
        return this.quantity;
    }


    public String getName(){
        return this.name;
    }

    public boolean allowed(int n, Ingredient in){
        if(n> thresholdQuantity[0] && n < thresholdQuantity[1] && in.getQuantity() >= n){
            return true;
        }
        return false;
    }

    public boolean isLowStock() {
        if (this.quantity < this.thresholdQuantity[0]) {
            return true;
        } else {
            return false;
        }
    }


    public void adjust(int n){
        quantity += n;
    }



}
