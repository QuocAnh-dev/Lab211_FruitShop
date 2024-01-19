package view;

import model.Fruit;
import model.FruitManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputter {

    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    static FruitManager fm = new FruitManager();

    public static double getDoubleFromInput(String name) {
        Scanner sc = new Scanner(System.in);
        double number;
        while (true) {
            System.out.print(String.format("Enter %s: ", name));
            try {
                number = sc.nextDouble();
                if (number > 0) {
                    return number;
                } else {
                    System.err.println("Please enter the double number > 0 ");
                }
            } catch (InputMismatchException e) {
                System.err.println("Only enter the double number > 0 ");
                sc.next();
            }
        }
    }

    public static int getIntFromInput(String name) {
        Scanner sc = new Scanner(System.in);
        int number;
        while (true) {
            System.out.print(String.format("Enter %s: ", name));
            try {
                number = sc.nextInt();
                if (number > 0) {
                    return number;
                } else {
                    System.err.println("Please enter the integer number > 0 ");
                }
            } catch (InputMismatchException e) {
                System.err.println("Only enter the integer number > 0 ");
                sc.next();
            }
        }
    }

    public static Date getDateFromInput(String name) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(String.format("Enter %s: ", name));
            String dateString = sc.nextLine();
            if (!dateString.isEmpty()) {
                try {
                    Date date = dateFormat.parse(dateString);
                    return date;
                } catch (ParseException e) {
                    System.err.println(String.format("Invalid date format. Please enter again (%s).", DATE_FORMAT));
                }
            }
        }
    }

    public static String getStringFromInput(String name) {
        Scanner sc = new Scanner(System.in);
        String s = null;

        while (s == null || s.isEmpty() || containsNumber(s)) {
            System.out.print(String.format("Enter %s: ", name));
            s = sc.nextLine();
            if (containsNumber(s)) {
                System.out.println("Invalid input. Please enter a valid string without numbers.");
            }
        }
        return s;
    }

    private static boolean containsNumber(String input) {
        return input.matches(".*\\d.*");
    }
    //validate fruit quantity
    public static void validateOrderQuantity(Fruit fruit, int quantity) {
        while(quantity > fruit.getQuantity()){
            System.out.printf("The quantity must less than %d\n", fruit.getQuantity());
            int newQuantity = getIntFromInput("quantity");
            quantity = newQuantity;
            if(quantity <= fruit.getQuantity()){
                //update quantity after order
                fruit.setQuantity(fruit.getQuantity() - quantity);
                break;
            }
        }
    }
}
