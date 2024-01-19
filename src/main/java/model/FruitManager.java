package model;

import data.FileManager;
import view.Inputter;
import java.util.*;
import java.util.function.Predicate;

public class FruitManager {
    public ArrayList<Fruit> fruitList;
    FileManager file;

    public FruitManager() {
        this.fruitList = new ArrayList<>();
        this.file = new FileManager();
    }
    public void addFruit() {
        int id = Inputter.getIntFromInput("Fruit ID");
        String name = Inputter.getStringFromInput("Fruit Name");
        String origin = Inputter.getStringFromInput("Origin");
        double price = Inputter.getDoubleFromInput("Price");
        int quantity = Inputter.getIntFromInput("Quantity");
        fruitList = file.readFruitsFromFile();
        //check if its already exist
        Predicate<Fruit> byName = fruit1 -> (fruit1.getName().equalsIgnoreCase(name));
        Predicate<Fruit> byId = fruit2 -> (fruit2.getId() == id);
        if(isExist(byName, byId)){
            //if it already existed
            Fruit existingfruit = searchByPredicate(byName);
            updateQuantity(existingfruit, existingfruit.getQuantity() + quantity);
        }else{
            //if its not exist in stock yet
            fruitList.add(new Fruit(id, name, price, quantity, origin));
        }
        file.writeFruitsIntoFile(fruitList);
    }
    public boolean isEmptyProduct(){
        if(fruitList.isEmpty()) return true;
        return false;
    }
    public void showFruit(ArrayList<Fruit> fruits){
        if(fruitList.isEmpty()){
            System.out.println("The shop has no fruit yet.");
            return;
        }
        for(int i = 0; i < fruits.size(); i++){
            System.out.println(i + 1 + "        " + fruits.get(i));
        }
    }
    public void updateQuantity(Fruit fruit, int quantity){
        fruit.setQuantity(quantity);
    }
    public Fruit searchByPredicate(Predicate p){
        Fruit result = new Fruit();
        for(Fruit f : fruitList){
            if(p.test(f)) {
                result = f;
                break;
            }
        }
        return result;
    }
    //check if its already on stock
    public boolean isExist(Predicate<Fruit> byName, Predicate<Fruit> byId){
        if(searchByPredicate(byName) != null && searchByPredicate(byId) != null){
            return true;
        }
        return false;
    }

//    public void getFromFile(){
//        String emp = "";
//        try (FileReader fr = new FileReader(f.fruitPath);
//             BufferedReader br = new BufferedReader(fr)) {
//            while ((emp = br.readLine()) != null){
//                String[] infor = emp.split(";");
//                fruitList.add(new Fruit(Integer.parseInt(infor[0]), infor[1], Double.parseDouble(infor[2]), Integer.parseInt(infor[3]), infor[4]));
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(FruitManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //test
//    public static void main(String[] args) {
//        List<String> services = Arrays.asList("add", "show", "search", "update", "exit");
//        Menu menu = new Menu("Fruit Menu", services) {
//            @Override
//            public void execute(int choice) {
//                switch (choice){
//                    case 1:
//                        System.out.println("ADD: ");
//                        addFruit();
//                        break;
//                    case 2:
//                        System.out.println("SHOW: ");
//                        showFruit(fruitList);
//                        break;
//                    case 3:
//                        System.out.println("SEARCH: ");
//                        Predicate<Fruit> bycost = fruit -> fruit.getPrice() < 1.3;
//                        ArrayList<Fruit> ans = searchByPredicate(bycost);
//                        showFruit(ans);
//                        break;
//                    case 4:
//                        System.out.println("UPDATE QUANTITY: ");
//                        //
//                        break;
//                    case 5:
//                        System.out.println("goodbye");
//                        return;
//                }
//
//            }
//        };
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Nhap lua chon: ");
//        int choice = sc.nextInt();
//        menu.execute(choice);
//    }
}
