package model;

import java.util.Objects;

public class Fruit {
    private int id;
    private String  name;
    private double price;
    private int quantity;
    private String origin;

    public Fruit() {
    }
    public Fruit(int id, String name, double price, int quantity, String origin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public double getAmount(){
        return this.getQuantity() * this.getPrice();
    }

    @Override
    public String toString() {
        return String.format("|%-10s | %-10s | %-10s | %-10s |", this.getName(), this.getQuantity(), this.getPrice() + "$", this.getAmount() + "$");
    }
    public String toFileString() {
        return id + ";" + name + ";" + price + ";" + quantity + ";" + origin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        // Case that o reference to itself
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Fruit fruit = (Fruit) o;

        return this.getName().equals(fruit.getName());
    }
}
