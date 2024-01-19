package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {

    protected String title;
    protected List<String> services = new ArrayList<>();

    public Menu(String title, List<String> services) {
        this.title = title;
        this.services = services;
    }

    public void display() {
        System.out.println(title);
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i + 1) + ". " + services.get(i));
        }
    }

    public int getChoice() {
        int choice = Inputter.getIntFromInput("selection");
        return choice;
    }

    public abstract void execute(int choice);

    public void run() {
        int choice;
        do {
            display();
            choice = getChoice();
            execute(choice);
        } while (choice != services.size());
    }
}
