package model;

import java.util.HashMap;

public class Order {
    private String customerName;
    private HashMap<Fruit, Integer> orders;

    public Order(String customerName, HashMap<Fruit, Integer> customerorder) {
        this.customerName = customerName;
        this.orders = customerorder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<Fruit, Integer> getCustomerorder() {
        return orders;
    }

    public void setCustomerorder(HashMap<Fruit, Integer> customerorder) {
        this.orders = customerorder;
    }

    @Override
    public String toString() {
        return "customerName: " + customerName + "       customerorder: " + orders;
    }
    //displayOrderItem
    public static void displayOrderedItem(HashMap<Fruit, Integer> order) {
        System.out.println(String.format("| %-10s | %-10s | %-10s | %-10s |", "Product", "Quantity", "Price", "Amount"));
        double total = 0.0;
        int count = 0;
        for (Fruit key : order.keySet()) {
            double price = key.getPrice();
            int quantity = order.get(key);
            double amount = price * quantity;
            total += amount;
            System.out.println("|" + ++count + String.format(". %-10s | %-10s | %-10s | %-10s |", key.getName(), quantity, price, amount));
        }
        System.out.println("Total: " +  total + "$");
    }

    //to file strin
    public String toFileString() {
        StringBuilder fileString = new StringBuilder("Customer: " + this.getCustomerName() + "\nOrder:\n");
        for (Fruit fruit : orders.keySet()) {
            fileString.append("- ").append(fruit.getName()).append(": ").append(orders.get(fruit)).append("\n");
        }
        return fileString.toString();
    }
}