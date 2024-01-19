package data;

import model.Fruit;
import model.Order;
import view.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileManager {
    private final String fruitPath;
    private final String orderPath;

    File fruitFile;
    File orderFile;
    public FileManager() {
        fruitFile = new File("C:\\Users\\This PC\\IdeaProjects\\Lab211_FruitShop\\src\\main\\java\\data\\fruit");
        fruitPath = fruitFile.getAbsolutePath();
        orderFile = new File("C:\\Users\\This PC\\IdeaProjects\\Lab211_FruitShop\\src\\main\\java\\data\\order");
        orderPath = orderFile.getAbsolutePath();
    }

    public ArrayList<Fruit> readFruitsFromFile() {
        String line;
        ArrayList<Fruit> fruits = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fruitPath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                fruits.add(new Fruit(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]), Integer.parseInt(values[3]), values[4]));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        return fruits;
    }
    //write fruit into file
    public void writeFruitsIntoFile(ArrayList<Fruit> fruits) {
        try (PrintWriter pr = new PrintWriter(fruitPath);) {
            for (Fruit fruit: fruits) {
                pr.println(fruit.toFileString());
            }
            pr.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //read order from File

    public void writeOrderIntoFile(ArrayList<Order> orders) {
        try (PrintWriter pr = new PrintWriter(orderPath);) {
            for (Order order : orders) {
                pr.println(order.toFileString());
            }
            pr.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
