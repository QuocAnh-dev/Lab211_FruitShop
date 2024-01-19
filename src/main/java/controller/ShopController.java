package controller;

import data.FileManager;
import model.Fruit;
import model.FruitManager;
import model.Order;
import model.OrderManager;
import view.Inputter;
import view.Menu;

import java.util.*;
import java.util.function.Predicate;
;

public class ShopController extends Menu {
    private final FruitManager fm = new FruitManager();
    private final OrderManager om = new OrderManager();
    private final FileManager file = new FileManager();
    private static final String title = "FRUIT SHOP SYSTEM.";
    private static final List<String> services = new ArrayList<String>(Arrays.asList("Add new Fruit", "View orders", "Shopping(for buyer),", "Exit"));
    public ShopController() {
        super(title, services);
    }
    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                System.out.println("ADD NEW FRUIT");
                fm.addFruit();
                break;
            case 2:
                System.out.println("VIEW ORDERS");
                om.displayOrders();
                break;
            case 3:
                System.out.println("SHOPPING(FOR BUYER)");
                takeOrder();
                break;
            case 4:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice! Enter again.");
        }
    }
    public boolean checkOrderName(String name){
        Fruit fruit = takeOrderedFruit(name);
        if(fruit == null){
            System.out.println("The fruit you entered does not exist!");
            return false;
        }
        if(fruit.getQuantity() == 0){
            System.out.println("Sold out!");
//            fm.fruitList.remove(fruit);
//            file.writeFruitsIntoFile(fm.fruitList);
            return false;
        }
        return true;
    }
    public Fruit takeOrderedFruit(String name){
        Predicate<Fruit> searchByName= f -> (f.getName().equalsIgnoreCase(name));
        Fruit result = fm.searchByPredicate(searchByName);
        return result;
    }
    public void takeOrder(){
        HashMap<Fruit, Integer> customerOrders = new HashMap<>();
        String choice = "N";
        boolean continueShopping = true;
        while (choice.equalsIgnoreCase("N") && continueShopping){
            displayMenu();
            System.out.println("Let's order");
            String f = Inputter.getStringFromInput("fruit name");
            if(checkOrderName(f)){
                Fruit orderedFruit = takeOrderedFruit(f);
                //get quantity from user
                int quantity = Inputter.getIntFromInput("quantity");
                //validate quantity
                Inputter.validateOrderQuantity(orderedFruit, quantity);
                //update file
                file.writeFruitsIntoFile(fm.fruitList);
                updateItemInOrderList(customerOrders, orderedFruit, quantity);
                //ask user to complete order
                System.out.println("Successfully!");
                System.out.println("Do you want to order now(Y/N)?");
                choice = Inputter.getStringFromInput("choice");
                if(choice.equalsIgnoreCase("Y")){
                    //display ordered items
                    Order.displayOrderedItem(customerOrders);
                    //take user's name
                    String name = Inputter.getStringFromInput("name");
                    //add current order into order list
                    Order currentOrder = new Order(name, customerOrders);
                    om.addNewOrder(currentOrder);
                    //write order into File
                    file.writeOrderIntoFile(om.orderList);
                    continueShopping = false;
//                    break;
                }
            }else{
//                continueShopping = true;
                System.out.println("Please choose another fruit.");
            }
        }
    }
    //display menu for shopping
    public void displayMenu(){
        fm.fruitList = file.readFruitsFromFile();
        System.out.println(String.format("|%-10s | %-10s | %-10s | %-10s |", "Item", "Fruit Name", "Origin", "Price"));
        for(int i = 0; i < fm.fruitList.size(); i++){
            System.out.println(String.format("|%-10s | %-10s | %-10s | %-10s |", i + 1, fm.fruitList.get(i).getName(), fm.fruitList.get(i).getOrigin(), fm.fruitList.get(i).getPrice() + "$"));
        }

    }
    //check if item is already in order list
    public void updateItemInOrderList(HashMap<Fruit, Integer> customerOrders, Fruit orderedFruit, int quantity) {
        if (customerOrders.containsKey(orderedFruit)) {
            int currentQuantity = customerOrders.get(orderedFruit);
            customerOrders.put(orderedFruit, currentQuantity + quantity);
        } else {
            customerOrders.put(orderedFruit, quantity);
        }
    }
}
