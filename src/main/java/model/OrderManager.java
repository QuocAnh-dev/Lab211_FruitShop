package model;

import data.FileManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderManager {
    public ArrayList<Order> orderList;
    private FileManager file = new FileManager();
    public OrderManager() {
        orderList = new ArrayList<>();
    }
    FileManager fm = new FileManager();

    public void displayOrders() {
//        fm.readOrderFile();
        for (Order order : orderList) {
            System.out.println("Customer: " + order.getCustomerName());
            Order.displayOrderedItem(order.getCustomerorder());
            System.out.println("");
        }
        System.out.println("Total order: " + orderList.size());
    }

    public void addNewOrder(Order order) {
        orderList.add(order);
    }
    //display order item for customer
    public void showOrders(HashMap<Fruit, Integer> customerOrders){
        System.out.println(String.format("|%-10s | %-10s | %-10s | %-10s |", "Product", "Quantity", "Price", "Amount"));
        for(Fruit fruit : customerOrders.keySet()){
            double amount = customerOrders.get(fruit) * fruit.getPrice();
            System.out.println(String.format("|%-10s | %-10s | %-10s | %-10s |", fruit.getName(), fruit.getQuantity(), fruit.getPrice(), amount));
        }
    }
}
