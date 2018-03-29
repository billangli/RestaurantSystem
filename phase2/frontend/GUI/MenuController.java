package frontend.GUI;

import backend.inventory.*;
import backend.server.Packet;
import backend.table.Order;
import frontend.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * the controller for menu GUI
 */
public class MenuController{
    @FXML
    GridPane tableView = new GridPane();
    private Stage window;
    private final Client client = Client.getInstance();
    private int numoforder = 0;
    volatile HashMap<String, InventoryIngredient> defaultInventory = (HashMap<String, InventoryIngredient>) client.sendRequest(Packet.REQUESTINVENTORY); //TODO should get menu from web ComputerServer requestMenu()
    private final Inventory inventory = Inventory.getInstance();
    private ArrayList<Dish> recipe = new ArrayList<>();
    private int tableNumber;

    volatile HashMap<String, DishRecipe> menuDishes = (HashMap<String, DishRecipe>) client.sendRequest(Packet.REQUESTMENU);
    private final Menu menu = Menu.getMenu();

    private int myId;

    public void setMyId(int id) {
        this.myId = id;
    }
    private Order dishOrder;
    private HashMap<String,Dish> order;

    /**
     * set up the table number for this order
     * @param tableNumber the table that ordering
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     *  set up the stage that is display GUI
     * @param stage the stage that is display GUI
     */
    public void setStage(Stage stage){
        this.window = stage;
    }

    /**
     *  update any difference in local inventory and back end inventory
     * @param newDisplayQuantity the quantity that changed
     */
    public void updateRunningQuantity(HashMap newDisplayQuantity) {
        for(Object i: newDisplayQuantity.keySet()){
            String ingredientName = (String) i;
            int runningQuantity = (int) newDisplayQuantity.get(i);
            InventoryIngredient inventoryIngredient = inventory.getIngredient(ingredientName);
            inventoryIngredient.setRunningQuantity(runningQuantity);
        }
        updateMenu();
    }

    /**
     * update the menu, grey out dish that are unavailable
     */
    public void updateMenu() {
        for(Dish dish:recipe){
            boolean cookable = dish.ableToCook(inventory);
            Button tb = (Button) tableView.lookup("#"+dish.getName());
            if(cookable){
                tb.setDisable(false);
            }
            else{
                tb.setDisable(true);
            }
        }
        try{
            window.showAndWait();
        }catch(IllegalStateException e) { }
    }


    /**
     * initialize the GUI
     */
    public void initialize(){
        menu.setDishes(menuDishes);
        inventory.setStock(defaultInventory);
        HashMap<String,DishRecipe> dishes = menu.getDishes();

        dishOrder = new Order();
        order = new HashMap<>();

        //set up dishes
        int i = 0;
        for(String di: dishes.keySet()){
            Button item = new Button(di);
            item.setId(di);
            item.setOnAction(new EventHandler<>() {
                //order a dish
                //TODO update inventory and order updateMenu()
                @Override
                public void handle(ActionEvent e) {
                    Button ordered = new Button(di);
                    ordered.setId("" + numoforder);

                    //set up the ingredient adjustment interface
                    Stage st = new Stage();
                    Dish dish = new Dish(dishes.get(di));

                    //pass ingredient to server
                    HashMap<String, DishIngredient> ingredients = dish.getIngredientsRequired();
                    ArrayList<DishIngredient> dishIngredients = new ArrayList<>();
                    for (String in : ingredients.keySet()) {
                        dishIngredients.add(ingredients.get(in));
                    }
                    client.sendAdjustIngredientRequest(dishIngredients, true);

                    order.put("" + numoforder, dish);
                    FXMLLoader ingredientLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/Ingredient.fxml"));
                    try {
                        GridPane ingredient = ingredientLoader.load();
                        IngredientController controller = ingredientLoader.getController();
                        controller.getDish(dish);
                        Scene ingredientScene = new Scene(ingredient, 400, 400);
                        st.setScene(ingredientScene);
                        st.show();
                    } catch (IOException e1) {
                        System.out.println(ingredientLoader);
                    }


                    //delete a dish
//TODO update order and inventory updateMenu()
                    ordered.setOnAction(e12 -> {
                        tableView.getChildren().remove(ordered);
                        order.remove(ordered.getId());
                        HashMap<String, DishIngredient> ingredients1 = dish.getIngredientsRequired();
                        ArrayList<DishIngredient> dishIngredients1 = new ArrayList<>();
                        for (String in : ingredients1.keySet()) {
                            dishIngredients1.add(ingredients1.get(in));
                        }
                        client.sendAdjustIngredientRequest(dishIngredients1, false);
                    });

                    numoforder++;
                    tableView.add(ordered, 2, numoforder + 1);
                }
            });

            tableView.add(item,0,i);
            i++;


        }
        Text orderText = new Text("your order");
        tableView.add(orderText,2,0);
        //submit the order
        Button submit = new Button("order");
        submit.setOnAction(e -> {
            for (String item : order.keySet()) {
                Dish dish = order.get(item);
                dishOrder.addDish(dish);
                Button tb = (Button) tableView.lookup("#" + item);
                tableView.getChildren().remove(tb);
            }
            ArrayList<Object> info = new ArrayList<>();
            info.add(tableNumber);
            info.add(dishOrder);
            client.sendEvent(Packet.ENTERMENU, info);

            numoforder = 0;
            dishOrder = new Order();
            order = new HashMap<>();


            ((Stage) submit.getScene().getWindow()).close();
        });
        tableView.add(submit,1,5);

        //set up background
        BackgroundImage menuImage= new BackgroundImage(new Image("menu.jpg",600,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background= new Background(menuImage);
        tableView.setBackground(background);


        for(String di: dishes.keySet()){
            recipe.add(new Dish(dishes.get(di)));
        }
        for(Dish dish:recipe){
            boolean cookable = dish.ableToCook(inventory);
            Button tb = (Button) tableView.lookup("#"+dish.getName());
            if(cookable){
                tb.setDisable(false);
            }
            else{
                tb.setDisable(true);
            }
        }
    }


}
