package snacks_machine.domain;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {

    private static int countSnacks = 0;

    private int idSnack;
    private String name;
    private double price;

    public Snack() {
        this.idSnack = ++Snack.countSnacks;
    }

    public Snack(String name, double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public Snack(int idSnack, String name, double price) {
        this.idSnack = idSnack;
        this.name = name;
        this.price = price;
    }
    

    public static int getCountSnacks() {
        return countSnacks;
    }

    public int getIdSnack() {
        return idSnack;
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

    @Override
    public String toString() {
        return "Snack{" + "idSnack=" + idSnack + ", name=" + name + ", price=" + price + '}';
    }

    public String writeSnack() {
        return idSnack + "," + name + "," + price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.idSnack;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Snack other = (Snack) obj;
        if (this.idSnack != other.idSnack) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

}
