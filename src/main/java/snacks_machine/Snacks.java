package snacks_machine;

import java.util.ArrayList;
import java.util.List;

public class Snacks {

    private static final List<Snack> snacks;

    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Refresco", 50));
        snacks.add(new Snack("Sandwich", 120));

    }

    public static void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public static void showSnacks() {
        var inventorySnacks = "";
        for (var snack : snacks) {
            inventorySnacks += snack.toString() + "\n";
        }
        System.out.println("---- Snacks en el inventario ----");
        System.out.println(inventorySnacks);
    }

    public static List<Snack> getSnacks() {
        return snacks;
    }
}
